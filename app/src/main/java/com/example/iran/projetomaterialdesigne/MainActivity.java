package com.example.iran.projetomaterialdesigne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.iran.projetomaterialdesigne.Api.FootBallApi;
import com.example.iran.projetomaterialdesigne.Models.Campeonatos.Liga;
import com.example.iran.projetomaterialdesigne.Models.Campeonatos.ResponseCampeonatos;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ArrayList<Liga> ligas;
    private ResponseCampeonatos res;
    private RecyclerView crecicleCampeonato;
    private LineAdapterCampeonato mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ligas = new ArrayList<Liga>();
        crecicleCampeonato = (RecyclerView)findViewById(R.id.recycler_campeonato);
        setupRecycler();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        mToolbar.setTitle("Selecione o campeonato");
        mToolbar.setSubtitle("Acompanhe todos os campeonatos");
        mToolbar.setLogo(R.drawable.ic_android_black_24dp);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        RequestParams params = new RequestParams();
        params.put("?season", "2017");

        FootBallApi.getAllCompetitions(params, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Deu Pau", throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Gson g = new Gson();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Liga l = new Liga();
                        JSONObject camp = new JSONObject();
                        camp = response.getJSONObject(i);

                        l.setId(camp.getInt("id"));
                        l.setCaption(camp.getString("caption"));
                        l.setLeague(camp.getString("league"));
                        l.setYear(camp.getString("year"));
                        l.setCurrentMatchday(camp.getInt("currentMatchday"));
                        l.setNumberOfMatchdays(camp.getInt("numberOfMatchdays"));
                        l.setNumberOfTeams(camp.getInt("numberOfTeams"));
                        l.setNumberOfGames(camp.getInt("numberOfGames"));
                        l.setLastUpdated(camp.getString("lastUpdated"));
                        l.set_links(camp.getJSONObject("_links"));

                        ligas.add(l);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        crecicleCampeonato.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapterCampeonato(ligas);
        crecicleCampeonato.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        crecicleCampeonato.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
