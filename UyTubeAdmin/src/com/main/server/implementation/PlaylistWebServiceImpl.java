package com.main.server.implementation;

import javax.jws.WebService;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.exception.DefaultPlaylistException;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.interfaces.IPlaylistController;
import com.main.server.service.PlaylistWebService;
import com.main.server.wrapper.Playlists;

@WebService(endpointInterface = "com.main.server.service.PlaylistWebService")
public class PlaylistWebServiceImpl implements PlaylistWebService {
    
    private IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();
    
    @Override
    public Playlists listPlaylistByCategory(String category) throws EntityNotFoundException {
        return new Playlists(playlistController.listPlaylistByCategory(category));
    }

    @Override
    public void addVideoToPlaylist(Long videoId, Long playlistId, String nickOwnerPlaylist)
            throws KeyAlreadyInUseException, EntityNotFoundException {
        playlistController.addVideoToPlaylist(videoId, playlistId, nickOwnerPlaylist);
        
    }

    @Override
    public boolean createParticularPlaylist(String name, String nick, boolean isPrivate)
            throws EntityNotFoundException, KeyAlreadyInUseException, PrivacyException {
        return playlistController.createParticularPlaylist(name, nick, isPrivate);
    }

    @Override
    public boolean removeFromPlaylist(String nickname, String playlist, Long video) {
        return playlistController.removeFromPlaylist(nickname, playlist, video);
    }

    @Override
    public boolean modifyPlaylist(String nickname, String playlistName, boolean isPrivate)
            throws EntityNotFoundException, DefaultPlaylistException, PrivacyException {
        return playlistController.modifyPlaylist(nickname, playlistName, isPrivate);
    }

    @Override
    public Playlists listPlaylistByUser(String nickname) {
        return new Playlists(playlistController.listPlaylistByUser(nickname));
    }

    @Override
    public Playlists listPlaylistNotFromUser(String nickname) {
        return new Playlists(playlistController.listPublicPlaylistsNotFromUser(nickname));
    }

}
