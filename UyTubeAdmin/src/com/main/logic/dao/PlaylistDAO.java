package com.main.logic.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.main.logic.domain.Playlist;
import com.main.logic.domain.Video;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.VideoDT;

public class PlaylistDAO extends AbstractDAO<Playlist, Long> {
    public PlaylistDAO() {
        super(Playlist.class);
    }
    
    public static PlaylistDT getDeletedPlaylistInfo(Long id) {
        Playlist playlist = AbstractDAO.getEntityManager().createQuery("FROM Playlist p WHERE p.id = :id", Playlist.class)
                .setParameter("id", id)
                .getSingleResult();
        return playlist.getDeletedPlaylistDT();
    }
    
    public static List<VideoDT> getDeletedVideosFromPlaylist(Long id) {
        List<Video> videos = AbstractDAO.getEntityManager()
                .createQuery("SELECT v FROM Playlist p INNER JOIN p.videos v WHERE p.id = :id", Video.class)
                .setParameter("id", id)
                .getResultList();
        return videos.stream().map(video -> video.getDeletedVideoDT()).collect(Collectors.toList());
    }
}
