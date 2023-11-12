package com.example.veterinariapatitasfelices;
public class Contacto {
    int id;
    String nombre;
    String edad;
    String tipo;
    String descripcion;

    public Contacto(){

    }

    public Contacto(String nombre, String edad, String tipo, String descripcion) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Contacto(int id, String nombre, String edad, String tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}