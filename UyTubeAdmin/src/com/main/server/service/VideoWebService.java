package com.main.server.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.main.logic.dts.CommentDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.utils.RatingEnum;
import com.main.server.wrapper.Videos;

@WebService
@SOAPBinding(style = Style.RPC)
public interface VideoWebService {
    
    @WebMethod
    public Videos listVideos();
    
    @WebMethod
    public Videos listVideosByUser(String nickname) throws EntityNotFoundException;
    
    @WebMethod
    public Videos listVideosNotFromUser(String nickname);
    
    @WebMethod
    public Videos listVideosByCategory(String category);
    
    @WebMethod
    public VideoDT getVideo(Long videoId) throws EntityNotFoundException;
    
    @WebMethod
    public VideoDT createVideo(String owner, String title, int length, String url, String description, String uploadDate) throws EntityNotFoundException;
    
    @WebMethod
    public VideoDT createVideoWithCategory(String owner, String title, int length, String url, String description, String uploadDate, String category) throws EntityNotFoundException;
    
    @WebMethod
    public CommentDT listComments(Long videoId) throws EntityNotFoundException;
    
    @WebMethod
    public void commentComment(String nickname, String text, String date, Long videoId, Long commentId) throws EntityNotFoundException;
    
    @WebMethod
    public void commentVideo(String nickname, String text, String date, Long videoId) throws EntityNotFoundException;
    
    @WebMethod
    public void modifyVideo(VideoDT updatedVideo, Long videoId) throws EntityNotFoundException, PrivacyException;
    
    @WebMethod
    public void rateVideo(Long videoId, String nickname, RatingEnum rating) throws EntityNotFoundException;
    
    @WebMethod
    public void deleteRaiting(Long videoId, String nickname) throws EntityNotFoundException;
}
