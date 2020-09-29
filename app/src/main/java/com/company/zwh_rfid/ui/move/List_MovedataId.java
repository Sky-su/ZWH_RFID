package com.company.zwh_rfid.ui.move;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstock;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.List;

public class List_MovedataId extends BaseAdapter {

    private List<Stocker_get> mData;
    private Context context;

    public List_MovedataId(List<Stocker_get> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movedataid, null);
            holder = new ViewHolder();

            holder.moveId = (TextView) convertView.findViewById(R.id.text_movedataID);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.moveId.setText(mData.get(position).getGetStockId());
        return convertView;
    }

    private void add(Stocker_get data){
        mData.add(data);
        notifyDataSetChanged();
    }

    public class ViewHolder{


        TextView moveId;

    }
}
