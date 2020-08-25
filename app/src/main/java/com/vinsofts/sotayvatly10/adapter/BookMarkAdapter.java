package com.vinsofts.sotayvatly10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.object.BookmarkObject;

import java.util.List;

/**
 * Created by HongChien on 26/04/2016.
 */
public class BookMarkAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private List<BookmarkObject> listBookmark;

    public BookMarkAdapter(Context mContext, int mResource, List<BookmarkObject> listBookmark) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.listBookmark = listBookmark;
    }


    @Override
    public int getCount() {
        return listBookmark.size();
    }

    @Override
    public Object getItem(int position) {
        return listBookmark.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_favorite_list_view, parent, false);
            holder = new ViewHolder();
            holder.tvFavoriteBookmark = (TextView) rowView.findViewById(R.id.tvFavoriteBookmark);
            holder.imgIcon = (ImageView) rowView.findViewById(R.id.imgIcon);
            rowView.setTag(holder);
        }
        holder = (ViewHolder) rowView.getTag();
        holder.tvFavoriteBookmark.setText(listBookmark.get(position).getDescription());
        if (listBookmark.get(position).getStoryId() == 0) {
            holder.imgIcon.setImageResource(R.drawable.icon_note);
        } else {
            holder.imgIcon.setImageResource(R.drawable.icon_favorite_detail_selected);
        }
        return rowView;
    }

    private class ViewHolder {
        TextView tvFavoriteBookmark;
        ImageView imgIcon;
    }
}
