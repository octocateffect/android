package com.donzz.justbuy948;

import java.util.List;

/**
 * Created by Ymow on 16/8/21.
 */
public class Menu {

    /**
     * category :
     * name : 熱那堤(中) - L2
     * items : ["56"]
     * image_url : https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3068.png?
     * prices : 65
     * id : 56
     */

    private String category;
    private String name;
    private String image_url;
    private String prices;
    private String id;
    private List<String> items;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
