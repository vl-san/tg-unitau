package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.BranchResponseDto;
import com.unitau.tgvinicius.dto.response.CommitFullResponseDto;
import com.unitau.tgvinicius.dto.response.CommitResponseDto;
import com.unitau.tgvinicius.entities.Commit;

public class CommitConverter {

    public static CommitResponseDto fromEntity(Commit commit) {
        return new CommitResponseDto(
            commit.getSha(),
            commit.getAuthorLogin(),
            commit.getCreatedAt()
        );
    }
    
    public static CommitFullResponseDto fromEntityWithBranch(Commit commit) {
        return new CommitFullResponseDto(
            commit.getSha(),
            commit.getAuthorLogin(),
            commit.getCreatedAt(),
            commit.getBranches().stream()
                  .map(branch -> new BranchResponseDto(branch.getName()))
                  .toList()
        );
    }
}

