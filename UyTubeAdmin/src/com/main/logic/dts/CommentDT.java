package com.main.logic.dts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDT {
    private Long commentId;
    private String commenter;
    private String text;
    private VideoDT commentedVideo;
    private LocalDateTime commentDate;
    private List<CommentDT> responses;

    public CommentDT() {
        responses = new ArrayList<>();
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public VideoDT getCommentedVideo() {
        return commentedVideo;
    }

    public void setCommentedVideo(VideoDT commentedVideo) {
        this.commentedVideo = commentedVideo;
    }

    public List<CommentDT> getResponses() {
        return responses;
    }

    public void setResponses(List<CommentDT> responses) {
        this.responses = responses;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime newDate) {
        commentDate = newDate;
    }

}
