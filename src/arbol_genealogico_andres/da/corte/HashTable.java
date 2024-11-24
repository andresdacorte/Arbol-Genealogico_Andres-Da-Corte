/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Clase HashTable que implementa una tabla hash personalizada para almacenar pares clave-valor.
 * Utiliza una estrategia de encadenamiento para manejar colisiones.
 *
 * @author Andres Da Corte
 * @since 2024-11-24
 * 
 * @param <K> Tipo de las claves.
 * @param <V> Tipo de los valores.
 * 
 */
public class HashTable<K, V> {
    /**
     * Clase estática interna que representa un nodo de la tabla hash.
     *
     * @param <K> Tipo de las claves.
     * @param <V> Tipo de los valores.
     */
    private static class Nodo<K, V> {
        /** Clave del nodo. */
        K clave;

        /** Valor asociado a la clave. */
        V valor;

        /** Referencia al siguiente nodo en caso de colisión. */
        Nodo<K, V> siguiente;

        /**
         * Constructor del nodo.
         *
         * @param clave Clave del nodo.
         * @param valor Valor asociado al nodo.
         */
        public Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    /** Array de nodos que representa la tabla hash. */
    private Nodo<K, V>[] tabla;

    /** Capacidad máxima de la tabla. */
    private int capacidad;

    /** Tamaño actual de la tabla (número de elementos almacenados). */
    private int tamaño;

    /**
     * Constructor de la tabla hash.
     * 
     * @param capacidad Tamaño inicial del array que almacena los nodos.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Nodo[capacidad];
        this.tamaño = 0;
    }

    /**
     * Calcula el índice del array para una clave utilizando su hash.
     *
     * @param clave Clave para calcular el índice.
     * @return Índice correspondiente en la tabla.
     */
    private int hash(K clave) {
        return Math.abs(clave.hashCode()) % capacidad;
    }

    /**
     * Inserta o actualiza un par clave-valor en la tabla hash.
     *
     * @param clave Clave del par.
     * @param valor Valor asociado a la clave.
     */
    public void put(K clave, V valor) {
        int indice = hash(clave);
        Nodo<K, V> nuevoNodo = new Nodo<>(clave, valor);

        if (tabla[indice] == null) {
            tabla[indice] = nuevoNodo;
        } else {
            Nodo<K, V> actual = tabla[indice];
            while (actual != null) {
                if (actual.clave.equals(clave)) {
                    actual.valor = valor; // Actualizar valor si la clave ya existe
                    return;
                }
                if (actual.siguiente == null) break;
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    /**
     * Obtiene el valor asociado a una clave.
     *
     * @param clave Clave para buscar el valor.
     * @return Valor asociado a la clave, o {@code null} si no se encuentra.
     */
    public V get(K clave) {
        int indice = hash(clave);
        Nodo<K, V> actual = tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    /**
     * Limpia la tabla hash eliminando todos los elementos.
     */
    public void limpiar() {
        this.tabla = new Nodo[capacidad]; // Reinicia el array de nodos
        this.tamaño = 0; // Reinicia el tamaño
        System.out.println("La tabla hash ha sido limpiada.");
    }

    /**
     * Verifica si la tabla contiene una clave específica.
     *
     * @param clave Clave a verificar.
     * @return {@code true} si la clave existe, {@code false} en caso contrario.
     */
    public boolean containsKey(K clave) {
        return get(clave) != null;
    }

    /**
     * Elimina un elemento de la tabla hash dado su clave.
     *
     * @param clave Clave del elemento a eliminar.
     */
    public void remove(K clave) {
        int indice = hash(clave);
        Nodo<K, V> actual = tabla[indice];
        Nodo<K, V> previo = null;

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (previo == null) {
                    tabla[indice] = actual.siguiente; // Eliminar el primer nodo
                } else {
                    previo.siguiente = actual.siguiente; // Eliminar nodo intermedio o final
                }
                tamaño--;
                return;
            }
            previo = actual;
            actual = actual.siguiente;
        }
    }

    /**
     * Obtiene el número de elementos almacenados en la tabla.
     *
     * @return Tamaño actual de la tabla.
     */
    public int size() {
        return tamaño;
    }

    /**
     * Verifica si la tabla está vacía.
     *
     * @return {@code true} si la tabla está vacía, {@code false} en caso contrario.
     */
    public boolean isEmpty() {
        return tamaño == 0;
    }

    /**
     * Devuelve un iterable con todas las claves almacenadas en la tabla.
     *
     * @return Iterable con las claves.
     */
    public Iterable<K> keys() {
        Lista<K> listaClaves = new Lista<>();
        for (int i = 0; i < capacidad; i++) {
            Nodo<K, V> actual = tabla[i];
            while (actual != null) {
                listaClaves.agregar(actual.clave);
                actual = actual.siguiente;
            }
        }
        return listaClaves;
    }

    /**
     * Devuelve un iterable con todos los valores almacenados en la tabla.
     *
     * @return Iterable con los valores.
     */
    public Iterable<V> values() {
        Lista<V> listaValores = new Lista<>();
        for (int i = 0; i < capacidad; i++) {
            Nodo<K, V> actual = tabla[i];
            while (actual != null) {
                listaValores.agregar(actual.valor);
                actual = actual.siguiente;
            }
        }
        return listaValores;
    }
}

