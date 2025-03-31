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
import com.unitau.tgvinicius.dto.ContributorDTO;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.services.ContributorService;
import com.unitau.tgvinicius.util.ContributorConverter;

@RestController
@RequestMapping(value = "/contributors")
public class ContributorResource {
	
	private static final String Token = "token";
	
	String username = "prefeiturasp";
	String repository = "SME-SIGPAE-API";
	
	@Autowired
	private ContributorService contributorService;
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
	public ResponseEntity<List<Contributor>> findAll() {
		List<Contributor> list = contributorService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Contributor> FindById(@PathVariable String id) {
		Contributor obj = contributorService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Contributor> insert(@RequestBody Contributor obj) {
		obj = contributorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		contributorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Contributor> update(@PathVariable String id, @RequestBody Contributor obj) {
		obj = contributorService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/contributors")
	public ResponseEntity<List<ContributorDTO>> listContributor(@RequestHeader(Token) String token) {
		List<ContributorDTO> contributorDtos = githubClient.listContributors("Bearer " + token, null, username, repository);
		List<Contributor> contributor = contributorDtos.stream()
				.map(ContributorConverter::dtoToEntity)
				.collect(Collectors.toList());
		contributorService.saveAll(contributor);
		return ResponseEntity.ok(contributorDtos);
	}
}
