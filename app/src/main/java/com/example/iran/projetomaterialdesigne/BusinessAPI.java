package com.example.iran.projetomaterialdesigne;

import android.util.Log;

import com.example.iran.projetomaterialdesigne.Api.FootBallApi;
import com.example.iran.projetomaterialdesigne.Models.Campeonatos.Liga;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Iran on 15/03/2018.
 */

public class BusinessAPI {
    static JSONObject j = new JSONObject();

    public static JSONObject getSelectedCompetitionTable(String href){
        final RequestParams params = new RequestParams();
        params.put("","");
        FootBallApi.getSelectedCampetition( href , params, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                j = response;
            }
        });

        return j;
    }
}
