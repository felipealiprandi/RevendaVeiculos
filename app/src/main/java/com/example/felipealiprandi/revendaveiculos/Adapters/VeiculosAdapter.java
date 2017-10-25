package com.example.felipealiprandi.revendaveiculos.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felipealiprandi.revendaveiculos.R;
import com.example.felipealiprandi.revendaveiculos.Tipos.Veiculo;

import java.util.List;

/**
 * Created by Felipe Aliprandi on 23/10/2017.
 */

public class VeiculosAdapter extends BaseAdapter {
    Context ctx;
    List<Veiculo> veiculos;

    @Override
    public int getCount() {
        return veiculos.size();
    }

    public VeiculosAdapter(Context ctx, List<Veiculo> veiculos) {
        this.ctx = ctx;
        this.veiculos = veiculos;
    }

    @Override
    public Object getItem(int position) {
        return veiculos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return veiculos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Veiculo carro = veiculos.get(position);
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_veiculo,null);

        ImageView imgLogo = (ImageView) linha.findViewById(R.id.imglogo);

        TextView txtModelo = (TextView) linha.findViewById(R.id.txtModelo);

        TextView txtAno = (TextView) linha.findViewById(R.id.txtAno);

        TextView txtCombustivel = (TextView) linha.findViewById(R.id.txtCombustivel);

        Resources res = ctx.getResources();
        TypedArray logos = res.obtainTypedArray(R.array.logos);
        imgLogo.setImageDrawable(logos.getDrawable(carro.getFabricante()));

        txtModelo.setText(carro.getModelo());
        txtAno.setText(String.valueOf(carro.getAno()));
        String combustivel = (carro.isGasolina() ? "G" : "") +
              (carro.isEtanol() ? "E" : "");
txtCombustivel.setText(combustivel);

        return linha;
    }
}
