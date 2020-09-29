package com.company.zwh_rfid.ui.move;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getRFIDandStockID;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.ArrayList;
import java.util.List;

public class Fragment_movedata extends Fragment {


    View viewMovegetList;
    List<Stocker_get> movedata;
    ListView listViewmoveId;
    List_MovedataId list_movedataId;
    Button movenew,moveApply;
    private String moveId = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewMovegetList = inflater.inflate(R.layout.fragment_movedata, container, false);
        return viewMovegetList;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewmoveId = viewMovegetList.findViewById(R.id.listMoveData);
        moveApply = viewMovegetList.findViewById(R.id.buttonMove);
        movenew = viewMovegetList.findViewById(R.id.button_newMOvedata);
        //movedata = new ArrayList<>();
        getuu();

      //  if (movedata.isEmpty()){
            list_movedataId = new List_MovedataId(movedata,getContext());
            listViewmoveId.setAdapter(list_movedataId);
       // }

        listViewmoveId.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                moveId = null;
                Stocker_get stocker_get = (Stocker_get) listViewmoveId.getItemAtPosition(i);
                moveId = stocker_get.getGetStockId();
            }
        });


        movenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                    bundle1.putString("moveid", moveId);
                    Navigation.findNavController(v).navigate(R.id.action_nav_movedata_to_nav_Move_iddata,bundle1);
            }
        });

        moveApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

    }

    private void getuu(){
        movedata = new ArrayList<>();
        for (int i=0;i<8;i++){
            Stocker_get stocker_get = new Stocker_get();
            stocker_get.setGetPlanId("4202"+i);
            stocker_get.setGetStockId("RK420219112903"+i);
            movedata.add(stocker_get);
        }
    }
}