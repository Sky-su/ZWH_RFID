package com.company.zwh_rfid.database;

public class SQLSchema {

    public static final class DateTable {
        public static final String NAME = "getStockID";
        public static final String receivedateTAblename = "cgn_storage_location_material";//主库
        public static final String getStockIdInfos = "getStockIDdatasinfo";//入库
        public static final String mm_outbound_order = "mm_outbound_order";//出库
        public static final String mm_return_order = "mm_return_order";//退库
        public static final String mm_movestorage = "mm_movestorage"; //移库
        public static final String mm_stocktaking_report = "mm_stocktaking_report";//盘点


        public static final class Cols {
            public static final String goods = "goods";
            public static final String goodId = "goodId";
            public static final String goodRFid = "goodRFid";
            public static final String status = "status";
            public static final String receivedateid = "receivedateid";
            public static final String seatdata = "seatdata";
        }
        //主库存参数
        public static final class cgn_storage_location_material{
            public static final String location_id = "location_id";
            public static final String cgn_m_code = "cgn_m_code";
            public static final String cgn_m_name = "cgn_m_name";
            public static final String cgn_m_short_desc= "cgn_m_short_desc";
            public static final String cgn_m_product_series = "cgn_m_product_series";
            public static final String mg_c_code = "mg_c_code";
            public static final String mg_c_name = "mg_c_name";
            public static final String cgn_m_brand = "cgn_m_brand";
            public static final String cgn_m_brand_name = "cgn_m_brand_name";
            public static final String cgn_m_brand_ename = "cgn_m_brand_ename";
            public static final String cgn_m_fields = "cgn_m_fields";
            public static final String cgn_m_fields_name = "cgn_m_fields_name";
            public static final String cgn_m_product = "cgn_m_product";
            public static final String cgn_m_product_name = "cgn_m_product_name";
            public static final String cgn_m_apply_position= "cgn_m_apply_position";
            public static final String cgn_m_apply_position_name = "cgn_m_apply_position_name";
            public static final String cgn_m_apply_model = "cgn_m_apply_model";
            public static final String cgn_m_apply_model_name = "cgn_m_apply_model_name";
            public static final String material_type = "material_type";
            public static final String cgn_m_unit = "cgn_m_unit";
            public static final String cgn_f_code = "cgn_f_code";
            public static final String cgn_s_code = "cgn_s_code";
            public static final String cgn_s_location = "cgn_s_location";
            public static final String cgn_s_shelf = "cgn_s_shelf";
            public static final String cgn_s_layer = "cgn_s_layer";
            public static final String cgn_s_place = "cgn_s_place";
            public static final String ts = "ts";
            public static final String dr = "dr";
            public static final String creator = "creator";
            public static final String create_time = "create_time";
            public static final String modifier = "modifier";
            public static final String modified_time = "modified_time";
            public static final String enterprise_id = "enterprise_id";

        }

        //入库参数
        public static final class ColsgetstockId{
            public static final String smIsOrderno = "smIsOrderno";
            public static final String smAcceptanceFcode = "smAcceptanceFcode";
            public static final String smProposerCode = "smProposerCode";
            public static final String smacceptancefname = "smacceptancefname";
            public static final String smAcceptanceType = "smAcceptanceType";
            public static final String smStatus = "smStatus";
            public static final String rfidinfos = "rfidinfos";
            public static final String creator = "creator";
            public static final String create_time = "create_time";


        }

        public static final class ColsgetstockIdInfos{

            public static final String smIsOrderno = "smIsOrderno";
            public static final String proposeIsNum = "proposeIsNum";
            public static final String cgnMUnit = "cgnMUnit";
            public static final String cgnMShortDesc = "cgnMShortDesc";
            public static final String rfidID = "rfidID";
            public static final String cgnSLocation = "cgnSLocation";
            public static final String cgnMName = "cgnMName";
            public static final String cgnMCode = "cgnMCode";
        }

        //出库单
        public static final class mm_outbound_orderclass{
            public static final String sm_os_orderno = "sm_os_orderno";
            public static final String sm_jx_orderno = "sm_jx_orderno";
            public static final String sm_os_type = "sm_os_type";
            public static final String sm_status = "sm_status";
            public static final String cgn_f_code = "cgn_f_code";
            public static final String cgn_s_code = "cgn_s_code";
            public static final String cgn_m_name = "cgn_m_name";
            public static final String cgn_m_short_desc = "cgn_m_short_desc";
            public static final String cgn_m_unit = "cgn_m_unit";
            public static final String propose_os_num = "propose_os_num";
            public static final String rfidinfos = "rfidinfos";
            public static final String cgn_s_location = "cgn_s_location";
            public static final String creator = "creator";
            public static final String create_time = "create_time";
        }

        //退库单
        public static final  class mm_return_orderclass {
            public static final String sm_return_orderno = "sm_return_orderno";
            public static final String sm_os_orderno = "sm_os_orderno";
            public static final String cgn_m_code = "cgn_m_code";
            public static final String cgn_m_name = "cgn_m_name";
            public static final String cgn_m_short_desc = "cgn_m_short_desc";
            public static final String return_num = "return_num";
            public static final String cgn_s_location = "cgn_s_location";
            public static final String rfidIDinfos = "rfidIDinfos";
            public static final String creator = "creator";
            public static final String create_time = "create_time";
        }

        //移库单
        public static final  class mm_movestorageclass{
            public static final String cgn_m_code = "cgn_m_code";
            public static final String cgn_m_name = "cgn_m_name";
            public static final String cgn_m_short_desc = "cgn_m_short_desc";
            public static final String cgn_m_unit = "cgn_m_unit";
            public static final String cgn_f_code = "cgn_f_code";//
            public static final String cgn_f_name = "cgn_f_name";
            public static final String cgn_s_code_s = "cgn_s_code_s";
            public static final String cgn_s_name_s = "cgn_s_name_s";
            public static final String cgn_s_location_s = "cgn_s_location_s";
            public static final String cgn_s_code_d = "cgn_s_code_d";
            public static final String cgn_s_location_d = "cgn_s_location_d";
            public static final String movestorage_num = "movestorage_num";
            public static final String rfidinfos = "rfidinfos";
            public static final String creator = "creator";
            public static final String create_time = "create_time";

        }

        //盘点表
        public static final class mm_stocktaking_reportclass{
            public static final String sm_sr_no = "smsrno";
            public static final String sm_sr_name = "sm_sr_name";
            public static final String sm_sr_status = "sm_sr_status";
            public static final String cgn_f_code = "cgn_f_code";
            public static final String cgn_f_name = "cgn_f_name";
            public static final String cgn_m_code = "cgn_m_code";
            public static final String cgn_m_name = "cgn_m_name";
            public static final String now_num = "now_num";
            public static final String cgn_m_unit = "cgn_m_unit";
            public static final String rfidinfos = "rfidinfos";
            public static final String creator = "creator";
            public static final String create_time = "create_time";
        }



    }
}
