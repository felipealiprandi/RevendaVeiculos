package com.example.felipealiprandi.revendaveiculos.Tipos;

import java.io.Serializable;

/**
 * Created by Felipe Aliprandi on 23/10/2017.
 */

public class Cliente implements Serializable{

    private String cpfcnpj;
    private boolean pessoaFisica;
    private boolean pessoaJuridica;
    private double Renda;
    private Long id;
    private String nome;
    private String obs;


    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public boolean isPessoaFisica() {
        return pessoaFisica;
    }

    public int PessoaFisica() {
        return pessoaFisica == true? 1 : 0;
    }

    public void setPessoaFisica(boolean pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public boolean isPessoaJuridica() {
        return pessoaJuridica;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getPessoaJuridica() {
        return pessoaJuridica == true? 1 : 0;
    }

    public void setPessoaJuridica(boolean pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public double getRenda() {
        return Renda;
    }

    public void setRenda(double renda) {
        Renda = renda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public Cliente(Long id, String cpfcnpj, String nome, boolean pessoaFisica, boolean pessoaJuridica, double renda, String obs) {
        this.cpfcnpj = cpfcnpj;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
        Renda = renda;
        this.id = id;
        this.nome = nome;
        this.obs = obs;
    }

    public Cliente(String cpfcnpj, boolean pessoaFisica, boolean pessoaJuridica, double renda,  String nome, String obs) {
        this.cpfcnpj = cpfcnpj;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
        Renda = renda;
        this.nome = nome;
        this.obs = obs;
    }
}
