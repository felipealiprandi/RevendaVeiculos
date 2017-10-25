package com.example.felipealiprandi.revendaveiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.felipealiprandi.revendaveiculos.Adapters.VeiculosAdapter;
import com.example.felipealiprandi.revendaveiculos.Formularios.formularioCliente;
import com.example.felipealiprandi.revendaveiculos.Formularios.formularioVeiculo;
import com.example.felipealiprandi.revendaveiculos.Models.VeiculoDao;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class listaVeiculos extends AppCompatActivity {

    List<Veiculo> veiculos;
    VeiculosAdapter adapter;
    ListView lista;
    private VeiculoDao mVeiculoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculos);

        mVeiculoDao = new VeiculoDao(this);

        Button btnVeiculo = (Button) findViewById(R.id.btnAddVeiculo);

        btnVeiculo.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent it = new Intent(listaVeiculos.this,formularioVeiculo.class);
                startActivity(it);
            }
        });



        lista = (ListView) findViewById(R.id.listaVeiculos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Veiculo veiculo = (Veiculo) parent.getItemAtPosition(position);

        Intent it = new Intent(listaVeiculos.this,formularioVeiculo.class);
        it.putExtra("veiculo",veiculo);

        startActivity(it);

    }
});

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Veiculo veiculo = (Veiculo) parent.getItemAtPosition(position);

                mVeiculoDao.excluir(veiculo);

                atualizarLista();

                Toast.makeText(listaVeiculos.this, "Veiculo excluido com sucesso",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        veiculos = mVeiculoDao.all();

        adapter = new VeiculosAdapter(this,veiculos);

        lista.setAdapter(adapter);

    }

    private void atualizarLista(){
        veiculos = mVeiculoDao.all();
        adapter.notifyDataSetChanged();
    }



}
