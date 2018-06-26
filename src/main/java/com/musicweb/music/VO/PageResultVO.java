package com.musicweb.music.VO;


public class PageResultVO<T> {

    private Integer pages;

    private Long total;

    private Integer currentPage;

    private Integer currentSize;

    private T data;

    public PageResultVO(Integer pages, Long total, Integer currentPage, Integer currentSize, T data) {
        this.pages = pages;
        this.total = total;
        this.currentPage = currentPage;
        this.currentSize = currentSize;
        this.data = data;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
