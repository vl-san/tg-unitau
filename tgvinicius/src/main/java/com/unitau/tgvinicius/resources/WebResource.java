package com.unitau.tgvinicius.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.services.BranchService;
import com.unitau.tgvinicius.services.CommitService;
import com.unitau.tgvinicius.services.ContributorService;
import com.unitau.tgvinicius.services.GitHubDataService;
import com.unitau.tgvinicius.services.IssueService;
import com.unitau.tgvinicius.services.RepositoryContributorService;
import com.unitau.tgvinicius.services.RepositoryService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/view")
public class WebResource {

	@Autowired
	private BranchService branchService;
	@Autowired
	private CommitService commitService;
	@Autowired
	private ContributorService contributorService;
	@Autowired
	private IssueService issueService;
	@Autowired
	RepositoryContributorService repositoryContributorService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private GitHubDataService gitHubDataService;

	@GetMapping
	public String homePage(Model model) {
		model.addAttribute("repositories", repositoryService.findAll());
		return "dashboard";
	}

	@Transactional
	@PostMapping("/import")
	public String postCompleteRepository(@RequestParam String owner, @RequestParam String repository,
			@RequestParam String token, Model model) {
		var dados = gitHubDataService.fetchGitHubData(token, owner, repository, "all", 1, 100);
		gitHubDataService.saveRepositoryData(dados);

		model.addAttribute("mensagem", "Repositório importado com sucesso!");
		return "redirect:/view";
	}

	@GetMapping("/repository-data")
	public String viewTable(@RequestParam String repositoryId, @RequestParam String entity) {

		return "redirect:/view/" + entity + "/" + repositoryId;
	}

	@GetMapping("/branches/{repositoryId}")
	public String showBranches(@PathVariable String repositoryId, Model model) {
		List<Branch> branches = branchService.findByRepositoryId(repositoryId);
		model.addAttribute("branches", branches);
		model.addAttribute("repositoryId", repositoryId);
		return "branches";
	}

	@GetMapping("/commits/{repositoryId}")
	public String showCommits(@PathVariable String repositoryId, Model model) {
		var commits = commitService.findByRepositoryId(repositoryId);
		model.addAttribute("commits", commits);
		model.addAttribute("repositoryId", repositoryId);
		return "commits";
	}

//	@GetMapping("/contributors/{repositoryId}")
//	public String showContributors(@PathVariable String repositoryId, Model model) {
//		var contributors = contributorService.findByRepositoryId(repositoryId);
//		var RepositoryContributor = repositoryContributorService.findByRepositoryId(repositoryId);
//		model.addAttribute("repositoryContributor", RepositoryContributor);
//		model.addAttribute("contributors", contributors);
//		model.addAttribute("repositoryId", repositoryId);
//		return "contributors";
//	}
	
	@GetMapping("/contributors/{repositoryId}")
	public String showContributors(@PathVariable String repositoryId, Model model) {
	    var repositoryContributors = repositoryContributorService.findByRepositoryId(repositoryId);
	    model.addAttribute("repositoryContributors", repositoryContributors);
	    model.addAttribute("repositoryId", repositoryId);
	    return "contributors";
	}

	@GetMapping("/issues/{repositoryId}")
	public String showIssues(@PathVariable String repositoryId, Model model) {
		var issues = issueService.findByRepositoryId(repositoryId);
		model.addAttribute("issues", issues);
		model.addAttribute("repositoryId", repositoryId);
		return "issues";
	}

	@GetMapping("/repository/{repositoryId}")
	public String showRepository(@PathVariable String repositoryId, Model model) {
		var repository = repositoryService.findById(repositoryId);
		model.addAttribute("repository", repository);
		model.addAttribute("repositoryId", repositoryId);
		return "repository";
	}

}
