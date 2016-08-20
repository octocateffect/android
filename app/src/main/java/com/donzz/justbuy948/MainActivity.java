package com.donzz.justbuy948;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.donzz.justbuy948.dao.UserDao;

public class MainActivity extends AppCompatActivity {
    private UserDao dao;
    private User user;
    private int number =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new UserDao(this);
        user = createUser(number);
        dao.insert(user);

        FragmentTabHost tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //1
        tabHost.addTab(tabHost.newTabSpec("Apple")
                        .setIndicator("A"),
                AFragment.class,
                null);
        //2
        tabHost.addTab(tabHost.newTabSpec("Google")
                        .setIndicator("B"),
                BFragment.class,
                null);
        //3
        tabHost.addTab(tabHost.newTabSpec("Facebook")
                        .setIndicator("C"),
                CFragment.class,
                null);
    }

    private User createUser(int number) {
        return new User("User" + number);
    }

    public Order createOrder(int currentPosition) {
        Order order = new Order(0,"StoreName A", "ProductName", "ProductPrice", "ImageURL", user);
        order.setUser(user);
        return order;
    }

    /**************************
     *
     * 給子頁籤呼叫用
     *
     **************************/
    public String getAppleData(){
        return "A";
    }
    public String getGoogleData(){
        return "B";
    }
    public String getFacebookData(){
        return "C";
    }
}
