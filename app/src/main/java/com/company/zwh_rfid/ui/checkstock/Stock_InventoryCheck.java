package com.company.zwh_rfid.ui.checkstock;

public class Stock_InventoryCheck {
    String  locationID,materialsID,materialsName,rfidID,checkId;
    int number;

    public Stock_InventoryCheck() {
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

    public String getRfidID() {
        return rfidID;
    }

    public void setRfidID(String rfidID) {
        this.rfidID = rfidID;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
