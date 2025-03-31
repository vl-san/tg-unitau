package com.unitau.tgvinicius.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitau.tgvinicius.client.GithubClient;
import com.unitau.tgvinicius.dto.IssueDTO;
import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.services.IssueService;
import com.unitau.tgvinicius.util.IssueConverter;

@RestController
@RequestMapping(value = "/issues")
public class IssueResource {

	private static final String Token = "token";

	String username = "prefeiturasp";
	String repository = "SME-SIGPAE-API";

	@Autowired
	private IssueService issueService;
	@Autowired
	private GithubClient githubClient;

	@GetMapping
	public ResponseEntity<List<Issue>> findAll() {
		List<Issue> list = issueService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Issue> FindById(@PathVariable String id) {
		Issue obj = issueService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/issues")
	public ResponseEntity<List<IssueDTO>> listIssue(@RequestHeader(Token) String token) {
		List<IssueDTO> issueDtos = githubClient.listIssues("Bearer " + token, null, username, repository);
		List<Issue> issue = issueDtos.stream()
				.map(IssueConverter::dtoToEntity)
				.collect(Collectors.toList());
		issueService.saveAll(issue);
		return ResponseEntity.ok(issueDtos);
	}
}
