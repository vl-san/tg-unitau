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
import com.unitau.tgvinicius.dto.RepositoryDTO;
import com.unitau.tgvinicius.entities.Repository;
import com.unitau.tgvinicius.services.RepositoryService;
import com.unitau.tgvinicius.util.RepositoryConverter;

@RestController
@RequestMapping(value = "/repositories")
public class RepositoryResource {

	String username = "prefeiturasp";

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private GithubClient githubClient;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@GetMapping
	public ResponseEntity<List<Repository>> findAll() {
		List<Repository> list = repositoryService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Repository> findById(@PathVariable String id) {
		Repository obj = repositoryService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Repository> insert(@RequestBody Repository obj) {
		obj = repositoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		repositoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Repository> update(@PathVariable String id, @RequestBody Repository obj) {
		obj = repositoryService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

//	@GetMapping("/repos")
//	public ResponseEntity<List<RepositoryDTO>> listRepos(@RequestHeader("token") String token) {
//		var repos = githubClient.listRepos("Bearer " + token, null, username);
//		return ResponseEntity.ok(repos);
//	}
	
	@GetMapping("/repos")
	public ResponseEntity<List<RepositoryDTO>> listRepos(@RequestHeader("token") String token) {
	    List<RepositoryDTO> reposDtos = githubClient.listRepos("Bearer " + token, null, username);
	    List<Repository> repos = reposDtos.stream()
	                                      .map(RepositoryConverter::dtoToEntity)
	                                      .collect(Collectors.toList());
	    repositoryService.saveAll(repos); // Salvar os dados no banco
	    return ResponseEntity.ok(reposDtos);
	}

}
