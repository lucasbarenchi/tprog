package com.main.logic.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.logic.dts.PlaylistDT;

@Entity
@Table(name="playlist")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, name = "default", columnDefinition = "BOOLEAN(1)")
public abstract class Playlist {
    
    @Transient
    private static Long generatedId = -1L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;
    
    @Column(name="private")
    private boolean isPrivate;
    
    @Column(name="name")
    private String name;

    @ManyToOne()
    @JoinTable(name="channel_playlist", 
        joinColumns=@JoinColumn(name="id_playlist", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="id_channel", referencedColumnName="id"))
    private Channel owner;
    
    @ManyToMany()
    @JoinTable(name="playlist_video", 
        joinColumns=@JoinColumn(name="id_playlist", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="id_video", referencedColumnName="id"))
    private List<Video> videos;
    
    @Transient
    private Long playlistId;
    
    @Transient
    private Set<Category> categories;

    public Playlist() {
        this.playlistId = generatedId;
        generatedId++;
        videos = new ArrayList<>();
        categories = new HashSet<>();
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Channel getOwner() {
        return owner;
    }

    public void setOwner(Channel owner) {
        this.owner = owner;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public boolean addVideo(Video video) {
        return videos.add(video);
    }

    public boolean removeVideo(String name) {
        Video quitado = videos.stream().filter(vid -> name.equals(vid.getTitle())).findFirst()
                .orElse(null);

        boolean removed = videos.remove(quitado);
        if (this instanceof Particular) {
            Set<Category> newCategories = videos.stream().map(Video::getCategory)
                    .collect(Collectors.toSet());
            categories = newCategories;
        }

        return removed;
    }

    public boolean removeVideo(Long videoId) {
        Video quitado = videos.stream().filter(vid -> videoId == vid.getVideoId()).findFirst().orElse(null);

        boolean removed = videos.remove(quitado);
        if (this instanceof Particular) {
            Set<Category> newCategories = videos.stream().map(Video::getCategory)
                    .collect(Collectors.toSet());
            categories = newCategories;
        }

        return removed;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public abstract PlaylistDT getDT();
    
    public abstract PlaylistDT getDeletedPlaylistDT();

}
