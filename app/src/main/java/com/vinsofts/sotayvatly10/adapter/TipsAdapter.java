package com.vinsofts.sotayvatly10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.object.TipsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 25/04/2016.
 */
public class TipsAdapter extends BaseAdapter {
    Context context;
    int resource;
    List<TipsObject> lst = new ArrayList<>();
    private static LayoutInflater inflater = null;

    public TipsAdapter(Context context, int resouce, List<TipsObject> lst) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.lst = lst;
        this.resource = resouce;
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
        TextView txtTitle;
        TextView url;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_tips, null);
            holder.img = (ImageView) rowView.findViewById(R.id.imgBackground);
            holder.txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);

            rowView.setTag(holder);
        }

        holder = (Holder) rowView.getTag();
        holder.img.setImageResource(lst.get(position).getIcon());
        holder.txtTitle.setText(lst.get(position).getTitle());

        return rowView;
    }

}
