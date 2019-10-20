package com.example.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.ambiente.Pessoa;
import com.example.project.utils.Colors;
import com.example.project.utils.Descricoes;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class PessoaActivity extends AppCompatActivity {

    private int position;
    private Pessoa pessoa;
    private PieChart graficoPessoa;
    private TextView nomePessoa;
    private TextView fatorPredominante;
    private TextView resumoPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent in = getIntent();
        if (in != null) {
            this.position = in.getIntExtra("position", 0);
        }

        pessoa = AmbienteActivity.ambiente.getPessoas().get(position);

        nomePessoa = (TextView) findViewById(R.id.nomePessoa);
        resumoPessoa = (TextView) findViewById(R.id.resumoPessoa);
        fatorPredominante = (TextView) findViewById(R.id.fator_predominante_pessoa);

        nomePessoa.setText(pessoa.getNome());

        char predominancia = pessoa.getPredominancia();

        switch (predominancia) {
            case 'D':
                fatorPredominante.setText("Fator Predominante: D - Dominância");
                resumoPessoa.setText(Descricoes.descricaoDPessoa);
                fatorPredominante.setTextColor(Colors.corD);
                break;
            case 'I':
                fatorPredominante.setText("Fator Predominante: I - Influência");
                resumoPessoa.setText(Descricoes.descricaoIPessoa);
                fatorPredominante.setTextColor(Colors.corI);
                break;
            case 'S':
                fatorPredominante.setText("Fator Predominante: S - Estabilidade");
                resumoPessoa.setText(Descricoes.descricaoSPessoa);
                fatorPredominante.setTextColor(Colors.corS);
                break;
            case 'C':
                fatorPredominante.setText("Fator Predominante: C - Conformidade");
                resumoPessoa.setText(Descricoes.descricaoCPessoa);
                fatorPredominante.setTextColor(Colors.corC);
                break;
        }

        List<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(pessoa.getNotaD(), "D"));
        pieEntries.add(new PieEntry(pessoa.getNotaI(), "I"));
        pieEntries.add(new PieEntry(pessoa.getNotaS(), "S"));
        pieEntries.add(new PieEntry(pessoa.getNotaC(), "C"));

        graficoPessoa = (PieChart) findViewById(R.id.pieChartPessoa);
        graficoPessoa.animateXY(1000, 1000);
        graficoPessoa.setVisibility(View.VISIBLE);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Distribuição DISC");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(Colors.colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(0f);
        //pieData.setValueFormatter(new PercentFormatter());
        graficoPessoa.setData(pieData);

        Description description = new Description();
        description.setText("Avaliação Pessoal");

        graficoPessoa.setDescription(description);
        graficoPessoa.invalidate();

        Toast.makeText(this, "Entrou no ambiente de posição " + this.position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
