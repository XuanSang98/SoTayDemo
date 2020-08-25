package com.vinsofts.sotayvatly10.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.activity.WebviewTips;
import com.vinsofts.sotayvatly10.adapter.TipsAdapter;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.object.TipsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 20/04/2016.
 */
public class TipsFragment extends Fragment {
    private ListView lsvTips;
    private List<TipsObject> arrayList = new ArrayList<>();
    private RelativeLayout reFragmentTip;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, viewGroup, false);
        lsvTips = (ListView) view.findViewById(R.id.lstTips);
        reFragmentTip = (RelativeLayout) view.findViewById(R.id.reFragmentTip);
        if (Build.VERSION.SDK_INT >= 21) {
            reFragmentTip.setPadding(dp2px(8), getStatusBarHeight(), dp2px(8), 0);
        }

        arrayList.add(new TipsObject(R.drawable.tip_calo_1, "hoa-hoc/lop-12.html", "Trắc nghiệm hóa học"));
        arrayList.add(new TipsObject(R.drawable.tip_calo_2, "sinh-hoc/lop-12.html", "Trắc nghiệm sinh học"));
        arrayList.add(new TipsObject(R.drawable.tip_calo_3, "vat-ly/lop-12.html", "Trắc nghiệm vật lý"));
        arrayList.add(new TipsObject(R.drawable.tip_food_1, "tieng-anh/lop-12.html", "Trắc nghiệm tiếng Anh"));
        arrayList.add(new TipsObject(R.drawable.tip_mile_1, "toan-hoc/lop-12.html", "Trắc nghiệm toán học"));
        arrayList.add(new TipsObject(R.drawable.tip_mile_3, "dia-ly/lop-12.html", "Trắc nghiệm địa lý"));
        arrayList.add(new TipsObject(R.drawable.tip_sleep_2, "lich-su/lop-12.html", "Trắc nghiệm lịch sử"));
        arrayList.add(new TipsObject(R.drawable.tip_sleep_3, "ngu-van/lop-12.html", "Trắc nghiệm ngữ văn"));
        arrayList.add(new TipsObject(R.drawable.tip_step_1, "gdcd/lop-12.html", "Trắc nghiệm GDCD"));

        TipsAdapter adapter = new TipsAdapter(getActivity(), R.layout.item_tips, arrayList);
        lsvTips.setAdapter(adapter);
        lsvTips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), WebviewTips.class);
                intent.putExtra(Constant.PASS_URL, arrayList.get(position).getUrl());
                startActivity(intent);

            }
        });
        return view;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getActivity().getResources().getDisplayMetrics());
    }
}
