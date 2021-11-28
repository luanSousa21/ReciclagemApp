package com.example.reciclandoapp.classes;

public class Ideia {

    private String tituloIdeia, descricaoIdeia, usuarioCriacao, caminhoImagem;

    public Ideia()
    {

    }


    public String getTituloIdeia() {
        return tituloIdeia;
    }

    public void setTituloIdeia(String tituloIdeia) {
        this.tituloIdeia = tituloIdeia;
    }

    public String getDescricaoIdeia() {
        return descricaoIdeia;
    }

    public void setDescricaoIdeia(String descricaoIdeia) {
        this.descricaoIdeia = descricaoIdeia;
    }

    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public void setUsuarioCriacao(String usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}
