package com.main.logic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.main.logic.dts.CommentDT;

public class Comment {

    private static Long generatedId = 0L;

    private Long commentId;
    private String commenter;
    private String text;
    private List<Comment> responses;
    private Video commentedVideo;
    private LocalDateTime date;

    public Comment() {
        commentId = generatedId;
        generatedId++;
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

    public List<Comment> getResponses() {
        return responses;
    }

    public void setResponses(List<Comment> responses) {
        this.responses = responses;
    }

    public Video getCommentedVideo() {
        return commentedVideo;
    }

    public void setCommentedVideo(Video commentedVideo) {
        this.commentedVideo = commentedVideo;
    }

    public CommentDT getDT() {
        CommentDT commentDT = new CommentDT();
        commentDT.setCommentId(commentId);
        commentDT.setText(text);
        commentDT.setCommenter(commenter);
        commentDT.setCommentedVideo(commentedVideo.getBasicDT());
        commentDT.setResponses(responses.stream().map(Comment::getDT).collect(Collectors.toList()));
        commentDT.setCommentDate(date);
        return commentDT;
    }

    public void addResponse(Comment comment) {
        responses.add(comment);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
