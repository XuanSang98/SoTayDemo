package com.vinsofts.sotayvatly10.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.adapter.YourOwnBackgroundAdapter;
import com.vinsofts.sotayvatly10.object.YourOwnBackgroundObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by Administrator on 22/04/2016.
 */
public class YourOwnFragment extends Fragment{
    private View view;
    private GridViewWithHeaderAndFooter gvBackground;
    private List<YourOwnBackgroundObject> listYourOwn = new ArrayList<>();
    private YourOwnBackgroundAdapter adapter;
    SharedPreferences prefs;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_yourown, container, false);

        gvBackground = (GridViewWithHeaderAndFooter) view.findViewById(R.id.gvBackground);



        adapter = new YourOwnBackgroundAdapter(getActivity(),R.layout.item_background,listYourOwn);

        gvBackground.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("background", Context.MODE_PRIVATE);
        int dem = sharedPref.getInt("check", -1);
        int positionImageChoose = sharedPref.getInt("position_choose_yourown", -1);
        if (positionImageChoose != -1) {
            if (dem == 1) {
                listYourOwn.get(positionImageChoose).setCheckChoose(R.drawable.icon_check_selected);
            } else {
                listYourOwn.get(positionImageChoose).setCheckChoose(R.drawable.icon_check);
            }
        }

//        adapter.notifyDataSetChanged();

    }
}
