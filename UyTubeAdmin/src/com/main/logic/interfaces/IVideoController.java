package com.main.logic.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.main.logic.dts.CommentDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.utils.RatingEnum;

public interface IVideoController {
    List<VideoDT> listAllVideos();

    List<VideoDT> listVideosNotFromUser(String nickname);

    List<VideoDT> listVideosByUser(String nickname) throws EntityNotFoundException;

    List<VideoDT> listVideosByCategory(String category);

    VideoDT getVideo(Long videoId) throws EntityNotFoundException;

    VideoDT createVideo(String owner, String title, int length, String url, String description, LocalDate uploadDate) throws EntityNotFoundException;

    VideoDT createVideo(String owner, String title, int length, String url, String description, LocalDate uploadDate,
            String category) throws EntityNotFoundException;

    List<CommentDT> listComments(Long videoId) throws EntityNotFoundException;

    void commentComment(String nickname, String comment, LocalDateTime date, Long videoId, Long commentId) throws EntityNotFoundException;

    void commentVideo(String nickname, String comment, LocalDateTime date, Long videoId) throws EntityNotFoundException;

    void modifyVideo(VideoDT updatedVideo, Long videoId) throws EntityNotFoundException, PrivacyException;

    void rateVideo(Long videoId, String nickname, RatingEnum rating) throws EntityNotFoundException;

    void deleteRaiting(Long videoId, String nickname) throws EntityNotFoundException; 
}
