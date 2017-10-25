package com.example.felipealiprandi.revendaveiculos.Formularios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipealiprandi.revendaveiculos.Models.ClienteDao;
import com.example.felipealiprandi.revendaveiculos.Models.VeiculoDao;
import com.example.felipealiprandi.revendaveiculos.R;
import com.example.felipealiprandi.revendaveiculos.Tipos.Cliente;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

public class formularioCliente extends AppCompatActivity {

    private Cliente mCliente;
    private ClienteDao mClienteDao;
    private TextView mTxtId;
    private EditText mEdtCPFCNPJ;
    private EditText mEdtNome;
    private CheckBox mChkPessoaFisica;
    private CheckBox mChkPessoaJuridica;
    private EditText mEdtRenda;
    private EditText mEdtObs;
    private Button mBtnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        mClienteDao = new ClienteDao(this);

        mTxtId = (TextView) findViewById(R.id.txtClienteID);
        mEdtCPFCNPJ = (EditText) findViewById(R.id.edtClienteCPF);
        mEdtNome = (EditText) findViewById(R.id.edtClienteNome);
        mChkPessoaFisica = (CheckBox) findViewById(R.id.chkClientePF);
        mChkPessoaJuridica= (CheckBox) findViewById(R.id.chkClientePJ);
        mEdtRenda = (EditText) findViewById(R.id.edtClienteRenda);
        mEdtObs = (EditText) findViewById(R.id.edtClienteObs);
        mBtnSalvar = (Button) findViewById(R.id.btnClienteSalvar);

        mBtnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        Intent it = getIntent();

        mCliente = (Cliente) it.getSerializableExtra("cliente");

        if(mCliente != null){
            mTxtId.setText(mCliente.getId().toString());
            mEdtCPFCNPJ.setText(mCliente.getCpfcnpj());
            mEdtNome.setText(mCliente.getNome());
            mChkPessoaFisica.setChecked(mCliente.isPessoaFisica());
            mChkPessoaJuridica.setChecked(mCliente.isPessoaJuridica());
            mEdtRenda.setText(String.valueOf(mCliente.getRenda()));
            mEdtObs.setText(mCliente.getObs());
        }

    }

    private void salvar(){
        String cpfcnpj = mEdtCPFCNPJ.getText().toString();
        String nome = mEdtNome.getText().toString();
        String obs = mEdtObs.getText().toString();
        boolean fisica = mChkPessoaFisica.isChecked();
        boolean juridica = mChkPessoaJuridica.isChecked();
        double renda = Double.valueOf(mEdtRenda.getText().toString());

        if(mCliente == null) {
            mCliente = new Cliente(
                    cpfcnpj,
                    fisica,
                    juridica,
                    renda,
                    nome,
                    obs
            );
        }else{
            mCliente.setNome(nome);
            mCliente.setCpfcnpj(cpfcnpj);
            mCliente.setPessoaFisica(fisica);
            mCliente.setPessoaJuridica(juridica);
            mCliente.setRenda(renda);
            mCliente.setObs(obs);
        }

        long id = mClienteDao.salvar(mCliente);

        if(id>0){
            Toast.makeText(this,"Cliente salvo com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar o Cliente!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }



}
