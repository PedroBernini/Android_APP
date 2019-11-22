package com.example.project.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapters.AmbientesAdapter;
import com.example.project.ambiente.Ambiente;
import com.example.project.ambiente.Database.AmbienteDB;
import com.example.project.ambiente.Database.EquipeDB;
import com.example.project.ambiente.Database.PessoaDB;
import com.example.project.ambiente.Equipe;
import com.example.project.ambiente.Pessoa;
import com.example.project.model.Ambientes;
import com.example.project.utils.BancoImprovisado;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewAmbientes;
    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_ambientes);

        listViewAmbientes = (ListView) findViewById(R.id.listViewAmbientes);
        Ambientes.ambientes.clear();
        reff = FirebaseDatabase.getInstance().getReference().child("Ambientes");
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postAmbiente: dataSnapshot.getChildren()) {
                    String nomeAmbiente = postAmbiente.child("nomeAmbiente").getValue().toString();
                    String tipoAmbiente = postAmbiente.child("tipoAmbiente").getValue().toString();

                    List<Pessoa> pessoasAmbiente = new ArrayList<>();
                    for (DataSnapshot postPessoa : postAmbiente.child("Pessoas").getChildren()) {
                        PessoaDB pessoaDB = postPessoa.getValue(PessoaDB.class);
                        pessoasAmbiente.add(new Pessoa(pessoaDB.nome, pessoaDB.email, Integer.parseInt(pessoaDB.notaD), Integer.parseInt(pessoaDB.notaI), Integer.parseInt(pessoaDB.notaS), Integer.parseInt(pessoaDB.notaC)));
                    }

                    List<Equipe> equipesAmbiente = new ArrayList<>();
                    for (DataSnapshot postEquipe : postAmbiente.child("Equipes").getChildren()) {
                        String nomeEquipe = postEquipe.child("nome").getValue().toString();

                        List<Pessoa> pessoasEquipe = new ArrayList<>();
                        for (DataSnapshot postPessoasEquipe : postEquipe.child("Pessoas").getChildren()) {
                            PessoaDB pessoaDB = postPessoasEquipe.getValue(PessoaDB.class);
                            pessoasEquipe.add(new Pessoa(pessoaDB.nome, pessoaDB.email, Integer.parseInt(pessoaDB.notaD), Integer.parseInt(pessoaDB.notaI), Integer.parseInt(pessoaDB.notaS), Integer.parseInt(pessoaDB.notaC)));
                        }

                        equipesAmbiente.add(new Equipe(nomeEquipe, pessoasEquipe));
                    }
                    Ambientes.ambientes.add(new Ambiente(nomeAmbiente, tipoAmbiente, pessoasAmbiente, equipesAmbiente));
                }
                AmbientesAdapter adapter = new AmbientesAdapter(getContext(), Ambientes.ambientes);
                listViewAmbientes.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Erro ao buscar no Firebase!");
            }
        });
    }

    public void criarNovoAmbiente(View view) {
        finish();
        Intent intent = new Intent(this, NovoAmbienteActivity.class);
        this.startActivity(intent);
    }

    public Context getContext() {
        return this;
    }
}
