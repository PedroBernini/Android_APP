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
import com.example.project.email.GmailSend;
import com.example.project.utils.CorpoEmail;
import com.example.project.utils.Questao;
import com.example.project.utils.Questoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NovaPessoaActivity extends AppCompatActivity {

    private ListView listViewQuestoes;
    private QuestoesAdapter adapter;
    private EditText editNomePessoa;
    private EditText editEmailPessoa;
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
        editEmailPessoa = (EditText) findViewById(R.id.editEmailPessoa);

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
            String nome = editNomePessoa.getText().toString();
            String email = editEmailPessoa.getText().toString();
            Pessoa novaPessoa = new Pessoa(nome, email, notaD, notaI, notaS, notaC);
            Toast.makeText(this, "Nome: " + novaPessoa.getNome() + "\n NotaD: " + novaPessoa.getNotaD() + "\n NotaI: " + novaPessoa.getNotaI() + "\nNotaS: " + novaPessoa.getNotaS() + "\nNotaC: " + novaPessoa.getNotaC() + "\nEmail: " + novaPessoa.getEmail(), Toast.LENGTH_SHORT).show();

            char disc[] = novaPessoa.ordenarNotas();
            char primeiroPadrao = disc[0];
            char segundoPadrao = disc[1];

            String padroesPerfil = "";

            if (primeiroPadrao == 'D') {
                padroesPerfil += CorpoEmail.dominancia;
            } else if (primeiroPadrao == 'I') {
                padroesPerfil += CorpoEmail.influencia;
            } else if (primeiroPadrao == 'S') {
                padroesPerfil += CorpoEmail.estabilidade;
            } else if (primeiroPadrao == 'C') {
                padroesPerfil += CorpoEmail.conformidade;
            }

            if (segundoPadrao == 'D') {
                padroesPerfil += CorpoEmail.dominancia;
            } else if (segundoPadrao == 'I') {
                padroesPerfil += CorpoEmail.influencia;
            } else if (segundoPadrao == 'S') {
                padroesPerfil += CorpoEmail.estabilidade;
            } else if (segundoPadrao == 'C') {
                padroesPerfil += CorpoEmail.conformidade;
            }

            String conteudo = "Olá " + nome+"," +
                    "\n" + CorpoEmail.cabecalho + padroesPerfil + CorpoEmail.desfecho;

            GmailSend send = new GmailSend(email, conteudo);
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
