package com.donzz.justbuy948;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.donzz.justbuy948.dao.UserDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private UserDao dao;
    private User user;
    private int number =0;
    private String CURRENT_QUERY_URL;
    public static OkHttpClient client = new OkHttpClient();
    private JSONObject jsonObjectMenu;
    private FragmentTabHost tabHost;
    private String aJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new UserDao(this);
        user = createUser(number);
        dao.insert(user);

//        GetStoreMenu asyncTask = new GetStoreMenu();
//        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //1
        tabHost.addTab(tabHost.newTabSpec("McDownload")
                        .setIndicator("A"),
                AFragment.class,
                null);
        //2
        tabHost.addTab(tabHost.newTabSpec("Mos")
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

    private class GetStoreMenu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
//            PreferSet.CURRENT_QUERY_URL = PreferSet.API_URL + PreferSet.API_shops + PreferSet.CurrentMerchantID;
//            CURRENT_QUERY_URL = "http://114.44.230.239:5000/menus/mcdonalds";
//             aJson  = ReadFromfile("mcdonalds.json", MainActivity.this);
//              aJson = loadJSONFromAsset();
            CURRENT_QUERY_URL = "https://best-way-948.appspot.com/menus/mcdonalds";
            aJson = "[{\"0\":{\"category\":\"\",\"name\":\"玉米濃湯\",\"items\":[\"0\"],\"image_url\":\"\",\"prices\":\"30\",\"id\":\"0\"},\"1\":{\"category\":\"\",\"name\":\"揚蝦圈圈堡\",\"items\":[\"1\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1236.png?\",\"prices\":\"95\",\"id\":\"1\"},\"2\":{\"category\":\"\",\"name\":\"嫩雞圈圈堡\",\"items\":[\"2\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/31361.png?\",\"prices\":\"95\",\"id\":\"2\"},\"3\":{\"category\":\"\",\"name\":\"起司豬排堡\",\"items\":[\"3\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1540.png?\",\"prices\":\"79\",\"id\":\"3\"},\"4\":{\"category\":\"\",\"name\":\"培根牛肉堡\",\"items\":[\"4\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1081.png?\",\"prices\":\"105\",\"id\":\"4\"},\"5\":{\"category\":\"\",\"name\":\"雙層四盎司牛肉堡\",\"items\":[\"5\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1060.png?\",\"prices\":\"115\",\"id\":\"5\"},\"6\":{\"category\":\"\",\"name\":\"四盎司牛肉堡\",\"items\":[\"6\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1059.png?\",\"prices\":\"85\",\"id\":\"6\"},\"7\":{\"category\":\"\",\"name\":\"大麥克\",\"items\":[\"7\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1058.png?\",\"prices\":\"79\",\"id\":\"7\"},\"8\":{\"category\":\"\",\"name\":\"雙層牛肉吉事堡\",\"items\":[\"8\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1044.png?\",\"prices\":\"75\",\"id\":\"8\"},\"9\":{\"category\":\"\",\"name\":\"勁辣鷄腿堡\",\"items\":[\"9\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1317.png?\",\"prices\":\"82\",\"id\":\"9\"},\"10\":{\"category\":\"\",\"name\":\"板烤鷄腿堡\",\"items\":[\"10\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1315.png?\",\"prices\":\"85\",\"id\":\"10\"},\"11\":{\"category\":\"\",\"name\":\"原味麥脆鷄\",\"items\":[\"11\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1309.png?\",\"prices\":\"50\",\"id\":\"11\"},\"12\":{\"category\":\"\",\"name\":\"辣味麥脆鷄\",\"items\":[\"12\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1310.png?\",\"prices\":\"50\",\"id\":\"12\"},\"13\":{\"category\":\"\",\"name\":\"九塊麥克鷄塊\",\"items\":[\"13\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1300.png?\",\"prices\":\"95\",\"id\":\"13\"},\"14\":{\"category\":\"\",\"name\":\"六塊麥克鷄塊\",\"items\":[\"14\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1302.png?\",\"prices\":\"69\",\"id\":\"14\"},\"15\":{\"category\":\"\",\"name\":\"麥香魚\",\"items\":[\"15\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1217.png?\",\"prices\":\"69\",\"id\":\"15\"},\"16\":{\"category\":\"\",\"name\":\"麥香鷄\",\"items\":[\"16\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1348.png?\",\"prices\":\"49\",\"id\":\"16\"},\"17\":{\"category\":\"\",\"name\":\"麥克雙牛堡\",\"items\":[\"17\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1048.png?\",\"prices\":\"49\",\"id\":\"17\"},\"18\":{\"category\":\"\",\"name\":\"法式芥末香鷄堡\",\"items\":[\"18\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/31307.png?\",\"prices\":\"39\",\"id\":\"18\"},\"19\":{\"category\":\"\",\"name\":\"美式辣鷄堡\",\"items\":[\"19\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/31333.png?\",\"prices\":\"39\",\"id\":\"19\"},\"20\":{\"category\":\"\",\"name\":\"冰炫風-布朗尼\",\"items\":[\"20\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4347.png?\",\"prices\":\"55\",\"id\":\"20\"},\"21\":{\"category\":\"\",\"name\":\"冰炫風 (芝麻)\",\"items\":[\"21\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4368.png?\",\"prices\":\"55\",\"id\":\"21\"},\"22\":{\"category\":\"\",\"name\":\"OREO冰炫風\",\"items\":[\"22\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4361.png?\",\"prices\":\"55\",\"id\":\"22\"},\"23\":{\"category\":\"\",\"name\":\"花聖代-布朗尼\",\"items\":[\"23\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4339.png?\",\"prices\":\"49\",\"id\":\"23\"},\"24\":{\"category\":\"\",\"name\":\"聖代(芝麻)\",\"items\":[\"24\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4337.png?\",\"prices\":\"30\",\"id\":\"24\"},\"25\":{\"category\":\"\",\"name\":\"巧克力聖代\",\"items\":[\"25\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4329.png?\",\"prices\":\"30\",\"id\":\"25\"},\"26\":{\"category\":\"\",\"name\":\"香草奶昔\",\"items\":[\"26\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4203.png?\",\"prices\":\"40\",\"id\":\"26\"},\"27\":{\"category\":\"\",\"name\":\"草莓奶昔\",\"items\":[\"27\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4202.png?\",\"prices\":\"40\",\"id\":\"27\"},\"28\":{\"category\":\"\",\"name\":\"洋蔥香圈圈\",\"items\":[\"28\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4072.png?\",\"prices\":\"49\",\"id\":\"28\"},\"29\":{\"category\":\"\",\"name\":\"小四季沙拉\",\"items\":[\"29\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1607.png?\",\"prices\":\"25\",\"id\":\"29\"},\"30\":{\"category\":\"\",\"name\":\"勁辣香雞翅\",\"items\":[\"30\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1330.png?\",\"prices\":\"39\",\"id\":\"30\"},\"31\":{\"category\":\"\",\"name\":\"鳳梨派\",\"items\":[\"31\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4136.png?\",\"prices\":\"28\",\"id\":\"31\"},\"32\":{\"category\":\"\",\"name\":\"蘋果派\",\"items\":[\"32\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4135.png?\",\"prices\":\"28\",\"id\":\"32\"},\"33\":{\"category\":\"\",\"name\":\"小包薯條\",\"items\":[\"33\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/4014.png?\",\"prices\":\"28\",\"id\":\"33\"},\"34\":{\"category\":\"\",\"name\":\"蘋果-四季水果袋\",\"items\":[\"34\"],\"image_url\":\"https://www.mcdelivery.com.tw/tw/browse/menu.html?daypartId=45&catId=100\",\"prices\":\"39\",\"id\":\"34\"},\"35\":{\"category\":\"\",\"name\":\"荔枝萊姆冰沙\",\"items\":[\"35\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34822.png?\",\"prices\":\"95\",\"id\":\"35\"},\"36\":{\"category\":\"\",\"name\":\"芒果萊姆冰沙\",\"items\":[\"36\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34818.png?\",\"prices\":\"95\",\"id\":\"36\"},\"37\":{\"category\":\"\",\"name\":\"野莓萊姆冰沙\",\"items\":[\"37\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34817.png?\",\"prices\":\"95\",\"id\":\"37\"},\"38\":{\"category\":\"\",\"name\":\"葡萄柚萊姆冰沙\",\"items\":[\"38\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34821.png?\",\"prices\":\"95\",\"id\":\"38\"},\"39\":{\"category\":\"\",\"name\":\"冰那堤-(中) - L2\",\"items\":[\"39\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3418.png?\",\"prices\":\"65\",\"id\":\"39\"},\"40\":{\"category\":\"\",\"name\":\"冰那堤(中)\",\"items\":[\"40\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34840.png?\",\"prices\":\"75\",\"id\":\"40\"},\"41\":{\"category\":\"\",\"name\":\"小杯可口可樂\",\"items\":[\"41\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3183.png?\",\"prices\":\"28\",\"id\":\"41\"},\"42\":{\"category\":\"\",\"name\":\"小杯檸檬紅茶\",\"items\":[\"42\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33413.png?\",\"prices\":\"28\",\"id\":\"42\"},\"43\":{\"category\":\"\",\"name\":\"小杯雪碧\",\"items\":[\"43\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3193.png?\",\"prices\":\"28\",\"id\":\"43\"},\"44\":{\"category\":\"\",\"name\":\"小杯無糖冰綠茶\",\"items\":[\"44\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33418.png?\",\"prices\":\"28\",\"id\":\"44\"},\"45\":{\"category\":\"\",\"name\":\"小杯零卡可樂\",\"items\":[\"45\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3188.png?\",\"prices\":\"28\",\"id\":\"45\"},\"46\":{\"category\":\"\",\"name\":\"小杯柳橙汁\",\"items\":[\"46\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33432.png?\",\"prices\":\"40\",\"id\":\"46\"},\"47\":{\"category\":\"\",\"name\":\"小杯美式咖啡\",\"items\":[\"76\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3014.png?\",\"prices\":\"33\",\"id\":\"47\"},\"48\":{\"category\":\"\",\"name\":\"小杯焦糖熱奶茶\",\"items\":[\"76\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3019.png?\",\"prices\":\"33\",\"id\":\"48\"},\"49\":{\"category\":\"\",\"name\":\"熱紅茶\",\"items\":[\"76\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3017.png?\",\"prices\":\"33\",\"id\":\"49\"},\"50\":{\"category\":\"\",\"name\":\"經典冰咖啡\",\"items\":[\"50\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33416.png?\",\"prices\":\"45\",\"id\":\"50\"},\"51\":{\"category\":\"\",\"name\":\"焦糖冰奶茶\",\"items\":[\"51\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33426.png?\",\"prices\":\"45\",\"id\":\"51\"},\"52\":{\"category\":\"\",\"name\":\"熱巧克力\",\"items\":[\"76\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3020.png?\",\"prices\":\"33\",\"id\":\"52\"},\"53\":{\"category\":\"\",\"name\":\"柳橙酷蘇打\",\"items\":[\"53\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3147.png?\",\"prices\":\"48\",\"id\":\"53\"},\"54\":{\"category\":\"\",\"name\":\"小杯玉米湯\",\"items\":[\"76\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1417.png?\",\"prices\":\"33\",\"id\":\"54\"},\"55\":{\"category\":\"\",\"name\":\"高鈣鮮乳\",\"items\":[\"55\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3508.png?\",\"prices\":\"28\",\"id\":\"55\"},\"56\":{\"category\":\"\",\"name\":\"熱那堤(中) - L2\",\"items\":[\"56\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3068.png?\",\"prices\":\"65\",\"id\":\"56\"},\"57\":{\"category\":\"\",\"name\":\"熱亞美利加諾(中) - L2\",\"items\":[\"57\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/3066.png?\",\"prices\":\"55\",\"id\":\"57\"},\"58\":{\"category\":\"\",\"name\":\"熱那堤(中)\",\"items\":[\"58\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33840.png?\",\"prices\":\"75\",\"id\":\"58\"},\"59\":{\"category\":\"\",\"name\":\"熱亞美利加諾\",\"items\":[\"59\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33808.png?\",\"prices\":\"65\",\"id\":\"59\"},\"60\":{\"category\":\"\",\"name\":\"熱焦糖瑪琪朵(中)\",\"items\":[\"60\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33844.png?\",\"prices\":\"85\",\"id\":\"60\"},\"61\":{\"category\":\"\",\"name\":\"冰亞美利加諾\",\"items\":[\"61\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34805.png?\",\"prices\":\"65\",\"id\":\"61\"},\"62\":{\"category\":\"\",\"name\":\"冰焦糖瑪琪朵(中)\",\"items\":[\"62\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34842.png?\",\"prices\":\"85\",\"id\":\"62\"},\"63\":{\"category\":\"\",\"name\":\"熱香草那堤(中)\",\"items\":[\"63\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33854.png?\",\"prices\":\"85\",\"id\":\"63\"},\"64\":{\"category\":\"\",\"name\":\"熱榛果那堤(中)\",\"items\":[\"64\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33856.png?\",\"prices\":\"85\",\"id\":\"64\"},\"65\":{\"category\":\"\",\"name\":\"熱抹茶那堤(中)\",\"items\":[\"65\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33822.png?\",\"prices\":\"85\",\"id\":\"65\"},\"66\":{\"category\":\"\",\"name\":\"冰香草那堤(中)\",\"items\":[\"66\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34848.png?\",\"prices\":\"85\",\"id\":\"66\"},\"67\":{\"category\":\"\",\"name\":\"冰榛果那堤(中)\",\"items\":[\"67\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34850.png?\",\"prices\":\"85\",\"id\":\"67\"},\"68\":{\"category\":\"\",\"name\":\"冰抹茶那堤(中)\",\"items\":[\"68\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34815.png?\",\"prices\":\"85\",\"id\":\"68\"},\"69\":{\"category\":\"\",\"name\":\"熱阿薩姆那堤(中)\",\"items\":[\"69\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33824.png?\",\"prices\":\"85\",\"id\":\"69\"},\"70\":{\"category\":\"\",\"name\":\"熱巧克力(中)\",\"items\":[\"70\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33845.png?\",\"prices\":\"75\",\"id\":\"70\"},\"71\":{\"category\":\"\",\"name\":\"熱特濃白咖啡(中)\",\"items\":[\"71\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/33842.png?\",\"prices\":\"75\",\"id\":\"71\"},\"72\":{\"category\":\"\",\"name\":\"冰阿薩姆那堤(中)\",\"items\":[\"72\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34819.png?\",\"prices\":\"85\",\"id\":\"72\"},\"73\":{\"category\":\"\",\"name\":\"冰巧克力(中)\",\"items\":[\"73\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/34843.png?\",\"prices\":\"75\",\"id\":\"73\"},\"74\":{\"category\":\"\",\"name\":\"四塊麥克鷄塊\",\"items\":[\"74\"],\"image_url\":\"https://drjaosdejw578.cloudfront.net/tw/static/1471535072755/assets/886/products/1302.png?\",\"prices\":\"46\",\"id\":\"74\"},\"75\":{\"category\":\"\",\"name\":\"四人分享餐\",\"items\":[\"11\",\"11\",\"11\",\"11\",\"13\",\"30\",\"30\",\"30\",\"30\",\"76\",\"76\",\"76\",\"76\"],\"image_url\":\"\",\"prices\":\"409\",\"id\":\"75\"},\"76\":{\"category\":\"\",\"name\":\"33元飲料\",\"items\":[\"76\"],\"image_url\":\"\",\"prices\":\"33\",\"id\":\"76\"}}]";
            try {
                Log.d("===TAG===", "START");
                JSONParser nearMeShops = new JSONParser();
                Log.d("===TAG===", "CHECK POINT 1");
                String menuResponse = nearMeShops.run(CURRENT_QUERY_URL);

                Log.d("===TAG===", "full json string: " + menuResponse);

                JSONArray ja = new JSONArray(menuResponse);
                Log.d("===TAG===", "CHECK POINT 3");

//                System.out.println("MenuResponse:" + jobject.getString("status"));
//                System.out.println("MenuResponse:" + jobject.getString("status"));
//
//                System.out.println(jobject.getJSONObject("data").toString());

                Log.d("===TAG===", "Get JSONArray items succeed");

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jobj = ja.getJSONObject(i);
                    String id = jobj.getString("id");
                    String name = jobj.getString("name");
                    String price = jobj.getString("prices");
                    String image_url = jobj.getString("image_url");
                    Log.d("===TAG===", "id = " + id + " name = " + name + ", price = " + price);
                }

                JSONArray jsonArrayQueueRules = ja;
                for (int i = 0; i < jsonArrayQueueRules.length(); i++) {
                    jsonObjectMenu = jsonArrayQueueRules.getJSONObject(i).getJSONObject(String.valueOf(i)); // create a single event jsonObject
                    System.out.println("jsonObjectMenu : "+jsonObjectMenu.toString());
//                    String queue_rule_id = jsonObjectMenu.getString("name");
//                    String StoreMenu = jsonObjectMenu.get
//                    storequeuerules = gson.fromJson(last_called_record, Menu.class);
                    String id = jsonObjectMenu.getString("id");
                    String name = jsonObjectMenu.getString("name");
                    String price = jsonObjectMenu.getString("prices");
                    String image_url = jsonObjectMenu.getString("image_url");
                    System.out.println("The Store Menu: id=" + id  + "name=" + name + "prices=" + price + "imageURL=" + image_url);
//                    HashMap<String, String> HashMapBubblesName = new HashMap<String, String>();
//                    HashMapBubblesName.put(PreferSet.TAG_ID, id);
//                    HashMapBubblesName.put(PreferSet.TAG_STORE_QUEUE_NAME, name);
//                    PreferSet.TempCacheBubblesName.add(HashMapBubblesName);
//
//                    queue_rules_bubbles_numbers++;
//
//                    // parsing json with last_called_record_number
//                    if (PreferSet.StoreOPENStatus.contains("open")) {
//                        String last_called_record = jsonObjectQueuerules.getString("last_called_record");
//                        System.out.println("last_called_record json:" + last_called_record);
//                        storequeuerules = gson.fromJson(last_called_record, ApiStoreQueueRules.class);
////                        System.out.println("last_called_record_number =" + storequeuerules.getQueueLastCalledNumber());
//
//                        if (last_called_record == null || (last_called_record.equals("null"))) {
//                            last_called_record_number = "0";
//                            System.out.println("last_called_record should 0:" + last_called_record_number);
//                        } else if (storequeuerules.getQueueLastCalledNumber() == null) {
//                            last_called_record_number = "0";
//                            System.out.println("last_called_record should 0:" + last_called_record_number);
//                        } else {
//                            last_called_record_number = storequeuerules.getQueueLastCalledNumber();
//                            System.out.println("last_called_record_OK:" + last_called_record_number);
//                        }
//
//                        HashMap<String, String> HashMapLastCalled = new HashMap<String, String>();
//                        HashMapLastCalled.put(PreferSet.TAG_ID, id);
//                        HashMapLastCalled.put(PreferSet.TAG_STORE_QUEUE_NAME, name);
//                        HashMapLastCalled.put(PreferSet.TAG_STORE_LASTCALLED, last_called_record_number);
//                        PreferSet.TempCacheStoreLastcalled.add(HashMapLastCalled);
//
//                        JSONArray jsonArrayUncalledRecords = jsonObjectQueuerules.getJSONArray("uncalled_records");
//                        for (int j = 0; j < jsonArrayUncalledRecords.length(); j++) {
//                            jsonObjectUncalledRecord = jsonArrayUncalledRecords.getJSONObject(j);
//                            String UncalledRecords = jsonObjectUncalledRecord.getString("id");
//                            System.out.println("The Store : UncalledRecords=" + UncalledRecords);
//                            UncalledRecord.add(UncalledRecords);
//                        }

//                    } else {
//                        System.out.println("PreferSet.StoreOPENStatus = close");
//                    }
//                    System.out.println("PreferSet.API_queue_rules_bubbles_numbers =" + Integer.toString(queue_rules_bubbles_numbers));
//                    System.out.println("PreferSet.UncalledRecordSize =" + String.valueOf(UncalledRecord.size()));

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

//            PreferSet.StoreBubblesNumbers = PreferSet.TempCacheBubblesName.size();
//            System.out.println("TempCacheBubblesName" + PreferSet.TempCacheBubblesName.toString());
//            System.out.println("TempCacheStoreLastcalled" + PreferSet.TempCacheStoreLastcalled.toString());
//
//            if (!UncalledRecord.isEmpty() && UncalledRecord != null) {
//                PreferSet.UncalledRecordSize = String.valueOf(UncalledRecord.size());
//            } else {
//                PreferSet.UncalledRecordSize = "0";
//            }
//
//            StoreRootPage f = (StoreRootPage) fragmentManager.findFragmentById(R.id.content_frame);
////            Bundle bundle = new Bundle();
////            bundle.putSerializable("TempCacheBubblesName", TempCacheBubblesName);
////            bundle.putSerializable("TempCacheStoreLastcalled", TempCacheStoreLastcalled);
////            f.setArguments(bundle);
//
////            mThread.start();
//            if (f == null || !f.isInLayout()) {
//                f = new StoreRootPage();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.content_frame, f);
////                fragmentTransaction.addToBackStack(PreferSet.FRAGMENT_TAG);
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.commitAllowingStateLoss(); // IllegalStateException: Can not perform this action after onSaveInstanceState
//
//
////                startService(new Intent(StoreMainActivity.this, StoreRootIntentService.class));
//
//            } else if (f.isAdded()) {
//                return;
//            }
//
//            StoreOPENStatusDialogChecking();
//
//            ApiLoadingDialog.dismiss();

        }

    }

    public class FileNotFoundExceptionExample {
        private static final String filename = "mcdonalds.json";

        public  void main(String[] args) {
            BufferedReader rd = null;
            try {
                // Open the file for reading.
                rd = new BufferedReader(new FileReader(new File(filename)));

                // Read all contents of the file.
                String inputLine = null;
                while((inputLine = rd.readLine()) != null)
                    System.out.println(inputLine);
                aJson = inputLine;
            }
            catch(IOException ex) {
                System.err.println("An IOException was caught!");
                ex.printStackTrace();
            }
            finally {
                // Close the file.
                try {
                    rd.close();
                }
                catch (IOException ex) {
                    System.err.println("An IOException was caught!");
                    ex.printStackTrace();
                }
            }
        }
    }

    public String loadJSONFromAsset() {

        String json = null;
        try {
                InputStream is = this.getAssets().open("mcdonalds.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public String ReadFromfile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public static class JSONParser {
        //GET httprequest method
        String run(String url) throws IOException, JSONException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
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
