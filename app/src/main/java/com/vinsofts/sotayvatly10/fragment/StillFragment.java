package com.vinsofts.sotayvatly10.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.adapter.BackgroundAdapter;
import com.vinsofts.sotayvatly10.object.BackgroundObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 22/04/2016.
 */
public class StillFragment extends Fragment {

    private GridView grvBackground;
    private List<BackgroundObject> arrayList;
    private BackgroundAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_still, viewGroup, false);

        grvBackground = (GridView) view.findViewById(R.id.lstBackground);
        arrayList = new ArrayList<>();

        arrayList.add(new BackgroundObject(R.drawable.background_still_10,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_13,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_14,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_15,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_17,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_18,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_7,R.drawable.icon_check));
        arrayList.add(new BackgroundObject(R.drawable.background_still_9,R.drawable.icon_check));





        adapter = new BackgroundAdapter(getActivity(), R.layout.item_background, arrayList);

        grvBackground.setAdapter(adapter);

        grvBackground.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                ((BaseAdapter)grvBackground.getAdapter()).notifyDataSetChanged();



//                arrayList.get(position).setCheckChoose(R.drawable.icon_check_selected);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT).show();
//                SharedPreferences sharedPref = getActivity().getSharedPreferences("background",
//                        Context.MODE_PRIVATE);
//
//                SharedPreferences.Editor editor = sharedPref.edit();
//                int selected_background = arrayList.get(position).getIcon();
//                editor.putInt("background_resource", selected_background);
//                editor.putInt("check", 0);
//                editor.putInt("position_choose", position);
//                editor.apply();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("background", Context.MODE_PRIVATE);
        int dem = sharedPref.getInt("check", -1);
        int positionImageChoose = sharedPref.getInt("position_choose", -1);
        Log.d("CHien12321312", positionImageChoose+"");
        if (positionImageChoose != -1) {
            if (dem == 0) {
                arrayList.get(positionImageChoose).setCheckChoose(R.drawable.icon_check_selected);
            } else {
                arrayList.get(positionImageChoose).setCheckChoose(R.drawable.icon_check);
            }
        }

    }
}
