package com.company.zwh_rfid.http;

public class HttpURl {

    public static final class PostUrl{
        //测试
        public static final String Urlpost = "http://140.143.53.114:8370/";
        //正式
       // public static final String Urlpost = "http://140.143.53.114:8370/";

        public static final class storage{

        //出库单号
         String getstaId = Urlpost +"ieop_base_mm_mobile/mfrontmmoutboundorder/querypage";
         String getstaIdpas = "{\"conditionMap\":{\"smIsOrderno\":\"\",\"ieopEnterpriseName\":\"\",\"smBjtOrderno\":\"\",\"smSapOrderno\":\"\",\"smProposerName\":\"\",\"smProposerTimeS\":\"\"},\"pageParams\":{\"pageSize\":200,\"pageIndex\":1},\"sortItemMap\":{}}";

        //出库详细
        static String outIDdata = Urlpost + "ieop_base_mm_mobile/mfrontmmoutboundorder/querypage";

        public static final  String outDatafind(String outID){
             String outdataparments = "{\"conditionMap\":{\"smOsOrderno\":\""+outID+"\"},\"pageParams\":{\"pageSize\":10,\"pageIndex\":1},\"sortItemMap\":{}}";

             return outdataparments;
        }


        //入库ID
        public static final String intoId = Urlpost + "ieop_base_mm_mobile/mfrontmminboundorder/querypage";
        public static final String intoIdparments = "{\"conditionMap\":{\"smIsOrderno\":\"\",\"ieopEnterpriseName\":\"\",\"smBjtOrderno\":\"\",\"smSapOrderno\":\"\",\"smProposerName\":\"\",\"smProposerTimeS\":\"\"},\"pageParams\":{\"pageSize\":200,\"pageIndex\":1},\"sortItemMap\":{}}\n";


        //入库data
        public static final String intoIDdatacheckID = Urlpost + "ieop_base_mm_mobile/mfrontmminboundorder/querysingle";

        public static final String intoIDdatacheckIDparnent(String checkid){
            String intoIDdatapaer = "{\"conditionMap\":{\"id\":\""+checkid+"\"}}";

            return intoIDdatapaer;
        }



        public static final String intoIDdata = Urlpost + "ieop_base_mm_mobile/mfrontmminboundorder/queryinfos";
            public static final String getIntodataparments(String pas){
                 String intodataparmentss = "{\"conditionMap\":{\"smIsOrderno\":\""+pas+"\"},\"pageParams\":{\"pageSize\":12},\"sortItemMap\":{}}";

                 return intodataparmentss;
            }
        //入库单提交
        static String into_checksavaurl = Urlpost + "ieop_base_mm_mobile/mfrontmminboundorder/saves";
        static String getInto_checksavaparments = "{\"conditionMap\":{\"smIsOrderno\":\"RK420420063002\",\"cgnFCode\":\"4202\"},\"dataMap\":{\"ents\":[{\"cgnSLocation\":\"2.1.1.1\",\"list\":[{\"cgnSLocation\":\"2.1.1.1\",\"cgnMCode\":\"000000110000000007\",\"cgnMName\":\"35kV动力电缆YJV22－26／353×240\",\"locationNum\":1}]}]}}";
        static String into_savaurl = Urlpost + "ieop_base_mm_mobile/mfrontmminboundorder/confirminboundorder";
        static String into_savapaersments = "{\"conditionMap\":{\"smIsOrderno\":\"RK420420063002\",\"cgnFCode\":\"4202\"}}";

        //退库
        static String storageEXitUrl = Urlpost + "ieop_base_mm_mobile/mfrontmmreturnorder/querypage";
        static String storageEXitparaments = "{\"conditionMap\":{},\"pageParams\":{\"pageSize\":12,\"pageIndex\":1},\"sortItemMap\":{}}";

        //TKdata
        static  String  storageEXitdataUrl = Urlpost + "ieop_base_mm_mobile/mfrontmmreturnorder/queryinfos";
        static  String  storageEXitdatapaements = "{\"conditionMap\":{\"smReturnOrderno\":\"TK420420060801\"},\"pageParams\":{\"pageSize\":12},\"sortItemMap\":{}}";
        //盘点
        static String inventoryurl = Urlpost + "ieop_base_mm_mobile/mfrontmmstocktakingreport/querypage";
        static String inventoryparaments = "{\"conditionMap\":{\"smCloseTimeS\":\"\",\"smCloseTimeE\":\"\",\"smSrNo\":\"\",\"smProposerName\":\"\",\"smSrName\":\"\"},\"pageParams\":{\"pageSize\":10,\"pageIndex\":1},\"sortItemMap\":{}}";
        //inventorydata
        static String inventorydataurl = Urlpost + "ieop_base_mm_mobile/mfrontmmstocktakingreport/queryinfospage";

        static String inventorydataparsments = "{\"conditionMap\":{\"smSrNo\":\"PD420420072201\"},\"pageParams\":{\"pageSize\":10,\"pageIndex\":1},\"sortItemMap\":{}}";
        }
    }
}
