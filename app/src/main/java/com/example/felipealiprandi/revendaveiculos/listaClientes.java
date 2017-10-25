package com.example.felipealiprandi.revendaveiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.felipealiprandi.revendaveiculos.Adapters.ClientesAdapter;
import com.example.felipealiprandi.revendaveiculos.Adapters.ComprasAdapter;
import com.example.felipealiprandi.revendaveiculos.Adapters.VeiculosAdapter;
import com.example.felipealiprandi.revendaveiculos.Formularios.formularioCliente;
import com.example.felipealiprandi.revendaveiculos.Formularios.formularioVeiculo;
import com.example.felipealiprandi.revendaveiculos.Models.ClienteDao;
import com.example.felipealiprandi.revendaveiculos.Models.CompraDao;
import com.example.felipealiprandi.revendaveiculos.Tipos.Cliente;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class listaClientes extends AppCompatActivity {

    List<Cliente> clientes;
    ClientesAdapter adapter;
    ListView lista;
    private ClienteDao mClienteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        mClienteDao = new ClienteDao(this);

        Button btnCliente = (Button) findViewById(R.id.btnAddClientes);

        btnCliente.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent it = new Intent(listaClientes.this,formularioCliente.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.listaClientes);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) parent.getItemAtPosition(position);

                Intent it = new Intent(listaClientes.this,formularioCliente.class);
                it.putExtra("cliente", cliente);

                startActivity(it);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente) parent.getItemAtPosition(position);

                mClienteDao.excluir(cliente);

                atualizarLista();

                Toast.makeText(listaClientes.this, "Cliente excluido com sucesso",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        clientes = mClienteDao.all();

        adapter = new ClientesAdapter(this,clientes);

        lista.setAdapter(adapter);

    }

    private void atualizarLista(){
        clientes = mClienteDao.all();
        adapter.notifyDataSetChanged();
    }

}
