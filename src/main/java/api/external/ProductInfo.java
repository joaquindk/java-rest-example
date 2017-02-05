/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.external;

import java.io.*;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by joaquinguillen on 22/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo implements Serializable{
    int id;
    String face;
    int size;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


