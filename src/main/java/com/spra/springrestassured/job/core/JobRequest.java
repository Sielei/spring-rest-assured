package com.spra.springrestassured.job.core;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record JobRequest(@Schema(type = "string", example = "Senior Backend Engineer (Java)") @NotBlank(message = "Job title is required") String jobTitle,
                         @Schema(type = "string", example = "Brief description of the Job.") @NotBlank(message = "Description is required") String description,
                         @NotNull(message = "Job type is required") JobType jobType,
                         Date datePosted,
                         @Schema(type = "string", example = "https://mycompany.breezy.hr/p/22b17727d5f4-senior-backend-engineer-java") @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)",
                         message = "Invalid Job link") String jobLink) {
}
