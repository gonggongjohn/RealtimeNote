package com.gonggongjohn.realtimenote;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONException;
import org.json.JSONObject;

public class ILPHandler {
    public static String SendLPData(String APP_ID, String API_KEY, String SECRET_KEY,String data){
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        JSONObject tres = client.depParser(data, null);
        String res = null;
        try{
            res = tres.toString(2);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return res;
    }
}
