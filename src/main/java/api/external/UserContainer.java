/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.external;

import java.io.*;

/**
 * Created by joaquinguillen on 22/01/2017.
 */
public class UserContainer implements Serializable{

    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
