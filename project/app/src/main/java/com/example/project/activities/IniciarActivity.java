package com.example.project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class IniciarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

    }

    public void clickEntrar(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
