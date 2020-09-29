package com.company.zwh_rfid.ui.checkstock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstock;
import com.company.zwh_rfid.ui.getstock.Stocker_get;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_incheck#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_incheck extends Fragment {

    View viewincheck;
    public List_Checstock list_checstock;
    public List<Stock_InventoryCheck> list;
    ListView listView1;
    Button faihui;

    public Fragment_incheck() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_incheck.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_incheck newInstance(String param1, String param2) {
        Fragment_incheck fragment = new Fragment_incheck();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewincheck = inflater.inflate(R.layout.fragment_incheck, container, false);
        return viewincheck;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        faihui = viewincheck.findViewById(R.id.buttoncheckfanhui);
        listView1 = viewincheck.findViewById(R.id.list_checkstock);
        getuu();
        list_checstock = new List_Checstock(list,getContext());
        listView1.setAdapter(list_checstock);
        faihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }

        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Navigation.findNavController(viewincheck).navigate(R.id.action_nav_incheck_to_nav_InventoryChecker);

            }
        });

    }
    private void getuu(){
        list = new ArrayList<>();
        for (int i=0;i<8;i++){
            Stock_InventoryCheck stocker_get = new Stock_InventoryCheck();
            stocker_get.setCheckId("4202"+i);
            //stocker_get.setGetStockId("RK420219112903"+i);
            list.add(stocker_get);
        }
    }
}
