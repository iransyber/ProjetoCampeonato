package com.example.iran.projetomaterialdesigne.Api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class FootBallApi {


    private static String BASE_URL = "http://api.football-data.org/v1/";
    private static String COMPETITION = "competitions/";
    private static String TEAMS = "teams";

    private static AsyncHttpClient cliente = new AsyncHttpClient();

    public static void getAllCompetitions(RequestParams params, AsyncHttpResponseHandler responseHandler){
        cliente.get(BASE_URL+COMPETITION, params, responseHandler);
    }

    public static void getSelectedCampetition(String link, AsyncHttpResponseHandler responseHandler){
        cliente.get(link, responseHandler);
    }

}
