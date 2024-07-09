package com.csanbar.stock_manager_consumer.models;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse <T>{
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    public PaginatedResponse(List<T> content, int totalPages, long totalElements, int currentPage) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
    }
}
