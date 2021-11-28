package com.example.reciclandoapp.ui.inicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reciclandoapp.R;
import com.example.reciclandoapp.adapter.Adapter;
import com.example.reciclandoapp.classes.Ideia;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {
    RecyclerView recyclerView;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    Adapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Query query = referencia.child("Ideia").orderByChild("tituloIdeia");

        FirebaseRecyclerOptions<Ideia> options = new FirebaseRecyclerOptions.Builder<Ideia>().setQuery(query, Ideia.class).build();


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        //ArrayList<Ideia> lista;
        //lista = listarIdeias();

       // Log .i("Valor teste", lista.get(0).getTituloIdeia());

        //configurar adapter
        adapter = new Adapter(options, getActivity());
        //configurar recyclerview

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
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
/*
    private ArrayList<Ideia> listarIdeias()
    {
        ArrayList<Ideia> array = new ArrayList<Ideia>();
        DatabaseReference listaIdeias = referencia.child("Ideia");
        Query pesquisa;
        pesquisa = listaIdeias.orderByChild("tituloIdeia");

        pesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;

                for (DataSnapshot dt : snapshot.getChildren())
                {
                    Ideia ideia = new Ideia();

                    ideia = dt.getValue(Ideia.class);
                    Log.i("data snapshot", dt.getValue().toString());
                    Log.i("Objeto Ideia", ideia.getTituloIdeia());
                    array.add(ideia);

                    Log.i("ArrayList",""+array.isEmpty());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return array;
    }
    */
}