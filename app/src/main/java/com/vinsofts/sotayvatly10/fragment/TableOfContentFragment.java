package com.vinsofts.sotayvatly10.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.common.IconTreeItemHolder;
import com.vinsofts.sotayvatly10.common.ProfileHolder;
import com.vinsofts.sotayvatly10.object.TableOfContentObject;
import com.vinsofts.sotayvatly10.sqlite.TableOfContentDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongChien on 27/04/2016.
 */
public class TableOfContentFragment extends Fragment {

    public interface TableContentFragmentListener {
        void onClickItem(int pageId, int storyId);
    }

    public TableContentFragmentListener listener;

    private TableOfContentDatabase databaseTOC;
    private List<TableOfContentObject> listTableOfContent = new ArrayList<>();
    private List<TableOfContentObject> listFirst = new ArrayList<>(); //with parentId = 0;
    private List<TableOfContentObject> listSecond = new ArrayList<>();
    private List<TableOfContentObject> listThird = new ArrayList<>();
    private TableOfContentObject tableOfContentObject;
    final TreeNode root = TreeNode.root();
    private AndroidTreeView tView;
    private TreeNode secondNode;
    private DrawerLayout drawerLayout;
    private FrameLayout containerTreeView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_of_content_layout, null, false);
        final ViewGroup containerView = (ViewGroup) view.findViewById(R.id.container);
        view.findViewById(R.id.status_bar).setVisibility(View.GONE);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerLayout);
        containerTreeView = (FrameLayout) getActivity().findViewById(R.id.containerTreeView);
        getListData();

        Bundle bundle = getArguments();
        int idCate = bundle.getInt("PASS_ID");

        for (int i = 0; i < listFirst.size(); i++) {
            if (listFirst.get(i).getStoryId() == idCate) {
//                TreeNode firstNode = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.drawable.icon_treeview_parent, listFirst.get(i).getName(),
//                        listFirst.get(i).getPageDetailId(),
//                        listFirst.get(i).getStoryId())).setViewHolder(new ProfileHolder(getActivity()));
//                root.addChild(firstNode);


                for (int j = 0; j < listSecond.size(); j++) {

                    if (listFirst.get(i).getId() == listSecond.get(j).getParentId()) {

                        secondNode = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_keyboard_arrow_down, listSecond.get(j).getName(),
                                listSecond.get(j).getPageDetailId(),
                                listSecond.get(j).getStoryId())).setViewHolder(new ProfileHolder(getActivity()));

                        root.addChild(secondNode);

                        secondNode.setClickListener(new TreeNode.TreeNodeClickListener() {
                            @Override
                            public void onClick(TreeNode node, Object value) {
                                IconTreeItemHolder.IconTreeItem item = (IconTreeItemHolder.IconTreeItem) value;
                                listener.onClickItem(item.pageId, item.storyId);
                                drawerLayout.closeDrawer(containerTreeView);
                            }
                        });
                    }
//                    for (int z = 0; z < listThird.size(); z++) {
//                        if (listSecond.get(j).getId() == listThird.get(z).getParentId() && listSecond.get(j).getParentId() == listFirst.get(i).getId()) {
//                            TreeNode thirdNode = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth,
//                                    listThird.get(z).getName(),
//                                    listThird.get(z).getPageDetailId(),
//                                    listThird.get(z).getStoryId()))
//                                    .setViewHolder(new ProfileHolder(getActivity()));
//                            secondNode.addChild(thirdNode);
//
//
//                            thirdNode.setClickListener(new TreeNode.TreeNodeClickListener() {
//                                @Override
//                                public void onClick(TreeNode node, Object value) {
//                                    IconTreeItemHolder.IconTreeItem item = (IconTreeItemHolder.IconTreeItem) value;
//                                    listener.onClickItem(item.pageId, item.storyId);
//                                    drawerLayout.closeDrawer(containerTreeView);
//                                }
//                            });
//                        }
//
//                    }
                }
            }
        }

        tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        containerView.addView(tView.getView());

        //Show all
        tView.expandAll();

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
        return view;
    }

    private void addObject(int j) {
        tableOfContentObject = new TableOfContentObject();
        tableOfContentObject.setName(listTableOfContent.get(j).getName());
        tableOfContentObject.setId(listTableOfContent.get(j).getId());
        tableOfContentObject.setStoryId(listTableOfContent.get(j).getStoryId());
        tableOfContentObject.setPageDetailId(listTableOfContent.get(j).getPageDetailId());
        tableOfContentObject.setParentId(listTableOfContent.get(j).getParentId());
    }

    private void getListData() {
        databaseTOC = new TableOfContentDatabase(getActivity());
        databaseTOC.openDatabase();
        //Get list Level 0
        listTableOfContent = databaseTOC.getListRoot();



        for (int i = 0; i < listTableOfContent.size(); i++) {
            if (listTableOfContent.get(i).getParentId() == 0) {
                addObject(i);
                listFirst.add(tableOfContentObject);
            }
        }

        for (int i = 0; i < listFirst.size(); i++) {
            for (int j = 0; j < listTableOfContent.size(); j++) {
                if (listFirst.get(i).getId() == listTableOfContent.get(j).getParentId()) {
                    addObject(j);
                    listSecond.add(tableOfContentObject);
                }
            }
        }

        for (int i = 0; i < listSecond.size(); i++) {
            for (int j = 0; j < listTableOfContent.size(); j++) {
                if (listSecond.get(i).getId() == listTableOfContent.get(j).getParentId()) {
                    addObject(j);
                    listThird.add(tableOfContentObject);
                }
            }
        }


//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //convert list to json and save to shared preferences
        SharedPreferences sharedPrefs = getActivity().getSharedPreferences("tableofcontent", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listSecond);
        editor.putString("ListTableOfContent", json);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(databaseTOC!=null) databaseTOC.closeDatabase();
    }
}
