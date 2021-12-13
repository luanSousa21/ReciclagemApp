package com.example.reciclandoapp.classes;

public class PedidoColeta {
    String usuarioSolicitante;
    String descricaoPedido;
    String materiais;
    String dataHoraColeta;
    String enderecoColeta;
    String nomeColetor;
    String telefoneContato;

    public String getNomeColetor() {
        return nomeColetor;
    }

    public void setNomeColetor(String nomeColetor) {
        this.nomeColetor = nomeColetor;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public PedidoColeta() {
    }

    public String getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(String usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }

    public void setDescricaoPedido(String descricaoPedido) {
        this.descricaoPedido = descricaoPedido;
    }

    public String getMateriais() {
        return materiais;
    }

    public void setMateriais(String materiais) {
        this.materiais = materiais;
    }

    public String getDataHoraColeta() {
        return dataHoraColeta;
    }

    public void setDataHoraColeta(String dataHoraColeta) {
        this.dataHoraColeta = dataHoraColeta;
    }

    public String getEnderecoColeta() {
        return enderecoColeta;
    }

    public void setEnderecoColeta(String enderecoColeta) {
        this.enderecoColeta = enderecoColeta;
    }
}
