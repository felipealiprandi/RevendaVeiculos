package com.example.felipealiprandi.revendaveiculos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.felipealiprandi.revendaveiculos.R;
import com.example.felipealiprandi.revendaveiculos.Tipos.Compra;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

import java.util.List;

/**
 * Created by Felipe Aliprandi on 24/10/2017.
 */

public class ComprasAdapter extends BaseAdapter {
    Context ctx;
    List<Compra> compras;

    @Override
    public int getCount() {
        return compras.size();
    }

    public ComprasAdapter(Context ctx, List<Compra> compras) {
        this.ctx = ctx;
        this.compras = compras;
    }

    @Override
    public Object getItem(int position) {
        return compras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return compras.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Compra compra = compras.get(position);
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_compra,null);

        TextView txtCNome = (TextView) linha.findViewById(R.id.txtCNome);
        TextView txtCFornecedor = (TextView) linha.findViewById(R.id.txtCFornecedor);

        txtCNome.setText(compra.getNome());
        txtCFornecedor.setText(compra.getFornecedor());

        return linha;
    }
}
