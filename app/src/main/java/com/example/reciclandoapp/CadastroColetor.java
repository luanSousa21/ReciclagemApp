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

import com.example.reciclandoapp.classes.Coletor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroColetor extends AppCompatActivity {
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();

    EditText txtNome, txtEndereco, txtcpf,txtTelefone, txtDataNasc, txtcnpj, txtveiculo, txtEmail, txtSenha;
    Button btnCadColetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_coletor);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Cadastro de coletor");
        txtNome = findViewById(R.id.txtNomeColetor);
        txtEndereco = findViewById(R.id.txtEnderecoColetor);
        txtcpf = findViewById(R.id.txtCPFColetor);
        txtTelefone = findViewById(R.id.txtTelefoneColetor);
        txtDataNasc = findViewById(R.id.txtDataNascimentoColetor);
        txtcnpj = findViewById(R.id.txtCNPJColetor);
        txtveiculo = findViewById(R.id.txtVeiculoColetor);
        txtEmail = findViewById(R.id.txtEmailColetor);
        txtSenha = findViewById(R.id.txtSenhaColetor);
        btnCadColetor = findViewById(R.id.btnCadastroColetor);
        
        btnCadColetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarColetor();
            }
        });
        
    }
    
    public void cadastrarColetor()
    {
        Coletor coletor = new Coletor();
        coletor.setNome(txtNome.getText().toString());
        coletor.setEndereco(txtEndereco.getText().toString());
        coletor.setCpf(txtcpf.getText().toString());
        coletor.setTelefone(txtTelefone.getText().toString());
        coletor.setDataNascimento(txtDataNasc.getText().toString());
        coletor.setCnpj(txtcnpj.getText().toString());
        coletor.setVeiculo(txtveiculo.getText().toString());
        coletor.setEmail(txtEmail.getText().toString());
        coletor.setTipoUsuario(2);
        DatabaseReference usuario = referencia.child("Usuario");
        usuario.push().setValue(coletor);

        usuarioAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),
                txtSenha.getText().toString()).addOnCompleteListener(CadastroColetor.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(CadastroColetor.this, LoginWhitEmailActivity.class);
                    startActivity(i);
                }else {
                    Log.i("CreateUser", "Deu ruim");
                }
            }
        });
    }
}