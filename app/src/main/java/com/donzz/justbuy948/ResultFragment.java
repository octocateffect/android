package com.donzz.justbuy948;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;


public class ResultFragment extends Fragment {
    private String value = "";
    private View rootview;
    private TabHost tabhost;
    private ImageButton upArrowButton;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity)activity;
        value = mainActivity.getAppleData();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_result, container, false);
        tabhost = (TabHost) getActivity().findViewById(android.R.id.tabhost);
        tabhost.getTabWidget().getChildAt(0).setVisibility(View.GONE);
        tabhost.getTabWidget().getChildAt(1).setVisibility(View.GONE);
        tabhost.getTabWidget().getChildAt(2).setVisibility(View.GONE);
        upArrowButton = (ImageButton) rootview.findViewById(R.id.backButton);
        upArrowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                AFragment aFragment= new AFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.realtabcontent, aFragment);
                fragmentTransaction.commit();
            }
        });
        return rootview;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}