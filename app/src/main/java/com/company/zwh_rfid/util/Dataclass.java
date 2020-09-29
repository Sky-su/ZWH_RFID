package com.company.zwh_rfid.util;

import android.content.Context;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public  class Dataclass {
    public static final class DateTableInface {

        /**
         * 实际库接口




         static String loginyanz = "http://123.207.175.212:8170/ieop_base_mobile/mfrontmalluserlogin/getenc";
         static String login = "http://123.207.175.212:8170/ieop_base_mobile/mfrontmalluserlogin/login";

         //出库单接口
         static String getoutstateid = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmoutboundorder/querypage";

         static String getoutstatedata = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmoutboundorder/queryInfos";
         static String getoutstatedataWhileSave = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmoutboundorder/saves";

         static String getoutstatedatasumit = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmminboundorder/submit";

         //移库接口
         static String moveState = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmmovestorage/submit";

         //盘点接口
         static String chenck = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmstocktakingreport/querypage";
         static String chencksave = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmstocktakingreport/updates";
         static String checksubmit = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmminboundorder/submit";

         //退库接口
         static String exitstateid = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmreturnorder/querypage";
         static String exitstatedata = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmreturnorder/queryinfos";

         static String exitstatesave = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmmreturnorder/saves";
         static String exitstasumbit = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmminboundorder/submit";
         //入库单

         static String getstateid = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmminboundorder/querypage";
         static String getstatesave = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmminboundorder/saves";
         static String getStateSubmit = "http://123.207.175.212:8370/ieop_base_mm_mobile/mfrontmminboundorder/confirminboundorder";

         */

        /**
         * 测试
         */

        public static final String loginyanz = "http://140.143.53.114:8170/ieop_base_mobile/mfrontmalluserlogin/getenc";
        public static final String login = "http://140.143.53.114:8170/ieop_base_mobile/mfrontmalluserlogin/login";

        //出库单接口
        public static final String getoutstateid = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmoutboundorder/querypage";

        public static final String getoutstatedata = "http://140.143.53.114/ieop_base_mm_mobile/mfrontmmoutboundorder/queryInfos";
        public static final String getoutstatedataWhileSave = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmoutboundorder/saves";

        public static final String getoutstatedatasumit = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmminboundorder/submit";

        //移库接口
        public static final String moveState = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmmovestorage/submit";

        //盘点接口
        public static final String chenck = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmstocktakingreport/querypage";
        public static final String chencksave = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmstocktakingreport/updates";
        public static final String checksubmit = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmminboundorder/submit";

        //退库接口
        public static final String exitstateid = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmreturnorder/querypage";
        public static final String exitstatedata = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmreturnorder/queryinfos";

        public static final String exitstatesave = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmmreturnorder/saves";
        public static final String exitstasumbit = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmminboundorder/submit";
        //入库单

        public static final String getstateid = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmminboundorder/querypage";
        public static final String getstatesave = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmminboundorder/saves";
        public static final String getStateSubmit = "http://140.143.53.114:8370/ieop_base_mm_mobile/mfrontmminboundorder/confirminboundorder";


    }

    //请求单号参数
    public static final class DataJson{
        //入库
        public static final String getstateId = "{\"conditionMap\":{\"smIsOrderno\":\"\",\"ieopEnterpriseName\":\"\",\"smBjtOrderno\":\"\",\"smSapOrderno\":\"\",\"smProposerName\":\"\",\"smProposerTimeS\":\"\"},\"pageParams\":{\"pageSize\":200,\"pageIndex\":1},\"sortItemMap\":{}}";


        //出库
        public static final String getoutid = "{\"conditionMap\":{\"smJxOrderno\":\"\",\"smOsOrderno\":\"\",\"smOsType\":\"\",\"smProposerName\":\"\",\"smProposerTime\":\"\"},\"pageParams\":{\"pageSize\":100,\"pageIndex\":1},\"sortItemMap\":{}}";

        //库存
        public static final String checkID= "{\"conditionMap\":{\"smCloseTimeS\":\"\",\"smCloseTimeE\":\"\",\"smSrNo\":\"\",\"smProposerName\":\"\",\"smSrName\":\"\"},\"pageParams\":{\"pageSize\":100,\"pageIndex\":1},\"sortItemMap\":{}}";

        //退库单
        public static final String exitId = "{\"conditionMap\":{\"smReturnOrderno\":\"\",\"smProposerTimeS\":\"\",\"smProposerTimeE\":\"\",\"smProposerName\":\"\",\"smOsOrderno\":\"\"},\"pageParams\":{\"pageSize\":100,\"pageIndex\":1},\"sortItemMap\":{}}";



    }
    //查询出库单明细
    public String  exitIDInfo (String exitId){

        String s = "{\"conditionMap\":{\"smReturnOrderno\":\""+exitId+"\"},\"pageParams\":{\"pageSize\":10,\"pageIndex\":1},\"sortItemMap\":{}}";
        return s;
    }
    //库存清单
    public String h(String checkID){

        String s = "{\"conditionMap\":{\"smSrNo\":\""+checkID+"\"},\"pageParams\":{\"pageSize\":10,\"pageIndex\":1},\"sortItemMap\":{}}";
         return s;
    }






}

