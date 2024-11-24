/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Clase genérica `Lista` que implementa una lista enlazada simple.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 * 
 * @param <T> El tipo de elementos almacenados en la lista.
 */

public class Lista<T> implements Iterable<T> {

    /**
     * Clase estática interna que representa un nodo de la lista.
     * 
     * @param <T> El tipo de dato almacenado en el nodo.
     */
    
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        /**
         * Constructor del nodo.
         * 
         * @param dato El dato almacenado en el nodo.
         */
        
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> cabeza;
    private int tamaño;

    /**
     * Constructor de la lista. Inicializa una lista vacía.
     */
    
    public Lista() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * 
     * @param dato El dato a agregar.
     */
    
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

    /**
     * Obtiene el elemento en una posición específica de la lista.
     * 
     * @param index El índice del elemento a obtener.
     * @return El elemento en la posición especificada.
     * @throws IndexOutOfBoundsException Si el índice está fuera de rango.
     */
    
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

    /**
     * Elimina un elemento específico de la lista.
     * 
     * @param dato El dato a eliminar.
     * @return `true` si el elemento fue eliminado, `false` en caso contrario.
     */
    
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

    /**
     * Devuelve el tamaño de la lista.
     * 
     * @return El número de elementos en la lista.
     */
    
    public int tamaño() {
        return tamaño;
    }

    /**
     * Verifica si la lista está vacía.
     * 
     * @return `true` si la lista está vacía, `false` en caso contrario.
     */
    
    public boolean esVacia() {
        return tamaño == 0;
    }

    /**
     * Devuelve un iterador para la lista.
     * 
     * @return Un iterador para recorrer los elementos de la lista.
     */
    
    @Override
    public Iterador<T> iterator() {
        return new Iterador<>(cabeza);
    }

    /**
     * Clase estática interna que implementa un iterador para la lista.
     * 
     * @param <T> El tipo de elemento iterado.
     */
    
    public static class Iterador<T> implements java.util.Iterator<T> {
        private Nodo<T> actual;

        /**
         * Constructor del iterador.
         * 
         * @param cabeza El nodo inicial del iterador.
         */
        
        public Iterador(Nodo<T> cabeza) {
            this.actual = cabeza;
        }

        /**
         * Verifica si hay más elementos en la lista.
         * 
         * @return `true` si hay más elementos, `false` en caso contrario.
         */
        
        @Override
        public boolean hasNext() {
            return actual != null;
        }

        /**
         * Devuelve el siguiente elemento de la lista.
         * 
         * @return El siguiente elemento.
         * @throws IllegalStateException Si no hay más elementos en la lista.
         */
        
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

    /**
     * Obtiene el elemento en una posición específica de la lista.
     * 
     * @param indice El índice del elemento a obtener.
     * @return El elemento en la posición especificada.
     * @throws IndexOutOfBoundsException Si el índice está fuera de rango.
     */
    
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

    /**
     * Remueve el primer elemento de la lista y lo devuelve.
     * 
     * @return El elemento removido.
     * @throws IllegalStateException Si la lista está vacía.
     */
    
    public T remover() {
        if (esVacia()) {
            throw new IllegalStateException("La lista está vacía.");
        }

        T dato = cabeza.dato; // Guardar el dato del nodo actual
        cabeza = cabeza.siguiente; // Mover la cabeza al siguiente nodo
        tamaño--;
        return dato; // Devolver el dato removido
    }
}

