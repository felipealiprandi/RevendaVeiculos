package com.example.felipealiprandi.revendaveiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.felipealiprandi.revendaveiculos.Adapters.ComprasAdapter;
import com.example.felipealiprandi.revendaveiculos.Adapters.VeiculosAdapter;
import com.example.felipealiprandi.revendaveiculos.Formularios.formularioCliente;
import com.example.felipealiprandi.revendaveiculos.Formularios.formularioCompra;
import com.example.felipealiprandi.revendaveiculos.Models.CompraDao;
import com.example.felipealiprandi.revendaveiculos.Models.VeiculoDao;
import com.example.felipealiprandi.revendaveiculos.Tipos.Cliente;
import com.example.felipealiprandi.revendaveiculos.Tipos.Compra;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class listaCompras extends AppCompatActivity {

    List<Compra> compras;
    ComprasAdapter adapter;
    ListView lista;
    private CompraDao mCompraDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);

        mCompraDao = new CompraDao(this);

        Button btnCompra = (Button) findViewById(R.id.btnAddCompras);

        btnCompra.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent it = new Intent(listaCompras.this,formularioCompra.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.listaCompras);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Compra compra = (Compra) parent.getItemAtPosition(position);

                Intent it = new Intent(listaCompras.this,formularioCompra.class);
                it.putExtra("compra", compra);

                startActivity(it);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Compra compra = (Compra) parent.getItemAtPosition(position);

                mCompraDao.excluir(compra);

                atualizarLista();

                Toast.makeText(listaCompras.this, "Compra excluida com sucesso",Toast.LENGTH_SHORT).show();

                return false;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        compras = mCompraDao.all();

        adapter = new ComprasAdapter(this,compras);

        lista.setAdapter(adapter);

    }
    private void atualizarLista(){
        compras = mCompraDao.all();
        adapter.notifyDataSetChanged();
    }

}
