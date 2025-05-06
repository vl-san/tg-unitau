package com.unitau.tgvinicius.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unitau.tgvinicius.dto.response.ContributorResponseDto;
import com.unitau.tgvinicius.entities.Contributor;
import com.unitau.tgvinicius.services.ContributorService;

@RestController
@RequestMapping(value = "/contributors")
public class ContributorResource {
	@Autowired
	private ContributorService contributorService;

	@GetMapping
	public ResponseEntity<List<ContributorResponseDto>> findAll() {
	    List<ContributorResponseDto> list = contributorService.findAll();
	    return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ContributorResponseDto> findById(@PathVariable String id) {
	    ContributorResponseDto obj = contributorService.findById(id);
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
}
