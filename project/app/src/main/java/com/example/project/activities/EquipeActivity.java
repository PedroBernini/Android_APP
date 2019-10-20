package com.example.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.ambiente.Equipe;
import com.example.project.ambiente.Pessoa;
import com.example.project.utils.Colors;
import com.example.project.utils.Descricoes;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class EquipeActivity extends AppCompatActivity {

    private int position;
    private Equipe equipe;
    private PieChart graficoEquipe;
    private TextView nomeEquipe;
    private TextView txtPessoasEquipe;
    private TextView fatorPredominante;
    private TextView resumoEquipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent in = getIntent();
        if (in != null) {
            this.position = in.getIntExtra("position", 0);
        }

        equipe = AmbienteActivity.ambiente.getEquipes().get(position);

        nomeEquipe = (TextView) findViewById(R.id.nomeEquipe);
        txtPessoasEquipe = (TextView) findViewById(R.id.txt_pessoas_da_equipe);
        resumoEquipe = (TextView) findViewById(R.id.resumoEquipe);
        fatorPredominante = (TextView) findViewById(R.id.fator_predominante_equipe);

        nomeEquipe.setText(equipe.getNome());

        List<Pessoa> pessoas = equipe.getPessoas();

        String pessoasEquipe = "";

        for (int i = 0; i<pessoas.size(); i++) {
            Pessoa pessoa = pessoas.get(i);
            if (i == pessoas.size() - 1) {
                pessoasEquipe += pessoa.getNome();
            } else {
                pessoasEquipe += pessoa.getNome() + "\n";
            }
        }

        txtPessoasEquipe.setText(pessoasEquipe);

        char predominancia = equipe.getCaracteristicaPrimaria();

        switch (predominancia) {
            case 'D':
                fatorPredominante.setText("Combinação Predominante: D - Dominância");
                resumoEquipe.setText(Descricoes.descricaoDEquipe);
                fatorPredominante.setTextColor(Colors.corD);
                break;
            case 'I':
                fatorPredominante.setText("Combinação Predominante: I - Influência");
                resumoEquipe.setText(Descricoes.descricaoIEquipe);
                fatorPredominante.setTextColor(Colors.corI);
                break;
            case 'S':
                fatorPredominante.setText("Combinação Predominante: S - Estabilidade");
                resumoEquipe.setText(Descricoes.descricaoSEquipe);
                fatorPredominante.setTextColor(Colors.corS);
                break;
            case 'C':
                fatorPredominante.setText("Combinação Predominante: C - Conformidade");
                resumoEquipe.setText(Descricoes.descricaoCEquipe);
                fatorPredominante.setTextColor(Colors.corC);
                break;
        }

        List<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(equipe.getNotaD(), "D"));
        pieEntries.add(new PieEntry(equipe.getNotaI(), "I"));
        pieEntries.add(new PieEntry(equipe.getNotaS(), "S"));
        pieEntries.add(new PieEntry(equipe.getNotaC(), "C"));

        graficoEquipe = (PieChart) findViewById(R.id.pieChartEquipe);
        graficoEquipe.animateXY(1000, 1000);
        graficoEquipe.setVisibility(View.VISIBLE);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Distribuição DISC");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(Colors.colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(0f);
        //pieData.setValueFormatter(new PercentFormatter());
        graficoEquipe.setData(pieData);

        Description description = new Description();
        description.setText("Avaliação da Equipe");

        graficoEquipe.setDescription(description);
        graficoEquipe.invalidate();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
