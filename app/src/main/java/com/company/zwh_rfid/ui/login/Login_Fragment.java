package com.company.zwh_rfid.ui.login;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.company.zwh_rfid.MainActivity;
import com.company.zwh_rfid.R;
import com.company.zwh_rfid.util.Base64;
import com.company.zwh_rfid.util.Dataclass;
import com.company.zwh_rfid.util.RsaUtil;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;


public class Login_Fragment extends Fragment {

    private LoginViewModel mViewModel;
    EditText editTextName;
    EditText editTextPasswd;
    Button buttonLogin;
    private JSONObject retjson;
    View view;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){

                case 0x11:
                    if (retjson.getString("status").equals("1")){
                        Navigation.findNavController(view).navigate(R.id.action_nav_login_to_nav_home);
                        Toast.makeText(getContext(),retjson.getString("msg"),Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };


    public static Login_Fragment newInstance() {
        return new Login_Fragment();
    }

    

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    void loginit(View view){
        editTextName = (EditText) view.findViewById(R.id.editName);
        editTextPasswd = (EditText) view.findViewById(R.id.editPassword);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        editTextName.setText("kg002");
        editTextPasswd.setText("Uf_0000000000");
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                yanIF();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                             retjson = new JSONObject(); // 用来存储响应数据
//                            String appurl = "http://140.143.53.114:8170/ieop_base_mobile/mfrontmalluserlogin/getenc";
//                            JSONObject  keyJson= JSONObject.parseObject(((MainActivity) getActivity()).sendByOKHttp(appurl, "{\"conditionMap\":{usercode: \""+editTextName.getText().toString()+"\", pwd: \""+editTextPasswd.getText().toString()+"\", language_type: \"0\"}}"));
//                            String pub_key = keyJson.getJSONObject("retData").getString("pub_key");
//                            String pwd = null;
//                            pwd = RsaUtil.encode(editTextPasswd.getText().toString(), pub_key);
//                            System.out.print(pwd);
//                            String loginurl = "http://140.143.53.114:8170/ieop_base_mobile/mfrontmalluserlogin/login";
//                            retjson = JSONObject.parseObject(((MainActivity) getActivity()).sendByOKHttp(loginurl, "{\"conditionMap\":{usercode: \""+editTextName.getText().toString()+"\", pwd: \""+pwd+"\", language_type: \"0\"}}"));
//                            System.out.println(retjson.toString());
//                            handler.sendEmptyMessage(0x11);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }) .start();
//
//            }
//        });

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        loginit(view);
        intouser(activity);
    }

    //空字符验证
    private void yanIF(){
        if (TextUtils.isEmpty(editTextName.getText())){

            Toast.makeText(getContext(),"请输入账号名！",Toast.LENGTH_LONG).show();
           // editTextPasswd.requestFocus();
            return;
        }else if (TextUtils.isEmpty(editTextPasswd.getText())){
            Toast.makeText(getContext(),"请输入密码！",Toast.LENGTH_LONG).show();
            return;
        }
    }
    private void intouser(MainActivity activity){
        SharedPreferences.Editor editor =activity.getSharedPreferences("uerinfo",MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("uerinfo",editTextName.getText().toString());
        editor.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    public static int getStatusBarHeight(Activity activity){
        int statusBarheght = 0;
        if (activity != null){
            int resid = activity.getResources().getIdentifier("status_bar_heght","dimen","android");
           statusBarheght = activity.getResources().getDimensionPixelSize(resid);
        }
        return statusBarheght;
    }
}
