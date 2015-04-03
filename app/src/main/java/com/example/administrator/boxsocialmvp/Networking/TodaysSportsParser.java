package com.example.administrator.boxsocialmvp.Networking;

import com.example.administrator.boxsocialmvp.Objects.TodaysSports;
import com.google.api.client.json.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Administrator on 3/26/2015.
 */
public class TodaysSportsParser {

    public void parseJsonObject(String result){
        try {
            JSONArray collection;
            JSONObject res = new JSONObject(result);

            JSONObject response = res.getJSONObject("results");
            Iterator keys = response.keys();
            while (keys.hasNext()){
                String currentDynamicKey =(String)keys.next();

                if(response.get(currentDynamicKey) instanceof JSONArray){
                    collection = response.getJSONArray(currentDynamicKey);
                    for(int i=0; i<collection.length(); i++){
                       if(collection.get(i)instanceof JSONObject){
                           iterateSportsJson(collection.getJSONObject(i));
                       }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void iterateSportsJson(JSONObject results) throws JSONException {
        Iterator keys = results.keys();
        while (keys.hasNext()){

            String currentDynamicKey = (String)keys.next();
            if(results.get(currentDynamicKey)instanceof JSONObject){
                JSONObject sport = results.getJSONObject(currentDynamicKey);



            }

        }

    }
}
