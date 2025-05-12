package com.unitau.tgvinicius.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitau.tgvinicius.client.GithubClient;
import com.unitau.tgvinicius.dto.request.BranchRequestDto;
import com.unitau.tgvinicius.dto.request.CommitRequestDto;
import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.dto.request.IssueRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryDataRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.embeddables.RepositoryContributorId;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.entities.RepositoryContributor;
import com.unitau.tgvinicius.mappers.BranchMapper;
import com.unitau.tgvinicius.mappers.CommitMapper;
import com.unitau.tgvinicius.mappers.ContributorMapper;
import com.unitau.tgvinicius.mappers.IssueMapper;
import com.unitau.tgvinicius.mappers.RepositoryMapper;
import com.unitau.tgvinicius.repositories.BranchRepository;
import com.unitau.tgvinicius.repositories.CommitRepository;
import com.unitau.tgvinicius.repositories.ContributorRepository;
import com.unitau.tgvinicius.repositories.IssueRepository;
import com.unitau.tgvinicius.repositories.RepositoryContributorRepository;
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
	RepositoryContributorRepository repositoryContributorRepository;
	@Autowired
	private RepositoryRepository repositoryRepository;

	@Autowired
	private BranchMapper branchMapper;
	@Autowired
	private CommitMapper commitMapper;
	@Autowired
	private ContributorMapper contributorMapper;
	@Autowired
	private IssueMapper issueMapper;
	@Autowired
	private RepositoryMapper repositoryMapper;

	@Autowired
	private GithubClient githubClient;

	public RepositoryDataRequestDto fetchGitHubData(String token, String owner, String repository, String state,
			Integer page, Integer perPage) {
		String authToken = "Bearer " + token;

		RepositoryRequestDto repositoryDto = githubClient.listRepository(authToken, owner, repository);

		List<ContributorRequestDto> contributorDto = fetchAllPages(
				(p) -> githubClient.listContributors(authToken, owner, repository, p, perPage));
		List<CommitRequestDto> commitDto = fetchAllPages(
				(p) -> githubClient.listCommits(authToken, owner, repository, p, perPage));
		List<BranchRequestDto> branchDto = fetchAllPages(
				(p) -> githubClient.listBranches(authToken, owner, repository, p, perPage));
		List<IssueRequestDto> issueDto = fetchAllPages(
				(p) -> githubClient.listIssues(authToken, owner, repository, state, p, perPage));

		return new RepositoryDataRequestDto(repositoryDto, contributorDto, commitDto, branchDto, issueDto);
	}

	private <T> List<T> fetchAllPages(Function<Integer, List<T>> fetchFunction) {
		List<T> allItems = new ArrayList<>();
		int currentPage = 1;
		int emptyPageCount = 0;
		final int maxEmptyPages = 3;

		while (true) {
			List<T> currentPageItems = fetchFunction.apply(currentPage);

			if (currentPageItems == null || currentPageItems.isEmpty()) {
				emptyPageCount++;
				if (emptyPageCount >= maxEmptyPages) {
					break;
				}
			} else {
				allItems.addAll(currentPageItems);
				emptyPageCount = 0;
			}

			currentPage++;
		}

		return allItems;
	}

	@Transactional
	public void saveRepositoryData(RepositoryDataRequestDto data) {
		Repository repo = repositoryMapper.toEntity(data.repository());
		repositoryRepository.save(repo);

		Map<String, Contributor> contributorMap = data.contributors().stream().map(contributorMapper::toEntity)
				.peek(contributor -> contributorRepository.save(contributor))
				.collect(Collectors.toMap(Contributor::getId, c -> c));

		data.contributors().forEach(dto -> {
			RepositoryContributorId id = new RepositoryContributorId(repo.getId(), dto.id());

			RepositoryContributor relation = new RepositoryContributor();
			relation.setId(id);
			relation.setRepository(repo);
			relation.setContributor(contributorMap.get(dto.id()));
			relation.setContributions(dto.contributions());

			repositoryContributorRepository.save(relation);
		});

		Map<String, Commit> commitMap = data.commits().stream().map(commitDto -> {
			Commit commit = commitMapper.toEntity(commitDto);
			commit.setRepository(repo);
			commit.setContributor(contributorMap.get(commitDto.contributorId()));
			return commitRepository.save(commit);
		}).collect(Collectors.toMap(Commit::getSha, c -> c));

		data.branches().forEach(branchDto -> {
			Branch branch = branchMapper.toEntity(branchDto);
			branch.setCommit(commitMap.get(branchDto.commitSha()));
			branchRepository.save(branch);
		});

		data.issues().forEach(issueDto -> {
			Issue issue = issueMapper.toEntity(issueDto);
			issue.setRepository(repo);
			issue.setContributor(contributorMap.get(issueDto.contributorId()));
			issueRepository.save(issue);
		});

	}

	@Transactional
	public void deleteAll() {
		repositoryContributorRepository.deleteAll();
		issueRepository.deleteAll();
		branchRepository.deleteAll();
		commitRepository.deleteAll();
		contributorRepository.deleteAll();
		repositoryRepository.deleteAll();
	}
}