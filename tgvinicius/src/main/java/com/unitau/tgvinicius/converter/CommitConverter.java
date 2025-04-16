package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.request.CommitRequestDto;
import com.unitau.tgvinicius.dto.response.CommitResponseDto;
import com.unitau.tgvinicius.entities.Branch;
import com.unitau.tgvinicius.entities.Commit;

public class CommitConverter {
	public static Commit dtoToEntity(CommitRequestDto dto) {
		Commit entity = new Commit();

		entity.setSha(dto.sha());
		entity.setAuthorLogin(dto.authorLogin());
		entity.setCreationAt(dto.creationAt());

		if (dto.branches() != null) {
			dto.branches().forEach(branchDTO -> {
				Branch branch = new Branch();
				branch.setName(branchDTO.name());
				entity.addBranch(branch);
				branch.setCommit(entity);
			});
		}

		return entity;
	}
	
	    public static CommitResponseDto fromEntity(Commit commit) {
	        return new CommitResponseDto(
	            commit.getSha(),
	            commit.getAuthorLogin(),
	            commit.getCreationAt(),
	            commit.getBranches().stream()
	            .map(BranchConverter::fromEntity)
	            .toList()
        );
	    }
	}

