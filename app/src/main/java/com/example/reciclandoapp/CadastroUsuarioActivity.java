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

import com.example.reciclandoapp.classes.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();

    private EditText nome, endereco, cpf, telefone, dtNasc, email, senha;
    private Button cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Cadastro");


        nome = findViewById(R.id.txtNome);
        endereco = findViewById(R.id.txtEndereco);
        cpf = findViewById(R.id.txtCPF);
        telefone = findViewById(R.id.txtTelefone);
        dtNasc = findViewById(R.id.txtDataNasc);
        email = findViewById(R.id.txtEmailAuth);
        senha = findViewById(R.id.txtSenhaAuth);
        cadastrar = findViewById(R.id.btnCadUser);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();

            }
        });
    }


    private void cadastrarUsuario()
    {
        Usuario user = new Usuario();
        user.setNome(nome.getText().toString());
        user.setEndereco(endereco.getText().toString());
        user.setCpf(Integer.parseInt(cpf.getText().toString()));
        user.setTelefone(telefone.getText().toString());
        user.setDataNascimento(dtNasc.getText().toString());
        user.setEmail(email.getText().toString());
        DatabaseReference usuario = referencia.child("Usuario");
        usuario.push().setValue(user);

        usuarioAuth.createUserWithEmailAndPassword(email.getText().toString(),
                senha.getText().toString()).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(CadastroUsuarioActivity.this, LoginWhitEmailActivity.class);
                            startActivity(i);
                        }else {
                            Log.i("CreateUser", "Deu ruim");
                        }
                    }
                });

    }
}