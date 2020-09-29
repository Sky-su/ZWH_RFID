package com.company.zwh_rfid.ui.move;

import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.List;

public class Movedata {

    String MovedataID;
    List<Stocker_getdata> listMove;

    public Movedata() {
    }

    public String getMovedataID() {
        return MovedataID;
    }

    public void setMovedataID(String movedataID) {
        MovedataID = movedataID;
    }

    public List<Stocker_getdata> getListMove() {
        return listMove;
    }

    public void setListMove(List<Stocker_getdata> listMove) {
        this.listMove = listMove;
    }
}
