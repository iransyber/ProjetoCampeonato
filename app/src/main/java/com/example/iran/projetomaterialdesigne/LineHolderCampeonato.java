package com.example.iran.projetomaterialdesigne;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by iran_ on 09/10/2017.
 */

public class LineHolderCampeonato extends RecyclerView.ViewHolder {


    public TextView caption;
    public TextView campeonato;
    public TextView ano;

    public LineHolderCampeonato(View itemview){
        super(itemview);
        caption = (TextView)itemView.findViewById(R.id.lbCaption);
        campeonato= (TextView)itemView.findViewById(R.id.lbLeague);
        ano = (TextView)itemView.findViewById(R.id.lbAno);
    }

}
