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
    public String getNombre() {
        return nombre;      }

    public String getApellido() {
        return apellido;    }

    public String getTelefono() {
        return telefono;    }

    //metodo set
    public void setTelefono(String telefono) {
        this.telefono = telefono;   }


    //1ra ves q uso esto, supongo que cumple la funcion de imprimir en consola
    //mas elegante que un system.out.print(); y se sobreescribe el nene.
    //imprime en consola los datos del objeto, o sea, los vuelve strings, porque si los imprimes sin esto es probable que
    //te imprima el nombre del objeto en memoria (algo como "Contacto@5f150435" por ejemplo).
    @Override
    public String toString() {
        return "Contacto: \n   -Nombre: " + getNombre() + " " + getApellido() + "\n   -Teléfono: " + getTelefono() + "\n"; }


    @Override
    //metodo incluido en la interfaz comparable que permite comparar dos objetos.
    //recibe de parametro un objeto de nombre 'o' en este caso, y evalua si el objeto que lo llama es igual al
    //objeto a comparar
    public boolean equals(Object o) {
        if (this == o) return true;
//si es null o la propiedad getClass() de cada uno de los objetos comparados NO son lo mismo, regresa false
        if (o == null || getClass() != o.getClass()) return false;

        //se hace casting para que el objeto 'o' sea tratado como una instancia de la clase "Contacto"
        Contacto contacto = (Contacto) o;

        //regresa true o false si ambas condiciones se cumplen
        return Objects.equals(nombre, contacto.nombre) && Objects.equals(apellido, contacto.apellido) ;

    }

    //este metodo (de la interfaz comparable) regresa un codigo hash generado mediante la combinación del nombre y de apellido del contacto
    //permite crear codigos de identiciación unicos para cada registro de usuario, y es unico de cada conjunto de nombre
    //y apellido en particular
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido);
    }

    //metodo incluido tambien en la interfaz comparable. Compara primero el apellido y regresa -1 si el apellido del objeto
    //que lo llama va alfabeticamente antes que 'o'. Sirve para ordenar los registros en la lista.
    @Override
    public int compareTo(Contacto o) {
        int cmpApellido = this.apellido.compareToIgnoreCase(o.apellido);
        if (cmpApellido != 0) {
            return cmpApellido;
        }
        //en caso de que el apellido sea el mismo, desempata con el nombre para poderlos ordenar
        return this.nombre.compareToIgnoreCase(o.nombre);
    }

}
