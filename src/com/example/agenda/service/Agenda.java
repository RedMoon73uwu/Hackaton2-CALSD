package com.example.agenda.service;

import com.example.agenda.model.Contacto;

public class Agenda implements Agendable {

    //Definir capacidad maxima de 10

    private static final int CAPACIDAD_MAXIMA = 10;
    private Contacto[] contactos;
    private int contador;

    public Agenda() {
        this.contactos = new Contacto[CAPACIDAD_MAXIMA];
        this.contador = 0;
    }

    //Se desarrollan Constructor para definir diferentes condiciones.
    public Agenda(int capacidad) {
        if (capacidad <= 0) {
            this.contactos = new Contacto[CAPACIDAD_MAXIMA];
        } else {
            this.contactos = new Contacto[capacidad];
        }
        this.contador = 0;
    }

    //Variables para añadir un contacto
    @Override
    public boolean anadirContacto(Contacto c) {
        if (c == null) {
            System.out.println("Error: No se puede añadir un contacto nulo.");
            return false;
        }

        if (agendaLlena()) {
            System.out.println("No se puede añadir el contacto: la agenda está llena.");
            return false;
        }

        if (existeContacto(c)) {
            System.out.println("No se puede añadir el contacto: ya existe en la agenda.");
            return false;
        }
        this.contactos[this.contador] = c;
        this.contador++;
        System.out.println("Contacto añadido correctamente: " + c.getNombre() + " " + c.getApellido());
        return true;
    }
    //Verificar existencia de un contacto
    @Override
    public boolean existeContacto(Contacto c) {
        if (c == null) {
            return false;
        }

        for (int i = 0; i < this.contador; i++) {
            // Se apoya en el método equals de Contacto o en la coincidencia de nombre y apellido
            if (this.contactos[i].equals(c) || coincideNombreCompleto(this.contactos[i], c.getNombre(), c.getApellido())) {
                return true;
            }
        }
        return false;
    }
    // Imprime la lista de contactos
    @Override
    public void listarContactos() {
        if (this.contador == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("=== LISTA DE CONTACTOS (" + this.contador + "/" + this.contactos.length + ") ===");
        for (int i = 0; i < this.contador; i++) {
            System.out.println((i + 1) + ". " + this.contactos[i].toString());
        }
    }
    //Busca contacto por nombre y apellido
    @Override
    public void buscaContacto(String nombre, String apellido) {
        for (int i = 0; i < this.contador; i++) {
            if (coincideNombreCompleto(this.contactos[i], nombre, apellido)) {
                System.out.println("Contacto encontrado -> Teléfono: " + this.contactos[i].getTelefono()
                        + " | Datos: " + this.contactos[i].toString());
                return; // Termina el método al encontrarlo para evitar iteraciones innecesarias
            }
        }
        System.out.println("No se encontró ningún contacto con el nombre: " + nombre + " " + apellido);
    }

    public void buscaContacto(String nombre) {
        if (nombre == null) {
            System.out.println("Nombre inválido.");
            return;
        }

        boolean encontrado = false;
        for (int i = 0; i < this.contador; i++) {
            if (this.contactos[i].getNombre() != null
                    && this.contactos[i].getNombre().trim().equalsIgnoreCase(nombre.trim())) {
                System.out.println("Contacto encontrado -> Teléfono: " + this.contactos[i].getTelefono()
                        + " | Datos: " + this.contactos[i].toString());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con el nombre: " + nombre);
        }
    }
    //Eliminar contacto
    @Override
    public boolean eliminarContacto(Contacto c) {
        if (c == null) {
            System.out.println("No se pudo eliminar: el contacto proporcionado es nulo.");
            return false;
        }

        for (int i = 0; i < this.contador; i++) {
            if (this.contactos[i].equals(c) || coincideNombreCompleto(this.contactos[i], c.getNombre(), c.getApellido())) {
                // Desplaza los contactos posteriores una posición hacia la izquierda
                for (int j = i; j < this.contador - 1; j++) {
                    this.contactos[j] = this.contactos[j + 1];
                }
                // Limpia la última referencia ocupada y reduce el contador
                this.contactos[this.contador - 1] = null;
                this.contador--;
                System.out.println("Contacto eliminado exitosamente.");
                return true;
            }
        }

        System.out.println("No se eliminó el contacto: no existe en la agenda.");
        return false;
    }

    //Modificar contacto
    @Override
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        if (nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) {
            System.out.println("No se pudo actualizar: el nuevo teléfono no es válido.");
            return false;
        }

        for (int i = 0; i < this.contador; i++) {
            if (coincideNombreCompleto(this.contactos[i], nombre, apellido)) {
                this.contactos[i].setTelefono(nuevoTelefono.trim());
                System.out.println("Teléfono actualizado correctamente para " + nombre + " " + apellido + ".");
                return true;
            }
        }

        System.out.println("No se pudo actualizar el teléfono: contacto no encontrado.");
        return false;
    }

    //Imprimir si la agenda esta llena
    @Override
    public boolean agendaLlena() {
        return this.contador >= this.contactos.length;
    }
    //
    @Override
    public int espacioLibres() {
        return this.contactos.length - this.contador;
    }

    //Verificar coincidencias

    private boolean coincideNombreCompleto(Contacto contacto, String nombre, String apellido) {
        if (contacto == null || nombre == null || apellido == null) {
            return false;
        }
        boolean mismoNombre = contacto.getNombre() != null
                && contacto.getNombre().trim().equalsIgnoreCase(nombre.trim());
        boolean mismoApellido = contacto.getApellido() != null
                && contacto.getApellido().trim().equalsIgnoreCase(apellido.trim());
        return mismoNombre && mismoApellido;
    }

    public int getContador() {
        return this.contador;
    }

    public int getCapacidad() {
        return this.contactos.length;
    }
}