package com.example.felipealiprandi.revendaveiculos.Formularios;

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

import com.example.felipealiprandi.revendaveiculos.Models.CompraDao;
import com.example.felipealiprandi.revendaveiculos.Models.VeiculoDao;
import com.example.felipealiprandi.revendaveiculos.R;
import com.example.felipealiprandi.revendaveiculos.Tipos.Compra;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

public class formularioCompra extends AppCompatActivity {

    private CompraDao mCompraDao;
    private TextView mTxtId;
    private EditText mEdtNome;
    private EditText mEdtFornecedor;
    private EditText mQuantidade;
    private Spinner mSpnTipo;
    private EditText mEdtPreco;
    private Button mBtnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_compra);

        mCompraDao = new CompraDao(this);

        mTxtId = (TextView) findViewById(R.id.txtCompraID);
        mEdtNome = (EditText) findViewById(R.id.edtCompraNome);
        mEdtFornecedor = (EditText) findViewById(R.id.edtCompraFornecedor);
        mQuantidade = (EditText) findViewById(R.id.edtCompraQuantidade);
        mSpnTipo = (Spinner) findViewById(R.id.spnCompraTipo);
        mEdtPreco= (EditText) findViewById(R.id.edtCompraPreco);
        mBtnSalvar = (Button) findViewById(R.id.btnCompraSalvar);

        mBtnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        String[] tipos = new String[]{
                "Peças", "Acessorios", "Combustiveis", "Ferramentas"
        };

        ArrayAdapter<String
                > spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,tipos
        );
        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        mSpnTipo.setAdapter(spinnerAdapter);

    }

    private void salvar(){
        String nome = mEdtNome.getText().toString();
        String quantidade = mQuantidade.getText().toString();
        int tipo = mSpnTipo.getSelectedItemPosition();
        String fornecedor = mEdtFornecedor.getText().toString();
        double preco = Double.valueOf(mEdtPreco.getText().toString());

        Compra compra = new Compra(
                nome,
                fornecedor,
                tipo,
                quantidade,
                preco
        );

        long id = mCompraDao.inserir(compra);

        if(id>0){
            Toast.makeText(this,"Compra salva com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar a compra!", Toast.LENGTH_SHORT).show();
        }

        finish();

    }


}
