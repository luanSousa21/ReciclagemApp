package com.example.reciclandoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //usuario.signOut();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                exibirProximaTela();
            }
        }, 3000);
    }

    private void exibirProximaTela()
    {
        if (usuario.getCurrentUser() != null)
        {
            Intent i = new Intent(this, PrincipalActivity.class);
            startActivity(i);
            finish();
        }else {
            Intent i = new Intent(this, LoginWhitEmailActivity.class);
            startActivity(i);
            finish();
        }
    }
}