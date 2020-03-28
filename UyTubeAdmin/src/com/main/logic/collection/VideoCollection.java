package com.main.logic.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.logic.domain.Video;

public class VideoCollection {

    private Map<Long, Video> videos = new HashMap<>();

    private static VideoCollection instance = new VideoCollection();

    private List<String> defaultPlaylists = new ArrayList<String>();

    public static VideoCollection getInstance() {
        if (instance == null) {
            instance = new VideoCollection();
        }
        return instance;
    }

    private VideoCollection() {
    }

    public Map<Long, Video> getVideos() {
        return videos;
    }

    public void addVideo(Video video) {
        videos.put(video.getVideoId(), video);
    }

    public Video getVideo(Long videoId) {
        return videos.get(videoId);
    }

    public Video removeVideo(Long videoId) {
        return videos.remove(videoId);
    }
    
    public void deleteVideos() {
        videos = new HashMap<Long, Video>();
    }

    public void addDefaultPlaylist(String newDefaultPL){
        defaultPlaylists.add(newDefaultPL);
    }

    public List<String> getDefaultPlaylists(){
        return defaultPlaylists;
    }

    public void deleteDefaultPlaylists() {
        defaultPlaylists = new ArrayList<>();
    }

}
