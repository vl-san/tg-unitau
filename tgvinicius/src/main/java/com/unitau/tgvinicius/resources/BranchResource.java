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

import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.services.BranchService;

@RestController
@RequestMapping(value = "/branches")
public class BranchResource {

	@Autowired
	private BranchService branchService;

	@GetMapping
	public ResponseEntity<List<Branch>> findAll() {
		List<Branch> list = branchService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Branch> FindById(@PathVariable String id) {
		Branch obj = branchService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Branch> insert(@RequestBody Branch obj) {
		obj = branchService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		branchService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Branch> update(@PathVariable String id, @RequestBody Branch obj) {
		obj = branchService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
