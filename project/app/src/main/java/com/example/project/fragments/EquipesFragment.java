package com.example.project.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.activities.AmbienteActivity;
import com.example.project.activities.MainActivity;
import com.example.project.activities.NovaEquipeActivity;
import com.example.project.activities.NovaPessoaActivity;
import com.example.project.adapters.EquipesAdapter;
import com.example.project.ambiente.Ambiente;
import com.example.project.ambiente.Equipe;

import java.util.ArrayList;
import java.util.List;


public class EquipesFragment extends Fragment {

    private View view;
    private ListView listViewEquipes;
    private List<Equipe> equipes;
    private EquipesAdapter adapter;
    private Ambiente ambiente;
    private Button btnNovaEquipe;

    public EquipesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_equipes, container, false);
            listViewEquipes = (ListView)view.findViewById(R.id.listViewEquipes);
            ambiente = AmbienteActivity.ambiente;
            equipes = ambiente.getEquipes();
            adapter = new EquipesAdapter(this.getActivity(), equipes);
            listViewEquipes.setAdapter(adapter);

        btnNovaEquipe = (Button) view.findViewById(R.id.btn_nova_equipe);

        btnNovaEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
                Intent intent = new Intent(context, NovaEquipeActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }

}
