package com.company.zwh_rfid.ui.exitstock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.DialogCustom;
import com.company.zwh_rfid.ui.getstock.List_getstockData;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;
import com.company.zwh_rfid.ui.gooutstock.List_gooutstock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fragment_exitStock extends Fragment {
    ListView listexiydata;
    List_gooutstock listexiydataID;
    List<Stocker_get> list;
    Button buttonExitnext;
    DialogCustom dialogCustom;
    String name;


    View v;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_exit_stock, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button fanhui = view.findViewById(R.id.buttongetfanhui);
        listexiydata = view.findViewById(R.id.list_exit);
        buttonExitnext = view.findViewById(R.id.buttonExitnext);
        Set<String> set = new HashSet<>();
        getuu();
        listexiydataID = new List_gooutstock(list,getContext());
        listexiydata.setAdapter(listexiydataID);
        listexiydata.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Stocker_get stocker_get = (Stocker_get) listexiydata.getItemAtPosition(i);
                name = stocker_get.getGetStockId();



            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
        buttonExitnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                if (name != null){
                    bundle1.putString("exitstockID", name);
                    Navigation.findNavController(v).navigate(R.id.action_nav_exitstorage_to_fragmentExitdata,bundle1);
                }else{
                    Toast.makeText(getContext(), "请选择", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void getuu(){
        list = new ArrayList<>();
        for (int i=0;i<8;i++){
            Stocker_get stocker_get = new Stocker_get();
            stocker_get.setGetPlanId("4202"+i);
            stocker_get.setGetStockId("RK420219112903"+i);
            list.add(stocker_get);
        }
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
//        list.add(stocker_getdata1);
//        list.add(stocker_getdata2);
    }

}
