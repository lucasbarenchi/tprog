package com.main.logic.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.logic.dts.VideoDT;
import com.main.logic.utils.RatingEnum;

@Entity
@Table(name="video")
public class Video {

    private static Long generatedId = -1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;
    
    @Column(name="url")
    private String url;
    
    @Column(name="private")
    private boolean isPrivate;
    
    @Column(name="description", columnDefinition="VARCHAR(500)")
    private String description;
    
    @Column(name="upload_date")
    private LocalDate uploadDate;
    
    @Column(name="length")
    private int length;
    
    @ManyToOne()
    @JoinTable(name="channel_video", 
        joinColumns=@JoinColumn(name="id_video", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="id_channel", referencedColumnName="id"))
    private Channel publisher;
    
    @Transient
    private Long videoId;
    
    @Transient
    private Category category;
    
    @Transient
    private List<Comment> comments;
    
    @Transient
    private List<Comment> allComments;
    
    @Transient
    private Map<String, Rating> ratings;

    public Video() {
        videoId = generatedId;
        generatedId++;
        comments = new ArrayList<>();
        allComments = new ArrayList<>();
        ratings = new HashMap<>();
    }

    public List<Comment> getAllComments() {
        return allComments;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Channel getPublisher() {
        return publisher;
    }

    public void setPublisher(Channel publisher) {
        this.publisher = publisher;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Map<String, Rating> getRating() {
        return ratings;
    }

    public void addRating(String nickname, Rating rating) {
        ratings.put(nickname, rating);
    }

    public void removeRaiting(String nickname) {
        ratings.remove(nickname);
    }

    public VideoDT getBasicDT() {
        VideoDT videoDT = new VideoDT();
        videoDT.setVideoId(videoId);
        videoDT.setDescription(description);
        videoDT.setLength(length);
        videoDT.setUrl(url);
        videoDT.setUploadDate(uploadDate);
        videoDT.setUploader(publisher.getOwner());
        if (category != null) {
            videoDT.setCategory(category.getDT());
        } else {
            videoDT.setCategory(null);
        }
        videoDT.setTitle(title);
        videoDT.setPrivate(isPrivate);
        return videoDT;
    }

    public VideoDT getAdvancedDT() {
        VideoDT videoDT = getBasicDT();
        videoDT.setPrivate(isPrivate);
        videoDT.setUploader(publisher.getOwner());
        videoDT.setComments(comments.stream().map(Comment::getDT).collect(Collectors.toList()));
        videoDT.setAllComments(allComments.stream().map(Comment::getDT).collect(Collectors.toList()));
        videoDT.setLiked(ratings.entrySet().stream()
                .filter(rating -> rating.getValue().getRatingType() == RatingEnum.LIKE)
                .map(rating -> rating.getValue().getRater().getNickname())
                .collect(Collectors.toList()));
        videoDT.setDisliked(ratings.entrySet().stream()
                .filter(rating -> rating.getValue().getRatingType() == RatingEnum.DISLIKE)
                .map(rating -> rating.getValue().getRater().getNickname())
                .collect(Collectors.toList()));
        return videoDT;
    }
    
    public VideoDT getDeletedVideoDT() {
        VideoDT videoDT = new VideoDT();
        videoDT.setVideoId(id);
        videoDT.setDescription(description);
        videoDT.setLength(length);
        videoDT.setUrl(url);
        videoDT.setUploadDate(uploadDate);
        videoDT.setTitle(title);
        videoDT.setPrivate(isPrivate);
        return videoDT;
    }
}
