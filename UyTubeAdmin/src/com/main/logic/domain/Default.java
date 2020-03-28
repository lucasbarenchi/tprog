package com.main.logic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.main.logic.dts.DefaultDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.VideoDT;

@Entity
@DiscriminatorValue("1")
public class Default extends Playlist {

    public Default() {
        
    }
    
    public Default(String name) {
        this.setName(name);
        this.setPrivate(true);
    }

    @Override
    public PlaylistDT getDT() {
        PlaylistDT playlistDT = null;

        List<VideoDT> videosDT = getVideos().stream().map(Video::getBasicDT)
                .collect(Collectors.toList());

        List<String> categoriesDT = new ArrayList<>();

        for (Category c : this.getCategories()) {
            if (c != null) {
                String name = c.getName();
                categoriesDT.add(name);
            }
        }
        playlistDT = new DefaultDT(this.getName());
        playlistDT.setOwner(this.getOwner().getDT());

        playlistDT.setPlaylistId(this.getPlaylistId());
        playlistDT.setPrivate(this.isPrivate());
        playlistDT.setVideos(videosDT);
        playlistDT.setCategories(categoriesDT);

        return playlistDT;
    }
    
    @Override
    public PlaylistDT getDeletedPlaylistDT() {
        PlaylistDT playlistDT = new DefaultDT(this.getName());
        playlistDT.setPrivate(this.isPrivate());
        playlistDT.setPlaylistId(this.id);
        playlistDT.setDefaultPlaylist(true);
        return playlistDT;
    }
}
