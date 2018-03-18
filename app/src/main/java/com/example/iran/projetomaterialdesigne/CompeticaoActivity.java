package com.example.iran.projetomaterialdesigne;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.iran.projetomaterialdesigne.Api.FootBallApi;
import com.example.iran.projetomaterialdesigne.Models.Campeonatos.Liga;
import com.example.iran.projetomaterialdesigne.Models.Table.TableOfGames;
import com.example.iran.projetomaterialdesigne.Models.Table.TableTeams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.iran.projetomaterialdesigne.Api.FootBallApi.getSelectedCampetition;

public class CompeticaoActivity extends AppCompatActivity {

    private Liga ligaSelecionada;
    private Toolbar mToolbar;
    private JSONObject tabela;
    private RecyclerView TabelaJogos;
    private List<TableTeams> jogos;
    private LineAdapterTable mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competicao);

        ligaSelecionada = (Liga) getIntent().getSerializableExtra("Liga");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        mToolbar.setTitle(ligaSelecionada.getCaption());
        mToolbar.setSubtitle("Ano: "+ligaSelecionada.getYear());
        mToolbar.setLogo(R.drawable.ic_android_black_24dp);

        jogos = new ArrayList<>();
        TabelaJogos = (RecyclerView)findViewById(R.id.recycler_competicao);
        setupRecycler();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final RequestParams params = new RequestParams();
        params.put("","");
        FootBallApi.getSelectedCampetition( ligaSelecionada.get_links().getLeagueTable().getHref() , params, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                tabela = response;
                Gson g = new Gson();
                TableOfGames games = g.fromJson(tabela.toString(), TableOfGames.class);
                mAdapter.updateDataSet(games.getStanding());
            }
        });


//        Gson g = new Gson();
//        TableOfGames games = g.fromJson(tabela.toString(), TableOfGames.class);
//        mAdapter.updateDataSet(games.getStanding());

//        CarregarLista();
    }

    public void CarregarLista(){
        new LoadCampeonatoAssyncTask().execute(
                ligaSelecionada.get_links().getLeagueTable().getHref());
    }
    private void setupRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        TabelaJogos.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new LineAdapterTable(jogos, onClickRecycleView);
        TabelaJogos.setAdapter(mAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        TabelaJogos.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private final RecycleViewOnClickListener<TableTeams> onClickRecycleView = new RecycleViewOnClickListener<TableTeams>() {
        @Override
        public void onClickListener(final TableTeams liga) {
            Toast.makeText(CompeticaoActivity.this, liga.getTeamName(), Toast.LENGTH_SHORT).show();
        }
    };

    class LoadCampeonatoAssyncTask extends AsyncTask<String, Void, JSONObject>{
        ProgressDialog dialog;
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            dialog.dismiss();
            if (jsonObject != null){
                tabela = jsonObject;
                Gson g = new Gson();
                TableOfGames games = g.fromJson(tabela.toString(), TableOfGames.class);
                mAdapter.updateDataSet(games.getStanding());
            }else {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(CompeticaoActivity.this).
                                setTitle("Erro").
                                setMessage("Não foi possivel carregar a tabela, tente novamente mais tarde!").
                                setPositiveButton("OK", null);
                builder.create().show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(
                    CompeticaoActivity.this,
                    "Aguarde", "Carregando tabela...");
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            String urlString = strings[0];
            tabela = BusinessAPI.getSelectedCompetitionTable(urlString);
            return tabela;
        }
    }
}
