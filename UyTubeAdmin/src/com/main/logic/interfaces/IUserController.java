package com.main.logic.interfaces;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.main.logic.dts.HistoryDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.EntityNotFoundException;

public interface IUserController {

    List<UserDT> listUsers();

    List<PlaylistDT> listUserPlaylist(String username) throws EntityNotFoundException;

    void createFollowRelationship(String userFollowing, String userToFollow) throws EntityNotFoundException;

    boolean createUser(String nickname, String name, String lastName, String email, LocalDate birthDate,
					   byte[] avatar, String channelDescription, String channelName, boolean IsPrivate);

    UserDT getUserData(String nickname) throws EntityNotFoundException;

    void unfollowUser(String followerNickname, String followingNickname) throws EntityNotFoundException;

    void modifyUser(UserDT newUserData) throws EntityNotFoundException;

    void setCategoryToChannel(String nickname, String categoryName) throws EntityNotFoundException;

    Map<String, Object> getAvatarData(String nickname) throws EntityNotFoundException, IOException;

    Map<String, Object> getDefAvatarData() throws IOException;

    boolean verifyPassword(String nickname, String password) throws EntityNotFoundException;

    boolean setPassword(String nickname, String password);

    PlaylistDT findPlaylistById(String nickname, Long playlistId) throws EntityNotFoundException;
    
    void addNewVisit(String nickname, Long videoId) throws EntityNotFoundException;

    List<HistoryDT> getUserHistory(String nickname) throws EntityNotFoundException;

    void deleteUser(String nickname) throws EntityNotFoundException;

}
