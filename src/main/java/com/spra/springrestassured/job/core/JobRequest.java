package com.spra.springrestassured.job.core;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record JobRequest(@NotBlank(message = "Job title is required") String jobTitle,
                         @NotBlank(message = "Description is required") String description,
                         @NotNull(message = "Job type is required") JobType jobType,
                         Date datePosted,
                         @Pattern(regexp = "/[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)/",
                         message = "Invalid Job link") String jobLink) {
}
