package com.company.zwh_rfid.ui.seeting;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;

public class Fragment_systeminfo extends Fragment {
    View viewbattay;
    private ImageView batteryLevelImage;
    private TextView batteryStatusText;
    private TextView batteryLevel;
    public Fragment_systeminfo() {
        // Required empty public constructor
    }


    private void hideNavigationBar() {
        MainActivity activity = (MainActivity)getActivity();
        if(activity.getWindow()!=null){
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // hideNavigationBar();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {

               // Navigation.findNavController(view1).navigateUp();
                Toast.makeText(getActivity(), "sss", Toast.LENGTH_SHORT).show();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewbattay = inflater.inflate(R.layout.fragment_systeminfo, container, false);
        return viewbattay;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        batteryLevelImage = viewbattay .findViewById(R.id.battayView);
        batteryLevel = (TextView) viewbattay.findViewById(R.id.batteryLevelText);
        batteryStatusText = (TextView) viewbattay.findViewById(R.id.batteryStatusText);
        batteryLevelImage.setImageLevel(6);
        batteryLevel.setText(10 + "%");
    }


    /**
     *
     * @param level
     * @param charging
     * @param cause
     */
    /**
    public void deviceStatusReceived(final int level, final boolean charging, final String cause) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (level >= Constants.BATTERY_FULL) {
                    batteryLevelImage.setImageLevel(10);
                    batteryLevel.setText(Constants.BATTERY_FULL + "%");
                } else {
                    batteryLevelImage.setImageLevel((int) (level / 10));
                    batteryLevel.setText(level + "%");
                }
                if (cause != null && cause.trim().equalsIgnoreCase(Constants.MESSAGE_BATTERY_CRITICAL)) {
                    batteryStatusText.setText(getString(R.string.battery_critical_message));
                    batteryStatusText.setTextAppearance(getActivity(), R.style.style_red_font);
                } else if (cause != null && cause.trim().equalsIgnoreCase(Constants.MESSAGE_BATTERY_LOW)) {
                    batteryStatusText.setText(getString(R.string.battery_low_message));
                    batteryStatusText.setTextAppearance(getActivity(), R.style.style_red_font);
                } else {
                    if (charging) {
                        if (level >= Constants.BATTERY_FULL)
                            batteryStatusText.setText(R.string.battery_full_message);
                        else
                            batteryStatusText.setText(R.string.battery_charging_message);
                    } else
                        batteryStatusText.setText(R.string.battery_discharging_message);
                    batteryStatusText.setTextAppearance(getActivity(), R.style.style_green_font);
                }
                batteryLevel.setVisibility(View.VISIBLE);
            }
        });
    }


     * method to update battery screen when device got disconnected
     */
    public void deviceDisconnected() {
        batteryLevelImage.setImageLevel(0);
        batteryLevel.setText(70 + "%");
        batteryLevel.setVisibility(View.INVISIBLE);
        batteryStatusText.setTextAppearance(getActivity(), R.style.style_grey_font);
        batteryStatusText.setText(R.string.battery_no_active_connection_message);
    }
}
