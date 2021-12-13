package com.example.reciclandoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalhePedidoActivity extends AppCompatActivity {
    TextView txtUsuarioSolicitante, txtEndereco, txtDescricao, txtDataHora, txtMateriais, txtTelefone;
    Button btnAceitarPedido;
    SharedPreferences preferences;

    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pedido);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Detalhes do Pedido");
        Bundle b = getIntent().getExtras();
        DatabaseReference pedidos =  referencia.child("PedidoColeta");
        preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);

        txtDescricao = findViewById(R.id.txtDescricaoPedidoColetaListagem);
        txtEndereco = findViewById(R.id.txtDescricaoEnderecoColeta);
        txtDataHora = findViewById(R.id.txtDescricaoDataHoraColeta);
        txtMateriais = findViewById(R.id.txtDescricaoMateriaisColeta);
        txtUsuarioSolicitante = findViewById(R.id.txtDescricaoUsuarioSolicitante);
        txtTelefone = findViewById(R.id.txtDescricaoTelefone);
        btnAceitarPedido = findViewById(R.id.btnAceitarPedido);

        txtDescricao.setText(txtDescricao.getText()+b.get("descricaoPedido").toString());
        txtEndereco.setText(txtEndereco.getText()+b.get("enderecoColeta").toString());
        txtDataHora.setText(txtDataHora.getText()+b.get("dataHoraColeta").toString());
        txtMateriais.setText(txtMateriais.getText()+b.get("materiais").toString());
        txtUsuarioSolicitante.setText(txtUsuarioSolicitante.getText()+b.get("usuarioSolicitante").toString());
        txtTelefone.setText(txtTelefone.getText()+b.get("telefone").toString());

        btnAceitarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference valor = pedidos.child(b.get("chave").toString());
                valor.child("nomeColetor").setValue(preferences.getString("nomeUsuario", " "));
                Intent i = new Intent(DetalhePedidoActivity.this, PrincipalActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetalhePedidoActivity.this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}