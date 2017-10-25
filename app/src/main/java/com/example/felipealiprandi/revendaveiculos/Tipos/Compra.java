package com.example.felipealiprandi.revendaveiculos.Tipos;

import java.io.Serializable;

/**
 * Created by Felipe Aliprandi on 23/10/2017.
 */

public class Compra implements Serializable{

    private Long id;
    private String nome;
    private String fornecedor;
    private int tipo;
    private String quantidade;
    private double preco;

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

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Compra(String nome, String fornecedor, int tipo, String quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Compra(Long id, String nome, String fornecedor, int tipo, String quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
    }

}
