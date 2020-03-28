package com.test.integration;

import java.time.LocalDate;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.main.logic.collection.CategoryCollection;
import com.main.logic.collection.UserCollection;
import com.main.logic.collection.VideoCollection;
import com.main.logic.controller.VideoController;
import com.main.logic.domain.Category;
import com.main.logic.domain.Channel;
import com.main.logic.domain.Comment;
import com.main.logic.domain.Rating;
import com.main.logic.domain.User;
import com.main.logic.domain.Video;
import com.main.logic.dts.CommentDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.utils.RatingEnum;

public class VideoControllerTest {

    private Video createTestVideo(Category category) {
        Video video = new Video();
        video.setTitle("TestVideo");
        video.setLength(123);
        video.setUrl("test.com");
        video.setDescription("test");
        video.setUploadDate(LocalDate.of(2018, 2, 1));
        video.setPrivate(true);
        video.setCategory(category);
        
        Channel channel = new Channel(createTestUser());
        channel.setName("TEST CHANNEL");
        channel.setDescription("Test Description");
        channel.setPrivate(true);
        
        video.setPublisher(channel);
        
        return video;
    }

    private User createTestUser() {
        User user = new User();
        user.setNickname("test123");
        user.setName("TEST");
        user.setSurname("TEST");
        user.setBirthdate(LocalDate.of(2018, 3, 12));

        Channel channel = new Channel(user);
        channel.setName("TEST CHANNEL");
        channel.setDescription("Test Description");
        channel.setPrivate(true);

        user.setChannel(channel);
        return user;
    }

    @Test
    public void testListVideosByUser() throws EntityNotFoundException {
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        Video video = createTestVideo(null);
        user.getChannel().addVideo(video);

        VideoController videoController = VideoController.getInstance();
        List<VideoDT> videos = videoController.listVideosByUser(user.getNickname());

        VideoDT res = videos.get(0);
        Assert.assertEquals(res.getTitle(), video.getTitle());
        Assert.assertEquals(res.getDescription(), video.getDescription());
        Assert.assertEquals(res.getLength(), video.getLength());
        Assert.assertEquals(1, videos.size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testListVideosByUserFail() throws EntityNotFoundException {
        VideoController videoController = VideoController.getInstance();
        videoController.listVideosByUser("testito");
    }

    @Test
    public void testListVideosByCategory() {
        Category cat = new Category("Juegos");
        Category cat2 = new Category("Ver más tarde");

        Video video = createTestVideo(cat);
        Video video2 = createTestVideo(cat2);
        Video video3 = createTestVideo(cat);

        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);
        videoCollection.addVideo(video2);
        videoCollection.addVideo(video3);

        User user = createTestUser();
        user.getChannel().addVideo(video);
        user.getChannel().addVideo(video2);
        user.getChannel().addVideo(video3);

        video.setPublisher(user.getChannel());
        video2.setPublisher(user.getChannel());
        video3.setPublisher(user.getChannel());

        VideoController videoController = VideoController.getInstance();
        List<VideoDT> videos = videoController.listVideosByCategory(cat.getName());

        VideoDT vid1 = videos.get(0);
        VideoDT vid2 = videos.get(1);

        Assert.assertEquals(2, videos.size());
        Assert.assertEquals(vid1.getTitle(), video.getTitle());
        Assert.assertEquals(vid1.getDescription(), video.getDescription());
        Assert.assertEquals(vid1.getLength(), video.getLength());

        Assert.assertEquals(vid2.getTitle(), video.getTitle());
        Assert.assertEquals(vid2.getDescription(), video.getDescription());
        Assert.assertEquals(vid2.getLength(), video.getLength());
    }

    @Test
    public void testCreateVideo() throws EntityNotFoundException {
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        VideoController videoController = VideoController.getInstance();
        VideoDT video = videoController.createVideo("test123", "Title Test", 123, "test.com", "Mejor Test", LocalDate.of(2017, 12, 23));

        Assert.assertEquals("Title Test", video.getTitle());
        Assert.assertEquals(123, video.getLength());
        Assert.assertEquals("test.com", video.getUrl());
        Assert.assertEquals("Mejor Test", video.getDescription());
        Assert.assertEquals(LocalDate.of(2017, 12, 23), video.getUploadDate());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCreateVideoFail() throws EntityNotFoundException {
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();

        VideoController videoController = VideoController.getInstance();
        videoController.createVideo("test123", "Title Test", 123, "test.com", "Mejor Test", LocalDate.of(2017, 12, 23));
    }

    @Test
    public void testCreateVideoWithCategory() throws EntityNotFoundException {
        Category category = new Category("juegos");
        CategoryCollection categoryCollection = CategoryCollection.getInstance();
        categoryCollection.addCategory(category);

        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        VideoController videoController = VideoController.getInstance();
        VideoDT video = videoController.createVideo("test123", "Title Test", 123, "test.com", "Mejor Test", LocalDate.of(2017, 12, 23), "juegos");

        Assert.assertEquals("Title Test", video.getTitle());
        Assert.assertEquals(123, video.getLength());
        Assert.assertEquals("test.com", video.getUrl());
        Assert.assertEquals("Mejor Test", video.getDescription());
        Assert.assertEquals(LocalDate.of(2017, 12, 23), video.getUploadDate());
    }

    @Test
    public void testListCommentsWithNoComments() throws EntityNotFoundException {
        Category cat = new Category("Juegos");
        Video video = createTestVideo(cat);

        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoController videoController = VideoController.getInstance();
        List<CommentDT> comments = videoController.listComments(video.getVideoId());

        Assert.assertEquals(comments.size(), 0);
    }

    @Test
    public void testListCommentsWithMultipleComments() throws EntityNotFoundException {
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        Category cat = new Category("Juegos");
        Video video = createTestVideo(cat);
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoController videoController = VideoController.getInstance();
        videoController.commentVideo("test123", "comentario!", LocalDate.of(2014, 3, 23).atTime(0, 0), video.getVideoId());
        List<CommentDT> comments = videoController.listComments(video.getVideoId());

        Assert.assertEquals(comments.size(), 1);
        CommentDT commentDT = comments.get(0);
        Assert.assertEquals(commentDT.getText(), "comentario!");
        Assert.assertEquals(commentDT.getResponses().size(), 0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testListCommentsVideoDontExist() throws EntityNotFoundException {
        VideoController videoController = VideoController.getInstance();
        videoController.listComments(new Long(2312));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCommentVideoDontExist() throws EntityNotFoundException {
        VideoController videoController = VideoController.getInstance();
        videoController.commentVideo("test_not_found", "", LocalDate.of(2032, 12, 12).atTime(0, 0), new Long(123));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCommentCommentVideoDontExist() throws EntityNotFoundException, IndexOutOfBoundsException {
        VideoController videoController = VideoController.getInstance();
        videoController.commentComment("test_not_found", "", LocalDate.of(2032, 12, 12).atTime(0, 0), new Long(123), new Long(4));
    }

    @Test
    public void testCommentComment() throws EntityNotFoundException, IndexOutOfBoundsException {
        VideoController videoController = VideoController.getInstance();
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        Category cat = new Category("Juegos");
        Video video = createTestVideo(cat);
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        videoController.commentVideo("test123", "comentario!", LocalDate.of(2014, 3, 23).atTime(0, 0), video.getVideoId());
        List<CommentDT> comments = videoController.listComments(video.getVideoId());

        videoController.commentComment(user.getNickname(), "Comentario de test", LocalDate.of(2018, 4, 4).atTime(0, 0), video.getVideoId(), comments.get(0).getCommentId());

        Assert.assertEquals(video.getComments().get(0).getResponses().size(), 1);
        Comment newComment = video.getComments().get(0).getResponses().get(0);
        Assert.assertEquals(newComment.getText(), "Comentario de test");
    }

    @Test
    public void testCommentCommentMultipleTimes() throws EntityNotFoundException, IndexOutOfBoundsException {
        VideoController videoController = VideoController.getInstance();
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        Category cat = new Category("Juegos");
        Video video = createTestVideo(cat);
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        videoController.commentVideo("test123", "comentario!", LocalDate.of(2014, 3, 23).atTime(0, 0), video.getVideoId());
        List<CommentDT> comments = videoController.listComments(video.getVideoId());

        videoController.commentComment(user.getNickname(), "Comentario de test", LocalDate.of(2018, 4, 4).atTime(0, 0), video.getVideoId(), comments.get(0).getCommentId());
        videoController.commentComment(user.getNickname(), "Otro comentario de test", LocalDate.of(2018, 4, 4).atTime(0, 0), video.getVideoId(), comments.get(0).getCommentId());
        videoController.commentComment(user.getNickname(), "Último comentario de test", LocalDate.of(2018, 4, 4).atTime(0, 0), video.getVideoId(), comments.get(0).getCommentId());

        Assert.assertEquals(video.getComments().get(0).getResponses().size(), 3);
        Comment newComment1 = video.getComments().get(0).getResponses().get(0);
        Comment newComment2 = video.getComments().get(0).getResponses().get(1);
        Comment newComment3 = video.getComments().get(0).getResponses().get(2);
        Assert.assertEquals("Comentario de test", newComment1.getText());
        Assert.assertEquals("Otro comentario de test", newComment2.getText());
        Assert.assertEquals("Último comentario de test", newComment3.getText());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testModifyVideoNotFound() throws Exception {
        VideoController videoController = VideoController.getInstance();
        videoController.modifyVideo(new VideoDT(), new Long(123));
    }

    @Test(expected = Exception.class)
    public void testModifyVideoPrivateWrong() throws Exception {
        User user = createTestUser();
        VideoController videoController = VideoController.getInstance();
        Video video = createTestVideo(new Category("Juegos"));
        video.setPrivate(true);
        video.setPublisher(user.getChannel());
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoDT videoDT = video.getBasicDT();
        videoDT.setPrivate(false);
        videoController.modifyVideo(videoDT, video.getVideoId());
    }

    @Test
    public void testModifyVideoSuccess() throws Exception {
        User user = createTestUser();

        VideoController videoController = VideoController.getInstance();
        Category cat = new Category("Juegos");
        CategoryCollection categoryCollection = CategoryCollection.getInstance();
        categoryCollection.addCategory(cat);
        Video video = createTestVideo(cat);
        video.setPrivate(true);
        video.setPublisher(user.getChannel());
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoDT videoDT = video.getBasicDT();
        videoDT.setLength(4444);
        videoDT.setTitle("MODIFICADO");
        videoDT.setUploadDate(LocalDate.of(1111, 1, 7));
        videoDT.setUrl("asdmodificado.com");
        videoDT.setDescription("DESCRIPTION MODIFICADO");

        videoController.modifyVideo(videoDT, video.getVideoId());

        Video vid = videoCollection.getVideo(video.getVideoId());
        Assert.assertEquals(4444, videoDT.getLength());
        Assert.assertEquals("MODIFICADO", vid.getTitle());
        Assert.assertEquals(LocalDate.of(1111, 1, 7), video.getUploadDate());
        Assert.assertEquals("asdmodificado.com", vid.getUrl());
        Assert.assertEquals("DESCRIPTION MODIFICADO", vid.getDescription());
    }

    @Test
    public void testModifyVideoSuccessNoCategory() throws Exception {
        User user = createTestUser();

        VideoController videoController = VideoController.getInstance();
        Category cat = new Category("Juegos");
        CategoryCollection categoryCollection = CategoryCollection.getInstance();
        categoryCollection.addCategory(cat);
        Video video = createTestVideo(cat);
        video.setPrivate(true);
        video.setPublisher(user.getChannel());
        video.setCategory(null);
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoDT videoDT = video.getBasicDT();
        videoDT.setLength(4444);
        videoDT.setTitle("MODIFICADO");
        videoDT.setUploadDate(LocalDate.of(1111, 1, 7));
        videoDT.setUrl("asdmodificado.com");
        videoDT.setDescription("DESCRIPTION MODIFICADO");

        videoController.modifyVideo(videoDT, video.getVideoId());

        Video vid = videoCollection.getVideo(video.getVideoId());
        Assert.assertEquals(4444, videoDT.getLength());
        Assert.assertEquals("MODIFICADO", vid.getTitle());
        Assert.assertEquals(LocalDate.of(1111, 1, 7), video.getUploadDate());
        Assert.assertEquals("asdmodificado.com", vid.getUrl());
        Assert.assertEquals("DESCRIPTION MODIFICADO", vid.getDescription());
    }

 

    @Test(expected = EntityNotFoundException.class)
    public void testRateVideoVideoNotFound() throws EntityNotFoundException {
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        Video video = createTestVideo(null);
        video.setPublisher(user.getChannel());
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();

        VideoController videoController = VideoController.getInstance();
        videoController.rateVideo(video.getVideoId(), user.getNickname(), RatingEnum.LIKE);
    }
    
    @Test
    public void testRateVideo() throws EntityNotFoundException {
        User user = createTestUser();
        
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();
        userCollection.addUser(user);

        Video video = createTestVideo(null);
        video.setPublisher(user.getChannel());
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoController videoController = VideoController.getInstance();
        videoController.rateVideo(video.getVideoId(), user.getNickname(), RatingEnum.LIKE);

        Assert.assertEquals(user.getRatings().size(), 1);
        Rating rating = user.getRatings().get(video.getVideoId());
        Assert.assertEquals(rating.getRated().getVideoId(), video.getVideoId());
        Assert.assertEquals(rating.getRater().getNickname(), user.getNickname());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testRateVideoUserNotFound() throws EntityNotFoundException {
        User user = createTestUser();
        UserCollection userCollection = UserCollection.getInstance();
        userCollection.deleteUsers();

        Video video = createTestVideo(null);
        video.setPublisher(user.getChannel());
        VideoCollection videoCollection = VideoCollection.getInstance();
        videoCollection.deleteVideos();
        videoCollection.addVideo(video);

        VideoController videoController = VideoController.getInstance();
        videoController.rateVideo(video.getVideoId(), user.getNickname(), RatingEnum.LIKE);
    }
}
