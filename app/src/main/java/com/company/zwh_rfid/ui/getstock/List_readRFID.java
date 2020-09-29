package com.company.zwh_rfid.ui.getstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.zwh_rfid.R;

import java.util.List;

public class List_readRFID extends BaseAdapter {

    private List<Stocker_getdata> mData;
    private Context context;

    public List_readRFID(List<Stocker_getdata> mData, Context context) {
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

        intit holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_readdataitem, null);
            holder = new intit();

            holder.readname = (TextView) convertView.findViewById(R.id.item_wupingname);
            holder.readID = (TextView) convertView.findViewById(R.id.item_wupingID);
            holder.readRFID = (TextView) convertView.findViewById(R.id.item_RFID);
            holder.readimage = convertView.findViewById(R.id.imageread);
            convertView.setTag(holder);
        }else {
            holder = (intit) convertView.getTag();
        }

        holder.readRFID.setText(mData.get(i).getRfidID());
        holder.readname.setText(mData.get(i).getMaterialsName());
        holder.readID.setText(mData.get(i).getMaterialsID());
        if (mData.get(i).state == 0){
            holder.readimage.setBackgroundResource(R.mipmap.readerro);
        }else if (mData.get(i).state == 1){
            holder.readimage.setBackgroundResource(R.mipmap.sucess);

        }



        //notifyDataSetChanged();

        return convertView;
    }
    public void aletate(Stocker_getdata stocker_getdata){
        stocker_getdata.setState(1);
        notifyDataSetChanged();
    }


    private class intit{
        TextView readRFID;
        TextView readname;
        TextView readID;
        ImageView readimage;
    }

}
