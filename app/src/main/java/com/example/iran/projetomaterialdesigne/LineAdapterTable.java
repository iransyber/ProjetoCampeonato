package com.example.iran.projetomaterialdesigne;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iran.projetomaterialdesigne.Models.Table.TableOfGames;
import com.example.iran.projetomaterialdesigne.Models.Table.TableTeams;

import java.util.List;

/**
 * Created by Iran on 16/03/2018.
 */

public class LineAdapterTable extends RecyclerView.Adapter<LineAdapterTable.LineHolderTable> {
    private List<TableTeams> jogos;
    private RecycleViewOnClickListener<TableTeams> mRecycleTableOnClickListener;

    public LineAdapterTable(List<TableTeams> table, @NonNull RecycleViewOnClickListener<TableTeams> listener) {
        jogos = table;
        mRecycleTableOnClickListener = listener;
    }

    @Override
    public LineHolderTable onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolderTable(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tabela, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolderTable holder, int position) {
        holder.posicao.setText(jogos.get(position).getPosition().toString());
        holder.time.setText(jogos.get(position).getTeamName());
        holder.jogados.setText("Jogos: "+jogos.get(position).getPlayedGames().toString());
        holder.pontos.setText("Pontos:"+jogos.get(position).getPoints().toString());
        holder.vitorias.setText("Vitórias: "+jogos.get(position).getWins().toString());
        holder.derrotas.setText("Derrotas: "+jogos.get(position).getLosses().toString());
    }

    @Override
    public int getItemCount() {
        return jogos != null ? jogos.size(): 0;
    }

    public void updateDataSet(final List<TableTeams> table) {
        jogos.clear();
        if ( table!= null )
            jogos.addAll(table);
        notifyDataSetChanged();
    }

    class LineHolderTable extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView posicao;
        private TextView time;
        private TextView jogados;
        private TextView pontos;
        private TextView vitorias;
        private TextView derrotas;

        public LineHolderTable(View itemview) {
            super(itemview);
            posicao  = (TextView)itemview.findViewById(R.id.lbPosicaoTable);
            time     = (TextView)itemview.findViewById(R.id.lbTiamName);
            jogados  = (TextView)itemview.findViewById(R.id.lbJogosTable);
            pontos   = (TextView)itemview.findViewById(R.id.lbPontosTable);
            vitorias = (TextView)itemview.findViewById(R.id.lbVitoriasTable);
            derrotas = (TextView)itemview.findViewById(R.id.lbDerrotasTable);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final TableTeams item = jogos.get(getAdapterPosition());
            mRecycleTableOnClickListener.onClickListener(item);
        }
    }
}
