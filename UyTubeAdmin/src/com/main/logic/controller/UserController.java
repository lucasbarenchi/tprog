package com.main.logic.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.main.logic.collection.CategoryCollection;
import com.main.logic.collection.UserCollection;
import com.main.logic.collection.VideoCollection;

import com.main.logic.domain.History;

import com.main.logic.dao.ChannelDAO;
import com.main.logic.domain.Category;
import com.main.logic.domain.Channel;
import com.main.logic.domain.Comment;

import com.main.logic.domain.Playlist;
import com.main.logic.domain.User;
import com.main.logic.domain.Video;
import com.main.logic.dts.HistoryDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.interfaces.IUserController;

public class UserController implements IUserController {

    private static UserController instance = null;
    private static Logger logger = Logger.getLogger(UserController.class);

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    private UserController() {
    }

    @Override
    public List<UserDT> listUsers() {
        UserCollection userCollection = UserCollection.getInstance();
        return userCollection.getUsers().entrySet().stream().map(Map.Entry::getValue).map(User::getBasicDT)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaylistDT> listUserPlaylist(String nickname) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nickname);

        List<PlaylistDT> playlist = user.getChannel().getPlaylists().stream().map(Playlist::getDT).filter(Objects::nonNull)
                .collect(Collectors.toList());
        return playlist;

    };

    @Override
    public void createFollowRelationship(String userFollowing, String userToFollow) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();

        User following = userCollection.getUserByNickname(userFollowing);
        User toFollow = userCollection.getUserByNickname(userToFollow);

        following.addFollowed(toFollow);
        toFollow.addFollower(following);

    };

    @Override
    public boolean createUser(String nickname, String name, String lastName, String email, LocalDate birthDate,
                              byte[] avatar, String channelDescription, String channelName, boolean isPrivate) {
        UserCollection userCollection = UserCollection.getInstance();
        User user;
        try {
            user = userCollection.getUserByNickname(nickname);
        } catch (EntityNotFoundException e) {
            try {
                user = userCollection.getUserByEmail(email);
            } catch (EntityNotFoundException e2) {
                user = new User();
                
                Channel channel = new Channel(user);
                channel.setName(channelName);
                channel.setDescription(channelDescription);
                channel.setPrivate(isPrivate);

                user.setNickname(nickname);
                user.setName(name);
                user.setSurname(lastName);
                user.setMail(email);
                user.setBirthdate(birthDate);
                user.setAvatar(avatar);
                user.setChannel(channel);

                userCollection.addUser(user);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void deleteUser(String nickname) throws EntityNotFoundException {
        ChannelDAO channelDAO = new ChannelDAO();
        
        UserCollection userCollection = UserCollection.getInstance();
        
        User user = userCollection.getUserByNickname(nickname);
        Channel channel = user.getChannel();
        
        VideoCollection videoCollection = VideoCollection.getInstance();
        Map<Long, Video> videos = videoCollection.getVideos();
        
        for (User followedUser : user.getFollowed().values()) {
            followedUser.removeFollower(user.getNickname());
        }
        
        for (User followerUser : user.getFollowers().values()) {
            followerUser.removeFollowed(user.getNickname());
        }
        
        for (Video video : videos.values()) {
            Iterator<Comment> iter = video.getAllComments().iterator();
            while (iter.hasNext()) {
                Comment comment = iter.next();
                if (comment.getCommenter().equals(user.getNickname()))
                    iter.remove();
            }
            video.removeRaiting(user.getNickname());
        }
        
        for (User user2 : userCollection.getUsers().values()) {
            Channel channel2 = user2.getChannel();
            if (channel.getOwner() != user.getNickname()) {
                for (Video video : channel.getVideos()) {
                    for (Playlist playlist : channel2.getPlaylists()) {
                        playlist.removeVideo(video.getVideoId());
                    }
                    user2.removeRaiting(video.getVideoId());
                    videoCollection.removeVideo(video.getVideoId());
                }
            }
        }
        
        for (Playlist playlist : channel.getPlaylists()) {
            List<Video> videosToPersist = new ArrayList<>();
            for (Video video : playlist.getVideos()) {
                if (video.getPublisher().getOwner() == user.getNickname()) {
                    videosToPersist.add(video);
                }
            }
            playlist.setVideos(videosToPersist);
            System.out.println("VIDEOS IN PLAYLIST " + playlist.getName() + " " + videosToPersist.size());
        }
        
        userCollection.removeUser(user);
        
        channelDAO.save(channel);
    }
    
    @Override
    public Map<String, Object> getAvatarData(String nickname) throws EntityNotFoundException, IOException {
        UserDT userDT = this.getUserData(nickname);
        Map<String, Object> data = new HashMap<>();
        data.put("bytes", userDT.getAvatar());
        InputStream bytes = new ByteArrayInputStream(userDT.getAvatar());
        data.put("type", URLConnection.guessContentTypeFromStream(bytes));
        return data;
    }

    @Override
    public Map<String, Object> getDefAvatarData() throws IOException {
        InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
        byte[] defAvatarBytes = new byte[defAvatarStream.available()];
        defAvatarStream.read(defAvatarBytes);
        
        Map<String, Object> data = new HashMap<>();
        data.put("bytes", defAvatarBytes);
        data.put("type", URLConnection.guessContentTypeFromStream(defAvatarStream));
        return data;
    }

    @Override
    public boolean setPassword(String nickname, String password) {
        UserCollection userCollection = UserCollection.getInstance();
        try {
            User user = userCollection.getUserByNickname(nickname);
            user.setPassword(password);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean verifyPassword(String nickmail, String password) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNicknameOrEmail(nickmail);
        return user.getPassword().equals(password);
    }

    @Override
    public UserDT getUserData(String nickmail) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNicknameOrEmail(nickmail);
        return user.getBasicDT();
    };
    
    @Override
    public void addNewVisit(String nickname, Long videoId) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nickname);
        user.addNewVisit(videoId);
    };
    
    @Override
    public List<HistoryDT> getUserHistory(String nickname) throws EntityNotFoundException {
        VideoCollection videoCollection = VideoCollection.getInstance();
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nickname);
        List<History> histList = user.getHistory();
        
        List<HistoryDT> ret = new ArrayList<HistoryDT>();
        for (History hist : histList) {
            Video video = videoCollection.getVideo(hist.getVideoId());
            if (!video.isPrivate() || video.getPublisher().getOwner().equals(nickname)) {
                HistoryDT histDt = hist.getHistoryDt();
                histDt.setVideoDt(video.getBasicDT());
                ret.add(histDt); // ITs OK TO SHOW
            }
        }
        return ret;
    };

    @Override
    public void unfollowUser(String followerNickname, String followingNickname) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        User follower = userCollection.getUserByNickname(followerNickname);
        User following = userCollection.getUserByNickname(followingNickname);

        follower.removeFollowed(followingNickname);
        following.removeFollower(followerNickname);
    }

    @Override
    public void modifyUser(UserDT newUserData) throws EntityNotFoundException {
        String nickname = newUserData.getNickname();
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nickname);

        user.setName(newUserData.getName());
        user.setSurname(newUserData.getSurname());
        if (newUserData.getAvatar() != null) {
            user.setAvatar(newUserData.getAvatar());
        }
        user.setBirthdate(newUserData.getBirthdate());
        Channel channel = user.getChannel();
        channel.setName(newUserData.getChannel().getName());
        channel.setDescription(newUserData.getChannel().getDescription());

        if (newUserData.getChannel().isPrivateChannel()) {
            channel.setPrivate(true);

            List<Video> videos = channel.getVideos();
            videos.forEach(video -> video.setPrivate(true));

            List<Playlist> playlists = channel.getPlaylists();
            playlists.forEach(playlist -> playlist.setPrivate(true));
        } else {
            channel.setPrivate(false);
        }
    }

    @Override
    public PlaylistDT findPlaylistById(String nickname, Long playlistId) throws EntityNotFoundException {
        List<PlaylistDT> playlists = listUserPlaylist(nickname);
        return playlists.stream().filter(playlist -> playlist.getPlaylistId().equals(playlistId)).findFirst().orElse(null);
    }

    @Override	
    public void setCategoryToChannel(String nickname, String categoryName) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nickname);
        Channel owner = user.getChannel();
        
        if (categoryName == null) {
            owner.setCategory(null);
        } else {
            CategoryCollection categoryCollection = CategoryCollection.getInstance();
            Category category = categoryCollection.getCategory(categoryName);
            owner.setCategory(category);
        }
    }
    
}