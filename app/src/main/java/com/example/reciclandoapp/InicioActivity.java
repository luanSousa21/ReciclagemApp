package com.example.reciclandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {
    Button btnLoginWhitEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnLoginWhitEmail = findViewById(R.id.btnLoginWhitEmail);
        btnLoginWhitEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(InicioActivity.this, LoginWhitEmailActivity.class);
                startActivity(i);
            }
        });
    }
}