package com.company.zwh_rfid.ui.getstock;

import android.widget.ImageView;

import java.util.List;

public class Read_states {

    List<Stocker_getdata> readlist;
    ImageView readstate;

    public Read_states() {
    }

    public List<Stocker_getdata> getReadlist() {
        return readlist;
    }

    public void setReadlist(List<Stocker_getdata> readlist) {
        this.readlist = readlist;
    }

    public ImageView getReadstate() {
        return readstate;
    }

    public void setReadstate(ImageView readstate) {
        this.readstate = readstate;
    }
}
