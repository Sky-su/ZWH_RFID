package com.company.zwh_rfid.ui.move;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;
import com.company.zwh_rfid.ui.gooutstock.List_getoudata;
import com.company.zwh_rfid.util.EventSting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Fragment_Move_iddata extends Fragment {


    View viewMoveData;
    List_movestockdata list_movestockdata;
    ListView listView;
    List<Stocker_getdata> listdata;
    Button clean,apply;
    private String moveid;
    private List_getoudata list_getoudata;







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        moveid = arguments.getString("moveid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewMoveData = inflater.inflate(R.layout.fragment__move_iddata, container, false);
        return viewMoveData;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventSting d) {
        if (list_getoudata == null){
            listdata.add(new Stocker_getdata(d.getData(),"35kV动力电缆YJLV32-26/351×150","35kV动力电缆YJLV32-26/351×150","data",1,1));
            list_getoudata = new List_getoudata(listdata,getContext());
            listView.setAdapter(list_getoudata);
        }else{
            list_getoudata.additem(d.getData());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        listView = viewMoveData.findViewById(R.id.listMoveIDData);
        listdata = new ArrayList<>();
        clean = viewMoveData.findViewById(R.id.button_F5);
        apply = viewMoveData.findViewById(R.id.buttonMove);
        TextView er = viewMoveData.findViewById(R.id.textnull);
        listView.setEmptyView(er);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_getoudata.clear();
            }
        });







    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //MainFragment.java中
        EventBus.getDefault().unregister(this);

    }
    private void getRFID(){

        MainActivity activity = (MainActivity) getActivity();
//        Set<String> name = activity.rfid();
//        Stocker_getdata android = new Stocker_getdata();
//        android.setMaterialsID(name);
//        listdata.add(android);
    }

    private void init(){
        listdata = new ArrayList<>();
        Stocker_getdata stocker_getdata1 = new Stocker_getdata();
        Stocker_getdata stocker_getdata2 = new Stocker_getdata();
        stocker_getdata1.setMaterialsName("35kV动力电缆YJLV32-26/351×150");
        stocker_getdata1.setMaterialsID("000000110000000014");
        stocker_getdata1.setNumber(8);
        stocker_getdata1.setLocationID("123");
        stocker_getdata2.setLocationID("234");

        stocker_getdata2.setMaterialsName("1kV动力电缆YJY23-13×240");
        stocker_getdata2.setMaterialsID("000000110000000024");
        stocker_getdata2.setNumber(5);
        listdata.add(stocker_getdata1);
        listdata.add(stocker_getdata2);
    }

}