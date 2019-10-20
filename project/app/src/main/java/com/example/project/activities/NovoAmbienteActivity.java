package com.example.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activities.MainActivity;
import com.example.project.ambiente.Ambiente;
import com.example.project.model.Ambientes;

public class NovoAmbienteActivity extends AppCompatActivity {

    private EditText editTextNomeAmbiente;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_ambiente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinner = (Spinner) findViewById(R.id.spinnerAmbientes);
        editTextNomeAmbiente = (EditText) findViewById(R.id.editTxtNomeAmbiente);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void clickCriar (View view) {
        String nomeAmbiente = editTextNomeAmbiente.getText().toString();
        String tipoAmbiente = String.valueOf(spinner.getSelectedItem());
        Ambientes.ambientes.add(new Ambiente(nomeAmbiente, tipoAmbiente));
        Toast.makeText(this,tipoAmbiente + " criado com sucesso!", Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);*/

        this.onBackPressed();
    }

}
