package com.unitau.tgvinicius.converter;

import com.unitau.tgvinicius.dto.response.BranchResponseDto;
import com.unitau.tgvinicius.dto.response.CommitFullResponseDto;
import com.unitau.tgvinicius.entities.Commit;

public class CommitFullConverter {

    public static CommitFullResponseDto fromEntity(Commit commit) {
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
