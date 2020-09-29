package com.company.zwh_rfid.ui.getstock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.util.EventSting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class Fragment_getitemdata extends Fragment {

    ListView listgetdata;
    List_getstockData list_getstockData;
    List<Stocker_getdata> list;
    Button zSave,buttonsure;
    DialogCustom dialogCustom;
    View viewgetitemData;
    String getStateId;
    String kuweiID;
    int getstockCount = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        getStateId = arguments.getString("getreeaId");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventSting d) {
        if (list != null){
            for (Stocker_getdata data:list) {
                if (d.getData().equals(data.getRfidID())){
                    getstockCount++;
                    Log.d("Count",String.valueOf(getstockCount));

                }
            }
        }

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
        viewgetitemData = inflater.inflate(R.layout.fragment_getitemdata, container, false);
        return viewgetitemData;
    }

    EditText datanumber;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listgetdata = viewgetitemData.findViewById(R.id.listgetitem);
        zSave = viewgetitemData.findViewById(R.id.buttinzSave);
        EventBus.getDefault().register(this);

        buttonsure = viewgetitemData.findViewById(R.id.buttongetstocksave);
        buttonsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigateUp();
            }
        });
        init();
        list_getstockData = new List_getstockData(list,getContext());
        listgetdata.setAdapter(list_getstockData);
        listgetdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                getstockCount = 0;
                Stocker_getdata stocker_getdata = (Stocker_getdata) listgetdata.getItemAtPosition(i);
                final int o = stocker_getdata.getNumber();
                dialogCustom = new DialogCustom(getContext(),R.layout.dialog_weight_change);
                dialogCustom.setDismissButtonId(R.id.dialog_no);
                View view12 = dialogCustom.setString(R.id.dialog_yes);
                 datanumber= dialogCustom.setString1(R.id.dialog_kwcount);
                final EditText editText1 = dialogCustom.setString1(R.id.dialog_kwId);
                view12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Count",String.valueOf(getstockCount));
                        datanumber.setText(String.valueOf(getstockCount));
                        try{
                            list_getstockData.sankwID(i, getstockCount,editText1.getText().toString(),o);
                            dialogCustom. dismiss();
                        }catch (Exception e){
                            Toast.makeText(getContext(), "请重新扫描", Toast.LENGTH_SHORT).show();
                        }





                    }
                });

            }
        });

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    private void init(){
        list = new ArrayList<>();
        Stocker_getdata stocker_getdata1 = new Stocker_getdata();
        Stocker_getdata stocker_getdata2 = new Stocker_getdata();
        stocker_getdata1.setMaterialsName("35kV动力电缆YJLV32-26/351×150");
        stocker_getdata1.setMaterialsID("000000110000000014");
        stocker_getdata1.setRfidID("FF88888888");
        stocker_getdata1.setNumber(8);
        stocker_getdata2.setMaterialsName("1kV动力电缆YJY23-13×240");
        stocker_getdata2.setMaterialsID("000000110000000024");
        stocker_getdata2.setNumber(5);
        list.add(stocker_getdata1);
        list.add(stocker_getdata2);
    }


}
