package com.main.logic.dts;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VideoDT implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long videoId;

    private String title;
    private String url;
    private boolean isPrivate;
    private String description;
    private LocalDate uploadDate;
    private int length;

    private CategoryDT category;
    private String uploader;
    private List<CommentDT> comments;
    private List<CommentDT> allComments;
    private List<String> liked;
    private List<String> disliked;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public CategoryDT getCategory() {
        return category;
    }

    public void setCategory(CategoryDT category) {
        this.category = category;
    }

    public List<CommentDT> getComments() {
        return comments;
    }

    public void setComments(List<CommentDT> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        if (title == null) {
            return "-";
        }
        return title + " (" + videoId.toString() + ")";
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public List<CommentDT> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<CommentDT> allComments) {
        this.allComments = allComments;
    }

    public List<String> getLiked() {
        return liked;
    }

    public void setLiked(List<String> liked) {
        this.liked = liked;
    }

    public void addLiked(String nickname) {
        this.liked.add(nickname);
    }

    public List<String> getDisliked() {
        return disliked;
    }

    public void setDisliked(List<String> disliked) {
        this.disliked = disliked;
    }

    public void addDisliked(String nickname) {
        this.disliked.add(nickname);
    }
    
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof VideoDT)) {
            return false;
        }
        VideoDT castOther = (VideoDT) other;
        return Objects.equals(this.videoId, castOther.videoId);
    }
}
