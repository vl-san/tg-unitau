package com.unitau.tgvinicius.dto.response;

import java.time.Instant;
import java.util.List;

public record CommitFullResponseDto(
	    String sha,
	    Instant creationAt,
	    List<BranchResponseDto> branches
	) {}
