package com.example.sugar.sugarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sugar.sugarexample.models.Producto;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etDesc, etCant;
    private TextView tvlista;
    private Button btdelete, btlistar, btsave, btbuscar, bteliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDesc = (EditText) findViewById(R.id.etDesc);
        etCant = (EditText) findViewById(R.id.etCant);
        tvlista = (TextView) findViewById(R.id.tvlista);
        btdelete = (Button) findViewById(R.id.btdelete);
        btlistar = (Button) findViewById(R.id.btlistar);
        btsave = (Button) findViewById(R.id.btsave);
        btbuscar = (Button) findViewById(R.id.btbuscar);
        bteliminar = (Button) findViewById(R.id.bteliminar);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        btlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });


        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });

        btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = buscar(etDesc.getText().toString().trim());
                if(p!=null){
                    mostrarResultado(p);
                }
                else{

                }
            }
        });

        bteliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarproducto(etDesc.getText().toString().trim());
            }
        });



    }
    public void guardar(){
        String descripcion = etDesc.getText().toString().trim();
        if(descripcion.length() > 0 && etCant.getText().toString().length()> 0 ){
            Producto p = buscar(descripcion);
            if(p!=null){
                Toast.makeText(this, "El Producto ya se encuentra registrado", Toast.LENGTH_LONG).show();
                etDesc.setText("");
                etCant.setText("");
            }else{
                Producto producto = new Producto( etDesc.getText().toString() , Integer.parseInt(etCant.getText().toString()));
                Producto.saveInTx(producto);
                etCant.setText("");
                Toast.makeText(this, "Producto guardado con Exito", Toast.LENGTH_LONG).show();
                tvlista.setText(p.toString());
            }

        }

    }

    private Producto buscar(String criterio){
        Log.d("test","el criterio es "+criterio);
        Producto p = null;
        List<Producto> listado = Producto.find(Producto.class, "descripcion = ?", criterio);
        if(!listado.isEmpty()){
            p = listado.get(0);
            Log.d("test", listado.size() + "");

        }

        return p;

    }

    private void mostrarResultado(Producto p){
        etCant.setText(String.valueOf(p.getCantidad()));
    }

    public void listar(){
        List<Producto> p = Producto.listAll(Producto.class);
        tvlista.setText(p.toString());

    }
    public void eliminar(){
        Producto.deleteAll(Producto.class);
        tvlista.setText("");
    }

    public void eliminarproducto(String criterio){
        List<Producto> listado = Producto.find(Producto.class, "descripcion = ?", criterio);
        if(!listado.isEmpty()){
            Producto p = listado.get(0);
            Producto.deleteInTx(p);
        }


    }


}