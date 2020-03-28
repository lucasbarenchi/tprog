package com.main.logic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.main.logic.dts.DefaultDT;
import com.main.logic.dts.ParticularDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.VideoDT;

@Entity
@DiscriminatorValue("0")
public class Particular extends Playlist {
    
    public Particular() {
        
    }
    
    public Particular(String name, boolean isPrivate) {
        super();
        this.setName(name);
        this.setPrivate(isPrivate);
    }

    public Particular(String name, boolean isPrivate, Category category) {
        super();
        this.setName(name);
        this.setPrivate(isPrivate);
        this.getCategories().add(category);
    }
    
    @Override
    public PlaylistDT getDT() {
        PlaylistDT playlistDT = null;

        List<VideoDT> videosDT = getVideos().stream().map(Video::getBasicDT)
                .collect(Collectors.toList());

        List<String> categoriesDT = new ArrayList<>();

        for (Category category : this.getCategories()) {
            if (category != null) {
                String name = category.getName();
                categoriesDT.add(name);
            }
        }
        
        playlistDT = new ParticularDT(this.getName(), this.isPrivate(), categoriesDT);
        playlistDT.setOwner(this.getOwner().getDT());
        
        playlistDT.setPlaylistId(this.getPlaylistId());
        playlistDT.setPrivate(this.isPrivate());
        playlistDT.setVideos(videosDT);
        playlistDT.setCategories(categoriesDT);

        return playlistDT;
    }
    
    @Override
    public PlaylistDT getDeletedPlaylistDT() {
        PlaylistDT playlistDT = new ParticularDT(this.getName(), this.isPrivate());
        playlistDT.setPlaylistId(this.id);
        playlistDT.setDefaultPlaylist(false);
        return playlistDT;
    }
}
