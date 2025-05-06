package com.unitau.tgvinicius.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.unitau.tgvinicius.converter.RepositoryFullConverter;
import com.unitau.tgvinicius.dto.request.BranchRequestDto;
import com.unitau.tgvinicius.dto.request.CommitRequestDto;
import com.unitau.tgvinicius.dto.request.ContributorRequestDto;
import com.unitau.tgvinicius.dto.request.IssueRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryDataRequestDto;
import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.dto.response.RepositoryFullResponseDto;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.entities.RepositoryContributor;
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
		Repository repo = saveRepository(data);

		List<Contributor> contributors = saveContributors(data, repo);

		List<Commit> commits = buildCommits(data, repo, contributors);
		commitRepository.saveAll(commits);

		List<Branch> branches = buildBranches(data, commits);
		branchRepository.saveAll(branches);

		List<Issue> issues = buildIssues(data, repo, contributors);
		issueRepository.saveAll(issues);
	}

	private Repository saveRepository(RepositoryDataRequestDto data) {
		Repository repo = RepositoryConverter.dtoToEntity(data.repository());
		return repositoryRepository.save(repo);
	}

	private List<Contributor> saveContributors(RepositoryDataRequestDto data, Repository repo) {
		List<Contributor> contributors = new ArrayList<>();
		for (ContributorRequestDto dto : data.contributors()) {
			Contributor contributor = ContributorConverter.dtoToEntity(dto);
			contributorRepository.save(contributor);

			RepositoryContributor rc = new RepositoryContributor();
			rc.setRepository(repo);
			rc.setContributor(contributor);
			rc.setContributions(dto.contributions());
			repositoryContributorRepository.save(rc);

			contributors.add(contributor);
		}
		return contributors;
	}

	private List<Commit> buildCommits(RepositoryDataRequestDto data, Repository repo, List<Contributor> contributors) {
		Map<String, Contributor> contributorMap = contributors.stream()
				.collect(Collectors.toMap(c -> c.getId(), Function.identity()));

		return data.commits().stream().map(dto -> {
			Commit commit = CommitConverter.dtoToEntity(dto);
			commit.setRepository(repo);
			commit.setContributor(contributorMap.get(dto.contributorId()));
			return commit;
		}).toList();
	}

	private List<Branch> buildBranches(RepositoryDataRequestDto data, List<Commit> commits) {
		Map<String, Commit> commitMap = commits.stream()
				.collect(Collectors.toMap(c -> c.getSha(), Function.identity()));

		return data.branches().stream().map(dto -> {
			Branch branch = BranchConverter.dtoToEntity(dto);
			branch.setCommit(commitMap.get(dto.commitSha()));
			return branch;
		}).toList();
	}

	private List<Issue> buildIssues(RepositoryDataRequestDto data, Repository repo, List<Contributor> contributors) {
		Map<String, Contributor> contributorMap = contributors.stream()
				.collect(Collectors.toMap(c -> c.getId(), Function.identity()));

		return data.issues().stream().map(dto -> {
			Issue issue = IssueConverter.dtoToEntity(dto);
			issue.setRepository(repo);
			issue.setContributor(contributorMap.get(dto.contributorId()));
			return issue;
		}).toList();
	}
	
    public List<RepositoryFullResponseDto> findAll() {
        return repositoryRepository.findAll().stream()
                .map(RepositoryFullConverter::fromEntity)
                .toList();
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
