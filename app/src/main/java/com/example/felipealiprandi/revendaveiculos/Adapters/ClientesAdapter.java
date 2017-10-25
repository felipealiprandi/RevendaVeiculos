package com.example.felipealiprandi.revendaveiculos.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felipealiprandi.revendaveiculos.R;
import com.example.felipealiprandi.revendaveiculos.Tipos.Cliente;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

import java.util.List;

public class ClientesAdapter extends BaseAdapter {
    Context ctx;
    List<Cliente> clientes;

    public ClientesAdapter(Context ctx, List<Cliente> clientes) {
        this.ctx = ctx;
        this.clientes = clientes;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return clientes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cliente cliente = clientes.get(position);
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_cliente,null);

        TextView txtCliente = (TextView) linha.findViewById(R.id.txtCliente);
        TextView txtTpessoa = (TextView) linha.findViewById(R.id.txtTpessoa);

        txtCliente.setText(cliente.getNome());

        String pessoa = (cliente.isPessoaFisica() ? "Pessoa Física" : "") +
                (cliente.isPessoaJuridica() ? "Pessoa juridica" : "");
        txtTpessoa.setText(pessoa);

        return linha;
    }
}
