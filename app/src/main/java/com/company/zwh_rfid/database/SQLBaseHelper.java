package com.company.zwh_rfid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.HashSet;
import java.util.Set;

public class SQLBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "zwh.db";
    private static Set<Stocker_getdata> stocker_getdata;

    public SQLBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //主数据

        db.execSQL("create table if not exists " + SQLSchema.DateTable.receivedateTAblename +
                "(" + " id Integer primary key autoincrement ,"+
                        SQLSchema.DateTable.cgn_storage_location_material.location_id +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_code +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_short_desc +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_product_series +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.mg_c_code +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.mg_c_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_brand +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_brand_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_brand_ename +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_fields +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_fields_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_product +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_product_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_apply_position +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_apply_position_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_apply_model +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_apply_model_name +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.material_type +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_m_unit +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_f_code +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_s_code +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_s_location +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_s_shelf +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_s_layer +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.cgn_s_place +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.ts +" Integer "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.dr +" Integer "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.creator +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.create_time +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.modifier +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.modified_time +" next "+"," +
                SQLSchema.DateTable.cgn_storage_location_material.enterprise_id +" next "+
                ")");


        //入库单
        db.execSQL("create table if not exists " + SQLSchema.DateTable.getStockIdInfos +
                 "("  +
                "_id integer primary key autoincrement, " +
                SQLSchema.DateTable.ColsgetstockId.smIsOrderno + " next "+","  +
                SQLSchema.DateTable.ColsgetstockId.smProposerCode+" next "+ "," +
                SQLSchema.DateTable.ColsgetstockId.smacceptancefname+" next " +"," +
                SQLSchema.DateTable.ColsgetstockId.smAcceptanceFcode+" next " +"," +
                SQLSchema.DateTable.ColsgetstockId.smAcceptanceType+" next "+"," +
                SQLSchema.DateTable.ColsgetstockId.smStatus+" integer "+"," +
                SQLSchema.DateTable.ColsgetstockId.rfidinfos +" next "+"," +
                SQLSchema.DateTable.ColsgetstockId.creator+" next " +"," +
                SQLSchema.DateTable.ColsgetstockId.create_time +" next "+
                ")");

        db.execSQL("create table if not exists " + SQLSchema.DateTable.mm_outbound_order +
                "("  +
                "_id integer primary key autoincrement, " +
                SQLSchema.DateTable.mm_outbound_orderclass.sm_os_orderno+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.sm_jx_orderno+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.sm_os_type+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.sm_status+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.cgn_f_code+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.cgn_s_code+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.cgn_m_name+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.cgn_m_short_desc+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.cgn_m_unit+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.propose_os_num+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.rfidinfos+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.cgn_s_location+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.creator+ " next "+","  +
                SQLSchema.DateTable.mm_outbound_orderclass.create_time+ " next "+
                ")");

        db.execSQL("create table if not exists " + SQLSchema.DateTable.mm_return_order +
                "("  +
                "_id integer primary key autoincrement, " +
                SQLSchema.DateTable.mm_return_orderclass.sm_return_orderno+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.sm_os_orderno+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.cgn_m_code+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.cgn_m_name+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.cgn_m_short_desc+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.return_num+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.cgn_s_location+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.rfidIDinfos+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.creator+ " next "+","  +
                SQLSchema.DateTable.mm_return_orderclass.create_time+ " next "+
                ")");

        db.execSQL("create table if not exists " + SQLSchema.DateTable.mm_movestorage +
                "("  +
                "_id integer primary key autoincrement, " +
                SQLSchema.DateTable.mm_movestorageclass.cgn_m_code+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_m_name+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_m_short_desc+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_m_unit+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_f_code+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_f_name+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_s_code_s+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_s_name_s+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_s_location_s+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_s_code_d+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.cgn_s_location_d+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.movestorage_num+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.rfidinfos+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.creator+ " next "+","  +
                SQLSchema.DateTable.mm_movestorageclass.create_time+ " next "+
                ")");
        db.execSQL("create table if not exists " + SQLSchema.DateTable.mm_stocktaking_report +
                "("  +
                "_id integer primary key autoincrement, " +
                SQLSchema.DateTable.mm_stocktaking_reportclass.sm_sr_no+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.sm_sr_name+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.sm_sr_status+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.cgn_f_code+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.cgn_f_name+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.cgn_m_code+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.cgn_m_name+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.now_num+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.cgn_m_unit+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.rfidinfos+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.creator+ " next "+"," +
                SQLSchema.DateTable.mm_stocktaking_reportclass.create_time+ " next "+

                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String insertdataID(SQLiteDatabase database,String smIsOrderno,String smProposerCode,
                               String smacceptancefname,String creator,String smAcceptanceFcode,
                              String smAcceptanceType, String smStatus,String fileGroup){

        ContentValues values = new ContentValues();
        values.put("smIsOrderno",smIsOrderno);
        values.put("smProposerCode",smProposerCode);
        values.put("smacceptancefname",smacceptancefname);
        values.put("creator",creator);
        values.put("smAcceptanceFcode",smAcceptanceFcode);
        values.put("smAcceptanceType",smAcceptanceType);
        values.put("smStatus",smStatus);
        values.put("fileGroup",fileGroup);

        try {
            database.insert(SQLSchema.DateTable.NAME,null,values);

        }catch (Exception e){
            return "-1";
        }


        return "0";
    }

    public String insertgetdata(SQLiteDatabase database,String goods,String goodsID,String goodRFid,String seatdata,String status,String getId){

        ContentValues values = new ContentValues();
        values.put("goods",goods);
        values.put("goodId",goodsID);
        values.put("goodRFid",goodRFid);
        values.put("seatdata",seatdata);
        values.put("status",status);
        values.put("status",status);
        values.put("receivedateid",getId);
        try {
            database.insert(SQLSchema.DateTable.NAME,null,values);

        }catch (Exception e){
            return "-1";
        }


        return "0";
    }

    public void quergetdataId(SQLiteDatabase database ){


    }

    public Set<Stocker_getdata> querdata(String s, SQLiteDatabase databaseHelper){
        stocker_getdata = new HashSet<>();


        Cursor cursor = databaseHelper.rawQuery("select * from "+ SQLSchema.DateTable.NAME+" " +"where status ="+ s,null);

        if (cursor != null){
            while(cursor.moveToNext()){
                Stocker_getdata d = new Stocker_getdata();
                String name = cursor.getString(cursor.getColumnIndex("goods"));
                String nameID = cursor.getString(cursor.getColumnIndex("goodId"));
                String rfid = cursor.getString(cursor.getColumnIndex("goodRFid"));
                d.setMaterialsName(name);
                d.setMaterialsID(nameID);
                d.setRfidID(rfid);
                stocker_getdata.add(d);
            }
            // 关闭游标，释放资源
            cursor.close();
        }else {

            cursor.close();
            databaseHelper.close();
        }
        return stocker_getdata;
    }

}
