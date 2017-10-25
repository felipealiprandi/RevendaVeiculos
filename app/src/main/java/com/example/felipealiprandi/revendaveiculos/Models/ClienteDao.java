package com.example.felipealiprandi.revendaveiculos.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.felipealiprandi.revendaveiculos.Tipos.Cliente;
import com.example.felipealiprandi.revendaveiculos.helpers.VeiculosSQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Aliprandi on 24/10/2017.
 */

public class ClienteDao {

    private VeiculosSQLHelper helper;
    public static final String TABELA_CLIENTES = "CLIENTES";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME = "NOME";
    public static final String COLUNA_CPFCNPJ = "CPFCNPJ";
    public static final String COLUNA_OBSERVACAO = "OBSERVACAO";
    public static final String COLUNA_PESSOAFISICA = "PESSOAFISICA";
    public static final String COLUNA_PESSOAJURIDICA = "PESSOAJURIDICA";
    public static final String COLUNA_RENDA= "RENDA";

    public ClienteDao (Context ctx){
        helper = new VeiculosSQLHelper(ctx);
    }

    public long inserir(Cliente cliente)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME, cliente.getNome());
        cv.put(COLUNA_CPFCNPJ, cliente.getCpfcnpj());
        cv.put(COLUNA_OBSERVACAO, cliente.getObs());
        cv.put(COLUNA_PESSOAFISICA, cliente.getPessoaJuridica());
        cv.put(COLUNA_PESSOAJURIDICA, cliente.getPessoaJuridica());
        cv.put(COLUNA_RENDA, cliente.getRenda());

        long id = db.insert(TABELA_CLIENTES,null,cv);

        db.close();
        return id;

    }

    public int atualizar(Cliente cliente)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME, cliente.getNome());
        cv.put(COLUNA_CPFCNPJ, cliente.getCpfcnpj());
        cv.put(COLUNA_OBSERVACAO, cliente.getObs());
        cv.put(COLUNA_PESSOAFISICA, cliente.getPessoaJuridica());
        cv.put(COLUNA_PESSOAJURIDICA, cliente.getPessoaJuridica());
        cv.put(COLUNA_RENDA, cliente.getRenda());

        int linhasAfetadas = db.update(
                TABELA_CLIENTES,
                cv,
                COLUNA_ID + " = ?",
                new String[]{String.valueOf(cliente.getId())}
        );

        db.close();

        return linhasAfetadas;

    }

    public long salvar (Cliente cliente){
        if (cliente.getId() != null)
        {
            return atualizar(cliente);
        }
        return inserir(cliente);
    }

    public int excluir(Cliente cliente)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasExcluidas = db.delete(
                TABELA_CLIENTES,
                COLUNA_ID + " = ?",
                new String[] {String.valueOf(cliente.getId())}
        );

        db.close();
        return linhasExcluidas;
    }

    public List<Cliente> all(){
        return  buscarCliente(null);
    }


    public List<Cliente> buscarCliente(String filtro){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + TABELA_CLIENTES;

        String[] argumentos = null;

        if (filtro != null){
            sql += "WHERE " + COLUNA_NOME + "LIKE ?";
            argumentos = new String[]{filtro};
        }

        sql += " ORDER BY " + COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql,argumentos);

        List<Cliente> clientes = new ArrayList<>();

        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndex(COLUNA_ID));
            String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
            String cpfcnpj = cursor.getString(cursor.getColumnIndex(COLUNA_CPFCNPJ));
            boolean juridica = cursor.getInt(cursor.getColumnIndex(COLUNA_PESSOAJURIDICA)) == 1;
            boolean fisica = cursor.getInt(cursor.getColumnIndex(COLUNA_PESSOAFISICA)) == 1;
            String obs = cursor.getString(cursor.getColumnIndex(COLUNA_OBSERVACAO));
            double renda = cursor.getDouble(cursor.getColumnIndex(COLUNA_RENDA));

            Cliente cliente = new Cliente(id,cpfcnpj,nome, fisica, juridica, renda, obs);
            clientes.add(cliente);
        }

        cursor.close();
        db.close();

        return clientes;
    }

}
