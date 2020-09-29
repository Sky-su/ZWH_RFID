package com.company.zwh_rfid.ui.seeting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.zebra.rfid.api3.RFIDReader;


public class Fragment_buletooth extends Fragment {
    View viewbuletooth;
    String buletoothname;
    TextView name;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewbuletooth = inflater.inflate(R.layout.fragment_buletooth, container, false);
        return viewbuletooth;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.textPDARFIDname);
        MainActivity activity = (MainActivity) getActivity();
       if (!(activity.name()).equals(""))name.setText(activity.name());
    }


}
