package com.example.project.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.project.R;
import com.example.project.activities.AmbienteActivity;
import com.example.project.ambiente.Ambiente;
import com.example.project.utils.Colors;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class GeralFragment extends Fragment {

    private View view;
    private Ambiente ambiente;
    private TextView tituloAmbiente;
    private TextView tituloTipo;
    private TextView tituloPessoas;
    private TextView tituloEquipes;
    private PieChart graficoAmbiente;

    public GeralFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_geral, container, false);

        ambiente = AmbienteActivity.ambiente;
        List<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(ambiente.getNotaD(), "D"));
        pieEntries.add(new PieEntry(ambiente.getNotaI(), "I"));
        pieEntries.add(new PieEntry(ambiente.getNotaS(), "S"));
        pieEntries.add(new PieEntry(ambiente.getNotaC(), "C"));

        graficoAmbiente = (PieChart) view.findViewById(R.id.pieChartAmbiente);
        graficoAmbiente.setVisibility(View.VISIBLE);
        graficoAmbiente.animateXY(2000, 2000);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Distribuição DISC");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(Colors.colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(0f);
        graficoAmbiente.setData(pieData);

        Description description = new Description();
        description.setText("Avaliação do Ambiente");
        graficoAmbiente.setDescription(description);
        graficoAmbiente.invalidate();

        tituloAmbiente = (TextView) view.findViewById(R.id.titulo_ambiente);
        tituloTipo = (TextView) view.findViewById(R.id.titulo_tipo);
        tituloPessoas = (TextView) view.findViewById(R.id.titulo_pessoas);
        tituloEquipes = (TextView) view.findViewById(R.id.titulo_equipe);
        tituloAmbiente.setText(ambiente.getNomeAmbiente());
        tituloTipo.setText(ambiente.getTipoAmbiente());
        tituloPessoas.setText(ambiente.getQtdPessoas() + " Pessoa(s)");
        tituloEquipes.setText(ambiente.getQtdEquipes() + " Equipe(s)");

        return view;
    }

}
