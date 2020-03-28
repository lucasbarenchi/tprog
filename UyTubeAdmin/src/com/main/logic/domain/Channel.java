package com.main.logic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.logic.collection.VideoCollection;
import com.main.logic.dts.ChannelDT;

@Entity
@Table(name="channel")
public class Channel {
    
    @Transient
    private static Long generatedId = 0L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name="private")
    private boolean privateChannel;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName="id", name="owner")
    private User user;
    
    @OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
    private List<Playlist> playlists;
    
    @OneToMany(mappedBy="publisher", cascade=CascadeType.ALL)
    private List<Video> videos;
    
    @Transient
    private Long channelId;
    
    @Transient
    private Category category;
    
    public Channel() {
        
    }
    
    public Channel(User ownerUser) {
        channelId = generatedId;
        generatedId++;
        videos = new ArrayList<>();
        playlists = new ArrayList<>();
        category = null;
        user = ownerUser;
        
        VideoCollection videoCollection = VideoCollection.getInstance();
        List<String> toAddPlaylists = videoCollection.getDefaultPlaylists();
        // Add every default playlist from the system
        toAddPlaylists.forEach(toAdd -> {
            Playlist dflt = new Default(toAdd);
            dflt.setOwner(this);
            playlists.add(dflt);

        });
    }

    public Channel(String channelName, String descripcion, boolean isPublic, User ownerUser){
        channelId = generatedId;
        generatedId++;
        videos = new ArrayList<>();
        playlists = new ArrayList<>();
        name = channelName;
        description = descripcion;
        privateChannel = !isPublic;
        category = null;
        user = ownerUser;

        VideoCollection videoCollection = VideoCollection.getInstance();
        List<String> toAddPlaylists = videoCollection.getDefaultPlaylists();
        // Add every default playlist from the system
        toAddPlaylists.forEach(toAdd -> playlists.add(new Default(toAdd)));
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ChannelDT getDT() {
        ChannelDT channelDT = new ChannelDT();
        channelDT.setChannelId(channelId);
        channelDT.setName(name);
        channelDT.setOwner(user.getNickname());
        channelDT.setVideos(videos
                .stream()
                .map(Video::getAdvancedDT)
                .collect(Collectors.toList()));
        channelDT.setDescription(description);
        channelDT.setPrivateChannel(privateChannel);
        channelDT.setPlaylists(playlists.stream()
                .map(Playlist::getName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        channelDT.setCategory(category != null ? category.getDT() : null);
        return channelDT;
    }
    
    public ChannelDT getDeletedChannelDT() {
        ChannelDT channelDT = new ChannelDT();
        channelDT.setChannelId(id);
        channelDT.setName(name);
        channelDT.setOwner(user.getNickname());
        channelDT.setDescription(description);
        channelDT.setPrivateChannel(privateChannel);
        return channelDT;
    }

    public boolean isPrivate() {
        return privateChannel;
    }

    public void setPrivate(boolean isPrivate) {
        this.privateChannel = isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public boolean hasPlaylist(String name) {
        return playlists.stream().anyMatch(playlist -> playlist.getName().equals(name));
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOwner() {
        return user.getNickname();
    }
}
