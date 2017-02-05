/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.external;

import java.io.*;

/**
 * Created by joaquinguillen on 22/01/2017.
 */
public class ProductContainer implements Serializable{

    ProductInfo product;

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }
}
