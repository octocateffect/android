package com.donzz.justbuy948;

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

/**
 * Created by Ymow on 16/8/21.
 */
public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    ArrayList<HashMap<String, String>> arrayListStoreMenu;
    private List<Order> orders;
    private LayoutInflater inflater;
    Context mContext;

    public ShoppingAdapter(Context context, ArrayList<HashMap<String, String>> adapterMenu) {
        this.mContext = context;
        this.orders = orders;
        this.arrayListStoreMenu = adapterMenu;
        inflater = LayoutInflater.from(context);
    }



    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setMenu(ArrayList<HashMap<String, String>> adapterMenu) {
        this.arrayListStoreMenu = adapterMenu;
    }

    public ArrayList<HashMap<String, String>> getMenu() {
        return arrayListStoreMenu;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mPrice;
        public ImageView mImageView;

        //        public Button AddButton,ReduceButton;
        public ViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.info_name);
            mPrice = (TextView) v.findViewById(R.id.info_peice);
            mImageView = (ImageView) v.findViewById(R.id.img);
            //            AddButton = (Button) v.findViewById(R.id.addButton);
            //            ReduceButton = (Button) v.findViewById(R.id.reduceButton);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(arrayListStoreMenu.get(position).get("name"));
        holder.mPrice.setText(arrayListStoreMenu.get(position).get("prices"));
        Glide.with(mContext)
                .load(arrayListStoreMenu.get(position).get("image_url"))
                //                .error(R.drawable.merchants_loading_bg)//load失敗的Drawable
                //                .placeholder(R.drawable.merchants_loading_bg)//loading時候的Drawable
                //                    .animate()//設置load完的動畫
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) // worth a try without this line too
                .centerCrop()//中心切圖, 會填滿
                .fitCenter()//中心fit, 以原本圖片的長寬為主
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return arrayListStoreMenu.size();
    }

    public Order getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}