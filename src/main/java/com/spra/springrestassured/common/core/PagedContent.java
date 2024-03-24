package com.spra.springrestassured.common.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PagedContent <T> (
        List<T> data,
        long totalElements,
        int pageNumber,
        int totalPages,
        @JsonProperty("isFirst") boolean isFirst,
        @JsonProperty("isLast") boolean isLast,
        @JsonProperty("hasNext") boolean hasNext,
        @JsonProperty("hasPrevious") boolean hasPrevious
) { }
