package com.vinsofts.sotayvatly10.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.activity.BackgroundActivity;
import com.vinsofts.sotayvatly10.activity.LessonActivity;
import com.vinsofts.sotayvatly10.activity.PageDetailActivity;
import com.vinsofts.sotayvatly10.adapter.CategoryAdapter;
import com.vinsofts.sotayvatly10.common.Constant;
import com.vinsofts.sotayvatly10.object.CategoryObject;
import com.vinsofts.sotayvatly10.sqlite.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 20/04/2016.
 */
public class ContentFragment extends BaseFragment implements TextWatcher, AdapterView.OnItemClickListener, View.OnClickListener {
    private Database database;
    private List<CategoryObject> listContent = new ArrayList<>();
    private ListView lstContent;
    private EditText svContent;
    private ImageButton imgContentShare;
    private CategoryAdapter adapter;
    private View view;
    private RelativeLayout reFragmentContent;
    private SwipeRefreshLayout swipeContainer;
    private ImageButton imgBackground;
    private int catId;
    private TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content, viewGroup, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();



        Bundle bundle = getArguments();
        if (bundle != null) {
            catId = bundle.getInt(Constant.BUNDLE_CAT_ID, 0);
        }

        swipeContainer.setOnRefreshListener(new MyRefreshListView(getActivity(), swipeContainer));

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //open database
        database = new Database(getActivity());

        if (catId == 0) {
            //Get chương
            listContent = database.listCateDb(Constant.CATEGORY_TABLE);
        } else {
            //Get bài học trong chương
            listContent = database.listLesson(catId);
        }

        initToolbar();
        initAdapter();

        return view;
    }

    private void initToolbar() {
        if (catId > 0) {
            imgBackground.setImageResource(R.drawable.ic_arrow_left);
            tvTitle.setText(getContext().getResources().getString(R.string.lesson));
        }

    }

    private void initAdapter() {
        adapter = new CategoryAdapter(getActivity(), R.layout.item_category_listview, listContent, database, catId);
        lstContent.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CategoryObject item = adapter.getItem(position);
        if (catId == 0) {
            Intent intent = new Intent(getActivity(), LessonActivity.class);
            intent.putExtra(Constant.BUNDLE_CAT_ID, item.getId());
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), PageDetailActivity.class);
            intent.putExtra(Constant.INTENT_ID_CATEGORY_TABLE, item.getId());
            intent.putExtra(Constant.INTENT_POSITION_LESSON, position);
            startActivity(intent);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgContentShare:
                shareApp();
                break;
            case R.id.imgBackground:

                if (catId > 0) {
                    getActivity().onBackPressed();
                    return;
                }
                Intent intent = new Intent(getActivity(), BackgroundActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void initView() {
        imgBackground = (ImageButton) view.findViewById(R.id.imgBackground);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        lstContent = (ListView) view.findViewById(R.id.lstContent);
        svContent = (EditText) view.findViewById(R.id.svContent);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        imgContentShare = (ImageButton) view.findViewById(R.id.imgContentShare);
        reFragmentContent = (RelativeLayout) view.findViewById(R.id.reFragmentContent);
        svContent.addTextChangedListener(this);
        lstContent.setTextFilterEnabled(true);
        lstContent.setOnItemClickListener(this);
        imgContentShare.setOnClickListener(this);
        imgBackground.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 21) {
            reFragmentContent.setPadding(0, getStatusBarHeight(), 0, 0);
        }


    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
