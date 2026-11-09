package com.example.agenda.service;

<<<<<<< HEAD
import com.example.agenda.model.Contacto;

public interface Agendable {
    //Genera una funcion y las retorna

    boolean anadirContacto(Contacto c);
    boolean existeContacto(Contacto c);
    boolean eliminarContacto(Contacto c);
    boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono);
    boolean agendaLlena();

    //Muestran en consola
    void listarContactos();
    void buscaContacto(String nombre, String apellido);

    //Muestra la disponibilidad de la agenda
    int espacioLibres();
}
=======
public interface Agendable {
}
>>>>>>> c321847f3cf9d01baf050926f750a98f41f779cc
