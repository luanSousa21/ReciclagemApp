package com.example.reciclandoapp.ui.inicio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.reciclandoapp.CadastroIdeiaActivity;
import com.example.reciclandoapp.DetalhePedidoActivity;
import com.example.reciclandoapp.R;
import com.example.reciclandoapp.RecyclerItemClickListener;
import com.example.reciclandoapp.adapter.Adapter;
import com.example.reciclandoapp.adapter.AdapterPedido;
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

public class InicioFragment extends Fragment {
    RecyclerView recyclerView, rvUser;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    Adapter adapter;
    AdapterPedido adapterPedido;
    SharedPreferences preferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        preferences = getActivity().getSharedPreferences("user_preferences", Context.MODE_PRIVATE);

        if (preferences.getInt("tipoUsuario", 1) == 1){
            Query query = referencia.child("Ideia").orderByChild("tituloIdeia");
            FirebaseRecyclerOptions<Ideia> options = new FirebaseRecyclerOptions.Builder<Ideia>().setQuery(query, Ideia.class).build();
            adapter = new Adapter(options, getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            // recyclerView.setAdapter();
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

        }else{
            Query query2 = referencia.child("PedidoColeta").orderByChild("nomeColetor").equalTo(" ");
            FirebaseRecyclerOptions<PedidoColeta> opcao = new FirebaseRecyclerOptions.Builder<PedidoColeta>().setQuery(query2, PedidoColeta.class).build();
            adapterPedido = new AdapterPedido(opcao, getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapterPedido);

            //evento de clique
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getContext(),
                            recyclerView,
                            new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Log.i("chave", opcao.getSnapshots().getSnapshot(position).getKey());
                                    Intent i = new Intent(getActivity(), DetalhePedidoActivity.class);
                                    i.putExtra("descricaoPedido", opcao.getSnapshots().get(position).getDescricaoPedido());
                                    i.putExtra("usuarioSolicitante", opcao.getSnapshots().get(position).getUsuarioSolicitante());
                                    i.putExtra("enderecoColeta", opcao.getSnapshots().get(position).getEnderecoColeta());
                                    i.putExtra("materiais", opcao.getSnapshots().get(position).getMateriais());
                                    i.putExtra("dataHoraColeta", opcao.getSnapshots().get(position).getDataHoraColeta());
                                    i.putExtra("telefone", opcao.getSnapshots().get(position).getTelefoneContato());
                                    i.putExtra("chave",  opcao.getSnapshots().getSnapshot(position).getKey());
                                    startActivity(i);
                                }

                                @Override
                                public void onLongItemClick(View view, int position) {

                                }

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                }
                            }));
        }
        return view;
    }

    @Override public void onStart()
    {
        super.onStart();
        if(preferences.getInt("tipoUsuario", 1) == 1) {
            adapter.startListening();
        }else {
            adapterPedido.startListening();
        }
    }

    @Override public void onStop()
    {
        super.onStop();
        if(preferences.getInt("tipoUsuario", 1) == 1)
        {
            adapter.stopListening();
        }else
        {
            adapterPedido.stopListening();
        }
    }
}