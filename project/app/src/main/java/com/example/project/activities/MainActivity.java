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
import com.example.project.ambiente.Database.PessoaDB;
import com.example.project.ambiente.Equipe;
import com.example.project.ambiente.Pessoa;
import com.example.project.model.Ambientes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewAmbientes;
    private AmbientesAdapter adapter;
    private DatabaseReference reff;
    private Button btn_atualizar;

    public void loadBD(){

        //Ambientes.ambientes = new ArrayList<>();

        List<Pessoa> listPessoas1 = new ArrayList<Pessoa>();
        listPessoas1.add(new Pessoa("Pedro Bernini", 1, 2, 3, 4));
        listPessoas1.add(new Pessoa("Brenda Alexsandra", 2, 4, 2, 2));
        listPessoas1.add(new Pessoa("Alfredo Albélis", 1, 1, 3, 5));
        listPessoas1.add(new Pessoa("Cléofas Peres", 1, 2, 5, 2));
        listPessoas1.add(new Pessoa("Vinícius Abrantes", 4, 2, 2, 2));
        listPessoas1.add(new Pessoa("Felipe Marchi", 2, 2, 3, 3));
        listPessoas1.add(new Pessoa("Leonardo Ferrari", 3, 3, 2, 2));
        List<Equipe> listEquipes1 = new ArrayList<Equipe>();
        List<Pessoa> grupo1 = new ArrayList<Pessoa>();
        List<Pessoa> grupo2 = new ArrayList<Pessoa>();
        List<Pessoa> grupo3 = new ArrayList<Pessoa>();
        List<Pessoa> grupo4 = new ArrayList<Pessoa>();
        List<Pessoa> grupo5 = new ArrayList<Pessoa>();
        List<Pessoa> grupo6 = new ArrayList<Pessoa>();
        List<Pessoa> grupo7 = new ArrayList<Pessoa>();
        grupo1.add(listPessoas1.get(0));
        grupo1.add(listPessoas1.get(1));
        grupo2.add(listPessoas1.get(3));
        grupo2.add(listPessoas1.get(4));
        grupo2.add(listPessoas1.get(5));
        grupo2.add(listPessoas1.get(6));
        for (int i = 0; i<20; i++) {
            grupo3.add(listPessoas1.get(2));
        }
        grupo4.add(listPessoas1.get(2));
        grupo4.add(listPessoas1.get(3));
        grupo4.add(listPessoas1.get(4));
        grupo5.add(listPessoas1.get(0));
        grupo5.add(listPessoas1.get(1));
        grupo5.add(listPessoas1.get(2));
        grupo6.add(listPessoas1.get(0));
        grupo6.add(listPessoas1.get(2));
        grupo6.add(listPessoas1.get(0));
        grupo6.add(listPessoas1.get(6));

        listEquipes1.add(new Equipe("Dupla Master Android", grupo1));
        listEquipes1.add(new Equipe("Bots do Android", grupo2));
        listEquipes1.add(new Equipe("Collection<ArrayList<Alfredo>>", grupo3));
        listEquipes1.add(new Equipe("Baunília", grupo4));
        listEquipes1.add(new Equipe("Python Crew", grupo5));
        listEquipes1.add(new Equipe("Rede Neural Recursiva", grupo6));
        listEquipes1.add(new Equipe("Totem", grupo7));

        List<Pessoa> listPessoas2 = new ArrayList<Pessoa>();
        listPessoas2.add(new Pessoa("Letícia Oliveira", 2, 2, 3, 3));
        listPessoas2.add(new Pessoa("Juliana Marchi", 1, 1, 4, 4));
        listPessoas2.add(new Pessoa("Danielle Hay", 1, 1, 5, 3));
        listPessoas2.add(new Pessoa("Irislene Silveira", 1, 1, 5, 3));
        listPessoas2.add(new Pessoa("Luciano Nascimento", 2, 3, 3, 2));
        listPessoas2.add(new Pessoa("Bruno do Valle", 1, 2, 3, 4));
        listPessoas2.add(new Pessoa("Marcelo Laendle", 2, 3, 4, 1));
        List<Equipe> listEquipes2 = new ArrayList<Equipe>();
        grupo1 = new ArrayList<Pessoa>();
        grupo2 = new ArrayList<Pessoa>();
        grupo1.add(listPessoas2.get(0));
        grupo1.add(listPessoas2.get(1));
        grupo1.add(listPessoas2.get(2));
        grupo1.add(listPessoas2.get(3));
        grupo2.add(listPessoas2.get(4));
        grupo2.add(listPessoas2.get(5));
        grupo2.add(listPessoas2.get(6));
        listEquipes2.add(new Equipe("Turminha da Iris", grupo1));
        listEquipes2.add(new Equipe("Pizzaria do Marcelo", grupo2));

        List<Pessoa> listPessoas3 = new ArrayList<Pessoa>();
        listPessoas3.add(new Pessoa("Rodrigo Martins", 3, 4, 1, 2));
        listPessoas3.add(new Pessoa("Vinícius Vilas", 3, 5, 4, 2));
        listPessoas3.add(new Pessoa("Isabel Sprogis", 4, 4, 1, 1));
        listPessoas3.add(new Pessoa("Cleyvesson Silva", 1, 5, 2, 2));
        List<Equipe> listEquipes3 = new ArrayList<Equipe>();
        grupo1 = new ArrayList<Pessoa>();
        grupo2 = new ArrayList<Pessoa>();
        grupo1.add(listPessoas3.get(0));
        grupo1.add(listPessoas3.get(1));
        grupo2.add(listPessoas3.get(2));
        grupo2.add(listPessoas3.get(3));
        listEquipes3.add(new Equipe("Meme 1", grupo1));
        listEquipes3.add(new Equipe("Meme 2", grupo2));

        List<Pessoa> listPessoas4 = new ArrayList<Pessoa>();
        listPessoas4.add(new Pessoa("Samuel Azorli", 1, 1, 3, 5));
        listPessoas4.add(new Pessoa("João Victor", 1, 2, 4, 3));
        listPessoas4.add(new Pessoa("Keith Kazumi", 1, 1, 5, 3));
        List<Equipe> listEquipes4 = new ArrayList<Equipe>();
        grupo1 = new ArrayList<Pessoa>();
        grupo2 = new ArrayList<Pessoa>();
        grupo1.add(listPessoas4.get(0));
        grupo1.add(listPessoas4.get(1));
        grupo2.add(listPessoas4.get(0));
        grupo2.add(listPessoas4.get(2));
        listEquipes4.add(new Equipe("Gumihu 1", grupo1));
        listEquipes4.add(new Equipe("Gumihu 2", grupo2));

        List<Pessoa> listPessoas5 = new ArrayList<Pessoa>();
        listPessoas5.add(new Pessoa("Ana Estela", 3, 2, 2, 3));
        listPessoas5.add(new Pessoa("André Leon", 2, 4, 3, 1));
        listPessoas5.add(new Pessoa("Antônio Zambon", 1, 3, 3, 3));
        listPessoas5.add(new Pessoa("Celmar Guimarães", 1, 3, 4, 2));
        listPessoas5.add(new Pessoa("Gisele Baioco", 2, 1, 3, 4));
        listPessoas5.add(new Pessoa("Guilherme Palermo", 2, 3, 3, 2));
        listPessoas5.add(new Pessoa("Ieda Hidalgo", 1, 2, 4, 3));
        listPessoas5.add(new Pessoa("Ivan Ricarte", 1, 3, 1, 5));
        listPessoas5.add(new Pessoa("João Bertini", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Luiz Camolesi", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Marco Antonio", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Marcos Borges", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Marli Hernandez", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Paulo Martins", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Plínio Vilela", 2, 4, 2, 2));
        listPessoas5.add(new Pessoa("Ulisses Dias", 1, 1, 3, 5));
        listPessoas5.add(new Pessoa("Varese Timóteo", 1, 1, 4, 4));
        listPessoas5.add(new Pessoa("Vitor Coluci", 1, 1, 4, 4));

        List<Equipe> listEquipes5 = new ArrayList<Equipe>();
        grupo1 = new ArrayList<Pessoa>();
        grupo2 = new ArrayList<Pessoa>();
        grupo1.add(listPessoas5.get(0));
        grupo1.add(listPessoas5.get(1));
        grupo1.add(listPessoas5.get(5));
        grupo2.add(listPessoas5.get(8));
        grupo2.add(listPessoas5.get(14));
        grupo2.add(listPessoas5.get(15));
        listEquipes5.add(new Equipe("HighPids", grupo1));
        listEquipes5.add(new Equipe("Companheiros de Bandeco", grupo2));

        Ambientes.ambientes.add(new Ambiente("Oudri Kanda","Ambiente de Produção", listPessoas1, listEquipes1));
        Ambientes.ambientes.add(new Ambiente("Wumpus Crew","Ambiente Organizacional", listPessoas2, listEquipes2));
        Ambientes.ambientes.add(new Ambiente("Liga dos Professores","Ambiente de Vendas", listPessoas5, listEquipes5));
        Ambientes.ambientes.add(new Ambiente("Federação do Truco","Ambiente de Marketing", listPessoas3, listEquipes3));
        Ambientes.ambientes.add(new Ambiente("Clube do Samuel","Ambiente de Suporte", listPessoas4, listEquipes4));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_ambientes);

        if(savedInstanceState == null) {
            //loadBD();
        }

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
