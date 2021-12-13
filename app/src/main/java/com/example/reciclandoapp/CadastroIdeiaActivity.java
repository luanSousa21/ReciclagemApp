package com.example.reciclandoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reciclandoapp.classes.Ideia;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class CadastroIdeiaActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuarioAuth = FirebaseAuth.getInstance();

    Button btnUploadImg, btnCadIdeia;
    ImageView imagem;
    EditText tituloideia, descricaoIdeia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ideia);
        btnUploadImg = findViewById(R.id.btnAddImagem);
        btnCadIdeia = findViewById(R.id.btnCadastrarIdeia);
        imagem = findViewById(R.id.imgUpload);
        tituloideia = findViewById(R.id.txtTituloIdeia);
        descricaoIdeia = findViewById(R.id.txtDescricaoIdeia);

        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), 123);
            }
        });
        btnCadIdeia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarIdeia();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 123) {
                Uri imagemSelecionada = data.getData();
                try {
                    ParcelFileDescriptor pfd = this.getContentResolver().openFileDescriptor(imagemSelecionada, "r");
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());
                    imagem.setImageBitmap(bitmap);
                    imagem.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void cadastrarIdeia()
    {
        imagem.setDrawingCacheEnabled(true);
        imagem.buildDrawingCache();
        //recupera bitmap da imagem
        Bitmap bitmap = imagem.getDrawingCache();
        //comprimo o bitmap para png/jpeg
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);
        // converte o baos para pixel brutos em uma matrix de bytes
        //(Daods da imagem)
        byte[] dadosImagem = baos.toByteArray();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = storageReference.child("Imagens");
        StorageReference imagemref = imagens.child(tituloideia.getText().toString()+".jpeg");

        UploadTask uploadTask = imagemref.putBytes(dadosImagem);
        uploadTask.addOnFailureListener(CadastroIdeiaActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastroIdeiaActivity.this, "Deu ruim"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(CadastroIdeiaActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagemref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri url = task.getResult();
                    }
                });
                Ideia ideia = new Ideia();

                ideia.setTituloIdeia(tituloideia.getText().toString());
                ideia.setDescricaoIdeia(descricaoIdeia.getText().toString());
                ideia.setUsuarioCriacao(usuarioAuth.getCurrentUser().getDisplayName());
                ideia.setCaminhoImagem(tituloideia.getText().toString()+".jpeg");

                DatabaseReference usuario = referencia.child("Ideia");
                usuario.push().setValue(ideia);
                //Toast.makeText(CadastroIdeiaActivity.this, "Deu Bom", Toast.LENGTH_LONG).show();

                Intent i = new Intent(CadastroIdeiaActivity.this, PrincipalActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}