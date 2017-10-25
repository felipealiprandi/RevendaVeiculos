package com.example.felipealiprandi.revendaveiculos.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Felipe Aliprandi on 23/10/2017.
 */

public class VeiculosSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbVeiculo";
    private static final int VERSAO_BANCO = 4;
    public static final String TABELA_CLIENTES = "CLIENTES";
    public static final String TABELA_COMPRAS = "COMPRAS";
    public static final String TABELA_VEICULOS = "VEICULOS";

    public VeiculosSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_CLIENTES + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " CPFCNPJ TEXT NOT NULL, " +
                " NOME TEXT NOT NULL, " +
                " OBSERVACAO TEXT NOT NULL, " +
                " PESSOAFISICA INTEGER, " +
                " PESSOAJURIDICA INTEGER, " +
                " RENDA REAL " +
                ") ");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_COMPRAS + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NOME TEXT NOT NULL, " +
                " FORNECEDOR TEXT NOT NULL, " +
                " TIPO INTEGER NOT NULL, " +
                " QUANTIDADE TEXT NOT NULL, " +
                " PRECO REAL " +
                ") ");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_VEICULOS + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " MODELO TEXT NOT NULL, " +
                " ANO INTEGER NOT NULL, " +
                " FABRICANTE INTEGER NOT NULL, " +
                " GASOLINA INTEGER, " +
                " ETANOL INTEGER, " +
                " PRECO REAL " +
                ") ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE " + TABELA_CLIENTES + " (" +
                " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " CPFCNPJ TEXT NOT NULL, " +
                " NOME TEXT NOT NULL, " +
                " OBSERVACAO TEXT NOT NULL, " +
                " PESSOAFISICA INTEGER, " +
                " PESSOAJURIDICA INTEGER, " +
                " RENDA REAL " +
                ") ");
    }
}
