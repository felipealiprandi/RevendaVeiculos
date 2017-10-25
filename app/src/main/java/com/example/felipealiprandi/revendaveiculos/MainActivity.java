package com.example.felipealiprandi.revendaveiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCliente = (Button) findViewById(R.id.btnClientes);
        Button btnVeiculo = (Button) findViewById(R.id.btnVeiculos);
        Button btnCompra = (Button) findViewById(R.id.btnCompras);

        btnCliente.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,listaClientes.class);
                startActivity(it);
            }
        });

        btnVeiculo.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,listaVeiculos.class);
                startActivity(it);
            }
        });

        btnCompra.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,listaCompras.class);
                startActivity(it);
            }
        });




    }
}
