/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.internal;

import java.io.*;
import java.util.*;

import api.external.*;

/**
 * Created by joaquinguillen on 22/01/2017.
 */
public class RecentProductPurchase implements Serializable {

    int id;
    String face;
    int size;
    int price;
    List<String> recent;

    public RecentProductPurchase() {
    }

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

    public List<String> getRecent() {
        return recent;
    }

    public void setRecent(List<String> recent) {
        this.recent = recent;
    }

    public static RecentProductPurchase fromProductInfo(ProductInfo info) {
        RecentProductPurchase p = new RecentProductPurchase();
        p.id = info.getId();
        p.face = info.getFace();
        p.size = info.getSize();
        p.price = info.getPrice();
        return p;
    }
}
