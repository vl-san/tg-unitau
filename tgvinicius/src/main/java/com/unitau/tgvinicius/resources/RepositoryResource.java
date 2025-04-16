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

import com.unitau.tgvinicius.dto.request.RepositoryRequestDto;
import com.unitau.tgvinicius.dto.response.RepositoryResponseDto;
import com.unitau.tgvinicius.services.RepositoryService;

@RestController
@RequestMapping(value = "/repositories")
public class RepositoryResource {

	@Autowired
	private RepositoryService repositoryService;

	@GetMapping
	public ResponseEntity<List<RepositoryResponseDto>> findAll() {
	    List<RepositoryResponseDto> list = repositoryService.findAll();
	    return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RepositoryResponseDto> findById(@PathVariable String id) {
	    RepositoryResponseDto response = repositoryService.findById(id);
	    return ResponseEntity.ok().body(response);
	}


	@PostMapping
	public ResponseEntity<RepositoryResponseDto> insert(@RequestBody RepositoryRequestDto dto) {
	    RepositoryResponseDto response = repositoryService.insert(dto);
	    URI uri = ServletUriComponentsBuilder
	        .fromCurrentRequest()
	        .path("/{id}")
	        .buildAndExpand(response.id())
	        .toUri();
	    return ResponseEntity.created(uri).body(response);
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		repositoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<RepositoryResponseDto> update(@PathVariable String id, @RequestBody RepositoryRequestDto dto) {
	    RepositoryResponseDto updatedRepository = repositoryService.update(id, dto);
	    return ResponseEntity.ok().body(updatedRepository);
	}

}
