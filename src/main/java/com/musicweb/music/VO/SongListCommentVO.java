package com.musicweb.music.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongListCommentVO<T> {
    @JsonProperty("allCount")
    private Integer commentNumber;
    @JsonProperty("firstPageComments")
    private T dataList;

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }
}
