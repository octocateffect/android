package com.donzz.justbuy948;

/**
 * Created by Ymow on 16/8/20.
 */
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User {
    @DatabaseField(generatedId = true) private int id;
    @DatabaseField private String name;
    @DatabaseField private int sort;
    @ForeignCollectionField
    private ForeignCollection<Order> groups;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getSort() {
        return sort;
    }

    public User setSort(int sort) {
        this.sort = sort;
        return this;
    }

    public void setGroups(ForeignCollection<Order> groups) {
        this.groups = groups;
    }

    public ForeignCollection<Order> getGroups() {
        return groups;
    }
}