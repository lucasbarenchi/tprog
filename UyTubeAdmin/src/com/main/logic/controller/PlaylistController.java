package com.main.logic.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.main.logic.collection.UserCollection;
import com.main.logic.collection.VideoCollection;
import com.main.logic.domain.Channel;
import com.main.logic.domain.Default;
import com.main.logic.domain.Particular;
import com.main.logic.domain.Playlist;
import com.main.logic.domain.User;
import com.main.logic.domain.Video;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.exception.DefaultPlaylistException;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.interfaces.IPlaylistController;

public class PlaylistController implements IPlaylistController {

    private static PlaylistController instance;

    public static PlaylistController getInstance() {
        if (instance == null) {
            instance = new PlaylistController();
        }
        return instance;
    }

    private PlaylistController() {
    }

    @Override
    public boolean createDefaultPlaylist(String name) throws KeyAlreadyInUseException {
        UserCollection userCollection = UserCollection.getInstance();
        VideoCollection videoCollection = VideoCollection.getInstance();

        // Check if none of the users have a playlist with this name
        boolean nameInUse = userCollection.getUsers().entrySet().stream()
                .map(Entry::getValue)
                .anyMatch(user -> user.getChannel().hasPlaylist(name));
        if (nameInUse) {
            throw new KeyAlreadyInUseException("Algún usuario ya tiene una playlist con ese nombre");
        }
        // Load default pl to future users
        videoCollection.addDefaultPlaylist(name);
        // Load default pl to actual users
        userCollection.getUsers().forEach((key, value) -> {
            Playlist dflt = new Default(name);
            dflt.setOwner(value.getChannel());
            value.getChannel().addPlaylist(dflt);
        });
        return true;
    }

    @Override
    public boolean createParticularPlaylist(String name, String nick, boolean isPrivate)
            throws EntityNotFoundException, KeyAlreadyInUseException, PrivacyException {
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nick);
        if (user.getChannel().hasPlaylist(name)) {
            throw new KeyAlreadyInUseException(String.format("El usuario con nick %s ya tiene una playlist con nombre %s", nick, name));
        }

        if (user.getChannel().isPrivate() && !isPrivate) {
            throw new PrivacyException("Un canal privado no puede tener playlists públicas");
        }
        Playlist playlist = new Particular(name, isPrivate);
        playlist.setOwner(user.getChannel());
        user.getChannel().addPlaylist(playlist);
        return true;
    }

    @Override
    public List<PlaylistDT> listPlaylistByCategory(String category) throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();

        return userCollection.getUsers().entrySet().stream()
                .map(entry -> entry.getValue())
                .map(User::getChannel)
                .map(Channel::getPlaylists)
                .flatMap(Collection::stream)
                .map(Playlist::getDT)
                .filter(playlist -> playlist.getCategories().contains(category))
                .collect(Collectors.toList());
    }

    @Override
    public void addVideoToPlaylist(String playlistName, String videoName, String nickOwnerVideo,
            String nickOwnerPlaylist) throws KeyAlreadyInUseException, EntityNotFoundException {

        UserCollection userCollection = UserCollection.getInstance();
        User userVideo = userCollection.getUserByNickname(nickOwnerVideo);
        User userPlaylist = userCollection.getUserByNickname(nickOwnerPlaylist);

        Playlist playlist = userPlaylist.getChannel().getPlaylists().stream()
                .filter(play -> play.getName().equals(playlistName)).findFirst().get();
        Video video = userVideo.getChannel().getVideos().stream()
                .filter(vid -> vid.getTitle().equals(videoName)).findFirst().get();

        boolean videoAlreadyInPlaylist = playlist.getVideos().stream()
                .anyMatch(vid -> vid.equals(video));

        if (videoAlreadyInPlaylist) {
            throw new KeyAlreadyInUseException(
                    String.format("El video \"%s\" ya pertenece a la playlist \"%s\"", video.getTitle(), playlist.getName()));
        }

        playlist.addCategory(video.getCategory());
        playlist.addVideo(video);
    }

    @Override
    public void addVideoToPlaylist(Long videoId, Long playlistId, String nickOwnerPlaylist)
            throws KeyAlreadyInUseException, EntityNotFoundException {

        UserCollection userCollection = UserCollection.getInstance();
        VideoCollection videoCollection = VideoCollection.getInstance();

        User userPlaylist = userCollection.getUserByNickname(nickOwnerPlaylist);

        Playlist playlist = userPlaylist.getChannel().getPlaylists().stream()
                .filter(play -> play.getPlaylistId().equals(playlistId)).findFirst().get();
        Video video = videoCollection.getVideo(videoId);

        boolean videoAlreadyInPlaylist = playlist.getVideos().stream()
                .anyMatch(vid -> vid.equals(video));

        if (videoAlreadyInPlaylist) {
            throw new KeyAlreadyInUseException(String.format("El video \"%s\" ya pertenece a la playlist \"%s\"",
                    video.getTitle(), playlist.getName()));
        }

        playlist.addCategory(video.getCategory());
        playlist.addVideo(video);
    }

    @Override
    public List<PlaylistDT> listPlaylistByUser(String nickname) {
        UserCollection userCollection = UserCollection.getInstance();

        try {
            User userSel = userCollection.getUserByNickname(nickname);
            return userSel.getChannel().getPlaylists().stream().map(Playlist::getDT)
                    .collect(Collectors.toList());
        } catch (EntityNotFoundException exc) {
            return new ArrayList<>();
        }
        
    }
    
    @Override
    public List<PlaylistDT> listPublicPlaylistsNotFromUser(String nickname) {

        UserCollection userCollection = UserCollection.getInstance();

        String newNickname = nickname != null ? nickname : "";

        return userCollection.getUsers().entrySet().stream()
                .map(Entry::getValue)
                .filter(user -> !user.getNickname().equals(newNickname))
                .map(user -> user.getChannel().getPlaylists())
                .flatMap(Collection::stream)
                .filter(playlist -> !playlist.isPrivate())
                .map(Playlist::getDT)
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeFromPlaylist(String nickname, String playlistName, String video) {
        UserCollection userCollection = UserCollection.getInstance();

        try {
            User userSel = userCollection.getUserByNickname(nickname);
            Playlist playL = userSel.getChannel().getPlaylists().stream()
                    .filter(playlist -> playlistName.equals(playlist.getName())).findFirst().orElse(null);

            return playL.removeVideo(video);
        } catch (EntityNotFoundException exc) {
            System.out.println("Usuario no encontrado");
            return false;
        }
    }

    @Override
    public boolean modifyPlaylist(String nickname, String playlistName, boolean isPrivate)
            throws EntityNotFoundException, DefaultPlaylistException, PrivacyException {
        
        UserCollection userCollection = UserCollection.getInstance();
        User user = userCollection.getUserByNickname(nickname);
        List<Playlist> playlists = user.getChannel().getPlaylists();

        Playlist playlist = playlists.stream()
            .filter(playl -> playlistName.equals(playl.getName()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("El usuario no tiene una playlist con ese nombre"));
        
        if (playlist instanceof Default && !isPrivate) {
            throw new DefaultPlaylistException("Una playlist por defecto no puede ser pública");
        }
        if (user.getChannel().isPrivate() && !isPrivate) {
            throw new PrivacyException("El channel es privado");
        } else if (playlist.isPrivate() == isPrivate) {
            throw new PrivacyException(
                    String.format("La playlist ya es %s", isPrivate ? "privada" : "pública"));
        }

        playlist.setPrivate(isPrivate);
        return true;

    }

    @Override
    public boolean removeFromPlaylist(String nickname, String playlistName, Long video) {
        UserCollection userCollection = UserCollection.getInstance();

        try {
            User userSel = userCollection.getUserByNickname(nickname);
            Playlist playL = userSel.getChannel().getPlaylists().stream()
                    .filter(playlist -> playlistName.equals(playlist.getName())).findFirst().orElse(null);

            return playL.removeVideo(video);
        } catch (EntityNotFoundException exc) {
            System.out.println("Usuario no encontrado");
            return false;
        }
    }

}
