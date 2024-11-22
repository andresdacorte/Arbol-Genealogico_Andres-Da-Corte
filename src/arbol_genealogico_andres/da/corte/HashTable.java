/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class HashTable<K, V> {
    private static class Nodo<K, V> {
        K clave;
        V valor;
        Nodo<K, V> siguiente;

        public Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<K, V>[] tabla;
    private int capacidad;
    private int tamaño;

    @SuppressWarnings("unchecked")
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Nodo[capacidad];
        this.tamaño = 0;
    }

    private int hash(K clave) {
        return Math.abs(clave.hashCode()) % capacidad;
    }

    public void put(K clave, V valor) {
        int indice = hash(clave);
        Nodo<K, V> nuevoNodo = new Nodo<>(clave, valor);

        if (tabla[indice] == null) {
            tabla[indice] = nuevoNodo;
        } else {
            Nodo<K, V> actual = tabla[indice];
            while (actual != null) {
                if (actual.clave.equals(clave)) {
                    actual.valor = valor;
                    return;
                }
                if (actual.siguiente == null) break;
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

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
    
    public void limpiar() {
        this.tabla = new Nodo[capacidad]; // Reinicia el array de nodos
        this.tamaño = 0; // Reinicia el tamaño
        System.out.println("La tabla hash ha sido limpiada.");
    }
    

    public boolean containsKey(K clave) {
        return get(clave) != null;
    }

    public void remove(K clave) {
        int indice = hash(clave);
        Nodo<K, V> actual = tabla[indice];
        Nodo<K, V> previo = null;

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (previo == null) {
                    tabla[indice] = actual.siguiente;
                } else {
                    previo.siguiente = actual.siguiente;
                }
                tamaño--;
                return;
            }
            previo = actual;
            actual = actual.siguiente;
        }
    }

    public int size() {
        return tamaño;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

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
