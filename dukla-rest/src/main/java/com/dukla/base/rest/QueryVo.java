package com.dukla.base.rest;

/**
 * 查询结构
 */
public class QueryVo<T> {
     //当前页
    private int page = 1;

    //每页条数
    private int pageSize = 20;

    //排序字段
    private String sortBy;
    //排序方向
    private SortOrder sortOrder;
    //查询条件
    private T filter;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
