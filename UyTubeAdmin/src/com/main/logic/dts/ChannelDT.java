package com.main.logic.dts;

import java.util.ArrayList;
import java.util.List;

public class ChannelDT {
    private Long channelId;
    private String name;
    private String owner;
    private String description;
    private boolean privateChannel;
    private List<VideoDT> videos;
    private List<String> playlists;
    private CategoryDT category;

    public ChannelDT() {
        videos = new ArrayList<>();
        playlists = new ArrayList<>();
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<VideoDT> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDT> videos) {
        this.videos = videos;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<String> playlists) {
        this.playlists = playlists;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivateChannel() {
        return privateChannel;
    }

    public void setPrivateChannel(boolean privateChannel) {
        this.privateChannel = privateChannel;
    }

    public CategoryDT getCategory() {
        return category;
    }

    public void setCategory(CategoryDT category) {
        this.category = category;
    }
}
