package com.main.server.wrapper;

import java.util.List;

import com.main.logic.dts.VideoDT;

public class Videos {
    List<VideoDT> videos;
    
    public Videos() {}
    
    public Videos(List<VideoDT> videos) {
        this.videos = videos;
    }

    public List<VideoDT> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDT> videos) {
        this.videos = videos;
    }
}
