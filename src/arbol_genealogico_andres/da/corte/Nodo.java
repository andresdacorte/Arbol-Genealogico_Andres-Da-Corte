/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class Nodo {
    private String nombreCompleto;
    private String titulo;
    public String mote;
    private Nodo padre;
    public ListaEnlazada hijos;

    public Nodo(String nombreCompleto, String titulo, String mote) {
        this.nombreCompleto = nombreCompleto;
        this.titulo = titulo;
        this.mote = mote;
        this.hijos = new ListaEnlazada();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public ListaEnlazada getHijos() {
        return hijos;
    }

    public void agregarHijo(Nodo hijo) {
        hijos.agregar(hijo);
        hijo.setPadre(this);
    }
}
