package com.example.reciclandoapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reciclandoapp.R;
import com.example.reciclandoapp.classes.Ideia;
import com.example.reciclandoapp.glide.GlideApp;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;

public class Adapter extends FirebaseRecyclerAdapter<Ideia, Adapter.IdeiaViewHolder> {
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private Context context;

    public Adapter(@NonNull FirebaseRecyclerOptions<Ideia> options, Context context)
    {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull IdeiaViewHolder holder, int position, @NonNull Ideia model) {
        holder.titulo.setText(model.getTituloIdeia());

        GlideApp.with(context)
                .load(storageReference.child("Imagens/"+model.getTituloIdeia()+".jpeg"))
                .into(holder.imgIdeia);
    }

    @NonNull
    @Override
    public IdeiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itens = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_inicio, parent, false);

        return new Adapter.IdeiaViewHolder(itens);
    }

    class IdeiaViewHolder extends RecyclerView.ViewHolder
    {
        TextView titulo;
        ImageView imgIdeia;
        public IdeiaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txtTitulo);
            imgIdeia = itemView.findViewById(R.id.imgIdeia);
        }
    }


}
