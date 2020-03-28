package com.main.logic.collection;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.HashMap;
import java.util.Map;

import com.main.logic.domain.User;
import com.main.logic.exception.EntityNotFoundException;

public class UserCollection {

    private Map<String, User> users;
    
    private String userRemembered = null;

    private static UserCollection instance = new UserCollection();

    public static UserCollection getInstance() {
        if (instance == null) {
            instance = new UserCollection();
        }
        return instance;
    }

    private UserCollection() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getNickname(), user);
    }

    public User removeUser(User user) {
        return users.remove(user.getNickname());
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public User getUserByNickname(String nickname) throws EntityNotFoundException {
        if (users.get(nickname) == null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        return users.get(nickname);
    }

    public User getUserByEmail(String email) throws EntityNotFoundException {
        User user = users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(usr -> usr.getMail().equals(email))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return user;
    }

    public User getUserByNicknameOrEmail(String nickmail) throws EntityNotFoundException {
        if (!users.containsKey(nickmail)) {
            return getUserByEmail(nickmail);
        } else {
            return users.get(nickmail);
        }
    }

    public Map<String, User> getUsers() { 
        return users; 
    }

    public void deleteUsers() {
        users = new HashMap<>();
    }

    public String getUserRemembered() {
        return userRemembered;
    }

    public void setUserRemembered(String userRemembered) {
        this.userRemembered = userRemembered;
    };
}
