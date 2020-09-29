package com.company.zwh_rfid.ui.move;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.List;

public class List_movestock extends BaseAdapter {

    private List<Stocker_getdata> mData;
    private Context context;
    private int currentItem = -1;//listview中显示位置，取默认值为-1。


    public List_movestock(List<Stocker_getdata> mData, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movestock, null);
            holder = new ViewHolder();

            holder.localid = (TextView) convertView.findViewById(R.id.text_MOvestock1);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.localid.setText(mData.get(i).getLocationID());

        //notifyDataSetChanged();

        return convertView;
    }
    public class ViewHolder{


        TextView localid;

    }
}
