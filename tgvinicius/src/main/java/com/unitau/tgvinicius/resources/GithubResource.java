package com.unitau.tgvinicius.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitau.tgvinicius.dto.request.RepositoryDataRequestDto;
import com.unitau.tgvinicius.services.GitHubDataService;

@RestController
@RequestMapping("/github")
public class GithubResource {

	private static final String Token = "token";

//	String username = "prefeiturasp";
//	String repository = "SME-SIGPAE-API";

	String username = "vl-san";
	String repository = "tg-unitau";

	int page = 1;
	int perPage = 100;

	@Autowired
	private GitHubDataService gitHubDataService;

	@GetMapping("/github")
	public ResponseEntity<RepositoryDataRequestDto> getData(@RequestHeader("Token") String token) {
		RepositoryDataRequestDto data = gitHubDataService.fetchGitHubData(token, username, repository, page, perPage);
		gitHubDataService.saveRepositoryData(data);

		return ResponseEntity.ok(data);
	}

}