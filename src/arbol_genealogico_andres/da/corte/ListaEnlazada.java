/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
class ListaEnlazada {
    public class NodoLista {
        Nodo dato;
        NodoLista siguiente;

        public NodoLista(Nodo dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public NodoLista cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void agregar(Nodo dato) {
        NodoLista nuevoNodo = new NodoLista(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    public Nodo buscar(String nombre) {
        NodoLista actual = cabeza;
        while (actual != null) {
            if (actual.dato.getNombreCompleto().equals(nombre)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public NodoLista getCabeza() {
        return cabeza;
    }
}
