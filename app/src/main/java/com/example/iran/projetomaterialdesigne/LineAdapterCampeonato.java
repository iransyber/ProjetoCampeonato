package com.example.iran.projetomaterialdesigne;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iran.projetomaterialdesigne.Models.Campeonatos.Liga;

import java.util.List;

public class LineAdapterCampeonato extends RecyclerView.Adapter<LineAdapterCampeonato.LineHolderCampeonato> {

    private List<Liga> ligasReceived;
    private RecycleViewOnClickListener<Liga> mRecycleCompeticaoOnClickListener;

    public LineAdapterCampeonato(List<Liga> ligas, @NonNull RecycleViewOnClickListener<Liga> listener) {
        ligasReceived = ligas;
        mRecycleCompeticaoOnClickListener = listener;
    }

    @Override
    public LineHolderCampeonato onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolderCampeonato(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_campeonato, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolderCampeonato holder, int position) {
        holder.caption.setText(ligasReceived.get(position).getCaption());
        holder.campeonato.setText(ligasReceived.get(position).getLeague());
        holder.ano.setText(ligasReceived.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return ligasReceived != null ? ligasReceived.size(): 0;
    }

    public void updateDataSet(final List<Liga> ligas) {
        ligasReceived.clear();
        ligasReceived.addAll(ligas);
        notifyDataSetChanged();
    }

    class LineHolderCampeonato extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView caption;
        private TextView campeonato;
        private TextView ano;

        public LineHolderCampeonato(View itemview) {
            super(itemview);
            caption = (TextView) itemView.findViewById(R.id.lbCaption);
            campeonato = (TextView) itemView.findViewById(R.id.lbLeague);
            ano = (TextView) itemView.findViewById(R.id.lbAno);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Liga item = ligasReceived.get(getAdapterPosition());
            mRecycleCompeticaoOnClickListener.onClickListener(item);
        }
    }
}
