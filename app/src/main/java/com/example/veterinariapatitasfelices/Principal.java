package com.example.veterinariapatitasfelices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    daoContacto dao;
    Adaptador adapter;
    ArrayList<Contacto> lista;
    Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        dao= new daoContacto(Principal.this);
        lista=dao.verTodo();
        adapter= new Adaptador(this,lista,dao);
        ListView list=findViewById(R.id.lista);
        Button insertar = findViewById(R.id.btn_insertar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Principal.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombreMascota);
                final EditText edad = dialog.findViewById(R.id.et_edad);
                final EditText tipo = dialog.findViewById(R.id.et_TipoMascota);
                final EditText descripcion = dialog.findViewById(R.id.et_descripcion);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            c = new Contacto(nombre.getText().toString(),
                                    edad.getText().toString(),
                                    tipo.getText().toString(),
                                    descripcion.getText().toString());
                            dao.insertar(c);
                            lista = dao.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"ERROR",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    public void atras(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}