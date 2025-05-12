package com.unitau.tgvinicius.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitau.tgvinicius.converter.CommitConverter;
import com.unitau.tgvinicius.dto.response.CommitResponseDto;
import com.unitau.tgvinicius.entities.Commit;
import com.unitau.tgvinicius.services.CommitService;

@RestController
@RequestMapping(value = "/commits")
public class CommitResource {
	@Autowired
	private CommitService commitService;

	 @GetMapping
	    public ResponseEntity<List<CommitResponseDto>> findAll() {
	        List<CommitResponseDto> list = commitService.findAll();
	        return ResponseEntity.ok().body(list);
	    }

	 @GetMapping(value = "/{id}")
	 public ResponseEntity<CommitResponseDto> findById(@PathVariable String id) {
	     Commit obj = commitService.findById(id);
	     CommitResponseDto dto = CommitConverter.fromEntity(obj);
	     return ResponseEntity.ok().body(dto);
	 }


//	 @PostMapping
//	 public ResponseEntity<CommitResponseDto> insert(@RequestBody CommitRequestDto dto) {
//	     Commit entity = CommitConverter.dtoToEntity(dto);
//	     Commit savedEntity = commitService.insert(entity);
//	     CommitResponseDto responseDto = CommitConverter.fromEntity(savedEntity);
//	     
//	     URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//	             .path("/{id}")
//	             .buildAndExpand(savedEntity.getSha())
//	             .toUri();
//	     return ResponseEntity.created(uri).body(responseDto);
//	 }

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
