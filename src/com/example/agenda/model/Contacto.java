package com.example.agenda.model;
import java.util.Objects;

public abstract class Contacto implements Comparable<Contacto> {

    private String nombre;
    private String apellido;
    private String telefono;

    //Constructor... este coso tambien establece un contrato a cumplir por las clases hijas :D
    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    //metodos get
    public String getNombre(String nombre) {
        return nombre;
    }

    public String getApellido(String apellido) {
        return apellido;
    }

    public String getTelefono(String telefono) {
        return telefono;
    }


    //metodo set
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    //1ra ves q uso esto, supongo que cumple la funcion de imprimir en consola
    //mas elegante que un system.out.print(); y se sobreescribe el nene.
    @Override
    public String toString() {
        return "Contacto: \n Nombre: " + nombre + " " + apellido + "\n Teléfono: " + telefono + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        return Objects.equals(nombre, contacto.nombre) && Objects.equals(apellido, contacto.apellido) ;

    }



}
