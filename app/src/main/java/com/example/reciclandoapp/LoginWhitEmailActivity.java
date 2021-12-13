package com.example.reciclandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reciclandoapp.adapter.AdapterUsuario;
import com.example.reciclandoapp.classes.Ideia;
import com.example.reciclandoapp.classes.Usuario;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginWhitEmailActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private Button btnLogin, btnCadastro;
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    AdapterUsuario adpt;
    RecyclerView rvUser;
    FirebaseRecyclerOptions<Usuario> options;
    SharedPreferences preferences;
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
        rvUser = findViewById(R.id.recyclerShare);
        DatabaseReference usuarios = referencia.child("Usuario");
        preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);

        Query pesquisaUsuario = usuarios.orderByChild("email");
        options = new FirebaseRecyclerOptions.Builder<Usuario>().setQuery(pesquisaUsuario, Usuario.class).build();
        adpt = new AdapterUsuario(options,this );
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        rvUser.setLayoutManager(layoutManager1);
        rvUser.setHasFixedSize(true);
        rvUser.setAdapter(adpt);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Teste", "Usuario: "+txtEmail.getText().toString());
                Log.i("Teste", "Senha: "+txtSenha.getText().toString());

                usuario.signInWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            gravaPrefs(txtEmail.getText().toString());
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
                Intent i = new Intent(LoginWhitEmailActivity.this, InicioActivity.class);
                startActivity(i);
            }
        });

    }

    public void gravaPrefs(String email)
    {
        for (Usuario dt : options.getSnapshots())
        {
            Log.i("Dados Usuarios", dt.getNome());
            if(dt.getEmail().equals(email))
            {
                SharedPreferences.Editor sp = preferences.edit();

                sp.putString("nomeUsuario", dt.getNome());
                sp.putString("email", dt.getEmail());
                sp.putInt("tipoUsuario", dt.getTipoUsuario());
                sp.commit();
            }
        }

    }

    @Override public void onStart()
    {
        super.onStart();
        adpt.startListening();
    }

    @Override public void onStop()
    {
        super.onStop();

        adpt.stopListening();

    }
}