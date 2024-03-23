package com.spra.springrestassured.job.core;

import org.springframework.stereotype.Component;

@Component
class JobDataMapper {
    public JobResponse mapJobToJobResponse(Job job) {
        return new JobResponse(job.getId(), job.getJobTitle(), job.getDescription(),
                job.getJobType(), job.getDatePosted(), job.getJobLink());
    }

    public Job mapJobRequestToJob(JobRequest jobRequest) {
        return Job.builder()
                .jobTitle(jobRequest.jobTitle())
                .description(jobRequest.description())
                .jobType(jobRequest.jobType())
                .datePosted(jobRequest.datePosted())
                .jobLink(jobRequest.jobLink())
                .build();
    }
}
