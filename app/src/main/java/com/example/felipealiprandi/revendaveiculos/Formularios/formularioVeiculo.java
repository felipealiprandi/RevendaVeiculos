package com.example.felipealiprandi.revendaveiculos.Formularios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipealiprandi.revendaveiculos.Models.VeiculoDao;
import com.example.felipealiprandi.revendaveiculos.R;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

public class formularioVeiculo extends AppCompatActivity {

    private Veiculo mVeiculo;
    private VeiculoDao mVeiculoDao;
    private TextView mTxtId;
    private EditText mEdtModelo;
    private EditText mEdtAno;
    private Spinner mSpnFabricante;
    private CheckBox mChkGasolina;
    private CheckBox mChkEtanol;
    private EditText mEdtPreco;
    private Button mBtnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_veiculo);

        mVeiculoDao = new VeiculoDao(this);

        mTxtId = (TextView) findViewById(R.id.txtVeiculoID);
        mEdtModelo = (EditText) findViewById(R.id.edtVeiculoModelo);
        mEdtAno = (EditText) findViewById(R.id.edtVeiculoAno);
        mSpnFabricante = (Spinner) findViewById(R.id.spnVeiculoFabricante);
        mChkGasolina = (CheckBox) findViewById(R.id.chkVeiculoGasolina);
        mChkEtanol= (CheckBox) findViewById(R.id.chkVeiculoEtanol);
        mEdtPreco = (EditText) findViewById(R.id.edtVeiculoPreco);
        mBtnSalvar = (Button) findViewById(R.id.btnVeiculoSalvar);

        mBtnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        String[] fabricantes = new String[]{
                "VW", "GM", "Fiat", "Ford"
        };

        ArrayAdapter<String
                > spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,fabricantes
        );
        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        mSpnFabricante.setAdapter(spinnerAdapter);

        Intent it = getIntent();

       mVeiculo = (Veiculo) it.getSerializableExtra("veiculo");

        if(mVeiculo != null){
            mTxtId.setText(mVeiculo.getId().toString());
            mEdtModelo.setText(mVeiculo.getModelo());
            mEdtAno.setText(String.valueOf(mVeiculo.getAno()));
            mSpnFabricante.setSelection(mVeiculo.getFabricante());
            mChkGasolina.setChecked(mVeiculo.isGasolina());
            mChkEtanol.setChecked(mVeiculo.isEtanol());
            mEdtPreco.setText(String.valueOf(mVeiculo.getPreco()));

        }

    }

    private void salvar(){
        String modelo = mEdtModelo.getText().toString();
        int ano = Integer.valueOf(mEdtAno.getText().toString());
        int fabricante = mSpnFabricante.getSelectedItemPosition();
        boolean gasolina = mChkGasolina.isChecked();
        boolean etanol = mChkEtanol.isChecked();
        double preco = Double.valueOf(mEdtPreco.getText().toString());

        if(mVeiculo == null){
            mVeiculo = new Veiculo(

                    modelo,
                    ano,
                    fabricante,
                    gasolina,
                    etanol,
                    preco
            );
        }else{
            mVeiculo.setModelo(modelo);
            mVeiculo.setAno(ano);
            mVeiculo.setFabricante(fabricante);
            mVeiculo.setGasolina(gasolina);
            mVeiculo.setEtanol(etanol);
            mVeiculo.setPreco(preco);

        }

        long id = mVeiculoDao.salvar(mVeiculo);

        if(id>0){
            Toast.makeText(this,"Veiculo salvo com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar o Veiculo!", Toast.LENGTH_SHORT).show();
        }

        finish();

    }


}
