package com.spra.springrestassured.job.core;

enum JobType {
    FULL_TIME("Full-Time"), CONTRACTOR("Contactor"), PART_TIME("Part-time");
    private String value;
    JobType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
