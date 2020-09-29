package com.company.zwh_rfid;

import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSONObject;
import com.company.zwh_rfid.database.SQLFunction;
import com.company.zwh_rfid.scanandread.Datawedeentity;
import com.company.zwh_rfid.scanandread.Scan;
import com.company.zwh_rfid.ui.exitstock.FragmentExitdata;
import com.company.zwh_rfid.util.Base64;
import com.company.zwh_rfid.util.EventSting;
import com.google.android.material.navigation.NavigationView;
import com.zebra.rfid.api3.Antennas;
import com.zebra.rfid.api3.ENUM_TRANSPORT;
import com.zebra.rfid.api3.ENUM_TRIGGER_MODE;
import com.zebra.rfid.api3.Events;
import com.zebra.rfid.api3.HANDHELD_TRIGGER_EVENT_TYPE;
import com.zebra.rfid.api3.InvalidUsageException;
import com.zebra.rfid.api3.OperationFailureException;
import com.zebra.rfid.api3.RFIDReader;
import com.zebra.rfid.api3.ReaderDevice;
import com.zebra.rfid.api3.Readers;
import com.zebra.rfid.api3.RfidEventsListener;
import com.zebra.rfid.api3.RfidReadEvents;
import com.zebra.rfid.api3.RfidStatusEvents;
import com.zebra.rfid.api3.SESSION;
import com.zebra.rfid.api3.START_TRIGGER_TYPE;
import com.zebra.rfid.api3.STATUS_EVENT_TYPE;
import com.zebra.rfid.api3.STOP_TRIGGER_TYPE;
import com.zebra.rfid.api3.TagData;
import com.zebra.rfid.api3.TriggerInfo;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MainActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    String deciverfidName = null;
    SoundPool soundPool;
    TextView  editText;
    //导航
    DrawerLayout drawer;
    NavigationView navigationView ;
    NavController navController;
    //okhttp
    OkHttpClient client;
    List<Cookie> cookies = null;

    //rfid

    private static final String TAG = "MainActivity";
    AsyncTask<Void, String, String> task = null;
    AsyncTask<Void, Void, Void> taskPerform = null;
    AsyncTask<Void, Void, Void> taskStop = null;
    Readers readers = null;
    ReaderDevice device = null;
     RFIDReader reader = null;
    ScheduledExecutorService scheduler = null;
    ScheduledFuture<?> taskHandler = null;
    static RfidEventHandler eventHandler = null;
    int battery = 0;
    int temperature = 0;
    ProgressDialog progressDialog = null;
    //扫描
    Scan scan;
    IntentFilter filter = new IntentFilter();
    String decodeStr = null;
    //全屏
    public void Windsclose(){
        getSupportActionBar().isShowing();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initmeum();
//        SQLFunction.initTable(this);
//        SQLFunction function = new SQLFunction();
//        setupProgressDialog();
//        setupLoadReaderTask();
        scan = new Scan(this);
        scan.load(filter);
        registerReceiver(Broadcast, filter);

    }
    public String getSacn(){
        return decodeStr;
    }

    public BroadcastReceiver Broadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Datawedeentity.ACTIVITY_INTENT_FILTER_ACTION)) {
                //  Received a barcode scan
                try {

                    decodeStr = intent.getStringExtra(Datawedeentity.DATAWEDGE_INTENT_KEY_DATA);
                    Log.d("hhhh",decodeStr);
                    Fragment ft = (Fragment) getSupportFragmentManager().getPrimaryNavigationFragment();
                    //seach.setText(name);
                } catch (Exception e) {
                    //  Catch if the UI does not exist when we receive the broadcast
                    Toast.makeText(getApplicationContext(), "Error; " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        }
    };
    //登录信息
    public String login() {
        SharedPreferences sp = getSharedPreferences("uerinfo",MODE_PRIVATE);
        return sp.getString("uerinfo","");
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(Broadcast);

    }

    public String name(){
        if (deciverfidName != null) {
            return deciverfidName;
        }
        return "";
    }

    //连接信息
    private void setupProgressDialog() {
        if (progressDialog == null)
            progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.connecting));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }


    //捕获事件
    private void setupStatusMonitorTimer() {
        if (scheduler != null) return;
        scheduler = Executors.newScheduledThreadPool(1);
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    if (reader != null) {
                        reader.Config.getDeviceStatus(true, true, true);
                    } else {
                        scheduler.shutdown();
                    }
                } catch (InvalidUsageException | OperationFailureException e) {
                    if (e instanceof OperationFailureException) {
                        Log.d(TAG, "OperationFailureException: " + ((OperationFailureException) e).getVendorMessage());
                    }
                    e.printStackTrace();
                }
            }
        };
        taskHandler = scheduler.scheduleAtFixedRate(task, 10, 60, SECONDS);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupLoadReaderTask() {
        if (task != null) task.cancel(true);
        if (readers == null) readers = new Readers(this, ENUM_TRANSPORT.SERVICE_SERIAL);
        if (!progressDialog.isShowing()) progressDialog.show();
        task = new AsyncTask<Void, String, String>() {
            @Override
            protected synchronized String doInBackground(Void... voids) {
                if (isCancelled()) return null;
                if (readers == null) return null;
                publishProgress("readers.GetAvailableRFIDReaderList()");
                if (isCancelled()) return null;
                List<ReaderDevice> list = null;
                try {
                    list = readers.GetAvailableRFIDReaderList();
                } catch (InvalidUsageException e) {
                    e.printStackTrace();
                }
                if (list == null || list.isEmpty()) return null;
                publishProgress("device.getRFIDReader()");
                if (isCancelled()) return null;
                for (ReaderDevice readerDevice : list) {
                    device = readerDevice;
                    // Log.d("setupLoadReaderTask", device.getName());
                    reader = device.getRFIDReader();
                    if (reader.isConnected()) return null;
                    publishProgress("reader.connect()");
                    if (isCancelled()) return null;
                    try {
                        reader.connect();
                        configureReader();
                        deciverfidName = device.getName();
                    } catch (InvalidUsageException | OperationFailureException e) {
                        e.printStackTrace();
                    }
                    if (reader.isConnected()) break;
                }
                if (!reader.isConnected()) return null;

                return String.format("Connected to %s", device.getName());
            }

            @Override
            protected void onProgressUpdate(String... values) {
                // if (values.length == 0) return;
                // String s = null;
                // for (String value : values) s = value;
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null) {
                    setupRetryDialog();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected void onCancelled() {
                reader = null;
                readers = null;
                Toast.makeText(getApplicationContext(), "Connection Cancelled", Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }

    //RFD设备设置
    private void configureReader() {
        if (reader == null || !reader.isConnected()) return;
        TriggerInfo triggerInfo = new TriggerInfo();
        triggerInfo.StartTrigger.setTriggerType(START_TRIGGER_TYPE.START_TRIGGER_TYPE_IMMEDIATE);
        triggerInfo.StopTrigger.setTriggerType(STOP_TRIGGER_TYPE.STOP_TRIGGER_TYPE_IMMEDIATE);
        if (eventHandler == null) eventHandler = new RfidEventHandler();
        try {
            reader.Events.addEventsListener(eventHandler);
        } catch (InvalidUsageException | OperationFailureException e) {
            e.printStackTrace();
        }
        reader.Events.setHandheldEvent(true);
        reader.Events.setTagReadEvent(true);
        reader.Events.setBatteryEvent(true);
        reader.Events.setPowerEvent(true);
        reader.Events.setTemperatureAlarmEvent(true);
        reader.Events.setAttachTagDataWithReadEvent(false);
        try {
            reader.Config.setTriggerMode(ENUM_TRIGGER_MODE.RFID_MODE,true);
            reader.Config.setStartTrigger(triggerInfo.StartTrigger);
            reader.Config.setStopTrigger(triggerInfo.StopTrigger);
        } catch (InvalidUsageException | OperationFailureException e) {
            e.printStackTrace();
        }
        try {
            Antennas.AntennaRfConfig config = null;
            config = reader.Config.Antennas.getAntennaRfConfig(1);
            config.setTransmitPowerIndex(270);
            config.setrfModeTableIndex(0);
            config.setTari(0);
            reader.Config.Antennas.setAntennaRfConfig(1, config);
            Antennas.SingulationControl control = reader.Config.Antennas.getSingulationControl(1);
            control.setSession(SESSION.SESSION_S0);
            reader.Config.Antennas.setSingulationControl(1, control);
        } catch (InvalidUsageException e) {
            e.printStackTrace();
        } catch (OperationFailureException e) {
            // Log.d(TAG, "SingulationControl: " + e.getVendorMessage());
            e.printStackTrace();
        }
        try {
            reader.Actions.PreFilters.deleteAll();
        } catch (InvalidUsageException | OperationFailureException e) {
            e.printStackTrace();
        }
    }
    private void setupRetryDialog() {
        if (progressDialog.isShowing()) progressDialog.dismiss();
        new AlertDialog.Builder(this)
                .setTitle(R.string.err_title)
                .setIcon(R.mipmap.logo)
                .setMessage(R.string.retry)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setupLoadReaderTask();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode ==10){
            //MainActivity mainActivity = (MainActivity)getActivity();
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) task.cancel(true);
        if (reader != null) {
            task = new AsyncTask<Void, String, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        reader.disconnect();
                    } catch (InvalidUsageException | OperationFailureException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            task.execute();
        }
        if (readers != null) {
            readers.Dispose();
        }
    }


    private class RfidEventHandler implements RfidEventsListener {
        @Override
        public void eventReadNotify(RfidReadEvents rfidReadEvents) {
            TagData[] tags = reader.Actions.getReadTags(100);
            if (tags == null) return;
            for (TagData tag : tags) {
                gotTag(tag);
            }
        }

        @SuppressLint("StaticFieldLeak")
        @Override
        public void eventStatusNotify(RfidStatusEvents rfidStatusEvents) {
            Events.StatusEventData data = rfidStatusEvents.StatusEventData;
            STATUS_EVENT_TYPE type = data.getStatusEventType();
            if (type == STATUS_EVENT_TYPE.BATTERY_EVENT) {
                battery = data.BatteryData.getLevel();
            } else if (type == STATUS_EVENT_TYPE.TEMPERATURE_ALARM_EVENT) {
                temperature = data.TemperatureAlarmData.getCurrentTemperature();
            } else if (type == STATUS_EVENT_TYPE.HANDHELD_TRIGGER_EVENT) {
                HANDHELD_TRIGGER_EVENT_TYPE eventType = data.HandheldTriggerEventData.getHandheldEvent();
                if (eventType == HANDHELD_TRIGGER_EVENT_TYPE.HANDHELD_TRIGGER_PRESSED) {
                    if (taskPerform != null) taskPerform.cancel(true);
                    taskPerform = new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                reader.Actions.Inventory.perform();
                            } catch (InvalidUsageException | OperationFailureException e) {
                                triggerStop();
                                e.printStackTrace();
                            }
                            return null;
                        }
                    };
                    taskPerform.execute();
                } else if (eventType == HANDHELD_TRIGGER_EVENT_TYPE.HANDHELD_TRIGGER_RELEASED) {
                    if (taskStop != null) taskStop.cancel(true);
                    taskStop = new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                reader.Actions.Inventory.stop();
                            } catch (InvalidUsageException | OperationFailureException e) {
                                e.printStackTrace();
                            }
                            triggerStop();
                            return null;
                        }
                    };
                    taskStop.execute();
                }
            }
        }
    }

    public void triggerStop() {
        if (task != null) task.cancel(true);

        if (reader != null) {
            task = new AsyncTask<Void, String, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        reader.disconnect();
                    } catch (InvalidUsageException | OperationFailureException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            task.execute();
        }

    }


    String rfid;
    //读取标签信息
    private void gotTag(TagData tag) {
        rfid = tag.getTagID();
       // rfidlist.add(rfid);
        Log.d(TAG, rfid);
        EventBus.getDefault().post(new EventSting(rfid));
    }


    //音效


    //获取信息
    public JSONObject sendByOKHttpgetdata(final String url1, final String parments) throws IOException {
        JSONObject a = new JSONObject();
        String decodeStr = null;
        a.put("params", Base64.encode(URLEncoder.encode(parments, "UTF-8")));
        JSONObject jsonObjecter = new JSONObject();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),a.toString());
        Request request = new Request.Builder().url(url1)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.code() == 200) {
            String result = response.body().string();
            jsonObjecter = (JSONObject) JSONObject.parse(result);
            try {
                String  encodeData = jsonObjecter.getString("retData");
                decodeStr = URLDecoder.decode(Base64.decode(encodeData), "UTF-8");
                Log.d("获取值：",decodeStr);
            }catch (Exception e){
                e.getMessage();
            }
        }

        return JSONObject.parseObject(decodeStr);
    }

    /**
     * 发送请求（使用 OKHttp）登录使用
     */
    public String sendByOKHttp(final String url, final String parments) throws IOException {
        final JSONObject retjson = new JSONObject();
        JSONObject a = new JSONObject();
        a.put("params", Base64.encode(URLEncoder.encode(parments, "UTF-8")));
        JSONObject jsonObject = new JSONObject();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),a.toString());
        client = new OkHttpClient
                .Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cllokies = new HashMap<>();
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cllokies.put(url,cookies);
                        cllokies.put(HttpUrl.parse("http://140.143.53.114:8170/ieop_base_mobile/mfrontmalluserlogin"),cookies);
                    }
                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        cookies = cllokies.get(HttpUrl.parse("http://140.143.53.114:8170/ieop_base_mobile/mfrontmalluserlogin"));
                        if (cookies == null){
                            System.out.println("没有Cookie");
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.code() == 200) {
            String result = response.body().string();
            jsonObject = (JSONObject) JSONObject.parse(result);
            try {
                String encodeData = jsonObject.getString("retData");
                String decodeStr = URLDecoder.decode(Base64.decode(encodeData), "UTF-8");
                JSONObject retData = jsonObject.parseObject(decodeStr);
                retjson.put("msg", jsonObject.get("msg"));
                retjson.put("pageParams", jsonObject.get("pageParams"));
                retjson.put("status", jsonObject.get("status"));
                retjson.put("retData", retData);
                retjson.put("ufcn",jsonObject.get("ufcn"));
                retjson.put("ufn",jsonObject.get("ufn"));

                Log.d("Main", "result: " + result);
                // handler.sendEmptyMessage(0x001);

            }catch (Exception e){
                e.getMessage();
                retjson.put("status", 0);
                retjson.put("msg", jsonObject.getString("msg"));
                retjson.put("pageParams", e.getMessage());
                retjson.put("retData", null);
                return retjson.toString();

            }

        }




        return retjson.toString();
    }

    /**
     * 菜单功能
     */
    private void initmeum(){
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_getstorage,R.id.nav_gooutstorage,
                R.id.nav_movedata,R.id.nav_exitstorage,
                R.id.nav_incheck,R.id.nav_login,
                R.id.nav_lookatstorage,R.id.nav_Seeting)
                .setDrawerLayout(drawer)
                .build();
         navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //返回捕获





}
