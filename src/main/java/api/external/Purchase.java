/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.external;

import java.io.*;
import java.util.*;

/**
 * Created by joaquinguillen on 21/01/2017.
 */
public class Purchase implements Serializable{

    int id;
    int productId;
    String username;
    Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
