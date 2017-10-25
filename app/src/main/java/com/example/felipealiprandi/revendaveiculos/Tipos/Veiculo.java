package com.example.felipealiprandi.revendaveiculos.Tipos;

import java.io.Serializable;

/**
 * Created by Felipe Aliprandi on 23/10/2017.
 */

public class Veiculo implements Serializable{

    private int ano;
    private int fabricante;  //0=vw; 1=gm 2=fiat 3=ford
    private boolean gasolina;
    private boolean etanol;
    private double preco;
    private Long id;
    private String modelo;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getFabricante() {
        return fabricante;
    }

    public void setFabricante(int fabricante) {
        this.fabricante = fabricante;
    }

    public boolean isGasolina() {
        return gasolina;
    }

    public int getGasolina() {
        return gasolina == true? 1 : 0;
    }

    public void setGasolina(boolean gasolina) {
        this.gasolina = gasolina;
    }

    public boolean isEtanol() {
        return etanol;
    }

    public int getEtanol() {
        return etanol == true? 1 : 0;
    }

    public void setEtanol(boolean etanol) {
        this.etanol = etanol;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Veiculo(Long id, String modelo, int ano, int fabricante, boolean gasolina, boolean etanol, double preco) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.fabricante = fabricante;
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.preco = preco;

    }

    public Veiculo(String modelo, int ano, int fabricante, boolean gasolina, boolean etanol, double preco) {
        this.modelo = modelo;
        this.ano = ano;
        this.fabricante = fabricante;
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.preco = preco;
    }





}
