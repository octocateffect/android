package com.donzz.justbuy948;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ymow on 16/8/20.
 */

class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private List<Order> orders;
    private LayoutInflater inflater;

    public ShoppingAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        inflater = LayoutInflater.from(context);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
//        public Button AddButton,ReduceButton;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.info_text);
//            AddButton = (Button) v.findViewById(R.id.addButton);
//            ReduceButton = (Button) v.findViewById(R.id.reduceButton);

        }
    }

    @Override
    public ShoppingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(orders.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public Order getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
