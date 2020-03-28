package com.main.logic.interfaces;

import java.util.List;

import com.main.logic.dts.PlaylistDT;
import com.main.logic.exception.DefaultPlaylistException;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.exception.PrivacyException;

public interface IPlaylistController {
    public boolean createDefaultPlaylist(String name) throws KeyAlreadyInUseException;

    public boolean createParticularPlaylist(String name, String nick, boolean isPrivate)
            throws EntityNotFoundException, KeyAlreadyInUseException, PrivacyException;

    public List<PlaylistDT> listPlaylistByCategory(String category) throws EntityNotFoundException;

    public void addVideoToPlaylist(String playlistName, String videoName, String nickOwnerVideo,
            String nickOwnerPlaylist) throws KeyAlreadyInUseException, EntityNotFoundException;

    void addVideoToPlaylist(Long videoId, Long playlistId, String nickOwnerPlaylist) throws KeyAlreadyInUseException, EntityNotFoundException;

    public List<PlaylistDT> listPlaylistByUser(String nickname);

    public List<PlaylistDT> listPublicPlaylistsNotFromUser(String nickname);

    public boolean removeFromPlaylist(String nickname, String playlist, String video);

    public boolean modifyPlaylist(String nickname, String playlistName, boolean isPrivate)
            throws EntityNotFoundException, DefaultPlaylistException, PrivacyException;

    boolean removeFromPlaylist(String nickname, String playlist, Long video);

}
