package com.sunday.goodhobby.goodhobby.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunday.goodhobby.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class HobbyAdapter extends ArrayAdapter<Hobby> {
    private int resourceid;
    public HobbyAdapter(Context context, int resource, List<Hobby> objects) {
        super(context, resource, objects);
        resourceid=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hobby hobby=getItem(position);
        View view;
        ViewHolder viewHolder;
         if(convertView==null) {
             view = LayoutInflater.from(getContext()).inflate(resourceid, null);
             viewHolder = new ViewHolder();
             viewHolder.textView = (TextView) view.findViewById(R.id.name);
             viewHolder.imageView= (ImageView) view.findViewById(R.id.hobby_image);
             //viewHolder.textView.setText(hobby.getName());
             view.setTag(viewHolder);
         }else{
             view=convertView;
             viewHolder= (ViewHolder) view.getTag();
         }
        viewHolder.textView.setText(hobby.getName());
        viewHolder.imageView.setImageResource(R.mipmap.target_icon);
        Log.d("getview",hobby.getName());

        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
