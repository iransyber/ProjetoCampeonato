package com.example.iran.projetomaterialdesigne;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.iran.projetomaterialdesigne.Api.FootBallApi;
import com.example.iran.projetomaterialdesigne.Models.Campeonatos.Liga;
import com.example.iran.projetomaterialdesigne.Models.Campeonatos.ResponseCampeonatos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity  {

    private Toolbar mToolbar;
    private List<Liga> ligas;
    private ResponseCampeonatos res;
    private RecyclerView crecicleCampeonato;
    private LineAdapterCampeonato mAdapter;
    private SwipeRefreshLayout myRefreshLista;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRefreshLista = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);

        ligas = new ArrayList<>();
        crecicleCampeonato = (RecyclerView)findViewById(R.id.recycler_campeonato);
        setupRecycler();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

//        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        mToolbar.setTitle("Selecione o campeonato");
        mToolbar.setSubtitle("Acompanhe todos os campeonatos");
        mToolbar.setLogo(R.mipmap.ic_launcher_futebol_round);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("Animated Toolbar");

        ListarCompeticoes();

        myRefreshLista.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 ListarCompeticoes();
             }
           }
        );
    }

    private void ListarCompeticoes(){
        final RequestParams params = new RequestParams();
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        params.put("?season", ano);
        FootBallApi.getAllCompetitions(params, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Deu Pau", throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Gson g = new Gson();
                final List<Liga> ll = g.fromJson(response.toString(), new TypeToken<List<Liga>>(){}.getType());
                mAdapter.updateDataSet(ll);
                myRefreshLista.setRefreshing(false);
            }
        });
    }

    private void setupRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        crecicleCampeonato.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapterCampeonato(ligas, onClickRecycleView);
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

    private final RecycleViewOnClickListener<Liga> onClickRecycleView = new RecycleViewOnClickListener<Liga>() {
        @Override
        public void onClickListener(final Liga liga) {
            Intent it = new Intent(MainActivity.this, CompeticaoActivity.class);
            it.putExtra("Liga", liga);
            startActivity(it);
//            Toast.makeText(MainActivity.this, liga.getCaption(), Toast.LENGTH_SHORT).show();
        }
    };
}
