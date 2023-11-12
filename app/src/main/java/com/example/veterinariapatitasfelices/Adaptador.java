package com.example.veterinariapatitasfelices;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter{
    ArrayList<Contacto> lista;
    daoContacto dao;
    Contacto c;
    Activity a;
    int id=0;
    public Adaptador(Activity a, ArrayList<Contacto> lista, daoContacto dao){
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    @Override
    public int getCount(){
        return lista.size();
    }
    @Override
    public Object getItem(int i){
        c=lista.get(i);
        return null;
    }
    @Override
    public long getItemId(int i){
        c=lista.get(i);
        return c.getId();
    }
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup){
        View v = view;
        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item,null);
        }
        c=lista.get(posicion);
        TextView nombre= v.findViewById(R.id.nombre);
        TextView edad= v.findViewById(R.id.edad);
        TextView tipo= v.findViewById(R.id.tipo);
        TextView descripcion= v.findViewById(R.id.descripcion);
        Button editar= v.findViewById(R.id.btn_editar);
        Button eliminar= v.findViewById(R.id.btn_eliminar);
        nombre.setText(c.getNombre());
        edad.setText(c.getEdad());
        tipo.setText(c.getTipo());
        descripcion.setText(c.getDescripcion());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos =Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText nombre = dialog.findViewById(R.id.et_nombreMascota);
                final EditText edad = dialog.findViewById(R.id.et_edad);
                final EditText tipo = dialog.findViewById(R.id.et_TipoMascota);
                final EditText descripcion = dialog.findViewById(R.id.et_descripcion);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                c=lista.get(pos);
                setId(c.getId());
                nombre.setText(c.getNombre());
                edad.setText(c.getEdad());
                tipo.setText(c.getTipo());
                descripcion.setText(c.getDescripcion());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            c =new Contacto(getId(),nombre.getText().toString(),
                                    edad.getText().toString(),
                                    tipo.getText().toString(),
                                    descripcion.getText().toString());
                            dao.editar(c);
                            lista=dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=Integer.parseInt(view.getTag().toString());
                c=lista.get(posicion);
                setId(c.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de Eliminar");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.eliminar(getId());
                        lista=dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
