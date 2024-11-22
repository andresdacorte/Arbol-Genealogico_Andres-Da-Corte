/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class Lista<T> implements Iterable<T> {
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> cabeza;
    private int tamaño;

    public Lista() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }
    
    public T get(int index) {
    if (index < 0 || index >= tamaño) {
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
    }

    Nodo<T> actual = cabeza;
    int contador = 0;

    while (actual != null) {
        if (contador == index) {
            return actual.dato;
        }
        actual = actual.siguiente;
        contador++;
    }

    return null; // Nunca debería llegar aquí si el índice es válido
}

    

    public boolean eliminar(T dato) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamaño--;
            return true;
        }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(dato)) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            tamaño--;
            return true;
        }
        return false;
    }
    

    public int tamaño() {
        return tamaño;
    }

    public boolean esVacia() {
        return tamaño == 0;
    }

    @Override
    public Iterador<T> iterator() {
        return new Iterador<>(cabeza);
    }

    public static class Iterador<T> implements java.util.Iterator<T> {
        private Nodo<T> actual;

        public Iterador(Nodo<T> cabeza) {
            this.actual = cabeza;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No hay más elementos en la lista.");
            }
            T dato = actual.dato;
            actual = actual.siguiente;
            return dato;
        }
    }
    
     public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }

        Nodo<T> actual = cabeza;
        int contador = 0;

        while (actual != null) {
            if (contador == indice) {
                return actual.dato;
            }
            actual = actual.siguiente;
            contador++;
        }

        return null; // Esto nunca debería suceder debido a la validación inicial
    }
     
     public T remover() {
        if (esVacia()) {
            throw new IllegalStateException("La lista está vacía.");
        }

        T dato = cabeza.dato; // Guardar el dato del nodo actual
        cabeza = cabeza.siguiente; // Mover la cabeza al siguiente nodo
        return dato; // Devolver el dato removido
    }

     
     
     
}
