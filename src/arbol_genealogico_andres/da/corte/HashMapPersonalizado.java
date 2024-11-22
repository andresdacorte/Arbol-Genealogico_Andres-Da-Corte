/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class HashMapPersonalizado<K, V> {
    private static final int CAPACIDAD_INICIAL = 16; // Tamaño inicial del array
    private NodoHash<K, V>[] tabla; // Array de nodos
    private int tamaño; // Número de elementos en el mapa

    // Constructor
    public HashMapPersonalizado() {
        this.tabla = new NodoHash[CAPACIDAD_INICIAL];
        this.tamaño = 0;
    }

    // Método para obtener un valor por clave
    public V obtener(K clave) {
        int indice = obtenerIndice(clave);
        NodoHash<K, V> actual = tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null; // Clave no encontrada
    }

    // Método para agregar un par clave-valor
    public void agregar(K clave, V valor) {
        int indice = obtenerIndice(clave);
        NodoHash<K, V> actual = tabla[indice];

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                actual.valor = valor; // Actualizar el valor si la clave ya existe
                return;
            }
            actual = actual.siguiente;
        }

        // Insertar el nuevo nodo al inicio de la lista
        NodoHash<K, V> nuevoNodo = new NodoHash<>(clave, valor);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice] = nuevoNodo;
        tamaño++;
    }

    // Método para eliminar un elemento por clave
    public boolean eliminar(K clave) {
        int indice = obtenerIndice(clave);
        NodoHash<K, V> actual = tabla[indice];
        NodoHash<K, V> anterior = null;

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente; // Eliminar el primer nodo
                } else {
                    anterior.siguiente = actual.siguiente; // Eliminar nodo intermedio o final
                }
                tamaño--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false; // Clave no encontrada
    }

    // Método para verificar si el mapa contiene una clave
    public boolean contieneClave(K clave) {
        return obtener(clave) != null;
    }

    // Método para obtener el tamaño del mapa
    public int tamaño() {
        return tamaño;
    }

    // Método para limpiar el mapa
    public void limpiar() {
        tabla = new NodoHash[CAPACIDAD_INICIAL];
        tamaño = 0;
    }

    // Método auxiliar para calcular el índice del array a partir de la clave
    private int obtenerIndice(K clave) {
        return (clave.hashCode() & 0x7FFFFFFF) % tabla.length;
    }

    // Nodo interno para almacenar pares clave-valor
    private static class NodoHash<K, V> {
        private K clave;
        private V valor;
        private NodoHash<K, V> siguiente;

        public NodoHash(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }
}

