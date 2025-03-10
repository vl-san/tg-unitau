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

import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.services.CommitService;

@RestController
@RequestMapping(value = "/commits")
public class CommitResource {

	@Autowired
	private CommitService commitService;

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
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
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
}
