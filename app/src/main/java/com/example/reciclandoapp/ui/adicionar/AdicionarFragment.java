package com.example.reciclandoapp.ui.adicionar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.reciclandoapp.CadastroIdeiaActivity;
import com.example.reciclandoapp.CadastroPedidoActivity;
import com.example.reciclandoapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdicionarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdicionarFragment extends Fragment {
    Button btnCadastrarIdeia, btnCadastrarPedido;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adicionar, container, false);
        btnCadastrarIdeia = view.findViewById(R.id.btnCadIdeia);
        btnCadastrarPedido = view.findViewById(R.id.btnCadastrarPedido);

        btnCadastrarIdeia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CadastroIdeiaActivity.class);
                startActivity(i);
            }
        });

        btnCadastrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CadastroPedidoActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}