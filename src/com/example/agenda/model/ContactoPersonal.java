package com.example.agenda.model;

public class ContactoPersonal extends Contacto {
    private String fechaDeCumple;

    //constructor de Contacto personal, igualmente hereda Contacto.java y tiene su atributo propio (B-day uwu)
    public ContactoPersonal(String nombre, String apellido, String telefono, String fechaDeCumple) {
        super(nombre, apellido, telefono);
        this.fechaDeCumple = fechaDeCumple;
    }

    //la fecha d cumple tiene su propio get-set pa q no chille
    public String getFechaDeCumple(String fechaDeCumple){
        return fechaDeCumple;                   }

    public void setFechaDeCumple(String fechaDeCumple){
        this.fechaDeCumple = fechaDeCumple;     }


    @Override
    public String toString() {
       return " Cumpleaños: "+fechaDeCumple;
        //return super.toString();
    }
}
