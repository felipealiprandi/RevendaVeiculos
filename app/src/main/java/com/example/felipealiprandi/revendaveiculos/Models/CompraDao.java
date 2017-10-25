package com.example.felipealiprandi.revendaveiculos.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.felipealiprandi.revendaveiculos.Tipos.Compra;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;
import com.example.felipealiprandi.revendaveiculos.helpers.VeiculosSQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Aliprandi on 24/10/2017.
 */

public class CompraDao {
    private VeiculosSQLHelper helper;
    public static final String TABELA_COMPRAS = "COMPRAS";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME = "NOME";
    public static final String COLUNA_FORNECEDOR = "FORNECEDOR";
    public static final String COLUNA_TIPO = "TIPO";
    public static final String COLUNA_QUANTIDADE = "QUANTIDADE";
    public static final String COLUNA_PRECO= "PRECO";

    public CompraDao (Context ctx){
        helper = new VeiculosSQLHelper(ctx);
    }

    public long inserir(Compra compra)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME, compra.getNome());
        cv.put(COLUNA_FORNECEDOR, compra.getFornecedor());
        cv.put(COLUNA_TIPO, compra.getTipo());
        cv.put(COLUNA_QUANTIDADE, compra.getQuantidade());
        cv.put(COLUNA_PRECO, compra.getPreco());

        long id = db.insert(TABELA_COMPRAS,null,cv);

        db.close();
        return id;

    }

    public int atualizar(Compra compra)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME, compra.getNome());
        cv.put(COLUNA_FORNECEDOR, compra.getFornecedor());
        cv.put(COLUNA_TIPO, compra.getTipo());
        cv.put(COLUNA_QUANTIDADE, compra.getQuantidade());
        cv.put(COLUNA_PRECO, compra.getPreco());

        int linhasAfetadas = db.update(
                TABELA_COMPRAS,
                cv,
                COLUNA_ID + " = ?",
                new String[]{String.valueOf(compra.getId())}
        );

        db.close();

        return linhasAfetadas;

    }

    public long salvar (Compra compra){
        if (compra.getId() != null)
        {
            return atualizar(compra);
        }
        return inserir(compra);
    }

    public int excluir(Compra compra)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasExcluidas = db.delete(
                TABELA_COMPRAS,
                COLUNA_ID + " = ?",
                new String[] {String.valueOf(compra.getId())}
        );

        db.close();
        return linhasExcluidas;
    }

    public List<Compra> all(){
        return  buscarCompra(null);
    }


    public List<Compra> buscarCompra(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + TABELA_COMPRAS;

        String[] argumentos = null;

        if (filtro != null){
            sql += "WHERE " + COLUNA_NOME + "LIKE ?";
            argumentos = new String[]{filtro};
        }

        sql += " ORDER BY " + COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql,argumentos);

        List<Compra> compras = new ArrayList<>();

        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex(COLUNA_ID));
            String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
            String fornecedor = cursor.getString(cursor.getColumnIndex(COLUNA_FORNECEDOR));
            String quantidade = cursor.getString(cursor.getColumnIndex(COLUNA_QUANTIDADE));
            int tipo = cursor.getInt(cursor.getColumnIndex(COLUNA_TIPO));
            double preco = cursor.getDouble(cursor.getColumnIndex(COLUNA_PRECO));

            Compra compra = new Compra(id,nome, fornecedor, tipo, quantidade, preco);
            compras.add(compra);
        }

        cursor.close();
        db.close();

        return compras;
    }


}
