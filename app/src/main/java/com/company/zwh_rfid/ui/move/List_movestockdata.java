package com.company.zwh_rfid.ui.move;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstockData;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.List;

public class List_movestockdata extends BaseAdapter {

    private List<Stocker_getdata> mData;
    private Context context;

    public List_movestockdata(List<Stocker_getdata> mData, Context context) {
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

        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movereaddata, null);
            viewHolder = new ViewHolder();
            viewHolder.stockName = (TextView) convertView.findViewById(R.id.item_wupingname);
            viewHolder.stockerId = (TextView) convertView.findViewById(R.id.item_wupingID);
            viewHolder.rfidId = (TextView) convertView.findViewById(R.id.item_RFID);
            viewHolder.localId = (TextView) convertView.findViewById(R.id.item_localID);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();

        }
        viewHolder.rfidId.setText(mData.get(position).getRfidID());
        viewHolder.stockName.setText(mData.get(position).getMaterialsName());
        viewHolder.localId.setText(mData.get(position).getLocationID());
        viewHolder.stockerId.setText(mData.get(position).getMaterialsID());

        return convertView;
    }

    public class ViewHolder{
        TextView localId,rfidId,stockerId,stockName;

    }
}
