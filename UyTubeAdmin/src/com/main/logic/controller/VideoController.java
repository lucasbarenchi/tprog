package com.main.logic.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Objects;

import com.main.logic.collection.CategoryCollection;
import com.main.logic.collection.UserCollection;
import com.main.logic.collection.VideoCollection;
import com.main.logic.domain.Category;
import com.main.logic.domain.Channel;
import com.main.logic.domain.Comment;
import com.main.logic.domain.Rating;
import com.main.logic.domain.User;
import com.main.logic.domain.Video;
import com.main.logic.dts.CommentDT;
import com.main.logic.dts.VideoDT;
import com.main.logic.exception.EntityNotFoundException;
import com.main.logic.exception.PrivacyException;
import com.main.logic.interfaces.IVideoController;
import com.main.logic.utils.RatingEnum;

public class VideoController implements IVideoController {

    private static VideoController instance = null;

    public static VideoController getInstance() {
        if (instance == null) {
            instance = new VideoController();
        }
        return instance;
    }

    private VideoController() {
    }

    private VideoCollection videos = VideoCollection.getInstance();
    private UserCollection users = UserCollection.getInstance();
    private CategoryCollection categories = CategoryCollection.getInstance();

    @Override
    public VideoDT getVideo(Long videoId) throws EntityNotFoundException {
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }
        return video.getAdvancedDT();
    }

    @Override
    public List<VideoDT> listAllVideos() {
        Map<Long, Video> allVideos = videos.getVideos();
        return allVideos.entrySet().stream().map(entry -> entry.getValue().getBasicDT()).collect(Collectors.toList());
    }

    @Override
    public List<VideoDT> listVideosByUser(String nickname) throws EntityNotFoundException {
        User user = users.getUserByNickname(nickname);
        List<Video> videosFromUser = user.getChannel().getVideos();
        return videosFromUser.stream().map(Video::getBasicDT).collect(Collectors.toList());
    }

    @Override
    public List<VideoDT> listVideosNotFromUser(String nickname) {
        String newNickname = nickname != null ? nickname : ""; 
        List<VideoDT> actualVideos = users.getUsers().entrySet().stream()
                .filter(user -> !newNickname.equals(user.getKey()))
                .map(user -> user.getValue().getChannel().getVideos())
                .flatMap(Collection::stream)
                .filter(video -> !video.isPrivate())
                .map(Video::getBasicDT)
                .collect(Collectors.toList());
        return actualVideos;
    }

    @Override
    public List<VideoDT> listVideosByCategory(String category) {

        return videos.getVideos()
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(Objects::nonNull)
                .filter(video -> video.getCategory().getName().equals(category))
                .map(Video::getAdvancedDT)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDT createVideo(String owner, String title, int length, String url, String description, LocalDate uploadDate) throws EntityNotFoundException {
        User user = users.getUserByNickname(owner);
        Channel channel = user.getChannel();

        Video video = new Video();
        video.setTitle(title);
        video.setLength(length);
        video.setUrl(url);
        video.setDescription(description);
        video.setUploadDate(uploadDate);
        video.setPrivate(true);
        video.setPublisher(channel);

        channel.addVideo(video);
        videos.addVideo(video);

        return video.getBasicDT();
    }

    @Override
    public VideoDT createVideo(String owner, String title, int length, String url, String description, LocalDate uploadDate,
            String category) throws EntityNotFoundException {

        VideoDT videoDT = createVideo(owner, title, length, url, description, uploadDate);

        Video video = videos.getVideo(videoDT.getVideoId());
        Category categoryType = categories.getCategory(category);
        video.setCategory(categoryType);

        return video.getBasicDT();
    }

    @Override
    public List<CommentDT> listComments(Long videoId) throws EntityNotFoundException {
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }

        return video.getComments()
                .stream()
                .map(Comment::getDT)
                .collect(Collectors.toList());
    }

    @Override
    public void commentComment(String nickname, String text, LocalDateTime date, Long videoId, Long commentId) throws EntityNotFoundException, IndexOutOfBoundsException {
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }

        Comment comment = new Comment();
        comment.setCommenter(nickname);
        comment.setText(text);
        comment.setCommentedVideo(video);
        comment.setDate(date);

        Comment answered = video.getAllComments()
                .stream()
                .filter(cmt -> cmt.getCommentId().equals(commentId))
                .findFirst().orElseThrow(() -> new IndexOutOfBoundsException("El comentario al que quieres responder no existe"));

        answered.addResponse(comment);
        video.getAllComments().add(comment);

    }

    @Override
    public void commentVideo(String nickname, String text, LocalDateTime date, Long videoId) throws EntityNotFoundException {
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }

        Comment comment = new Comment();
        comment.setCommenter(nickname);
        comment.setText(text);
        comment.setCommentedVideo(video);
        comment.setDate(date);
        video.getComments().add(comment);
        video.getAllComments().add(comment);
    }

    @Override
    public void modifyVideo(VideoDT updatedVideo, Long videoId) throws EntityNotFoundException, PrivacyException {
        VideoCollection videos = VideoCollection.getInstance();
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }

        if (video.getPublisher().isPrivate() && !updatedVideo.isPrivate()) {
            throw new PrivacyException("No se puede tener un video publico en un canal privado.");
        }

        video.setPrivate(updatedVideo.isPrivate());
        video.setDescription(updatedVideo.getDescription());
        video.setTitle(updatedVideo.getTitle());
        video.setUrl(updatedVideo.getUrl());
        video.setLength(updatedVideo.getLength());
        video.setUploadDate(updatedVideo.getUploadDate());

        if (updatedVideo.getCategory() != null) {
            Category newCategory = categories.getCategory(updatedVideo.getCategory().getName());
            video.setCategory(newCategory);
        } else {
            video.setCategory(null);
        }
    }

    @Override
    public void rateVideo(Long videoId, String nickname, RatingEnum rating) throws EntityNotFoundException {
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }

        User user = users.getUserByNickname(nickname);
        if (user == null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        Rating ratingFinal = new Rating();
        ratingFinal.setRated(video);
        ratingFinal.setRater(user);
        ratingFinal.setRatingType(rating);

        user.addRating(videoId, ratingFinal);
        video.addRating(nickname, ratingFinal);
    }

    @Override
    public void deleteRaiting(Long videoId, String nickname) throws EntityNotFoundException {
        Video video = videos.getVideo(videoId);
        if (video == null) {
            throw new EntityNotFoundException("Video no encontrado.");
        }

        User user = users.getUserByNickname(nickname);
        if (user == null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        video.removeRaiting(nickname);
        user.removeRaiting(videoId);
    }

}
