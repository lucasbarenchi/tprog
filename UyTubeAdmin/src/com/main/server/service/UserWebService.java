package com.main.server.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.main.logic.dts.HistoryDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.server.wrapper.MapWrapper;
import com.main.server.wrapper.Users;

@WebService
@SOAPBinding(style = Style.RPC)
public interface UserWebService {

    @WebMethod
    public Users listUsers();
    
    @WebMethod
    public boolean verifyPassword(String nickname, String password) throws EntityNotFoundException;
    
    @WebMethod
    public PlaylistDT findPlaylistById(String nickname, Long id) throws EntityNotFoundException;
    
    @WebMethod
    public boolean createUser(String nickname, String name, String lastName, String email, String birthDate,
                              byte[] avatar, String channelDescription, String channelName, boolean isPrivate);
    
    @WebMethod
    public void setCategoryToChannel(String nickname, String categoryName) throws EntityNotFoundException;
    
    @WebMethod
    public boolean setPassword(String nickname, String password);
    
    @WebMethod
    public void modifyUser(UserDT newUserData) throws EntityNotFoundException;
    
    @WebMethod
    public void unfollowUser(String followerNickname, String followingNickname) throws EntityNotFoundException;
    
    @WebMethod
    public void createFollowRelationship(String userFollowing, String userToFollow) throws EntityNotFoundException;
    
    @WebMethod
    public UserDT getUserData(String nickmail) throws EntityNotFoundException;
    
    @WebMethod
    public MapWrapper getDefAvatarData() throws IOException;
    
    @WebMethod
    public MapWrapper getAvatarData(String nickname) throws EntityNotFoundException, IOException;

    @WebMethod
    public void addNewVisit(String nickname, Long videoId) throws EntityNotFoundException;
    
    @WebMethod
    public HistoryDT[] getUserHistory(String nickname) throws EntityNotFoundException;

    public void deleteUser(String nickname) throws EntityNotFoundException;
    
}
