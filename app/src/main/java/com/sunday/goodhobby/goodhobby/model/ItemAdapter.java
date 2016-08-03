package com.sunday.goodhobby.goodhobby.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sunday.goodhobby.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ItemAdapter extends ArrayAdapter<Item>{
    private int resourceId;
    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Item item=getItem(position);
        ViewHolder viewHolder;
        View view;
        if(convertView==null){
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        viewHolder =new ViewHolder();
        viewHolder.itemName=(TextView)view.findViewById(R.id.item_name);
        view.setTag(viewHolder);}
        else{
            view=convertView;
            viewHolder= (ViewHolder) convertView.getTag();
        }
        TextView itemName=viewHolder.itemName;
        itemName.setText(item.getName());
        return view;
    }
    class ViewHolder{
        TextView itemName;
    }
}
