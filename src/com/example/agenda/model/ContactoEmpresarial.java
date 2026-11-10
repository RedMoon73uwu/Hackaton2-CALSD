package com.example.agenda.model;

public class ContactoEmpresarial extends Contacto {
    private String empresa;
    private String cargo;

    //el constructor de Contacto empresarial, hereda lo de Contacto.java y tiene sus propias cositas (empresa y cargo)
    public ContactoEmpresarial (String nombre, String apellido, String telefono, String empresa, String cargo){
        super (nombre, apellido, telefono);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    //los get de empresa y cargo
    public String getEmpresa(String empresa){
        return empresa;         }

    public String getCargo(String cargo){
        return cargo;           }


    //los set de empresa y cargo
    public void setEmpresa(String empresa){
        this.empresa = empresa; }

    public void setCargo(String cargo){
        this.cargo = cargo;     }

    /*@Override
    public String toString(){
        return "Empresa: "+empresa+"\nCargo: "+cargo; }
    */

    //se integra la definicion del metodo toString de la interfaz comparable. Para imprimir los datos del objeto sin
    //que imprima el nombre en dirección de memoria del objeto
    @Override
    public String toString() {
        return "[Empresarial] " + super.toString() + "   -Empresa: " + empresa + "\n   -Cargo: " + cargo;
    }
}
