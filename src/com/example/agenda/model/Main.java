package com.example.agenda.app;

import java.util.Scanner;

// importar las clases
import com.example.agenda.model.Contacto;
import com.example.agenda.model.ContactoPersonal;
import com.example.agenda.model.ContactoEmpresarial;
import com.example.agenda.service.Agenda;
import com.example.agenda.service.Agendable;
import com.example.agenda.util.Validador;


public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Agendable agenda = crearAgenda(); // crear la agenda de tamaño variable

        int opcion; //variable que guarda la opcion que elige el usuario

        //1. ciclo menu con do-while, se muestra una vez y se repite hasta que el usuario decida salir
        do{
            mostrarMenu();
            opcion = leerOpcion("Elige una opcion: ");
            System.out.println();

            //switch :  segun la opcion elegida, se ejecuta esa accion
            switch (opcion){
                case 1:
                    opcionAnadirContacto(agenda);
                    break;
                case 2:
                    opcionExisteContacto(agenda);
                    break;
                case 3:
                    opcionListarContactos(agenda);
                    break;
                case 4:
                    opcionBuscarContacto(agenda);
                    break;
                case 5:
                    opcionEliminarContacto(agenda);
                    break;
                case 6:
                    opcionModificarTelefono(agenda);
                    break;
                case 7:
                    opcionAgendaLlena(agenda);
                    break;
                case 8:
                    opcionEspacioLibres(agenda);
                    break;
                case 0:
                    System.out.println("Hasta pronto!!");
                    break;
                default:
                    //cualquier otro numero o letra, cae aqui
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            System.out.println();
        } while(opcion != 0);

        scan.close();
    }

    //2. crear la agenda
    //preguntar al usuario como quiere crear la agenda, si tamaño fijo (10) o personalizdo
    private static Agendable crearAgenda(){
        System.out.println("=== CONFIGURACION AGENDA TELEFONICA ===");
        System.out.println("  1. Crear agenda con tamaño por defecto (10).");
        System.out.println("  2. Crear agenda indicando el tamaño máximo.");
        int tipo = leerOpcion("Elige una opción: ");

        if(tipo == 2){
            int tamano = leerOpcion("Tamaño máximo: ");

            //nota, solo aceptar numeros mayorees de 0
            if(tamano > 0){
                System.out.println("Agenda creada con capacidad para " + tamano + " contactos. \n");
                return new Agenda(tamano); //este siendo mi constructor de tamaño
            }
            System.out.println("Tamaño no válido. Se usará el tamaño por defecto.");
        }
        //opcion 1
        System.out.println("Agenda creada con el tamaño por defecto.\n");
        return new Agenda();
    }

    //3. menu
    private static void mostrarMenu(){
        System.out.println("========== MENU ==========");
        System.out.println(" 1. Añadir contacto");
        System.out.println(" 2. Existe contacto");
        System.out.println(" 3. Listar contactos");
        System.out.println(" 4. Buscar contacto");
        System.out.println(" 5. Eliminar contacto");
        System.out.println(" 6. Modificar teléfono");
        System.out.println(" 7. ¿Agenda llena?");
        System.out.println(" 8. Espacios libres");
        System.out.println(" 0. Salir");
        System.out.println("===========================");
    }

    // Funciones para cada opcion del menu
    //opcion 1. añadir contacto
    private static void opcionAnadirContacto(Agendable agenda){
        //primero ver si la agenda no esta llena
        if(agenda.agendaLlena()){
            System.out.println("Lo siento, la agenda esta llena. No se pueden añadir mas contactos.");
            return;
        }

        //Datos comunes en personal y empresarial
        //leerTextoValido, repite la pregunta hasta que el texto no este vacio
        String nombre = leerTextoValido("Nombre: ");
        String apellido = leerTextoValido("Apellido: ");
        String telefono = leerTextoValido("Teléfono: ");

        //preguntar que tipo de contacto es, si personal o empresarial
        System.out.println("Tipo de contacto: ");
        System.out.println("1. Personal");
        System.out.println("2. Empresarial");
        int tipo = leerOpcion("Elige una opcion: ");

        // si el tipo no es 1 ni 2, cancelamos
        if(tipo != 1 && tipo != 2){
            System.out.println("Tipo de contacto no válido.");
            return;
        }

        //guardar los dtos en la variable contacto que pertenece a Contacto
        Contacto contacto;
        if(tipo == 2){
            String empresa = leerTexto("Empresa: ");
            String cargo = leerTexto("Cargo: ");
            contacto = new ContactoEmpresarial(nombre, apellido, telefono, empresa, cargo);
        } else{
            String fechaDeCumple = leerTexto("Fecha de nacimiento: ");
            contacto = new ContactoPersonal(nombre, apellido, telefono, fechaDeCumple);
        }

        //antes de añadir, hay que revisar si ya existe el mismo apellido o el mismo nombre
        if(agenda.existeContacto(contacto)){
            System.out.println("Ese contacto ya existe en la agenda.");
        } else if(agenda.anadirContacto(contacto)){
            System.out.println("Contacto añadido correctamente.");
        } else {
            System.out.println("No se pudo añadir el contacto.");
        }
    }

    // opcion 2. existe contacto
    private static void opcionExisteContacto(Agendable agenda){
        String nombre = leerTextoValido("Nombre: ");
        String apellido = leerTextoValido("Apellido: ");

        //necesitamos un objeto c
        // el constructor pide 4 datos, pero para buscar solo importan el nombre y el apellido,
        // asi que el telefono y la fecha los dejamos vacios
        String telefonoVacio = "";
        String fechaVacia = "";
        Contacto contactoABuscar = new ContactoPersonal(nombre, apellido, telefonoVacio, fechaVacia);
        if(agenda.existeContacto(contactoABuscar)){
            System.out.println("El contacto SI existe en la agenda.");
        } else {
            System.out.println("El contacto NO existe en la agenda.");
        }
    }

    // opcion 3. listar contacto, se ordenan y se enlistan.
    private static void opcionListarContactos(Agendable agenda){
        agenda.listarContactos();
    }
    // opcion 4. buscar contacto
    private static void opcionBuscarContacto(Agendable agenda) {
        String nombre = leerTextoValido("Nombre: ");
        String apellido = leerTextoValido("Apellido: ");
        agenda.buscaContacto(nombre, apellido);
    }

    // opcion 5. eliminar telefono
    private static void opcionEliminarContacto(Agendable agenda) {
        String nombre = leerTextoValido("Nombre: ");
        String apellido = leerTextoValido("Apellido: ");
        // igual que en existe: telefono y fecha vacios, porque solo importan nombre y apellido
        String telefonoVacio = "";
        String fechaVacia = "";
        Contacto contactoAEliminar = new ContactoPersonal(nombre, apellido, telefonoVacio, fechaVacia);

        // eliminarContacto devuelve true si lo elimino, false si no existía.
        if (agenda.eliminarContacto(contactoAEliminar)) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar: el contacto no existe.");
        }
    }
    // opcion 6. modificar telefono
    private static void opcionModificarTelefono(Agendable agenda) {
        String nombre = leerTextoValido("Nombre: ");
        String apellido = leerTextoValido("Apellido: ");
        String nuevoTelefono = leerTexto("Nuevo teléfono: ");

        // modificarTelefono devuelve true si lo cambió, false si el contacto no existe.
        if (agenda.modificarTelefono(nombre, apellido, nuevoTelefono)) {
            System.out.println("Teléfono modificado correctamente.");
        } else {
            System.out.println("El contacto no existe. No se modificó el teléfono.");
        }
    }

    // opcion 7. agenda llena??
    private static void opcionAgendaLlena(Agendable agenda) {
        if (agenda.agendaLlena()) {
            System.out.println("La agenda está llena: no hay espacio disponible para nuevos contactos.");
        } else {
            System.out.println("La agenda NO está llena. Todavía hay espacio.");
        }
    }
    // opcion 8. espacios libres
    private static void opcionEspacioLibres(Agendable agenda) {
        System.out.println("Puedes agregar " + agenda.espacioLibres() + " contactos más.");
    }

    // =================== METODO AYUDA ==================================

    // leerOpcion: muestra un mensaje y lee un numero.
    // Si el usuario escribe letras, devuelve -1 en lugar de que el programa se caiga.
    private static int leerOpcion(String mensaje){
        System.out.print(mensaje);
        try{
            return Integer.parseInt(scan.nextLine().trim());
        } catch(NumberFormatException e){
            return -1;
        }
    }

    // leerTexto: muestra un mensaje y devuelve lo que escribe el usuario (sin espacios sobrantes).
    private static String leerTexto(String mensaje){
        System.out.print(mensaje);
        return scan.nextLine().trim();
    }

    // leerTextoValido: igual que leerTexto, pero vuelve a preguntar mientras el texto este vacio.
    // usar Validador.esValido para saber si el texto sirve.
    private static String leerTextoValido(String mensaje){
        String texto = leerTexto(mensaje);
        while(!Validador.esValido(texto)){
            System.out.println("Este dato no puede estar vacio. Intenta de nuevo.");
            texto = leerTexto(mensaje);
        }
        return texto;
    }
}