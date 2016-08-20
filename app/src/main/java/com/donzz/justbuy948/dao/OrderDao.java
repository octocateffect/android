package com.donzz.justbuy948.dao;

import android.content.Context;

import com.donzz.justbuy948.Order;
import com.donzz.justbuy948.User;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ymow on 16/8/20.
 */


    public class OrderDao extends BaseDao<Order, Integer>{

        public OrderDao(Context context) {
            super(context, Order.class);
        }

        public List<Order> getByUserId(int userId){
            try {
                return getDao().queryBuilder()
                        .orderBy("id", false)
                        .where().eq("user_id", userId).query();
            }catch (SQLException e){
                e.printStackTrace();
                return Collections.emptyList();
            }
        }
    }