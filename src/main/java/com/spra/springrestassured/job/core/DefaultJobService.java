package com.spra.springrestassured.job.core;

import common.core.PagedContent;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
class DefaultJobService implements JobService{
    private final JobRepository jobRepository;
    private final JobDataMapper jobDataMapper;

    public DefaultJobService(JobRepository jobRepository, JobDataMapper jobDataMapper) {
        this.jobRepository = jobRepository;
        this.jobDataMapper = jobDataMapper;
    }

    @Override
    @Transactional
    public JobResponse addNewJob(JobRequest jobRequest) {
        var job = jobDataMapper.mapJobRequestToJob(jobRequest);
        var persistedJob = jobRepository.save(job);
        return jobDataMapper.mapJobToJobResponse(persistedJob);
    }

    @Override
    public JobResponse findJobById(Long jobId) {
        var job  = jobRepository.findByIdOrThrow(jobId);
        return jobDataMapper.mapJobToJobResponse(job);
    }

    @Override
    public void updateJob(Long jobId, JobRequest updateJobRequest) {
        var jobToUpdate = jobRepository.findByIdOrThrow(jobId);
        jobToUpdate.updateJob(updateJobRequest.jobTitle(), updateJobRequest.description(),
                updateJobRequest.jobType(), updateJobRequest.datePosted(),
                updateJobRequest.jobLink());
        jobRepository.save(jobToUpdate);
    }

    @Override
    public void deleteJob(Long jobId) {
        var jobToDelete = jobRepository.findByIdOrThrow(jobId);
        jobRepository.delete(jobToDelete);
    }

    @Override
    public PagedContent<JobResponse> findAllJobs(Integer pageNo, Integer pageSize) {
        var pageNumber = pageNo > 0 ? pageNo - 1 : 0;
        var pageable = PageRequest.of(pageNumber, pageSize);
        var page = jobRepository.findAll(pageable).map(jobDataMapper::mapJobToJobResponse);
        return new PagedContent<>(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}
