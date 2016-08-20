package com.donzz.justbuy948;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.donzz.justbuy948.dao.OrderDao;
import com.donzz.justbuy948.dao.UserDao;

import java.util.ArrayList;

public class AFragment extends Fragment {
    private CarouselLayoutManager layoutManager;
    private String value = "";
    private TextView ItemCounter;
    private View rootview;
    private ImageButton ResultButton;
    private Button AddButton, ReduceButton;
    private String TAG = "AFragment";
    private ShoppingAdapter adapter;
    private int SingleItemCounterA = 0;
    private OrderDao dao;
    private User user;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity)activity;
        value = mainActivity.getAppleData();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_a, container, false);
        TabHost host = (TabHost) getActivity().findViewById(android.R.id.tabhost);
        host.getTabWidget().getChildAt(0).setVisibility(View.VISIBLE);
        host.getTabWidget().getChildAt(1).setVisibility(View.VISIBLE);
        host.getTabWidget().getChildAt(2).setVisibility(View.VISIBLE);
        layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        UserDao userDao = new UserDao(getActivity());
        user = userDao.getById(0);
        dao = new OrderDao(getActivity());

        ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(i + "");
        }
        adapter = new ShoppingAdapter(getActivity(), dao.getByUserId(0));
        final RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        ResultButton = (ImageButton) rootview.findViewById(R.id.resultButton);
        AddButton = (Button)rootview.findViewById(R.id.addButton);
        ReduceButton = (Button)rootview.findViewById(R.id.reduceButton);
        ItemCounter  = (TextView) rootview.findViewById(R.id.itemCounter);

        recyclerView.addOnScrollListener(new CenterScrollListener());
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.v("", "onScrolled !");
                AddButton.setVisibility(View.INVISIBLE);
                ReduceButton.setVisibility(View.INVISIBLE);
                ItemCounter.setVisibility(View.INVISIBLE);

//                User user = createUser(SingleItemCounterA);
//                ord.insert(user);
//                adapter.setUsers(dao.getAll("sort", false));
//                adapter.notifyDataSetChanged();

                SingleItemCounterA = 0;
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //Call your method here for next set of data
                    AddButton.setVisibility(View.VISIBLE);
                    ReduceButton.setVisibility(View.VISIBLE);
                    ItemCounter.setText("0");
                    ItemCounter.setVisibility(View.VISIBLE);
                    //TODO if dao != null, load database to adapter

                    Log.v("getCenterItemPosition", String.valueOf( layoutManager.getCenterItemPosition()));
                }
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleItemCounterA = ++SingleItemCounterA;
                ItemCounter.setText(String.valueOf(SingleItemCounterA));
                dao.insert(createOrder(layoutManager.getCenterItemPosition()));
//                adapter.setOrders(dao.getByUserId(user.getId()));
//                adapter.notifyDataSetChanged();
            }
        });

        ReduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleItemCounterA = --SingleItemCounterA;
                ItemCounter.setText(String.valueOf(SingleItemCounterA));
            }
        });

        ResultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                ResultFragment resultFragment= new ResultFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.realtabcontent, resultFragment);
                fragmentTransaction.commit();
            }
        });

        return rootview;
    }

    public Order createOrder(int currentPosition) {
        Order order = new Order(0,"StoreName A", "ProductName", "ProductPrice", "ImageURL", user);
        order.setUser(user);
        return order;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}