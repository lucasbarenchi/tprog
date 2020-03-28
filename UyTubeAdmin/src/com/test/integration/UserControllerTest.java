package com.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.main.logic.collection.UserCollection;
import com.main.logic.collection.VideoCollection;
import com.main.logic.controller.ControllerFactory;
import com.main.logic.domain.Channel;
import com.main.logic.domain.Particular;
import com.main.logic.domain.Playlist;
import com.main.logic.domain.User;
import com.main.logic.domain.Video;
import com.main.logic.dts.ChannelDT;
import com.main.logic.dts.PlaylistDT;
import com.main.logic.dts.UserDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.KeyAlreadyInUseException;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;

public class UserControllerTest {

    private static UserCollection userCollection;
    private static IUserController userController;
    private static IPlaylistController playlistController;
    private static VideoCollection videoCollection;


    @Before
    public void init() {
        userController = ControllerFactory.getInstance().getUserController();
        userCollection = UserCollection.getInstance();
        playlistController = ControllerFactory.getInstance().getPlaylistController();
        videoCollection = VideoCollection.getInstance();
    }

    @Test
    public void testCreateUser() throws EntityNotFoundException {

        boolean userWithNoAvatar = userController.createUser("nicknameTEST", "nameTEST", "lastNameTEST", "emailTEST", LocalDate.now(), null, "descriptionTEST", "channelNameTEST", true);
        assertTrue(userWithNoAvatar);

        User user = userCollection.getUserByNickname("nicknameTEST");
        assertEquals(user.getNickname(), "nicknameTEST");
        assertEquals(user.getName(), "nameTEST");
        assertEquals(user.getSurname(), "lastNameTEST");
        assertEquals(user.getMail(), "emailTEST");
        assertEquals(user.getBirthdate(), LocalDate.now());
        assertNull(user.getAvatar());
        assertEquals(user.getChannel().getName(), "channelNameTEST");
        assertEquals(user.getChannel().getOwner(), "nicknameTEST");
        assertEquals(user.getChannel().getDescription(), "descriptionTEST");


        boolean userWithSameNickname = userController.createUser("nicknameTEST", "nameTEST", "lastNameTEST", "differentEmailTEST", LocalDate.now(), null, "descriptionTEST", "channelNameTEST", true);
        boolean userWithSameEmail = userController.createUser("differentNicknameTEST", "nameTEST", "lastNameTEST", "emailTEST", LocalDate.now(), null, "descriptionTEST", "channelNameTEST", true);

        assertFalse(userWithSameNickname);
        assertFalse(userWithSameEmail);
    }

    @Test
    public void testListUsers() {
        userCollection.deleteUsers();
        boolean userWithNoAvatar = userController.createUser("nicknameTEST", "nameTEST", "lastNameTEST", "emailTEST", LocalDate.now(), null, "descriptionTEST", "channelNameTEST", true);
        assertTrue(userWithNoAvatar);

        List<UserDT> users = userController.listUsers();

        assertEquals(1, users.size());
        assertEquals(users.get(0).getNickname(), "nicknameTEST");
        assertEquals(users.get(0).getName(), "nameTEST");
        assertEquals(users.get(0).getSurname(), "lastNameTEST");
        assertEquals(users.get(0).getMail(), "emailTEST");
        assertEquals(users.get(0).getBirthdate(), LocalDate.now());
        assertNull(users.get(0).getAvatar());
        assertEquals(users.get(0).getChannel().getName(), "channelNameTEST");
        assertEquals(users.get(0).getChannel().getOwner(), "nicknameTEST");
        assertEquals(users.get(0).getChannel().getDescription(), "descriptionTEST");

    }

    @Test(expected = EntityNotFoundException.class)
    public void testListUserPlaylistsException() throws EntityNotFoundException{
        userCollection.deleteUsers();
        userController.listUserPlaylist("nicknameTEST");

    }

    @Test
    public void testListUserPlaylists() throws EntityNotFoundException, KeyAlreadyInUseException{
        userCollection.deleteUsers();
        videoCollection.deleteDefaultPlaylists();

        userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.now(), null, "descriptionTEST1", "channelNameTEST1", true);
        assertEquals(userController.listUserPlaylist("nicknameTEST1").size(), 0);

        userController.getUserData("nicknameTEST1");
        playlistController.createDefaultPlaylist("DefaultPlaylistTEST1");
        List<PlaylistDT> userPlaylists = userController.listUserPlaylist("nicknameTEST1");
        assertEquals(userPlaylists.size(), 1);

    }

    @Test
    public void testFollowAndUnfollow() throws EntityNotFoundException {
        userCollection.deleteUsers();
        userController.createUser("seguidoNickname", "seguidoName", "seguidoLastName", "seguidoEmail", LocalDate.now(), null, "seguidoDescription", "seguidoChannelName", true);
        userController.createUser("seguidorNickname", "seguidorName", "seguidorLastName", "seguidorEmail", LocalDate.now(), null, "seguidorDescription", "seguidorChannelName", true);

        assertEquals(0, userController.getUserData("seguidorNickname").getFollowed().size());
        assertEquals(0, userController.getUserData("seguidoNickname").getFollowers().size());

        userController.createFollowRelationship("seguidorNickname", "seguidoNickname");

        assertEquals(1, userController.getUserData("seguidorNickname").getFollowed().size());
        assertEquals(1, userController.getUserData("seguidoNickname").getFollowers().size());

        userController.unfollowUser("seguidorNickname", "seguidoNickname");

        assertEquals(0, userController.getUserData("seguidorNickname").getFollowed().size());
        assertEquals(0, userController.getUserData("seguidoNickname").getFollowers().size());

    }

    @Test
    public void testModifyUserSuccess() throws EntityNotFoundException {
        userCollection.deleteUsers();
        userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.of(2014, 4, 2), null, "descriptionTEST1", "channelNameTEST1", false);

        UserDT userDT = new UserDT();
        userDT.setNickname("nicknameTEST1");
        userDT.setName("NAME_MODIFICADO");
        userDT.setSurname("SURNAME_MODIFICADO");
        userDT.setBirthdate(LocalDate.of(1111, 1, 1));

        ChannelDT channelDT = new ChannelDT();
        channelDT.setName("CHANNEL_NAME_MODIFICADO");
        channelDT.setDescription("CHANNEL_DESC_MODIFICADO");
        channelDT.setPrivateChannel(false);

        userDT.setChannel(channelDT);

        userController.modifyUser(userDT);
        User modifiedUser = userCollection.getUserByNickname("nicknameTEST1");

        assertEquals("nicknameTEST1", modifiedUser.getNickname());
        assertEquals("NAME_MODIFICADO", modifiedUser.getName());
        assertEquals("SURNAME_MODIFICADO", modifiedUser.getSurname());
        assertEquals(LocalDate.of(1111, 1, 1), modifiedUser.getBirthdate());
        assertEquals("CHANNEL_NAME_MODIFICADO", modifiedUser.getChannel().getName());
        assertEquals("CHANNEL_DESC_MODIFICADO", modifiedUser.getChannel().getDescription());
        assertEquals(false, modifiedUser.getChannel().isPrivate());
    }

    @Test
    public void testModifyUserSuccessSetPrivate() throws EntityNotFoundException {
        userCollection.deleteUsers();
        userController.createUser("nicknameTEST1", "nameTEST1", "lastNameTEST1", "emailTEST1", LocalDate.of(2014, 4, 2), null, "descriptionTEST1", "channelNameTEST1", false);
        User user = userCollection.getUserByNickname("nicknameTEST1");

        Playlist playlist1 = new Particular("pl1", false);
        Playlist playlist2 = new Particular("pl2", false);

        Video video1 = new Video();
        Video video2 = new Video();

        video1.setPrivate(false);
        video2.setPrivate(false);

        user.getChannel().getPlaylists().add(playlist1);
        user.getChannel().getPlaylists().add(playlist2);

        user.getChannel().getVideos().add(video1);
        user.getChannel().getVideos().add(video2);

        UserDT userDT = new UserDT();
        userDT.setNickname("nicknameTEST1");
        userDT.setName("NAME_MODIFICADO");
        userDT.setSurname("SURNAME_MODIFICADO");
        userDT.setBirthdate(LocalDate.of(1111, 1, 1));

        ChannelDT channelDT = new ChannelDT();
        channelDT.setName("CHANNEL_NAME_MODIFICADO");
        channelDT.setDescription("CHANNEL_DESC_MODIFICADO");
        channelDT.setPrivateChannel(true);

        userDT.setChannel(channelDT);

        userController.modifyUser(userDT);
        User modifiedUser = userCollection.getUserByNickname("nicknameTEST1");

        assertEquals("nicknameTEST1", modifiedUser.getNickname());
        assertEquals("NAME_MODIFICADO", modifiedUser.getName());
        assertEquals("SURNAME_MODIFICADO", modifiedUser.getSurname());
        assertEquals(LocalDate.of(1111, 1, 1), modifiedUser.getBirthdate());

        Channel modifiedChannel = modifiedUser.getChannel();
        assertEquals("CHANNEL_NAME_MODIFICADO", modifiedChannel.getName());
        assertEquals("CHANNEL_DESC_MODIFICADO", modifiedChannel.getDescription());
        assertTrue(modifiedChannel.isPrivate());

        assertTrue(modifiedChannel.getPlaylists().get(0).isPrivate());
        assertTrue(modifiedChannel.getPlaylists().get(1).isPrivate());

        assertTrue(modifiedChannel.getVideos().get(0).isPrivate());
        assertTrue(modifiedChannel.getVideos().get(1).isPrivate());
    }
}
