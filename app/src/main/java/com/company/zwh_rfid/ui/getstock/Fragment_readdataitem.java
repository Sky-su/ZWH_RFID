package com.company.zwh_rfid.ui.getstock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.gooutstock.List_getoudata;
import com.company.zwh_rfid.util.EventSting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class Fragment_readdataitem extends Fragment  {


    View viewreadRFID;
    List<Stocker_getdata>list;
    List_readRFID list_readRFID;
    ListView listViewread;
    Button buttonsure,buttonexit;
    String getdataid;
    Bundle bundlegetdatId;




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventSting d) {
        if (list != null){
            for (Stocker_getdata data:list) {
              if (d.getData().equals(data.getRfidID())){
                  list_readRFID.aletate(data);
              }
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
         getdataid = arguments.getString("getdataIdKEY");
      //  Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //MainFragment.java中
        EventBus.getDefault().unregister(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewreadRFID = inflater.inflate(R.layout.fragment_readdataitem, container, false);
        init();
        return viewreadRFID;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);

        listViewread = viewreadRFID.findViewById(R.id.readdataitemlist);
        buttonsure = viewreadRFID.findViewById(R.id.buttonreadsure1);
        buttonexit = viewreadRFID.findViewById(R.id.button_getreadexit);
        list_readRFID = new List_readRFID(list,getContext());
        bundlegetdatId = new Bundle();
        listViewread.setAdapter(list_readRFID);
        buttonsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundlegetdatId.putString("getreeaId",getdataid);
                Navigation.findNavController(viewreadRFID).navigate(R.id.action_nav_readdataitem_to_nav_getitemdata,bundlegetdatId);
            }
        });
        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(viewreadRFID).navigateUp();
            }
        });


    }
    private void init(){
        list = new ArrayList<>();
        Stocker_getdata stocker_getdata1 = new Stocker_getdata();
        Stocker_getdata stocker_getdata2 = new Stocker_getdata();
        stocker_getdata1.setMaterialsName("35kV动力电缆YJLV32-26/351×150");
        stocker_getdata1.setMaterialsID("000000110000000014");
        stocker_getdata1.setRfidID("FF8888888888");
        stocker_getdata1.setState(0);
        stocker_getdata2.setMaterialsName("1kV动力电缆YJY23-13×240");
        stocker_getdata2.setMaterialsID("000000110000000024");
        stocker_getdata2.setRfidID("4040");
        stocker_getdata2.setState(0);
        list.add(stocker_getdata1);
        list.add(stocker_getdata2);

    }


}
