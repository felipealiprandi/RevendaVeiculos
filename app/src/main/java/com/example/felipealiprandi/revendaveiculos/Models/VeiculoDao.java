package com.example.felipealiprandi.revendaveiculos.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;
import com.example.felipealiprandi.revendaveiculos.helpers.VeiculosSQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Aliprandi on 24/10/2017.
 */

public class VeiculoDao {
    private VeiculosSQLHelper helper;
    public static final String TABELA_VEICULOS = "VEICULOS";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_MODELO = "MODELO";
    public static final String COLUNA_ANO = "ANO";
    public static final String COLUNA_FABRICANTE = "FABRICANTE";
    public static final String COLUNA_GASOLINA = "GASOLINA";
    public static final String COLUNA_ETANOL= "ETANOL";
    public static final String COLUNA_PRECO = "PRECO";

    public VeiculoDao (Context ctx){
        helper = new VeiculosSQLHelper(ctx);
    }

    public long inserir(Veiculo veiculo)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_MODELO, veiculo.getModelo());
        cv.put(COLUNA_ANO, veiculo.getAno());
        cv.put(COLUNA_FABRICANTE, veiculo.getFabricante());
        cv.put(COLUNA_GASOLINA, veiculo.getGasolina());
        cv.put(COLUNA_ETANOL, veiculo.getEtanol());
        cv.put(COLUNA_PRECO, veiculo.getPreco());

        long id = db.insert(TABELA_VEICULOS,null,cv);

        db.close();
        return id;

    }

    public int atualizar(Veiculo veiculo)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_MODELO, veiculo.getModelo());
        cv.put(COLUNA_ANO, veiculo.getAno());
        cv.put(COLUNA_FABRICANTE, veiculo.getFabricante());
        cv.put(COLUNA_GASOLINA, veiculo.getGasolina());
        cv.put(COLUNA_ETANOL, veiculo.getEtanol());
        cv.put(COLUNA_PRECO, veiculo.getPreco());

        int linhasAfetadas = db.update(
                TABELA_VEICULOS,
                cv,
                COLUNA_ID + " = ?",
                new String[]{String.valueOf(veiculo.getId())}
        );

        db.close();

        return linhasAfetadas;

    }

    public long salvar (Veiculo veiculo){
        if (veiculo.getId() != null)
        {
            return atualizar(veiculo);
        }
        return inserir(veiculo);
    }

    public int excluir(Veiculo veiculo)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasExcluidas = db.delete(
                TABELA_VEICULOS,
                COLUNA_ID + " = ?",
                new String[] {String.valueOf(veiculo.getId())}
        );

        db.close();
        return linhasExcluidas;
    }

    public List<Veiculo> all(){
        return  buscarCarro(null);
    }


    public List<Veiculo> buscarCarro(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + TABELA_VEICULOS;

        String[] argumentos = null;

        if (filtro != null){
            sql += "WHERE " + COLUNA_MODELO + "LIKE ?";
            argumentos = new String[]{filtro};
        }

        sql += " ORDER BY " + COLUNA_MODELO;

        Cursor cursor = db.rawQuery(sql,argumentos);

        List<Veiculo> veiculos = new ArrayList<>();

        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex(COLUNA_ID));
            String modelo = cursor.getString(cursor.getColumnIndex(COLUNA_MODELO));
            int ano = cursor.getInt(cursor.getColumnIndex(COLUNA_ANO));
            int fabricante = cursor.getInt(cursor.getColumnIndex(COLUNA_FABRICANTE));
            boolean gasolina = cursor.getInt(cursor.getColumnIndex(COLUNA_GASOLINA)) == 1;
            boolean etanol = cursor.getInt(cursor.getColumnIndex(COLUNA_ETANOL)) == 1;
            double preco = cursor.getDouble(cursor.getColumnIndex(COLUNA_PRECO));

            Veiculo veiculo = new Veiculo(id,modelo, ano, fabricante, gasolina ,etanol,preco);
            veiculos.add(veiculo);
        }

        cursor.close();
        db.close();

        return veiculos;
    }


}
