package com.donzz.justbuy948;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by andyang on 15/7/23
 */

@DatabaseTable(tableName = "Order")
public class Order {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String storename;
    @DatabaseField
    private String productname;
    @DatabaseField
    private String productprice;
    @DatabaseField
    private String description;
    @DatabaseField
    private String image_url;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false, columnName = "user_id")
    private User user;

    public Order() {
    }

    public Order(int id, String storename, String productname, String productprice, String image_url, User user) {
        this.id = id;
        this.storename = storename;
        this.productname = productname;
        this.productprice = productprice;
        this.image_url = image_url;
        this.user = user;
    }

    public Order(String name, String description) {
        this.storename = storename;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }


    public String getStoreName() {
        return storename;
    }

    public Order setStoreName(String storename) {
        this.storename = storename;
        return this;
    }

    public String getProductName() {
        return productname;
    }

    public Order setProductName(String productname) {
        this.productname = productname;
        return this;
    }

    public String getProductPrice() {
        return productprice;
    }

    public Order setProductPrice(String productprice) {
        this.productprice = productprice;
        return this;
    }

    public String getImageURL() {
        return image_url;
    }

    public Order setImageURL(String description) {
        this.image_url = image_url;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }
}
