package com.company.zwh_rfid.ui.move;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstock;
import com.company.zwh_rfid.ui.getstock.List_getstockData;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Fragment_movestock extends Fragment {


    View viewgetstock;
    public List_movestock list_movestock;
    public List<Stocker_getdata> list;
    ListView listView1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movestock, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button fanhui = view.findViewById(R.id.buttMoveexit);
        listView1 = view.findViewById(R.id.movestorage1);
        init();

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
        list_movestock = new List_movestock(list,getContext());
        listView1.setAdapter(list_movestock);

    }

    private void init(){
        list = new ArrayList<>();
        Stocker_getdata stocker_getdata1 = new Stocker_getdata();
        Stocker_getdata stocker_getdata2 = new Stocker_getdata();
        stocker_getdata1.setMaterialsName("35kV动力电缆YJLV32-26/351×150");
        stocker_getdata1.setMaterialsID("000000110000000014");
        stocker_getdata1.setNumber(1);
        stocker_getdata1.setLocationID("1.1.1.1");
        stocker_getdata1.setRfidID("ZWH2345647689");
        stocker_getdata2.setMaterialsName("1kV动力电缆YJY23-13×240");
        stocker_getdata2.setMaterialsID("000000110000000024");
        stocker_getdata2.setNumber(1);
        stocker_getdata2.setRfidID("ZWH2345647689");
        stocker_getdata2.setLocationID("2.2.2.2");
        list.add(stocker_getdata1);
        list.add(stocker_getdata2);
    }
}
