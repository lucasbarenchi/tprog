package com.main.server.wrapper;

import java.util.List;

import com.main.logic.dts.PlaylistDT;

public class Playlists {
    List<PlaylistDT> playlists;
    
    public Playlists() {}
    
    public Playlists(List<PlaylistDT> playlists) {
        this.playlists = playlists;
    }

    public List<PlaylistDT> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDT> playlists) {
        this.playlists = playlists;
    }
}
