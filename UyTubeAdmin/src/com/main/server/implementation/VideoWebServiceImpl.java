package com.main.server.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.CommentDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;
import com.main.logic.utils.RatingEnum;
import com.main.server.service.UserWebService;
import com.main.server.service.VideoWebService;
import com.main.server.wrapper.Videos;

@WebService(endpointInterface = "com.main.server.service.VideoWebService")
public class VideoWebServiceImpl implements VideoWebService{

    private IVideoController videoController = ControllerFactory.getInstance().getVideoController();
    
    @Override
    public Videos listVideos(){
        return new Videos(videoController.listAllVideos()); 
    };
    
    @Override
    public Videos listVideosByUser(String nickname) throws EntityNotFoundException {
        return new Videos(videoController.listVideosByUser(nickname));
    }
    
    @Override
    public Videos listVideosNotFromUser(String nickname) {
        return new Videos(videoController.listVideosNotFromUser(nickname));
    }

    @Override
    public Videos listVideosByCategory(String category) {
        return new Videos(videoController.listVideosByCategory(category));
    }

    @Override
    public VideoDT getVideo(Long videoId) throws EntityNotFoundException {
        return videoController.getVideo(videoId);
    }

    @Override
    public VideoDT createVideo(String owner, String title, int length, String url,
            String description, String uploadDate) throws EntityNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(uploadDate, formatter);
        
        return videoController.createVideo(owner, title, length, url, description, localDate);
    }

    @Override
    public VideoDT createVideoWithCategory(String owner, String title, int length, String url,
            String description, String uploadDate, String category)
            throws EntityNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(uploadDate, formatter);
        
        return videoController.createVideo(owner, title, length, url, description, localDate, category);
    }

    @Override
    public CommentDT listComments(Long videoId) throws EntityNotFoundException {
        List<CommentDT> comments = videoController.listComments(videoId);
        CommentDT parent = new CommentDT();
        parent.setText("Comentarios");
        parent.setResponses(comments);
        
        return parent;
    }

    @Override
    public void commentComment(String nickname, String text, String date, Long videoId,
            Long commentId) throws EntityNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        
        videoController.commentComment(nickname, text, dateTime, videoId, commentId);   
    }

    @Override
    public void commentVideo(String nickname, String text, String date, Long videoId)
            throws EntityNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        
        videoController.commentVideo(nickname, text, dateTime, videoId);
    }

    @Override
    public void modifyVideo(VideoDT updatedVideo, Long videoId)
            throws EntityNotFoundException, PrivacyException {
        videoController.modifyVideo(updatedVideo, videoId);
    }

    @Override
    public void rateVideo(Long videoId, String nickname, RatingEnum rating)
            throws EntityNotFoundException {
        videoController.rateVideo(videoId, nickname, rating);
    }

    @Override
    public void deleteRaiting(Long videoId, String nickname) throws EntityNotFoundException {
        videoController.deleteRaiting(videoId, nickname);
        
    }
}
