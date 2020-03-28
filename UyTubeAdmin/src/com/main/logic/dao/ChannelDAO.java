package com.main.logic.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.main.logic.domain.Channel;
import com.main.logic.domain.Playlist;
import com.main.logic.domain.User;
import com.main.logic.domain.Video;
import com.main.logic.dts.ChannelDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.dts.VideoDT;

public class ChannelDAO extends AbstractDAO<Channel, Long> {
    public ChannelDAO() {
        super(Channel.class);
    }
    
    public static ChannelDT getDeletedChannelInfo(Long userId) {
        Channel channel = AbstractDAO.getEntityManager().createQuery("FROM Channel c WHERE c.user.id = :userId", Channel.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return channel.getDeletedChannelDT();
    }
    
    public static List<VideoDT> getDeletedVideosFromChannel(Long channelId) {
        List<Video> videos = AbstractDAO.getEntityManager()
                .createQuery("SELECT v FROM Channel c INNER JOIN c.videos v WHERE c.id = :channelId", Video.class)
                .setParameter("channelId", channelId)
                .getResultList();
        return videos.stream().map(video -> video.getDeletedVideoDT()).collect(Collectors.toList());
    }
    
    public static List<PlaylistDT> getDeletedPlaylistFromChannel(Long channelId) {
        List<Playlist> playlists = AbstractDAO.getEntityManager()
                .createQuery("SELECT p FROM Channel c INNER JOIN c.playlists p WHERE c.id = :channelId", Playlist.class)
                .setParameter("channelId", channelId)
                .getResultList();
        return playlists.stream().map(playlist -> playlist.getDeletedPlaylistDT()).collect(Collectors.toList());
    }
}
