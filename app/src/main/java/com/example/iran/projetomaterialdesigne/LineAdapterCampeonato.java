package com.example.iran.projetomaterialdesigne;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.iran.projetomaterialdesigne.Models.Campeonatos.Liga;

import java.util.ArrayList;
import java.util.List;

class LineAdapterCampeonato extends RecyclerView.Adapter<LineHolderCampeonato> {

    private List<Liga> ligasReceived;

    public LineAdapterCampeonato(ArrayList<Liga> ligas) {
        ligasReceived = ligas;
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
}
