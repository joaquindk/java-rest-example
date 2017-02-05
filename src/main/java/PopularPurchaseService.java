/**
 * Copyright Schantz A/S, all rights reserved
 */

import static spark.Spark.*;

import java.util.*;
import java.util.stream.*;

import api.external.*;
import api.internal.*;
import com.google.gson.*;
import org.apache.commons.jcs.*;
import org.apache.commons.jcs.access.*;
import org.apache.log4j.*;

/**
 * Created by joaquinguillen on 21/01/2017.
 */
public class PopularPurchaseService {

    public static void main(String[] args) {
        //Logging configuration
        BasicConfigurator.configure();
        LogManager.getRootLogger().setLevel(Level.INFO);
        Logger logger = Logger.getLogger(PopularPurchaseService.class);

        //REST API Client
        PurchasesClient apiClient = new PurchasesClient();

        //Serializer
        Gson gson = new Gson();

        //Service cache
        CacheAccess<String, Object> cache = JCS.getInstance("default");


        get("/api/recent_purchases/:username", (req, res) -> {
            res.type("application/json");

            String username = req.params(":username");
            if(cache.get(username) != null) {
                logger.info("Cached result ");
                return cache.get(username);
            } else {
                logger.info("Cached failure - accessing REST API ");
                if(!apiClient.userExists(username)) {
                    String userNotFound = "User with username of " + username + " was not found";
                    cache.put(username, userNotFound);
                    return userNotFound;
                } else {
                    List<Purchase> recentPurchases = apiClient.fetchRecentPurchasesForUser(username, 5);
                    List<RecentProductPurchase> recentProductPurchases = recentPurchases.stream()
                            .map(Purchase::getProductId)
                            .distinct()
                            .map((prodId) -> apiClient.fetchProductInfoFromId(prodId))
                            .map(RecentProductPurchase::fromProductInfo)
                            .peek((recentPurchase) -> recentPurchase.setRecent(
                                    apiClient.fetchProductPurchaseList(recentPurchase.getId())
                                            .stream().
                                            map((purchase -> purchase.getUsername()))
                                            .distinct().collect(Collectors.toList())
                                    )
                            ).collect(Collectors.toList());
                    cache.put(username, recentProductPurchases);
                    return recentProductPurchases;

                }
            }
        }, gson::toJson);

    }
}
