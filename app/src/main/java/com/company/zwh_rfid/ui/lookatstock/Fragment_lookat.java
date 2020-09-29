package com.company.zwh_rfid.ui.lookatstock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.company.zwh_rfid.R;
import com.company.zwh_rfid.ui.getstock.List_getstockData;
import com.company.zwh_rfid.ui.getstock.Stocker_get;
import com.company.zwh_rfid.ui.getstock.Stocker_getdata;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_lookat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_lookat extends Fragment {

    View viewlookat;
    Button faihui,sure;
    List<List_getstockData> item;
    ListView listViewlookat;
    EditText editlookat;


    public Fragment_lookat() {
        // Required empty public constructor
    }


    public static Fragment_lookat newInstance(String param1, String param2) {
        Fragment_lookat fragment = new Fragment_lookat();

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
        viewlookat = inflater.inflate(R.layout.fragment_lookat, container, false);
        return viewlookat;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        faihui = viewlookat.findViewById(R.id.buttonlookatfanhui);
        sure = viewlookat.findViewById(R.id.buttonlookatOk);
        editlookat = viewlookat.findViewById(R.id.editlookat);
        listViewlookat = viewlookat.findViewById(R.id.listlookat);
        faihui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });
    }
    private void seach_list(List<Stocker_getdata> list1){
        String u = editlookat.getText().toString();
        int len = list1.size();
        List<Stocker_getdata> listSeach = new ArrayList<>();
        for(int i = 0; i < len; ++i) {
            if (list1.get(i).getMaterialsName().equals(u)||list1.get(i).getMaterialsID().contains(u)) {
                Stocker_getdata item = new Stocker_getdata();
                item.setMaterialsID(list1.get(i).getMaterialsID());
                item.setMaterialsName(list1.get(i).getMaterialsName());
                item.setLocationID(list1.get(i).getLocationID());
                item.setNumber(list1.get(i).getNumber());
                listSeach.add(item);
            }
        }

    }
    private void seach_changelookat(){

        editlookat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }
}
