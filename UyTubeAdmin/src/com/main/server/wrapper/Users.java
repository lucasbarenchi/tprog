package com.main.server.wrapper;

import java.util.List;
import com.main.logic.dts.UserDT;

public class Users {
    private List<UserDT> users;

    public Users() {}
    
    public Users(List<UserDT> users) {
        this.users = users;
    }
    
    public List<UserDT> getUsers() {
        return users;
    }

    public void setUsers(List<UserDT> users) {
        this.users = users;
    }
    
    
}
