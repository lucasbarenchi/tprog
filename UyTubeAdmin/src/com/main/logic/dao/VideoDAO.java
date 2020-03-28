package com.main.logic.dao;

import com.main.logic.domain.Video;
import com.main.logic.dts.VideoDT;

public class VideoDAO extends AbstractDAO<Video, Long> {
    public VideoDAO() {
        super(Video.class);
    }
    
    public static VideoDT getDeletedVideoInfo(Long id) {
        Video video = AbstractDAO.getEntityManager().createQuery("FROM Video v WHERE v.id = :id", Video.class)
                .setParameter("id", id)
                .getSingleResult();
        return video.getDeletedVideoDT();
    }
}
