/**
 * Copyright Schantz A/S, all rights reserved
 */
package api.external;

import java.util.*;

/**
 * Created by joaquinguillen on 21/01/2017.
 */
public class PurchaseContainer {

    List<Purchase> purchases;

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
