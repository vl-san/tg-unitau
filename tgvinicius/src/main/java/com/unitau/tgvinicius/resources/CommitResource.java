package com.unitau.tgvinicius.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unitau.tgvinicius.client.GithubClient;
import com.unitau.tgvinicius.dto.CommitDTO;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.services.CommitService;
import com.unitau.tgvinicius.util.CommitConverter;

@RestController
@RequestMapping(value = "/commits")
public class CommitResource {

	private static final String Token = "token";

	String username = "prefeiturasp";
	String repository = "SME-SIGPAE-API";

	@Autowired
	private CommitService commitService;
	@Autowired
	private GithubClient githubClient;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Commit>> findAll() {
		List<Commit> list = commitService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Commit> FindById(@PathVariable String id) {
		Commit obj = commitService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Commit> insert(@RequestBody Commit obj) {
		obj = commitService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getSha()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		commitService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Commit> update(@PathVariable String id, @RequestBody Commit obj) {
		obj = commitService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/commits")
	public ResponseEntity<List<CommitDTO>> listCommits(@RequestHeader(Token) String token) {
		List<CommitDTO> commitsDtos = githubClient.listCommits("Bearer " + token, null, username, repository);
		List<Commit> commits = commitsDtos.stream()
				.map(CommitConverter::dtoToEntity)
				.collect(Collectors.toList());
		commitService.saveAll(commits);
		return ResponseEntity.ok(commitsDtos);
	}
}
