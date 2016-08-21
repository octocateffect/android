package com.donzz.justbuy948;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Justbuy948Application extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public class PreferSet {
        public final static String id = "id";
        public final static String name = "name";
        public final static String prices = "prices";
        public final static String imageURL = "image_url";
    }

    /**
     * Created by Ymow on 16/8/20.
     */


}