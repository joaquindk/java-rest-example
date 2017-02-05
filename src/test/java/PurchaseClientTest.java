/**
 * Copyright Schantz A/S, all rights reserved
 */

import java.util.*;
import java.util.stream.*;

import api.external.*;
import api.internal.*;
import org.hamcrest.*;
import org.junit.*;

/**
 * Created by joaquinguillen on 21/01/2017.
 */
public class PurchaseClientTest {

    PurchasesClient client = new PurchasesClient();

    @Test
    public void testFetchUsers() {
        List<User> users = client.fetchUsers(10);

        Assert.assertNotNull(users);
        Assert.assertThat("users contains entries", users.size(), Matchers.greaterThan(0));
    }

    @Test
    public void testFetchPurchases() {
        List<User> users = client.fetchUsers(1);

        List<Purchase> purchases = client.fetchRecentPurchasesForUser(users.get(0).getUsername(), 5);
        Assert.assertNotNull(purchases);
    }

    @Test
    public void testFetchProductPurchaseList() {
        List<User> users = client.fetchUsers(1);

        List<Purchase> purchases = client.fetchRecentPurchasesForUser(users.get(0).getUsername(), 5);

        if(purchases.size() > 0) {
            List<Purchase> purchaseList = client.fetchProductPurchaseList(purchases.get(0).getProductId());
            Assert.assertNotNull(purchases);
        } else {
            Assert.fail("No purchases found for user");
        }
    }

    @Test
    public void testFetchProductInfoFromIds() {
        List<User> users = client.fetchUsers(1);

        List<Purchase> purchases = client.fetchRecentPurchasesForUser(users.get(0).getUsername(), 5);
        List<ProductInfo> productInfos = purchases.stream().map(Purchase::getProductId).distinct().map((id) -> client.fetchProductInfoFromId(id)).collect(Collectors.toList());

        Assert.assertNotNull(productInfos);
        Assert.assertThat("product information is found", productInfos.size(), Matchers.greaterThan(0));
    }

    @Test
    public void testUserExists() {
        Assert.assertFalse(client.userExists("BLABLA"));
        Assert.assertTrue(client.userExists("Jarret_Schumm"));
    }

    @Test
    public void testCreateRecentPurchasesForKnownUser() {
        List<Purchase> recentPurchases = client.fetchRecentPurchasesForUser("Jarret_Schumm", 5);

        List<RecentProductPurchase> recentProductPurchases = recentPurchases.stream()
                .map(Purchase::getProductId)
                .distinct()
                .map((prodId) -> client.fetchProductInfoFromId(prodId))
                .map(RecentProductPurchase::fromProductInfo)
                .peek((recentPurchase) -> recentPurchase.setRecent(
                        client.fetchProductPurchaseList(recentPurchase.getId())
                                .stream().
                                map((purchase -> purchase.getUsername()))
                                .distinct().collect(Collectors.toList())
                        )
                ).collect(Collectors.toList());

        Assert.assertNotNull(recentProductPurchases);
    }
}
