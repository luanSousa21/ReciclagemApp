package com.example.reciclandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginWhitEmailActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private Button btnLogin, btnCadastro;
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_whit_email);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("LOGIN");


        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Teste", "Usuario: "+txtEmail.getText().toString());
                Log.i("Teste", "Senha: "+txtSenha.getText().toString());

                usuario.signInWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(LoginWhitEmailActivity.this, PrincipalActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(LoginWhitEmailActivity.this, "Usuário não cadastrado", Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });



        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginWhitEmailActivity.this, CadastroUsuarioActivity.class);
                startActivity(i);
            }
        });

    }


    private void verificaLogin(String email, String senha)
    {

       usuario.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Intent i = new Intent(LoginWhitEmailActivity.this, PrincipalActivity.class);
                   startActivity(i);
                   finish();
               }else {
                   Toast.makeText(LoginWhitEmailActivity.this, "Usuário não cadastrado", Toast.LENGTH_LONG);
               }
           }
       });
    }
}