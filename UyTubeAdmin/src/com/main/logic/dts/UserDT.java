package com.main.logic.dts;

import java.time.LocalDate;
import java.util.List;

public class UserDT {
    private Long id;
    private String nickname;
    private String name;
    private String surname;
    private String mail;
    private LocalDate birthdate;
    private ChannelDT channel;
    private byte[] avatar;
    private List<String> followed;
    private List<String> followers;

    public UserDT() {}

    public UserDT(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nick) {
        this.nickname = nick;
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

    public ChannelDT getChannel() {
        return channel;
    }

    public void setChannel(ChannelDT channel) {
        this.channel = channel;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<String> getFollowed() {
        return followed;
    }

    public void setFollowed(List<String> followed) {
        this.followed = followed;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        if (nickname == null){
            return "-";
        }
        return nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
