package com.company.zwh_rfid.ui.checkstock;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstockData;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Fragment_check extends Fragment {


    ListView listcheckdata;
    List_getstockData list_getstockData;
    List<Stocker_getdata> list;
    View view1;
    Button save;
    Spinner spinner1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity)getActivity();
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {

                Navigation.findNavController(view1).navigateUp();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_check, container, false);
        return view1;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        save = view1.findViewById(R.id.buttonSave11);
        listcheckdata = view1.findViewById(R.id.listViewchecl);
        spinner1 = view1.findViewById(R.id.spinner1);
        Set<String> set = new HashSet<>();
        init();
        for (Stocker_getdata entity : list) set.add(entity.getLocationID());
        final String[] list1 = set.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, list1);
        spinner1.setAdapter(adapter);
        list_getstockData = new List_getstockData(list,getContext());
        listcheckdata.setAdapter(list_getstockData);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"保存ok!",Toast.LENGTH_LONG).show();
            }
        });


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
