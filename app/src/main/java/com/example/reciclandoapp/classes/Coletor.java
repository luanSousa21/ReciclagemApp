package com.example.reciclandoapp.classes;

public class Coletor extends Usuario{
    private String cnpj;
    private String veiculo;

    public Coletor() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
}
