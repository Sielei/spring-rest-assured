package com.spra.springrestassured.job.core;


import common.core.PagedContent;

public interface JobService {
    JobResponse addNewJob(JobRequest jobRequest);

    JobResponse findJobById(Long jobId);

    void updateJob(Long jobId, JobRequest updateJobRequest);

    void deleteJob(Long jobId);

    PagedContent<JobResponse> findAllJobs(Integer pageNo, Integer pageSize);
}
