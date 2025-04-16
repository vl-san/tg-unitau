package com.unitau.tgvinicius.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.client.GithubClient;
import com.unitau.tgvinicius.converter.BranchConverter;
import com.unitau.tgvinicius.converter.CommitConverter;
import com.unitau.tgvinicius.converter.ContributorConverter;
import com.unitau.tgvinicius.converter.IssueConverter;
import com.unitau.tgvinicius.converter.RepositoryConverter;
import com.unitau.tgvinicius.dto.request.BranchRequestDto;
import com.unitau.tgvinicius.dto.request.CommitRequestDto;
import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.dto.request.IssueRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryDataRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.repositories.BranchRepository;
import com.unitau.tgvinicius.repositories.CommitRepository;
import com.unitau.tgvinicius.repositories.ContributorRepository;
import com.unitau.tgvinicius.repositories.IssueRepository;
import com.unitau.tgvinicius.repositories.RepositoryRepository;

import jakarta.transaction.Transactional;

@Service
public class GitHubDataService {

	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private CommitRepository commitRepository;
	@Autowired
	private ContributorRepository contributorRepository;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private RepositoryRepository repositoryRepository;
	@Autowired
	private GithubClient githubClient;

	public RepositoryDataRequestDto fetchGitHubData(String token, String username, String repository, Integer page,
			Integer perPage) {
		String authToken = "Bearer " + token;

		RepositoryRequestDto repositoryDto = githubClient.listRepository(authToken, username, repository);

		List<ContributorRequestDto> contributorDto = fetchAllPages(
				(p) -> githubClient.listContributors(authToken, username, repository, p, perPage));
		List<CommitRequestDto> commitDto = fetchAllPages(
				(p) -> githubClient.listCommits(authToken, username, repository, p, perPage));
		List<BranchRequestDto> branchDto = fetchAllPages(
				(p) -> githubClient.listBranches(authToken, username, repository, p, perPage));
		List<IssueRequestDto> issueDto = fetchAllPages(
				(p) -> githubClient.listIssues(authToken, username, repository, p, perPage));

		return new RepositoryDataRequestDto(repositoryDto, contributorDto, commitDto, branchDto, issueDto);
	}

	private <T> List<T> fetchAllPages(Function<Integer, List<T>> fetchFunction) {
		List<T> allItems = new ArrayList<>();
		int currentPage = 1;
		int emptyPageCount = 0;
		final int maxEmptyPages = 3; // segurança extra para não entrar em loop infinito

		while (true) {
			List<T> currentPageItems = fetchFunction.apply(currentPage);

			if (currentPageItems == null || currentPageItems.isEmpty()) {
				emptyPageCount++;
				if (emptyPageCount >= maxEmptyPages) {
					break;
				}
			} else {
				allItems.addAll(currentPageItems);
				emptyPageCount = 0; // reset se teve dados
			}

			currentPage++;
		}

		return allItems;
	}

	@Transactional
	public void saveRepositoryData(RepositoryDataRequestDto data) {
		// Convertendo RepositoryDTO para a entidade Repository
		Repository repo = RepositoryConverter.dtoToEntity(data.repository());

		// Convertendo e associando Contributors ao Repository
		List<Contributor> contributors = data.contributors().stream().map(dto -> {
			Contributor contributor = ContributorConverter.dtoToEntity(dto);
			contributor.setRepository(repo);
			return contributor;
		}).collect(Collectors.toList());

		// Convertendo e associando Commits ao Repository e Contributor diretamente via
		// DTO
		List<Commit> commits = data.commits().stream().map(dto -> {
			Commit commit = CommitConverter.dtoToEntity(dto);
			commit.setRepository(repo);

			// Associar Contributor diretamente pelo ID capturado no DTO
			Contributor relatedContributor = contributors.stream().filter(c -> c.getId().equals(dto.contributorId()))
					.findFirst().orElse(null);
			commit.setContributor(relatedContributor);

			return commit;
		}).collect(Collectors.toList());

		// Convertendo e associando Branches ao Commit
		List<Branch> branches = data.branches().stream().map(dto -> {
			Branch branch = BranchConverter.dtoToEntity(dto);

			// Associar ao Commit diretamente pelo sha capturado no DTO
			Commit relatedCommit = null;
			if (dto.commit() != null) {
				relatedCommit = commits.stream().filter(c -> c.getSha().equals(dto.commit().getSha())).findFirst()
						.orElse(null);
			}
			branch.setCommit(relatedCommit);
			return branch;
		}).collect(Collectors.toList());

		// Convertendo Issues e associando ao Repository
		List<Issue> issues = data.issues().stream().map(dto -> {
			Issue issue = IssueConverter.dtoToEntity(dto);
			issue.setRepository(repo);

			// Associar ao Contributor diretamente pelo ID capturado no DTO (se aplicável)
			Contributor relatedContributor = contributors.stream().filter(c -> c.getId().equals(dto.contributorId()))
					.findFirst().orElse(null);
			issue.setContributor(relatedContributor);

			return issue;
		}).collect(Collectors.toList());

		// Persistindo entidades no banco
		repositoryRepository.save(repo);
		contributorRepository.saveAll(contributors);
		commitRepository.saveAll(commits);
		branchRepository.saveAll(branches);
		issueRepository.saveAll(issues);
	}

}
