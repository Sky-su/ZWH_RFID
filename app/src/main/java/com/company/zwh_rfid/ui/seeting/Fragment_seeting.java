package com.company.zwh_rfid.ui.seeting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.company.zwh_rfid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_seeting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_seeting extends Fragment {

    private View viewsetting;
    Button buttonbuleTooth,buttonPower,buttonBattay;




    public Fragment_seeting() {
        // Required empty public constructor
    }

    public static Fragment_seeting newInstance(String param1, String param2) {
        Fragment_seeting fragment = new Fragment_seeting();
        Bundle args = new Bundle();

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
        viewsetting = inflater.inflate(R.layout.fragment_seeting, container, false);
        return viewsetting;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonBattay = viewsetting.findViewById(R.id.button_Systeminfo);
        buttonbuleTooth = viewsetting.findViewById(R.id.button_buletooth);
        buttonPower = viewsetting.findViewById(R.id.button_power);

        buttonBattay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(viewsetting).navigate(R.id.action_nav_Seeting_to_nav_systeminfo2);
            }
        });
        buttonbuleTooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(viewsetting).navigate(R.id.action_nav_Seeting_to_nav_buletooth2);

            }
        });
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(viewsetting).navigate(R.id.action_nav_Seeting_to_nav_power2);

            }
        });

    }
}
