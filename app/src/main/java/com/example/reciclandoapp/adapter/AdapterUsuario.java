package com.example.reciclandoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciclandoapp.R;
import com.example.reciclandoapp.classes.Ideia;
import com.example.reciclandoapp.classes.Usuario;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterUsuario  extends FirebaseRecyclerAdapter<Usuario, AdapterUsuario.UsuarioViewHolder> {
    private Context context;
    /**
     * Initialize a {@link} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterUsuario(@NonNull FirebaseRecyclerOptions<Usuario> options, Context context) {

        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterUsuario.UsuarioViewHolder holder, int position, @NonNull Usuario model) {
        holder.txtEndereco.setText(model.getEndereco());
        holder.txtNome.setText(model.getNome());

    }

    @NonNull
    @Override
    public AdapterUsuario.UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itens = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_usuario, parent, false);

        return new AdapterUsuario.UsuarioViewHolder(itens);
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtEndereco, txtCPF, txtDataNasc, txtEmail;

        public UsuarioViewHolder(View itemView){
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtAdapterNome);
            txtCPF = itemView.findViewById(R.id.txtAdapterCPF);
            txtEmail = itemView.findViewById(R.id.txtAdapterEmail);
            txtEndereco = itemView.findViewById(R.id.txtAdapterEndereco);
            txtDataNasc = itemView.findViewById(R.id.txtAdapterDataNasc);
        }
    }
}
