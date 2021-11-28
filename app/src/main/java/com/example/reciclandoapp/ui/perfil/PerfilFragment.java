package com.example.reciclandoapp.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
   // private PerfilViewModel mViewModel;
    private TextView txtNome, txtEmail;
    private Query pesquisaUsuario;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.perfil_fragment, container, false);
        txtNome = view.findViewById(R.id.txtPerfilNome);
        txtEmail = view.findViewById(R.id.txtPerfilEmail);
        DatabaseReference listaUsuarios = referencia.child("Usuario");
        pesquisaUsuario = listaUsuarios.orderByChild("email").equalTo(usuario.getCurrentUser().getEmail());

        pesquisaUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dt : snapshot.getChildren())
                {
                    Usuario user = dt.getValue(Usuario.class);
                    txtNome.setText(user.getNome());
                    txtEmail.setText(user.getEmail());
                }

//                Usuario user = snapshot.getValue(Usuario.class);
//                Log.i("Usuario", snapshot.getValue().toString());
                //
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
//        // TODO: Use the ViewModel
//    }

}