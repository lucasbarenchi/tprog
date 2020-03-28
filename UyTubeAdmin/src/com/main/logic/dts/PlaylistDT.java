package com.main.logic.dts;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDT {
    private Long playlistId;
    private boolean isPrivate;
    private String name;
    private boolean defaultPlaylist;
    
    private ChannelDT owner;
    private List<VideoDT> videos;
    private List<String> categories;

    public PlaylistDT() {
        owner = null;
        videos = new ArrayList<>();
        categories = new ArrayList<>();
    }
    
    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public ChannelDT getOwner() {
        return owner;
    }

    public void setOwner(ChannelDT channel) {
        owner = channel;
    }

    public List<VideoDT> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDT> videos) {
        this.videos = videos;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaultPlaylist() {
        return defaultPlaylist;
    }

    public void setDefaultPlaylist(boolean defaultPlaylist) {
        this.defaultPlaylist = defaultPlaylist;
    }
}
