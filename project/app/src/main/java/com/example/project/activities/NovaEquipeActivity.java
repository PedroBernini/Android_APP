package com.example.project.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.adapters.SelectPessoasAdapter;
import com.example.project.ambiente.Ambiente;
import com.example.project.ambiente.Database.EquipeDB;
import com.example.project.ambiente.Database.PessoaDB;
import com.example.project.ambiente.Equipe;
import com.example.project.ambiente.Pessoa;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NovaEquipeActivity extends AppCompatActivity {

    private EditText editNomeEquipe;
    private ListView listViewSelectPessoas;
    private SelectPessoasAdapter adapter;
    public static List<Pessoa> pessoas;
    public static List<Pessoa> grupo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_equipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editNomeEquipe = (EditText) findViewById(R.id.editNomeEquipe);
        listViewSelectPessoas = (ListView) findViewById(R.id.listViewSelectPessoas);
        pessoas = AmbienteActivity.ambiente.getPessoas();
        adapter = new SelectPessoasAdapter(this, pessoas);
        try {
            listViewSelectPessoas.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(this, "Não existe nenhuma pessoa no ambiente!", Toast.LENGTH_SHORT).show();
        }
    }

    public void criarNovaEquipe (View view) {
        if (editNomeEquipe.getText().toString().equals("")) {
            Toast.makeText(this, "Escolha um nome para a equipe!", Toast.LENGTH_SHORT).show();
        }
        else {

            if (grupo.size() < 2) {
                Toast.makeText(this, "Escolha pelo menos dois integrantes!", Toast.LENGTH_SHORT).show();
            }
            else {
                String nome = editNomeEquipe.getText().toString();
                Equipe equipe = new Equipe(nome, grupo);
                AmbienteActivity.ambiente.adicionarEquipe(equipe);

                EquipeDB equipeFireBase = new EquipeDB(equipe.getNome(), Integer.toString(equipe.getNotaD()), Integer.toString(equipe.getNotaI()), Integer.toString(equipe.getNotaS()), Integer.toString(equipe.getNotaC()));
                FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

                firebaseDB.getReference().child("Ambientes").child(AmbienteActivity.ambiente.getNomeAmbiente()).child("Equipes").child(equipeFireBase.getNome()).setValue(equipeFireBase);
                for (Pessoa pessoa : equipe.getPessoas()) {
                    PessoaDB pessoaFireBase = new PessoaDB(pessoa.getNome(), pessoa.getEmail(), Integer.toString(pessoa.getNotaD()), Integer.toString(pessoa.getNotaI()), Integer.toString(pessoa.getNotaS()), Integer.toString(pessoa.getNotaC()));
                    firebaseDB.getReference().child("Ambientes").child(AmbienteActivity.ambiente.getNomeAmbiente()).child("Equipes").child(equipeFireBase.getNome()).child("Pessoas").child(pessoaFireBase.getNome()).setValue(pessoaFireBase);
                }
                grupo = new ArrayList<>();
                Toast.makeText(this, "Equipe registrada com sucesso!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
