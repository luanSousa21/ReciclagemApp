package com.example.reciclandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {
    Button btnCadCli, btnCadCol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnCadCli = findViewById(R.id.btnCadastrarCliente);
        btnCadCol = findViewById(R.id.btnCadastrarColetor);
        btnCadCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(InicioActivity.this, CadastroUsuarioActivity.class);
                startActivity(i);
            }
        });
        btnCadCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(InicioActivity.this, CadastroColetor.class);
                startActivity(i);
            }
        });
    }
}