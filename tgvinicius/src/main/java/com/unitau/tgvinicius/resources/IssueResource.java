package com.unitau.tgvinicius.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
