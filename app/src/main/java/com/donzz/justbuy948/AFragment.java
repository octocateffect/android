package com.donzz.justbuy948;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.donzz.justbuy948.dao.OrderDao;
import com.donzz.justbuy948.dao.UserDao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class AFragment extends Fragment {
    private String CURRENT_QUERY_URL;
    public static OkHttpClient client = new OkHttpClient();
    private JSONObject jsonObjectMenu, jsonObjectMenuItem;
    private FragmentTabHost tabHost;
    private String aJson;
    public static ArrayList<HashMap<String, String>> arrayListStoreMenu;

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
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity)activity;
        value = mainActivity.getAppleData();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_a, container, false);
        arrayListStoreMenu = new ArrayList<HashMap<String, String>>();
        arrayListStoreMenu.clear();
        GetStoreMenu asyncTask = new GetStoreMenu();
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        TabHost host = (TabHost) getActivity().findViewById(android.R.id.tabhost);
        host.getTabWidget().getChildAt(0).setVisibility(View.VISIBLE);
        host.getTabWidget().getChildAt(1).setVisibility(View.VISIBLE);
        host.getTabWidget().getChildAt(2).setVisibility(View.VISIBLE);
        layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        UserDao userDao = new UserDao(getActivity());
        user = userDao.getById(0);
        dao = new OrderDao(getActivity());

//        ArrayList<String> myDataset = new ArrayList<>();
//        for(int i = 0; i < 100; i++){
//            myDataset.add(i + "");
//        }
        recyclerView = (RecyclerView) rootview.findViewById(R.id.list_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

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
                adapter.setOrders(dao.getByUserId(user.getId()));
                adapter.notifyDataSetChanged();
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

    private class GetStoreMenu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            CURRENT_QUERY_URL = "https://best-way-948.appspot.com/menus/mcdonalds";
            try {

                Log.d("===TAG===", "START");
                MainActivity.JSONParser nearMeShops = new MainActivity.JSONParser();
                Log.d("===TAG===", "CHECK POINT 1");
                String menuResponse = nearMeShops.run(CURRENT_QUERY_URL);

                Log.d("===TAG===", "full json string: " + menuResponse);

                JSONArray ja = new JSONArray(menuResponse);
                Log.d("===TAG===", "CHECK POINT 3");

                Log.d("===TAG===", "Get JSONArray items succeed");

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jobj = ja.getJSONObject(i);
                    String id = jobj.getString("id");
                    String name = jobj.getString("name");
                    String price = jobj.getString("prices");
                    String image_url = jobj.getString("image_url");
                    Log.d("===TAG===", "id = " + id + " name = " + name + ", price = " + price);

                        HashMap<String, String> HashMapMenu = new HashMap<String, String>();
                        HashMapMenu.put(Justbuy948Application.PreferSet.id, id);
                        HashMapMenu.put(Justbuy948Application.PreferSet.name, name);
                        HashMapMenu.put(Justbuy948Application.PreferSet.prices, price);
                        HashMapMenu.put(Justbuy948Application.PreferSet.imageURL, image_url);
                        arrayListStoreMenu.add(HashMapMenu);
                        System.out.println("arrayListStoreMenu: " + arrayListStoreMenu.toString());

                }

            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            } finally {
                Log.e("TAG", "OK");

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter = new ShoppingAdapter(getActivity(), arrayListStoreMenu);
            recyclerView.setAdapter(adapter);
        }

    }
}