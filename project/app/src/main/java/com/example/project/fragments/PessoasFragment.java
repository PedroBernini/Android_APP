package com.example.project.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.activities.AmbienteActivity;
import com.example.project.activities.MainActivity;
import com.example.project.activities.NovaPessoaActivity;
import com.example.project.adapters.PessoasAdapter;
import com.example.project.ambiente.Ambiente;
import com.example.project.ambiente.Pessoa;

import java.util.List;

public class PessoasFragment extends Fragment {

    private View view;
    private ListView listViewPessoas;
    private List<Pessoa> pessoas;
    private PessoasAdapter adapter;
    private Ambiente ambiente;
    private Button btnNovaPessoa;

    public PessoasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null)
            view = inflater.inflate(R.layout.fragment_pessoas, container, false);
            ambiente = AmbienteActivity.ambiente;
            pessoas = ambiente.getPessoas();

            listViewPessoas = (ListView)view.findViewById(R.id.listViewPessoas);
            adapter = new PessoasAdapter(this.getActivity(), pessoas);
            listViewPessoas.setAdapter(adapter);

            btnNovaPessoa = (Button) view.findViewById(R.id.btn_nova_pessoa);

            btnNovaPessoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = getActivity();
                    Intent intent = new Intent(context, NovaPessoaActivity.class);
                    context.startActivity(intent);
                }
            });
        return view;
    }

}
