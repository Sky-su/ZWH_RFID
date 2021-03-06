package com.company.zwh_rfid.ui.gooutstock;

import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.List;

public class Stocker_goout {
    private String getStockId,getPlanId;
    private List<Stocker_getdata> getstockitem;

    public Stocker_goout() {
    }

    public Stocker_goout(String getStockId, String getPlanId, List<Stocker_getdata> getstockitem) {
        this.getStockId = getStockId;
        this.getPlanId = getPlanId;
        this.getstockitem = getstockitem;
    }

    public String getGetStockId() {
        return getStockId;
    }

    public void setGetStockId(String getStockId) {
        this.getStockId = getStockId;
    }

    public String getGetPlanId() {
        return getPlanId;
    }

    public void setGetPlanId(String getPlanId) {
        this.getPlanId = getPlanId;
    }

    public List<Stocker_getdata> getGetstockitem() {
        return getstockitem;
    }

    public void setGetstockitem(List<Stocker_getdata> getstockitem) {
        this.getstockitem = getstockitem;
    }
}
