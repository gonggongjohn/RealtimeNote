package com.gonggongjohn.realtimenote;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements EventListener {
    protected TextView rstText;
    protected Button btnStart;
    protected Button btnStop;
    private EventManager asr;
    private boolean logTime = true;
    private boolean enableOffline = false;

    private void start(){
        rstText.setText("");
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        String event = null;
        event = SpeechConstant.ASR_START;
        if(enableOffline){
            params.put(SpeechConstant.DECODER, 2);
        }
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 0);
        params.put(SpeechConstant.PID, 1537);
        String json = null;
        json = new JSONObject(params).toString();
        asr.send(event, json, null, 0, 0);
    }

    private  void stop(){
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermission();
        asr = EventManagerFactory.create(this, "asr");
        asr.registerListener(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length){
        String logTxt = "name: " + name;

        if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)){
            if(params.contains("\"nlu_result\"")){
                if(length > 0 && data.length > 0){
                    logTxt += " ;data length=" + data.length;
                }
            }
        }else if(data != null){
            logTxt += " ;data length=" +data.length;
        }
        printLog(logTxt);
    }

    private void printLog(String text) {
        if (logTime) {
            text += "  ;time=" + System.currentTimeMillis();
        }
        text += "\n";
        Log.i(getClass().getName(), text);
        rstText.append(text + "\n");
    }

    private  void initView(){
        rstText = (TextView) findViewById(R.id.rstText);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
    }

    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}
