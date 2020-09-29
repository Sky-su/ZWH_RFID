package com.company.zwh_rfid.ui.getstock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.http.HttpURl;
import com.company.zwh_rfid.scanandread.Datawedeentity;
import com.company.zwh_rfid.scanandread.Scan;
import com.company.zwh_rfid.uientity.StoragegetID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_getstock extends Fragment {
    Button fanhui,buttongetCheck;
    View viewgetstock;
    public List_getstock list_getstock;
    public List<StoragegetID> list = new ArrayList<>();
    public List<StoragegetID> listSeach;
    ListView listView1;
    EditText seach;
    String getStockerID;
    String csanId = null;
    MainActivity activity;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewgetstock = inflater.inflate(R.layout.fragment_getstock, container, false);
        return viewgetstock;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fanhui = viewgetstock.findViewById(R.id.buttongetfanhui);
        listView1 = viewgetstock.findViewById(R.id.list_getstock);
        seach = viewgetstock.findViewById(R.id.edit_getstock);
        buttongetCheck = viewgetstock.findViewById(R.id.buttongetcheck);
        final Bundle bundle1 = new Bundle();
       // init();
        getuu();
        list_getstock = new List_getstock(list,getContext());
        listView1.setAdapter(list_getstock);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              getStockerID = null;
                StoragegetID g = (StoragegetID) listView1.getItemAtPosition(i);
                getStockerID = g.getSmIsOrderno();
            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
        buttongetCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle1.putString("getdataIdKEY", getStockerID);
                Navigation.findNavController(viewgetstock).navigate(R.id.action_nav_getstorage_to_nav_readdataitem2,bundle1);
            }
        });

    }
    private void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StoreID();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void StoreID() throws IOException {
        List<StoragegetID> listtest = new ArrayList<>();
       JSONObject js = ((MainActivity)getActivity()).sendByOKHttpgetdata(HttpURl.PostUrl.storage.intoId,HttpURl.PostUrl.storage.intoIdparments);
        listtest = JSON.parseArray(js.getString("ents"),StoragegetID.class);
        for (StoragegetID io:listtest) {
            list.add(io);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (activity.getSacn()!=null)seach.setText(activity.getSacn());



    }

    /**
     * 搜索功能
     */
    private void seach_change(){
        seach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                seachid(list);

            }
        });
    }
    private void seachid(List<StoragegetID> ist){
        int len = ist.size();
        listSeach = new ArrayList<>();
        for(int i = 0; i < len; ++i) {
            if (ist.get(i).getSmIsOrderno().contains(seach.getText().toString())) {
                StoragegetID item = new StoragegetID();
                item.setSmIsOrderno(ist.get(i).getSmIsOrderno());
                item.setSmAcceptanceFcode(ist.get(i).getSmAcceptanceFcode());
                listSeach.add(item);
            }
        }
        list_getstock = new List_getstock(listSeach,getContext());
        listView1.setAdapter(list_getstock);

    }

    private void getuu(){
        list = new ArrayList<>();
        for (int i=0;i<8;i++){
            StoragegetID stocker_get = new StoragegetID();
            stocker_get.setSmIsOrderno("4202"+i);
            stocker_get.setSmAcceptanceFcode("RK420219112903"+i);
            list.add(stocker_get);
        }
    }


}
