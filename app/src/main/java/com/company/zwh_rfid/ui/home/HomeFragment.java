package com.company.zwh_rfid.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;

import org.greenrobot.eventbus.EventBus;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    String usr = null;
    View root;
    ImageButton buttongetstock;
    ImageButton buttongoutstock;
    ImageButton buttonexitstock;
    ImageButton buttonMOVEStock;
    ImageButton buttonlookatstock;
    ImageButton buttonseeting;
    ImageButton buttonCheck;
    ImageButton buttonLogin;
    MainActivity activity ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        if (activity.login().equals("")) Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_login);
        homeinit();
    }

    void homeinit() {
        buttongetstock = root.findViewById(R.id.buttonGET);
        buttongoutstock = root.findViewById(R.id.buttonOut);
        buttonexitstock = root.findViewById(R.id.buttonExit);
        buttonMOVEStock = root.findViewById(R.id.buttonMove);
        buttonlookatstock = root.findViewById(R.id.buttonclookat);
        buttonseeting = root.findViewById(R.id.buttonSetting);
        buttonCheck = root.findViewById(R.id.buttoncheck);
        buttonLogin = root.findViewById(R.id.buttonLogin);
        ((MainActivity)getActivity()).Windsclose();
        buttongetstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_getstorage);
            }
        });
        buttongoutstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_gooutstorage);
            }
        });
        buttonexitstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_exitstorage2);
            }
        });
        buttonMOVEStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_movedata);
            }
        });
        buttonlookatstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_lookatstorage);
            }
        });
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_incheck);
            }
        });
        buttonseeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_Seeting);
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_login);
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    

    public boolean onBackPressed() {
        activity.onBackPressed();
        new AlertDialog.Builder(getActivity()).setTitle("确认退出吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = activity.getSharedPreferences("uerinfo", MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
                        activity.finish();

                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
        return true; // return true 代表自己处理
    }
}
