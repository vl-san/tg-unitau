package com.unitau.tgvinicius.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitau.tgvinicius.dto.request.RepositoryDataRequestDto;
import com.unitau.tgvinicius.dto.response.RepositoryFullResponseDto;
import com.unitau.tgvinicius.services.GitHubDataService;

@RestController
@RequestMapping("/github")
public class GithubResource {

	private static final String Token = "token";

//	String owner = "prefeiturasp";
//	String repository = "SME-SIGPAE-API";

	String owner = "vl-san";
	String repository = "tg-unitau";
	String state = "all";

	int page = 1;
	int perPage = 100;

	@Autowired
	private GitHubDataService gitHubDataService;

	@GetMapping("/github")
	public ResponseEntity<RepositoryDataRequestDto> getData(@RequestHeader("Token") String token) {
		RepositoryDataRequestDto data = gitHubDataService.fetchGitHubData(token, owner, repository, state, page, perPage);
		gitHubDataService.saveRepositoryData(data);

		return ResponseEntity.ok(data);
	}
	
    @GetMapping
    public ResponseEntity<List<RepositoryFullResponseDto>> findAll() {
        List<RepositoryFullResponseDto> list = gitHubDataService.findAll();
        return ResponseEntity.ok().body(list);
    }
    
//    @DeleteMapping("/limpar")
//    public ResponseEntity<Void> limparBanco() {
//        gitHubDataService.deleteAll();
//        return ResponseEntity.ok().build();
//    }


}