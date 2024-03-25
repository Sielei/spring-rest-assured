package com.spra.springrestassured.job.core;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "jobs")
class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String jobTitle;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    @Temporal(TemporalType.DATE)
    private Date datePosted;
    private String jobLink;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public Job() {
    }

    private Job(Builder builder) {
        id = builder.id;
        jobTitle = builder.jobTitle;
        description = builder.description;
        jobType = builder.jobType;
        datePosted = builder.datePosted;
        jobLink = builder.jobLink;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public String getJobLink() {
        return jobLink;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void updateJob(String jobTitle, String description, JobType jobType, Date datePosted, String jobLink) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.jobType = jobType;
        this.datePosted = datePosted;
        this.jobLink = jobLink;
    }


    public static final class Builder {
        private Long id;
        private String jobTitle;
        private String description;
        private JobType jobType;
        private Date datePosted;
        private String jobLink;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder jobTitle(String val) {
            jobTitle = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder jobType(JobType val) {
            jobType = val;
            return this;
        }

        public Builder datePosted(Date val) {
            datePosted = val;
            return this;
        }

        public Builder jobLink(String val) {
            jobLink = val;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}