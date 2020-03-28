package com.main.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlElement;

import com.main.logic.exception.DefaultPlaylistException;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.exception.PrivacyException;
import com.main.server.wrapper.Playlists;

@WebService
@SOAPBinding(style = Style.RPC)
public interface PlaylistWebService {

    @WebMethod
    public Playlists listPlaylistByCategory(String category) throws EntityNotFoundException;
    
    @WebMethod
    void addVideoToPlaylist(Long videoId, Long playlistId, String nickOwnerPlaylist) throws KeyAlreadyInUseException, EntityNotFoundException;
    
    @WebMethod
    public boolean createParticularPlaylist(String name, String nick, boolean isPrivate)
            throws EntityNotFoundException, KeyAlreadyInUseException, PrivacyException;
    
    @WebMethod
    public boolean removeFromPlaylist(String nickname, String playlist, Long video);
    
    @WebMethod
    public boolean modifyPlaylist(String nickname, String playlistName, boolean isPrivate)
            throws EntityNotFoundException, DefaultPlaylistException, PrivacyException;
    
    @WebMethod
    public Playlists listPlaylistByUser(String nickname);
    
    @WebMethod
    public Playlists listPlaylistNotFromUser(@XmlElement(nillable=true, required=false) @WebParam(name="nickname") String nickname);
    
}
