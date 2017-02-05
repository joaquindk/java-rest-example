/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.external;

import java.util.*;

/**
 * Created by joaquinguillen on 21/01/2017.
 */
public class UsersContainer {

    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersContainer users1 = (UsersContainer) o;

        return users != null ? users.equals(users1.users) : users1.users == null;

    }

    @Override
    public int hashCode() {
        return users != null ? users.hashCode() : 0;
    }
}