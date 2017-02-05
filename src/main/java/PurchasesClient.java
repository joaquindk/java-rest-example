/**
 * Copyright Schantz A/S, all rights reserved
 */

import java.io.*;
import java.util.*;

import api.external.*;
import com.fasterxml.jackson.core.*;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.*;

/**
 * Created by joaquinguillen on 21/01/2017.
 */
public class PurchasesClient {

    private final Properties appProperties;
    private final String purchasesBaseUrl;

    public PurchasesClient() {
        appProperties = LocalProperties.getInstance().getProps();
        purchasesBaseUrl = appProperties.getProperty(LocalProperties.PURCHASES_KEY);

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public List<User> fetchUsers(int limit) {
        try {
            HttpResponse<UsersContainer> userList = Unirest.get(purchasesBaseUrl + "/users")
                    .queryString("limit", limit)
                    .asObject(UsersContainer.class);

            return userList.getBody().getUsers();

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Purchase> fetchRecentPurchasesForUser(String username, int limitTo) {
        try {
            HttpResponse<PurchaseContainer> response = Unirest.get(purchasesBaseUrl + "/purchases/by_user/{username}")
                    .routeParam("username", username)
                    .queryString("limit", limitTo)
                    .asObject(PurchaseContainer.class);

            return response.getBody().getPurchases();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Purchase> fetchProductPurchaseList(int productId) {
        try {
            HttpResponse<PurchaseContainer> productPurchases = Unirest.get(purchasesBaseUrl + "/purchases/by_product/{product_id}")
                    .routeParam("product_id", productId + "")
                    .asObject(PurchaseContainer.class);

            return productPurchases.getBody().getPurchases();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductInfo fetchProductInfoFromId(Integer productId) {
        try{
            return Unirest.get(purchasesBaseUrl + "/products/{productId}")
                        .routeParam("productId", productId.intValue() + "")
                        .asObject(ProductContainer.class).getBody().getProduct();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userExists(String username) {
        try {
            User user = Unirest.get(purchasesBaseUrl + "/users/{username}")
                    .routeParam("username", username)
                    .asObject(UserContainer.class).getBody().getUser();
            return user != null && user.getUsername() != null;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return false;
    }


}
