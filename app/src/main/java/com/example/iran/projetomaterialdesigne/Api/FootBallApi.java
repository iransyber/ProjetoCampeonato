package com.example.iran.projetomaterialdesigne.Api;

import android.preference.PreferenceActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

public class FootBallApi {

    private String teste;

    private static String BASE_URL = "http://api.football-data.org/v1/";
    private static String COMPETITION = "competitions/";
    private static String TEAMS = "teams";

    private static AsyncHttpClient cliente = new AsyncHttpClient();

    public static void getAllCompetitions(RequestParams params, AsyncHttpResponseHandler responseHandler){
        cliente.addHeader("X-Auth-Token","9876af7fc10c4255a969dd3eda6ecf79");
        cliente.get(BASE_URL+COMPETITION, params, responseHandler);
    }

    public static void getSelectedCampetition(String link,RequestParams params, AsyncHttpResponseHandler responseHandler){
        cliente.addHeader("X-Auth-Token","9876af7fc10c4255a969dd3eda6ecf79");
        cliente.get(link, params, responseHandler);
    }
}
