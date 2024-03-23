package com.spra.springrestassured.job;

import com.spra.springrestassured.job.core.JobRequest;
import com.spra.springrestassured.job.core.JobResponse;
import com.spra.springrestassured.job.core.JobService;
import common.core.PagedContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobsController {
    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    ResponseEntity<JobResponse> addNewJob(@RequestBody JobRequest jobRequest){
        var newJob = jobService.addNewJob(jobRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newJob.id())
                .toUri();
        return ResponseEntity.created(location).body(newJob);
    }

    @GetMapping
    PagedContent<JobResponse> findAllJobs(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "size", defaultValue = "10") Integer pageSize){
        return jobService.findAllJobs(pageNo, pageSize);
    }

    @GetMapping("/{jobId}")
    JobResponse findById(@PathVariable("jobId") Long jobId){
        return jobService.findJobById(jobId);
    }

    @PutMapping("/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateJob(@PathVariable("jobId") Long jobId, @RequestBody JobRequest updateJobRequest){
        jobService.updateJob(jobId, updateJobRequest);
    }

    @DeleteMapping("/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteJob(@PathVariable("jobId") Long jobId){
        jobService.deleteJob(jobId);
    }

}
