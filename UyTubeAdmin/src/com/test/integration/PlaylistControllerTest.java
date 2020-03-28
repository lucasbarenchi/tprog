package com.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.main.logic.collection.UserCollection;
import com.main.logic.collection.VideoCollection;
import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.exception.DefaultPlaylistException;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.interfaces.ICategoryController;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;

public class PlaylistControllerTest {

    private static UserCollection userCollection;
    private static IUserController userController;
    private static IPlaylistController playlistController;
    private static IVideoController videoController;
    private static ICategoryController categoryController;
    private static VideoCollection videoCollection;


    @Before
    public void init() {
        userController = ControllerFactory.getInstance().getUserController();
        userCollection = UserCollection.getInstance();
        playlistController = ControllerFactory.getInstance().getPlaylistController();
        videoController = ControllerFactory.getInstance().getVideoController();
        categoryController = ControllerFactory.getInstance().getCategoryController();
        videoCollection = VideoCollection.getInstance();
    }

    @Test
    public void testCreateDefaultPlaylist() {
        userCollection.deleteUsers();
        videoCollection.deleteDefaultPlaylists();
        boolean user = userController.createUser("nicknameTEST", "nameTEST", "lastNameTEST", "emailTEST", LocalDate.now(), null, "descriptionTEST", "channelNameTEST", true);
        assertTrue(user);
        List<PlaylistDT> playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST");
        // Check that the user`s playlists is empty
        assertEquals(0, playlistFromUser.size());
        // Load a new defaultPlaylist
        try {
            playlistController.createDefaultPlaylist("defaultPlaylistTEST");
            assertEquals(1, 1);
        } catch(KeyAlreadyInUseException e) {
            fail("Expected to create the playlist without problem");
        }
        // Check if it was saved succ
        playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST");
        assertEquals(1, playlistFromUser.size());
        assertEquals("defaultPlaylistTEST", playlistFromUser.get(0).getName());
        assertEquals(true, playlistFromUser.get(0).isPrivate());
        assertEquals("channelNameTEST", playlistFromUser.get(0).getOwner().getName());

        try {
            playlistController.createDefaultPlaylist("defaultPlaylistTEST");
            fail("Expected KeyAlreadyInUseException");
        } catch(KeyAlreadyInUseException e) {
            assertEquals(1, 1);    		
        }
    }

    @Test
    public void testCreateParticularPlaylist() {
        userCollection.deleteUsers();
        videoCollection.deleteDefaultPlaylists();
        boolean user1 = userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.now(), null, "descriptionTEST1", "channelNameTEST1", true);
        boolean user2 = userController.createUser("nicknameTEST2", "nameTEST2", "lastNameTEST2", "emailTEST2", LocalDate.now(), null, "descriptionTEST2", "channelNameTEST2", true);

        assertTrue(user1);
        assertTrue(user2);
        List<PlaylistDT> playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST1");
        // Check that the user1`s playlists is empty
        assertEquals(0, playlistFromUser.size());

        playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST2");
        // Check that the user2`s playlists is empty
        assertEquals(0, playlistFromUser.size());

        // Load a new particularPlaylist
        try {
            playlistController.createParticularPlaylist("particularPlaylistTEST2", "nicknameTEST2", true);
            assertEquals(1, 1);
        } catch(KeyAlreadyInUseException e) {
            fail("Expected to create the playlist without problem");
        } catch(EntityNotFoundException e){
            fail("The user is loaded in the system");
        } catch (PrivacyException e){
            fail("The user channel was private to");
        }

        // Check if it was saved succ in User2
        playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST2");
        assertEquals(1, playlistFromUser.size());
        assertEquals("particularPlaylistTEST2", playlistFromUser.get(0).getName());
        assertEquals(true, playlistFromUser.get(0).isPrivate());
        assertEquals("channelNameTEST2", playlistFromUser.get(0).getOwner().getName());

        // Check if it wasn't saved in User1
        playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST1");
        assertEquals(0, playlistFromUser.size());

        // Check if exception is throw correctly
        try {
            playlistController.createParticularPlaylist("particularPlaylistTEST2", "nicknameTEST2", true);
            fail("Expected to create the playlist without problem");  		
        } catch(KeyAlreadyInUseException e) {
            assertEquals(1, 1);
        } catch(EntityNotFoundException e){
            fail("The user is loaded in the system");
        } catch (PrivacyException e){
            fail("The user channel was private to");
        }
    }

    @Test
    public void testAddVideoToPlaylist() {
        userCollection.deleteUsers();
        videoCollection.deleteDefaultPlaylists();
        boolean user1 = userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.now(), null, "descriptionTEST1", "channelNameTEST1", true);
        boolean user2 = userController.createUser("nicknameTEST2", "nameTEST2", "lastNameTEST2", "emailTEST2", LocalDate.now(), null, "descriptionTEST2", "channelNameTEST2", true);
        assertTrue(user1);
        assertTrue(user2);
        // Load a new particularPlaylist
        try {
            playlistController.createParticularPlaylist("particularPlaylistTEST2", "nicknameTEST2", true);
            assertEquals(1, 1);
        } catch(KeyAlreadyInUseException e) {
            fail("Expected to create the playlist without problem");
        } catch(EntityNotFoundException e){
            fail("The user is loaded in the system");
        } catch (PrivacyException e){
            fail("The user channel was private to");
        }

        categoryController.createCategory("VideoCategory");
        // Create the video
        try {
            LocalDate inputDate = LocalDate.of(2018,9,1);
            videoController.createVideo("nicknameTEST1", "VideoTitle", 45, "video.com/videoTitle", "VideoDesc", inputDate, "VideoCategory");			
            assertEquals(1, 1);
        } catch(EntityNotFoundException e) {
            fail("Expected to create the video without problem");
        }
        // Add video to playlist
        try {
            playlistController.addVideoToPlaylist("particularPlaylistTEST2", "VideoTitle", "nicknameTEST1", "nicknameTEST2");
            assertEquals(1, 1);
        } catch (EntityNotFoundException e) {
            fail("Expected to add the video to de playlist succ");
        } catch(KeyAlreadyInUseException e){
            fail("Expected to add the video to de playlist succ");
        }
        // IMPORTANT ASSERTS
        List<PlaylistDT> playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST2");
        assertEquals(1, playlistFromUser.get(0).getVideos().size());
        assertEquals("VideoTitle", playlistFromUser.get(0).getVideos().get(0).getTitle());
        assertEquals("video.com/videoTitle", playlistFromUser.get(0).getVideos().get(0).getUrl());
        assertEquals("VideoDesc", playlistFromUser.get(0).getVideos().get(0).getDescription());
        assertEquals("VideoCategory", playlistFromUser.get(0).getVideos().get(0).getCategory().getName());

        //Try to reAdd the video to the playlist
        try {
            playlistController.addVideoToPlaylist("particularPlaylistTEST2", "VideoTitle", "nicknameTEST1", "nicknameTEST2");
            fail("The video already is on the playlist");
        } catch (EntityNotFoundException e) {
            fail("The user and the video are loaded in the system");
        } catch(KeyAlreadyInUseException e){
            assertEquals(1,1);
        }
    }

    @Test
    public void testListPlaylistByCategory() {
        userCollection.deleteUsers();
        videoCollection.deleteDefaultPlaylists();
        boolean user1 = userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.now(), null, "descriptionTEST1", "channelNameTEST1", true);
        boolean user2 = userController.createUser("nicknameTEST2", "nameTEST2", "lastNameTEST2", "emailTEST2", LocalDate.now(), null, "descriptionTEST2", "channelNameTEST2", true);
        assertTrue(user1);
        assertTrue(user2);
        
        // Load a new particularPlaylists
        try {
            playlistController.createParticularPlaylist("particularPlaylistTEST1", "nicknameTEST1", true);
            playlistController.createParticularPlaylist("particularPlaylistTEST2", "nicknameTEST2", true);    		
            assertEquals(1, 1);
        } catch(KeyAlreadyInUseException e) {
            fail("Expected to create the playlists without problem");
        } catch(EntityNotFoundException e){
            fail("Expected to create the playlists without problem");
        } catch (PrivacyException e){
            fail("Expected to create the playlists without problem");
        }

        categoryController.createCategory("VideoCategorySinVideo");
        categoryController.createCategory("VideoCategoryConVideo");
        // Create the video
        try {
            LocalDate inputDate = LocalDate.of(2018,9,1);
            videoController.createVideo("nicknameTEST1", "VideoTitle", 45, "video.com/videoTitle", "VideoDesc", inputDate, "VideoCategoryConVideo");
            assertEquals(1, 1);
        } catch(EntityNotFoundException e) {
            fail("Expected to create the videos without problem");
        }
        // Add video to playlist
        try {
            playlistController.addVideoToPlaylist("particularPlaylistTEST2", "VideoTitle", "nicknameTEST1", "nicknameTEST2");
            assertEquals(1, 1);
        } catch (EntityNotFoundException e) {
            fail("Expected to add the video to de playlist succ");
        } catch(KeyAlreadyInUseException e){
            fail("Expected to add the video to de playlist succ");
        }
        // List playlist from category with playlists
        List<PlaylistDT> playlistFromCategory = null;
        try{
            playlistFromCategory = playlistController.listPlaylistByCategory("VideoCategoryConVideo");
        } catch(EntityNotFoundException e){
            fail("Expected to list the playlists succ");
        }
        assertEquals(1, playlistFromCategory.size());
        assertEquals("particularPlaylistTEST2", playlistFromCategory.get(0).getName());

        // List playlist from category without playlists
        playlistFromCategory = null;
        try{
            playlistFromCategory = playlistController.listPlaylistByCategory("VideoCategorySinVideo");
        } catch(EntityNotFoundException e){
            fail("Expected to list the playlists succ");
        }
        assertEquals(0, playlistFromCategory.size());

        // NOW WE ARE GOING TO REMOVE THE VIDEO AND FROM THE PLAYLIST AND CHECK IF THE DATA IS UPDATED
        playlistController.removeFromPlaylist("nicknameTEST2", "particularPlaylistTEST2", "VideoTitle");
        // LIST AGAIN THE PLAYLIST FROM THIS CATEGORY AND CHECK ASSERTS
        try{
            playlistFromCategory = playlistController.listPlaylistByCategory("VideoCategoryConVideo");
        } catch(EntityNotFoundException e){
            fail("Expected to list the playlists succ");
        }
        assertEquals(0, playlistFromCategory.size());
    }

    @Test
    public void testModifyPlaylist() {
        userCollection.deleteUsers();
        videoCollection.deleteDefaultPlaylists();

        boolean user1 = userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.now(), null, "descriptionTEST1", "channelNameTEST1", false);
        assertTrue(user1);
        // Load a new particularPlaylist to User1
        try {
            playlistController.createParticularPlaylist("particularPlaylistTEST1", "nicknameTEST1", true);   		
            assertEquals(1, 1);
        } catch(KeyAlreadyInUseException | EntityNotFoundException | PrivacyException exception) {
            fail("Expected to create the playlists without problem");
        }

        List<PlaylistDT> playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST1");
        assertEquals(true, playlistFromUser.get(0).isPrivate());

        // Set isPrivate false
        try {
            playlistController.modifyPlaylist("nicknameTEST1", "particularPlaylistTEST1", false);
        } catch(EntityNotFoundException | DefaultPlaylistException | PrivacyException exception){
            fail(exception.getMessage());
        }
        // Check if it was modified correctly
        playlistFromUser = playlistController.listPlaylistByUser("nicknameTEST1");
        assertEquals(false, playlistFromUser.get(0).isPrivate());
    }
}
