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
import com.example.reciclandoapp.classes.PedidoColeta;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterPedido  extends FirebaseRecyclerAdapter<PedidoColeta, AdapterPedido.PedidoViewHolder> {
    private Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterPedido(@NonNull FirebaseRecyclerOptions<PedidoColeta> options,  Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterPedido.PedidoViewHolder holder, int position, @NonNull PedidoColeta model) {
        holder.txtDescricaoPedido .setText(model.getDescricaoPedido());
        holder.txtMateriais.setText(model.getMateriais());
        holder.txtHorario.setText(model.getDataHoraColeta());
        holder.txtEndereco.setText(model.getEnderecoColeta());
    }

    @NonNull
    @Override
    public AdapterPedido.PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itens = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_pedidos, parent, false);

        return new AdapterPedido.PedidoViewHolder(itens);
    }

    public class PedidoViewHolder  extends RecyclerView.ViewHolder{
        TextView txtDescricaoPedido, txtMateriais, txtHorario, txtEndereco;
        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescricaoPedido = itemView.findViewById(R.id.txtDescricaoColeta);
            txtMateriais = itemView.findViewById(R.id.txtMateriais);
            txtHorario =itemView.findViewById(R.id.txtDataColetaPedido);
            txtEndereco = itemView.findViewById(R.id.txtEnderecoColeta);
        }
    }
}
