package com.company.zwh_rfid.ui.getstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import com.company.zwh_rfid.R;

import java.util.LinkedList;
import java.util.List;

public class List_getstockData extends BaseAdapter {

    private List<Stocker_getdata> mData;
    private Context context;
    Fragment fragment;
    private int currentItem = -1;//listview中显示位置，取默认值为-1。


    public List_getstockData(List<Stocker_getdata> mData, Context context) {
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_getstockdata1, null);
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
     void sankwID(int ii,int num,String ker,int old){
        if (old-num==0){
            //mData.get(ii).setNumber(num);
            mData.get(ii).setLocationID(ker);
            notifyDataSetChanged();
        }else if (old-num>=0) {
            mData.get(ii).setNumber(num);
            mData.get(ii).setLocationID(ker);
            add(mData.get(ii),old,num);
        }



    }
    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }
    public void add(Stocker_getdata data1,int old,int yu) {
       Stocker_getdata data = new Stocker_getdata();
        data.setMaterialsName(data1.getMaterialsName());
        data.setMaterialsID(data1.getMaterialsID());
        data.setNumber(old-yu);
        mData.add(data);
        notifyDataSetChanged();
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
