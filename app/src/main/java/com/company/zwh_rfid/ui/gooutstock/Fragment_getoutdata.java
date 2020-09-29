package com.company.zwh_rfid.ui.gooutstock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;
import com.company.zwh_rfid.util.EventSting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class Fragment_getoutdata extends Fragment {

    View viewgetoutdata;
    ListView listgetoutdata;
    List<Stocker_getdata> list;
    List_getoudata list_getoudata;
    Button buttongetoutsave,buttonYT;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventSting d) {
        if (list_getoudata == null){
            list.add(new Stocker_getdata("data",d.getData(),"1","data",1,1));
            list_getoudata = new List_getoudata(list,getContext());
            listgetoutdata.setAdapter(list_getoudata);
        }else{
            list_getoudata.additem(d.getData());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewgetoutdata = inflater.inflate(R.layout.fragment_getoutdata, container, false);
        return viewgetoutdata;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listgetoutdata = viewgetoutdata.findViewById(R.id.getoutdatalist);
        TextView en = (TextView) view.findViewById(R.id.textgetoutnull);
        listgetoutdata.setEmptyView(en);
        buttongetoutsave = viewgetoutdata.findViewById(R.id.buttongetoutdataapply);
        buttonYT = viewgetoutdata.findViewById(R.id.buttongetoutdataTS);
        list = new ArrayList<>();
        String strName = getArguments().getString("getoutdataId");
        EventBus.getDefault().register(this);
        buttonYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list_getoudata.clear();
            }
        });
        buttongetoutsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(viewgetoutdata).navigateUp();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //MainFragment.java中
        EventBus.getDefault().unregister(this);

    }
    private void time(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Navigation.findNavController(viewgetoutdata).navigateUp();
            }
        },2000);//延时1s执行
    }
}