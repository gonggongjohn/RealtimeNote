package com.gonggongjohn.realtimenote;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gonggongjohn.realtimenote.ParseData.InnerUtil;
import com.gonggongjohn.realtimenote.ParseData.OuterUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    TextView txtData;
    TextView txtRst;
    Button btnStart;
    private static final String APP_ID = "10859775";
    private static final String API_KEY = "5EVuI64lUAPEg7SobkYun3lz";
    private static final String SECRET_KEY = "PnW1MEWmAXmeXrCKPxHMMZxEPPMLiAAr";
    private static String OriginRstText = null;
    private OuterUtil ou = new OuterUtil();
    private List<InnerUtil> iu = new ArrayList<InnerUtil>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		initPermission();
		txtData = (TextView) findViewById(R.id.txtData);
		txtRst = (TextView) findViewById(R.id.txtRst);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("result");
            txtRst.setText(val);
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            Bundle data = new Bundle();
            OriginRstText = ILPHandler.SendLPData(APP_ID, API_KEY, SECRET_KEY, txtData.getText().toString());
            ou = IResultParser.parseObject(OriginRstText, OuterUtil.class);
            iu = IResultParser.parseArray(ou.getItems(), InnerUtil.class);
            StringBuffer TargetRstBuf = new StringBuffer();
            for(int i=0; i< iu.size(); i++){
                TargetRstBuf.append("第" + (i + 1) + "个成分:\n" + iu.get(i).toString() + "\n\n");
            }
            data.putString("result", TargetRstBuf.toString());
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

    private void initPermission() {
        String permissions[] = {Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

}
