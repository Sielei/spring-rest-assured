package com.spra.springrestassured.job.core;

import org.springframework.data.jpa.repository.JpaRepository;

interface JobRepository extends JpaRepository<Job, Long> {

    default Job findByIdOrThrow(Long jobId){
        return findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job with id: " + jobId +
                        " not found!"));
    }
}