package com.main.logic.domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.logic.dts.UserDT;

@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="nickname")
    private String nickname;
    
    @Column(name="password")
    private String password;
    
    @Column(name="name")
    private String name;
    
    @Column(name="surname")
    private String surname;
    
    @Column(name="mail")
    private String mail;
    
    @Lob
    @Column(name="avatar", length=10485760)
    private byte[] avatar;

    @Column(name="birthdate")
    private LocalDate birthdate;
    
    @Column(name="deleted_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Calendar deletedDate;
    
    @Transient
    private Channel channel;

    @Transient
    private Map<Long, Rating> ratings;
    
    @Transient
    private Map<String, User> followed;
    
    @Transient
    private Map<String, User> followers;
    
    @Transient
    private Map<Long, History> history;

    public User() {
        ratings = new HashMap<>();
        followed = new HashMap<>();
        followers = new HashMap<>();
        history = new HashMap<>();
    }

    public User(String nick, String nombre, String apellido, String email, LocalDate date){
        nickname = nick;
        name = nombre;
        surname = apellido;
        mail = email;
        ratings = new HashMap<>();
        followed = new HashMap<>();
        followers = new HashMap<>();

        history = new HashMap<>();
        birthdate = date;
    }

    public UserDT getBasicDT() {
        UserDT userDT = new UserDT();
        userDT.setNickname(nickname);
        userDT.setName(name);
        userDT.setSurname(surname);
        userDT.setBirthdate(birthdate);
        userDT.setMail(mail);
        userDT.setAvatar(avatar);
        userDT.setChannel(channel.getDT());

        List<String> followedList= followed.values().stream().map(val -> val.getNickname()).collect(Collectors.toList());
        userDT.setFollowed(followedList);

        List<String> followersList= followers.values().stream().map(val -> val.getNickname()).collect(Collectors.toList());
        userDT.setFollowers(followersList);

        return userDT;
    }

    public UserDT getDeletedUserDT() {
        UserDT userDT = new UserDT();
        userDT.setId(id);
        userDT.setNickname(nickname);
        userDT.setName(name);
        userDT.setSurname(surname);
        userDT.setBirthdate(birthdate);
        userDT.setMail(mail);
        userDT.setAvatar(avatar);

        return userDT;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Map<Long, Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Long, Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Long videoId, Rating rating) {
        ratings.put(videoId, rating);
    }

    public void removeRaiting(Long videoId) {
        ratings.remove(videoId);
    }

    public Map<String, User> getFollowed() {
        return followed;
    }

    public void setFollowed(Map<String, User> followed) {
        this.followed = followed;
    }

    public Map<String, User> getFollowers() {
        return followers;
    }

    public void setFollowers(Map<String, User> followers) {
        this.followers = followers;
    }

    public void addFollower(User follower) {
        this.followers.put(follower.getNickname(), follower);
    }

    public void removeFollower(String nickname) {
        this.followers.remove(nickname);
    }

    public void addFollowed(User followed) {
        this.followed.put(followed.getNickname(), followed);
    }

    public void removeFollowed(String nickname) {
        this.followed.remove(nickname);
    }
    
    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addNewVisit(Long videoId) {
        History hUser;
        if (history.containsKey(videoId)) {
            hUser = history.get(videoId);
            hUser.newVisit();
        } else {
            hUser = new History(videoId);
            history.put(videoId, hUser);
        }
    }
    
    public List<History> getHistory() {
        List<History> list = history.values()
                .stream()
                .sorted(Comparator.comparing(History::getVisitCount).reversed())
                .collect(Collectors.toList());
                
        return list;
    }
}
