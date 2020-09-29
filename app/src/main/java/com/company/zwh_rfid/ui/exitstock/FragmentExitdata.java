package com.company.zwh_rfid.ui.exitstock;

import android.app.usage.UsageEvents;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;
import com.company.zwh_rfid.ui.gooutstock.List_getoudata;
import com.company.zwh_rfid.util.EventSting;
import com.zebra.rfid.api3.TriggerInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class FragmentExitdata extends Fragment {

    private Button buttonZC,buttonsave;
    private List<Stocker_getdata> list;
    private List_getoudata list_getoudata;
    private ListView listEXititem;
    private String exitstockID;
    private View exitstockview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        exitstockID = arguments.getString("exitstockID");

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventSting d) {
        if (list_getoudata == null){
            list.add(new Stocker_getdata("data",d.getData(),"1","data",1,1));
            list_getoudata = new List_getoudata(list,getContext());
            listEXititem.setAdapter(list_getoudata);
        }else{
            list_getoudata.additem(d.getData());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        exitstockview = inflater.inflate(R.layout.fragment_exitdata, container, false);
        return exitstockview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        EventBus.getDefault().register(this);

        buttonZC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ZC", Toast.LENGTH_SHORT).show();
            }
        });
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //MainFragment.java中
            EventBus.getDefault().unregister(this);

    }



    private void init(View view){
        list = new ArrayList<Stocker_getdata>();
        buttonZC = (Button) view.findViewById(R.id.buttonexitZC);
        buttonsave = (Button) view.findViewById(R.id.buttonExitstocksave);
        listEXititem = (ListView) view.findViewById(R.id.listEXititem);
        TextView er = exitstockview.findViewById(R.id.textexitnull);
        listEXititem.setEmptyView(er);
    }

}