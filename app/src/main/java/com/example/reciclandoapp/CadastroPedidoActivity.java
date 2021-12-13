package com.example.reciclandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.reciclandoapp.adapter.Adapter;
import com.example.reciclandoapp.adapter.AdapterUsuario;
import com.example.reciclandoapp.classes.Ideia;
import com.example.reciclandoapp.classes.PedidoColeta;
import com.example.reciclandoapp.classes.Usuario;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CadastroPedidoActivity extends AppCompatActivity {
    SeekBar seekbar;
    TextView txtHorario;
    EditText txtDescricao, txtDataColeta;
    RadioButton rbPlastico, rbMetal, rbVidro, rbPapel;
    CheckBox chkEndereco;
    Button btnCadastrar;

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();
    AdapterUsuario adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);
        recyclerView = findViewById(R.id.recyclerUsuario);
        seekbar = findViewById(R.id.seeBar);
        txtHorario = findViewById(R.id.txtcoleta);
        txtDescricao = findViewById(R.id.txtDescricaoPedido);
        txtDataColeta = findViewById(R.id.txtDatacoleta);
        rbPlastico = findViewById(R.id.rbPlastico);
        rbMetal = findViewById(R.id.rbMetal);
        rbVidro = findViewById(R.id.rbVidro);
        rbPapel = findViewById(R.id.rbPapel);
        chkEndereco = findViewById(R.id.chkEndereco);
        btnCadastrar = findViewById(R.id.btnCadPedidoColeta);
        seekbar.setMax(23);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //double hora = progress/24;
                txtHorario.setText(progress+":00");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        DatabaseReference usuarios = referencia.child("Usuario");
        Query pesquisa = usuarios.orderByChild("email").equalTo(usuarioAuth.getCurrentUser().getEmail());
        FirebaseRecyclerOptions<Usuario> options = new FirebaseRecyclerOptions.Builder<Usuario>().setQuery(pesquisa, Usuario.class).build();
        adapter = new AdapterUsuario(options, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarPedido(options);
            }
        });
    }


    public  void cadastrarPedido(FirebaseRecyclerOptions<Usuario> options)
    {
        String materiaisSelecionados = "";
        PedidoColeta pedido = new PedidoColeta();

        pedido.setUsuarioSolicitante(options.getSnapshots().get(0).getNome());
        pedido.setDescricaoPedido(txtDescricao.getText().toString());
        pedido.setDataHoraColeta(txtDataColeta.getText().toString()+" "+txtHorario.getText().toString());
        if (chkEndereco.isChecked())
        {
            pedido.setEnderecoColeta(options.getSnapshots().get(0).getEndereco());
        }else
        {
            pedido.setEnderecoColeta("A combinar com o coletor.");
        }
        if(rbPlastico.isChecked())
        {
            materiaisSelecionados +="Plastico";
        }
        if(rbMetal.isChecked())
        {
            if (materiaisSelecionados.toString() == "")
            {
                materiaisSelecionados +="Metal";
            }else
            {
                materiaisSelecionados +=", Metal";
            }

        }
        if(rbVidro.isChecked())
        {
            if (materiaisSelecionados.toString() == "")
            {
                materiaisSelecionados +="Vidro";
            }else
            {
                materiaisSelecionados +=", Vidro";
            }
        }
        if (rbPapel.isChecked())
        {
            if (materiaisSelecionados.toString() == "")
            {
                materiaisSelecionados +="Papel";
            }else
            {
                materiaisSelecionados +=", Papel";
            }
        }
        pedido.setMateriais(materiaisSelecionados);
        pedido.setTelefoneContato(options.getSnapshots().get(0).getTelefone());
        pedido.setNomeColetor(" ");

        DatabaseReference coleta =  referencia.child("PedidoColeta");

        coleta.push().setValue(pedido);

        Intent i = new Intent(CadastroPedidoActivity.this, PrincipalActivity.class);
        startActivity(i);
        finish();
    }

    @Override public void onStart()
    {
        super.onStart();
        adapter.startListening();

    }

    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();

    }
}