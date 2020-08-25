package com.vinsofts.sotayvatly10.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.object.BackgroundObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 22/04/2016.
 */
public class BackgroundAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private List<BackgroundObject> lst = new ArrayList<>();
    private static LayoutInflater inflater = null;
    Holder holder;

    public BackgroundAdapter(Context context, int resource, List<BackgroundObject> lst) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.lst = lst;
        this.resource = resource;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        ImageView img;
        ImageButton btnChooseImage;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        holder = new Holder();
        View rowView= convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_background, null);
            holder.img = (ImageView) rowView.findViewById(R.id.imgBackground);
            holder.btnChooseImage = (ImageButton) rowView.findViewById(R.id.btnChooseImage);
            rowView.setTag(holder);
        }



        holder = (Holder) rowView.getTag();
        holder.img.setImageResource(lst.get(position).getIcon());
        if (lst.get(position).getCheckChoose() == R.drawable.icon_check_selected) {
            holder.btnChooseImage.setVisibility(View.VISIBLE);
        } else {
            holder.btnChooseImage.setVisibility(View.GONE);
        }
        holder.btnChooseImage.setImageResource(lst.get(position).getCheckChoose());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < lst.size(); i++) {
                    lst.get(i).setCheckChoose(R.drawable.icon_check);
                }

                lst.get(position).setCheckChoose(R.drawable.icon_check_selected);

                SharedPreferences sharedPref = context.getSharedPreferences("background",
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                int selected_background = lst.get(position).getIcon();
                editor.putInt("background_resource", selected_background);
                editor.putInt("check", 0);
                editor.putInt("position_choose", position);
                editor.apply();

                notifyDataSetChanged();
            }
        });


//        Log.d("ChienAdapter 1", "run");
//        SharedPreferences sharedPref = context.getSharedPreferences("background",
//                Context.MODE_PRIVATE);
//        int dem = sharedPref.getInt("check", -1);
//        Log.d("ChienAdapter", dem + "");
//        if (dem == Constant.SHARED_STILL_BACKGROUND) {
//
//        }


        return rowView;
    }

}
