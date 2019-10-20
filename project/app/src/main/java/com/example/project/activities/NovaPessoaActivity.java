package com.example.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.adapters.QuestoesAdapter;
import com.example.project.ambiente.Pessoa;
import com.example.project.utils.Questao;
import com.example.project.utils.Questoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NovaPessoaActivity extends AppCompatActivity {

    private ListView listViewQuestoes;
    private QuestoesAdapter adapter;
    private EditText editNomePessoa;
    public static List<Questao> questoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_pessoa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editNomePessoa = (EditText) findViewById(R.id.editNomePessoa);

        questoes = Arrays.asList(Questoes.questoes);
        for(Questao questao : questoes) {
            questao.setPontuacaoD(0);
            questao.setPontuacaoI(0);
            questao.setPontuacaoS(0);
            questao.setPontuacaoC(0);
        }

        listViewQuestoes = (ListView)this.findViewById(R.id.listViewQuestoes);
        adapter = new QuestoesAdapter(this, questoes);
        listViewQuestoes.setAdapter(adapter);
    }

    public void criarNovaPessoa (View view) {
        int notaD = 0;
        int notaI = 0;
        int notaS = 0;
        int notaC = 0;

        for(Questao questao : questoes){
            notaD += questao.getPontuacaoD();
            notaI += questao.getPontuacaoI();
            notaS += questao.getPontuacaoS();
            notaC += questao.getPontuacaoC();
        }

        if(notaD + notaI + notaS + notaC == questoes.size()){
            Pessoa novaPessoa = new Pessoa(editNomePessoa.getText().toString(), notaD, notaI, notaS, notaC);
            Toast.makeText(this, "Nome: " + editNomePessoa.getText().toString() + "\n NotaD: " + notaD + "\n NotaI: " + notaI + "\nNotaS: " + notaS + "\nNotaC: " + notaC, Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(this, "Preencha os campos faltantes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
