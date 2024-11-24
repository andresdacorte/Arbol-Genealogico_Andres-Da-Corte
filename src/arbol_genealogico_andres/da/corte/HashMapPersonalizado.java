/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Implementación personalizada de un mapa hash (HashMap).
 * Permite almacenar pares clave-valor y proporciona operaciones básicas como agregar, eliminar, y obtener.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 *
 * @param <K> El tipo de las claves.
 * @param <V> El tipo de los valores.
 */
public class HashMapPersonalizado<K, V> {
    private static final int CAPACIDAD_INICIAL = 16; // Tamaño inicial del array
    private NodoHash<K, V>[] tabla; // Array de nodos
    private int tamaño; // Número de elementos en el mapa

    /**
     * Constructor para inicializar el mapa con la capacidad predeterminada.
     */
    
    public HashMapPersonalizado() {
        this.tabla = new NodoHash[CAPACIDAD_INICIAL];
        this.tamaño = 0;
    }

    /**
     * Obtiene el valor asociado a una clave.
     *
     * @param clave La clave cuyo valor se desea obtener.
     * @return El valor asociado a la clave, o {@code null} si no se encuentra.
     */
    
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

    /**
     * Agrega un par clave-valor al mapa. Si la clave ya existe, actualiza su valor.
     *
     * @param clave La clave del par.
     * @param valor El valor asociado a la clave.
     */
    
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

    /**
     * Elimina un elemento del mapa usando su clave.
     *
     * @param clave La clave del elemento a eliminar.
     * @return {@code true} si el elemento fue eliminado, {@code false} si la clave no se encontró.
     */
    
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

    /**
     * Verifica si el mapa contiene una clave específica.
     *
     * @param clave La clave a verificar.
     * @return {@code true} si la clave está presente, {@code false} en caso contrario.
     */
    
    public boolean contieneClave(K clave) {
        return obtener(clave) != null;
    }

    /**
     * Obtiene el número de elementos almacenados en el mapa.
     *
     * @return El tamaño actual del mapa.
     */
    
    public int tamaño() {
        return tamaño;
    }

    /**
     * Limpia el mapa eliminando todos los elementos almacenados.
     */
    
    public void limpiar() {
        tabla = new NodoHash[CAPACIDAD_INICIAL];
        tamaño = 0;
    }

    /**
     * Calcula el índice del array donde se almacenará un nodo, basado en su clave.
     *
     * @param clave La clave para calcular el índice.
     * @return El índice correspondiente en la tabla hash.
     */
    
    private int obtenerIndice(K clave) {
        return (clave.hashCode() & 0x7FFFFFFF) % tabla.length;
    }

    /**
     * Clase interna que representa un nodo en la tabla hash.
     *
     * @param <K> El tipo de las claves.
     * @param <V> El tipo de los valores.
     */
    
    private static class NodoHash<K, V> {
        private K clave; // La clave del nodo
        private V valor; // El valor asociado a la clave
        private NodoHash<K, V> siguiente; // Referencia al siguiente nodo en caso de colisión

        /**
         * Constructor para inicializar un nodo con una clave y un valor.
         *
         * @param clave La clave del nodo.
         * @param valor El valor asociado al nodo.
         */
        
        public NodoHash(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }
}

