package com.example.reciclandoapp.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBindings;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reciclandoapp.LoginWhitEmailActivity;
import com.example.reciclandoapp.PrincipalActivity;
import com.example.reciclandoapp.R;
import com.example.reciclandoapp.classes.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PerfilFragment extends Fragment {
    private Button btnSairApp;
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private TextView txtNome, txtEmail;
    private Query pesquisaUsuario;
    SharedPreferences preferences;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.perfil_fragment, container, false);
        txtNome = view.findViewById(R.id.txtPerfilNome);
        txtEmail = view.findViewById(R.id.txtPerfilEmail);
        preferences = getActivity().getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        txtNome.setText(preferences.getString("nomeUsuario", " "));
        txtEmail.setText(preferences.getString("email", " "));
        btnSairApp = view.findViewById(R.id.btnSairApp);
        btnSairApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.signOut();
                Intent i = new Intent(getActivity(), LoginWhitEmailActivity.class);
                startActivity(i);
            }
        });


        return  view;
    }


}