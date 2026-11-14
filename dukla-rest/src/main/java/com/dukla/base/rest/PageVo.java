package com.dukla.base.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据结构
 */
public class PageVo<T> {
     //总记录数
    private int total = 0;
    //总页数
    private int pages = 0;
    //每页条数
    private int pageSize = 20;
    //当前页
    private int currentPage = 1;
    //是否还有下一页
    private boolean hasNext = false;
    //记录集合
    private List<T> list = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
