package com.company.zwh_rfid;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.zwh_rfid.database.SQLFunction;
import com.company.zwh_rfid.http.HttpURl;
import com.company.zwh_rfid.uientity.MainEnt;
import com.company.zwh_rfid.uientity.StoragegetID;
import com.company.zwh_rfid.util.Base64;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JionDB {
    //okhttp
    private Context context;
    OkHttpClient client;
    List<Cookie> cookies = null;
    SQLFunction function ;


    public JionDB(Context context, OkHttpClient client) {
        this.context = context;
        this.client = client;
    }

    //入库单ID信息
    public Set<StoragegetID> jiondb_gettable( )  {
        Set<StoragegetID> listtest = new HashSet<>();
        Set<String> keys = new HashSet<String>();
        JSONObject js = null;
        try {
            js = sendByOKHttpgetdata(HttpURl.PostUrl.storage.intoId,HttpURl.PostUrl.storage.intoIdparments);
            listtest = (Set<StoragegetID>) JSON.parseArray(js.getString("ents"),StoragegetID.class);

            return listtest;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    //入库信息
//    public boolean jiondb_getdata(){
//      function = new SQLFunction();
//        Set<StoragegetID> ids = jiondb_gettable();
//        try {
//            for (StoragegetID key:ids
//                 ) {
//                List<MainEnt> lS = new ArrayList<>();
//                String result = HttpURl.PostUrl.storage.intoIDdatacheckIDparnent(key.getSmIsOrderno());
//                JSONObject js = sendByOKHttpgetdata(HttpURl.PostUrl.storage.intoIDdatacheckID,result);
//                lS = JSON.parseArray(js.getString("ents"),MainEnt.class);
//
//                for (MainEnt k:lS ){
//                    Object [] data = {key.getSmIsOrderno(),key.getSmAcceptanceFcode(),k.getCgnMCode(),k.getCgnMName(),
//                    null,null,null};
//                    String sql = SQLSchema.DateTable.Cols.deliveryID + "," +
//                            SQLSchema.DateTable.Cols.deliveryfactory + "," +
//                            SQLSchema.DateTable.Cols.materialsID + "," +
//                            SQLSchema.DateTable.Cols.materialsName + "," +
//                            SQLSchema.DateTable.Cols.rfidID + "," +
//                            SQLSchema.DateTable.Cols.locationID + "," +
//                            SQLSchema.DateTable.Cols.state;
//                    function.insert(context,SQLSchema.DateTable.getStockIdInfos,sql,data);
//                }
//                return true;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    //出库单信息





    //获取信息
    public JSONObject sendByOKHttpgetdata(final String url1, final String parments) throws IOException {
        JSONObject a = new JSONObject();
        String decodeStr = null;
        a.put("params", Base64.encode(URLEncoder.encode(parments, "UTF-8")));
        JSONObject jsonObjecter = new JSONObject();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),a.toString());
        Request request = new Request.Builder().url(url1)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.code() == 200) {
            String result = response.body().string();
            jsonObjecter = (JSONObject) JSONObject.parse(result);
            try {
                String  encodeData = jsonObjecter.getString("retData");
                decodeStr = URLDecoder.decode(Base64.decode(encodeData), "UTF-8");
                Log.d("获取值：",decodeStr);
            }catch (Exception e){
                e.getMessage();
            }
        }

        return JSONObject.parseObject(decodeStr);
    }
}
