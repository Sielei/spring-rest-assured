package com.spra.springrestassured.job.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public record JobResponse(@Schema(type = "long", example = "10001") Long id,
                          @Schema(type = "string", example = "Senior Backend Engineer (Java)") String jobTitle,
                          @Schema(type = "string", example = "Brief description of the Job.") String description,
                          JobType jobType,
                          Date datePosted,
                          @Schema(type = "string", example = "https://mycompany.breezy.hr/p/22b17727d5f4-senior-backend-engineer-java") String jobLink) {
}
