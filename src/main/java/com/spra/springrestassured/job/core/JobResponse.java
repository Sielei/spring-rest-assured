package com.spra.springrestassured.job.core;

import java.util.Date;

public record JobResponse(Long id, String jobTitle, String description, JobType jobType,
                          Date datePosted, String jobLink) {
}
