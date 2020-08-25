package com.vinsofts.sotayvatly10.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vinsofts.sotayvatly10.R;
import com.vinsofts.sotayvatly10.object.YourOwnBackgroundObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HongChien on 29/04/2016.
 */
public class YourOwnBackgroundAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private List<YourOwnBackgroundObject> listYourOwn = new ArrayList<>();
    private static LayoutInflater inflater = null;
    YourOwnBackgroundObject object;

    public YourOwnBackgroundAdapter(Context context, int resource, List<YourOwnBackgroundObject> listYourOwn) {
        this.context = context;
        this.resource = resource;

        this.listYourOwn = listYourOwn;
        getAllShownImagesPath();


    }

    @Override
    public int getCount() {
        return listYourOwn.size();
    }

    @Override
    public Object getItem(int position) {
        return listYourOwn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_background, parent, false);
            holder = new ViewHolder();
            holder.imgBackground = (ImageView) rowView.findViewById(R.id.imgBackground);
            holder.btnChooseImage = (ImageButton) rowView.findViewById(R.id.btnChooseImage);
            rowView.setTag(holder);
        }
        holder = (ViewHolder) rowView.getTag();
        Glide.with(context).load(listYourOwn.get(position).getPath())
                .centerCrop().into(holder.imgBackground);

        if (listYourOwn.get(position).getCheckChoose() == R.drawable.icon_check_selected) {
            holder.btnChooseImage.setVisibility(View.VISIBLE);
        } else {
            holder.btnChooseImage.setVisibility(View.GONE);
        }
        holder.btnChooseImage.setImageResource(listYourOwn.get(position).getCheckChoose());

        holder.imgBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listYourOwn.size(); i++) {
                    listYourOwn.get(i).setCheckChoose(R.drawable.icon_check);
                }
                listYourOwn.get(position).setCheckChoose(R.drawable.icon_check_selected);

                SharedPreferences sharedPref = context.getSharedPreferences("background",
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                String selected_background = listYourOwn.get(position).getPath();
                Log.d("Chien_path1", selected_background);
                editor.putString("background_path", selected_background);
                editor.putInt("check", 1);
                editor.putInt("position_choose_yourown", position);
                editor.apply();


                notifyDataSetChanged();
            }
        });





        return rowView;
    }

    public class ViewHolder{
        ImageView imgBackground;
        ImageButton btnChooseImage;
    }

    private List<YourOwnBackgroundObject> getAllShownImagesPath() {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

//        ArrayList<YourOwnBackgroundObject> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = context.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            object=new YourOwnBackgroundObject();
            object.setPath(absolutePathOfImage);
            listYourOwn.add(object);
        }
        return listYourOwn;
    }
}
