package com.company.zwh_rfid.ui.gooutstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstockData;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.LinkedList;
import java.util.List;

public class List_getoudata extends BaseAdapter {

    private List<Stocker_getdata> mData;
    private Context context;
    private int currentItem = -1;//listview中显示位置，取默认值为-1。

    public List_getoudata(List<Stocker_getdata> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return  mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {

          ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_getoutdata, null);
            holder = new ViewHolder();

            holder.wuzName =(TextView) convertView.findViewById(R.id.dataName);
            holder.kuweiId = (TextView)convertView.findViewById(R.id.datakuweiID);

            holder.wuNUb = (TextView)convertView.findViewById(R.id.getdata_wuznumber) ;
            holder.wuzID = (TextView)convertView.findViewById(R.id.getdata_wuzID) ;


            holder.lay1 = (ConstraintLayout) convertView.findViewById(R.id.line_getdata1);
            holder.lay2 =(ConstraintLayout)convertView.findViewById(R.id.getdatalay2);
            convertView.setTag(holder);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.wuzID.setText(mData.get(i).getMaterialsID());
        holder.wuzName.setText(mData.get(i).getMaterialsName());
        holder.wuNUb.setText(String.valueOf(mData.get(i).getNumber()));
        holder.kuweiId.setText(String.valueOf(mData.get(i).getLocationID()));

        if (currentItem == i){
            holder.lay2.setVisibility(View.VISIBLE);
        }else {
            holder.lay2.setVisibility(View.GONE);
        }
        holder.lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = i;
                if (tag == currentItem){
                    currentItem = -1;

                }else {
                    currentItem = tag;
                }
                notifyDataSetChanged();
            }
        });
        return convertView;


    }
    public void clear() {
        if (mData != null){
            mData.clear();
        }
        notifyDataSetChanged();
    }

    public void add(Stocker_getdata data1) {
        mData.add(data1);
        notifyDataSetChanged();
    }

    public void additem(String data){
        if (mData == null) {
            mData = new LinkedList<>();
        }else {
            Boolean td = false;
            for (Stocker_getdata item:mData) {
                if (item.getMaterialsID().equals(data)){
                    item.setNumber(item.getNumber()+1);
                    notifyDataSetChanged();
                    td = true;
                    break;
                }
            }
            if (td == false) mData.add(new Stocker_getdata("data",data,"35kV动力电缆YJLV32","data",1,1));
            notifyDataSetChanged();
        }

    }
    public class ViewHolder{

        ConstraintLayout lay1;
        ConstraintLayout lay2;
        TextView kuweiId;
        TextView wuzName;
        TextView wuNUb;
        TextView wuzID;
        TextView rfidID;
    }
}
