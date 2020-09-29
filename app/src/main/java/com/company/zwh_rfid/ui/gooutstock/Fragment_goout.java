package com.company.zwh_rfid.ui.gooutstock;

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

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstock;
import com.company.zwh_rfid.ui.getstock.Stocker_get;

import java.util.ArrayList;
import java.util.List;


public class Fragment_goout extends Fragment {

    public List<Stocker_get> list;
    ListView listView1;
    View viewgetout;
    List_gooutstock list_gooutstock;
    Button fanhui,getoutsure;
    String getoutID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       viewgetout = inflater.inflate(R.layout.fragment_goout, container, false);
        return viewgetout;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         fanhui = view.findViewById(R.id.buttongetoutfanhui);
         getoutsure = viewgetout.findViewById(R.id.buttongetoutsave);
         final Bundle getout = new Bundle();


        listView1 = viewgetout.findViewById(R.id.list_gooutstock);
        getuu();
        list_gooutstock = new List_gooutstock(list,getContext());

        listView1.setAdapter(list_gooutstock);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stocker_get getoutdataID = (Stocker_get) listView1.getItemAtPosition(position);
                 getoutID = getoutdataID.getGetStockId();
            }
        });
        getoutsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getout.putString("getoutdataId",getoutID);
                Navigation.findNavController(v).navigate(R.id.action_nav_gooutstorage_to_nav_getoutdata,getout);
            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
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
}
