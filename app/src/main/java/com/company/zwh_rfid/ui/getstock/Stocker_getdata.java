package com.company.zwh_rfid.ui.getstock;

import android.widget.ImageView;

public class Stocker_getdata {
    String  locationID,materialsID,materialsName,rfidID;
    int number,state;

    public Stocker_getdata(String locationID, String materialsID, String materialsName, String rfidID, int number, int state) {
        this.locationID = locationID;
        this.materialsID = materialsID;
        this.materialsName = materialsName;
        this.rfidID = rfidID;
        this.number = number;
        this.state = state;
    }

    public Stocker_getdata() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRfidID() {
        return rfidID;
    }

    public void setRfidID(String rfidID) {
        this.rfidID = rfidID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getMaterialsID() {
        return materialsID;
    }

    public void setMaterialsID(String materialsID) {
        this.materialsID = materialsID;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
