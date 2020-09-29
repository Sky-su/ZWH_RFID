package com.company.zwh_rfid.ui.getstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.uientity.StoragegetID;

import java.util.List;

public class List_getstock extends BaseAdapter {

    private List<StoragegetID> mData;
    private Context context;
    private int currentItem = -1;//listview中显示位置，取默认值为-1。


    public List_getstock(List<StoragegetID> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
       ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_getstock, null);
            holder = new ViewHolder();

            holder.getstockid = (TextView) convertView.findViewById(R.id.text_getstock1);
            holder.getplanId = (TextView) convertView.findViewById(R.id.text_getstockgong);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getstockid.setText(mData.get(i).getSmIsOrderno());
        holder.getplanId.setText(mData.get(i).getSmAcceptanceFcode());
        //notifyDataSetChanged();

        return convertView;
    }
    public class ViewHolder{


        TextView getstockid;
        TextView getplanId;
    }
}
