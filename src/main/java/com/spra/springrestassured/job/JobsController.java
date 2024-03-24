package com.spra.springrestassured.job;

import com.spra.springrestassured.job.core.JobRequest;
import com.spra.springrestassured.job.core.JobResponse;
import com.spra.springrestassured.job.core.JobService;
import com.spra.springrestassured.common.core.PagedContent;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Operation(summary = "Add a new Job post")
    @PostMapping
    ResponseEntity<JobResponse> addNewJob(@Valid @RequestBody JobRequest jobRequest){
        var newJob = jobService.addNewJob(jobRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newJob.id())
                .toUri();
        return ResponseEntity.created(location).body(newJob);
    }

    @Operation(summary = "Find all available Job posts")
    @GetMapping
    PagedContent<JobResponse> findAllJobs(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "size", defaultValue = "10") Integer pageSize){
        return jobService.findAllJobs(pageNo, pageSize);
    }

    @Operation(summary = "Find Job post by id")
    @GetMapping("/{jobId}")
    JobResponse findById(@PathVariable("jobId") Long jobId){
        return jobService.findJobById(jobId);
    }

    @Operation(summary = "Update a Job Post")
    @PutMapping("/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateJob(@PathVariable("jobId") Long jobId, @Valid @RequestBody JobRequest updateJobRequest){
        jobService.updateJob(jobId, updateJobRequest);
    }

    @Operation(summary = "Delete a Job post")
    @DeleteMapping("/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteJob(@PathVariable("jobId") Long jobId){
        jobService.deleteJob(jobId);
    }

}
