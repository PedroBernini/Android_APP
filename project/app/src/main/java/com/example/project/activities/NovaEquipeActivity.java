package com.example.project.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.adapters.SelectPessoasAdapter;
import com.example.project.ambiente.Pessoa;

import java.util.List;

public class NovaEquipeActivity extends AppCompatActivity {

    private EditText editNomeEquipe;
    private ListView listViewSelectPessoas;
    private List<Pessoa> pessoas;
    private SelectPessoasAdapter adapter;

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
        listViewSelectPessoas.setAdapter(adapter);
    }

    public void criarNovaEquipe (View view) {
        Toast.makeText(this, "Equipe registrada com sucesso!", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
