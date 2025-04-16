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

import com.unitau.tgvinicius.entities.Issue;
import com.unitau.tgvinicius.services.IssueService;

@RestController
@RequestMapping(value = "/issues")
public class IssueResource {
	@Autowired
	private IssueService issueService;

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

	@PostMapping
	public ResponseEntity<Issue> insert(@RequestBody Issue obj) {
		obj = issueService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		issueService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Issue> update(@PathVariable String id, @RequestBody Issue obj) {
		obj = issueService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
