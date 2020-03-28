package com.main.logic.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.main.logic.domain.User;
import com.main.logic.dts.UserDT;


public class UserDAO extends AbstractDAO<User, String> {
    public UserDAO() {
        super(User.class);
    }
    
    public static List<UserDT> getDeletedUsersNicknames() {
        List<User> users = AbstractDAO.getEntityManager().createQuery("FROM User", User.class).getResultList();
        return users.stream().map(user -> user.getDeletedUserDT()).collect(Collectors.toList());
    }
    
    public static UserDT getDeletedUserInfo(String nickname, Long id) {
        User user = AbstractDAO.getEntityManager().createQuery("FROM User u WHERE u.nickname = :nickname AND u.id = :id", User.class)
                .setParameter("nickname", nickname)
                .setParameter("id", id)
                .getSingleResult();
        return user.getDeletedUserDT();
    }
}
