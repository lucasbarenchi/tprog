package com.main.server.implementation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.jws.WebService;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.HistoryDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.IUserController;
import com.main.server.service.UserWebService;
import com.main.server.wrapper.MapWrapper;
import com.main.server.wrapper.Users;

@WebService(endpointInterface = "com.main.server.service.UserWebService")
public class UserWebServiceImpl implements UserWebService{

    private IUserController userController = ControllerFactory.getInstance().getUserController();
    
    @Override
    public boolean verifyPassword(String nickname, String password) throws EntityNotFoundException {
        return userController.verifyPassword(nickname, password);
    }

    @Override
    public Users listUsers() {
        return new Users(userController.listUsers());
    }

    @Override
    public PlaylistDT findPlaylistById(String nickname, Long playlistId) throws EntityNotFoundException {
        return userController.findPlaylistById(nickname, playlistId);
    }

    @Override
    public boolean createUser(String nickname, String name, String lastName, String email,
            String birthDate, byte[] avatar, String channelDescription, String channelName,
            boolean isPrivate) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birth = LocalDate.parse(birthDate, formatter);
        
        return userController.createUser(
                nickname, name, lastName, email, birth, avatar, channelDescription, channelName, isPrivate);
        
    }

    @Override
    public void setCategoryToChannel(String nickname, String categoryName) throws EntityNotFoundException { 
        userController.setCategoryToChannel(nickname, categoryName);
    }

    @Override
    public boolean setPassword(String nickname, String password) {
        return userController.setPassword(nickname, password);
    }

    @Override
    public void modifyUser(UserDT newUserData) throws EntityNotFoundException {
        userController.modifyUser(newUserData);
    }

    @Override
    public void unfollowUser(String followerNickname, String followingNickname) throws EntityNotFoundException {
        userController.unfollowUser(followerNickname, followingNickname);
    }

    @Override
    public void createFollowRelationship(String userFollowing, String userToFollow) throws EntityNotFoundException {
        userController.createFollowRelationship(userFollowing, userToFollow);
    }

    @Override
    public UserDT getUserData(String nickmail) throws EntityNotFoundException {
        return userController.getUserData(nickmail);
    }

    @Override
    public MapWrapper getDefAvatarData() throws IOException {
        return new MapWrapper(userController.getDefAvatarData());
    }

    @Override
    public MapWrapper getAvatarData(String nickname) throws EntityNotFoundException, IOException {    
        return new MapWrapper(userController.getAvatarData(nickname));
    }
    
    @Override
    public void addNewVisit(String nickname, Long videoId) throws EntityNotFoundException{
        userController.addNewVisit(nickname, videoId);
    }
    
    @Override
    public HistoryDT[] getUserHistory(String nickname) throws EntityNotFoundException{
        return userController.getUserHistory(nickname).stream().toArray(size -> new HistoryDT[size]);
    }

    public void deleteUser(String nickname) throws EntityNotFoundException {
        userController.deleteUser(nickname);
    }
    
}
