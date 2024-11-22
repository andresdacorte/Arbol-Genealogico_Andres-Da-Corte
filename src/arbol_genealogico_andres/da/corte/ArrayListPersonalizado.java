/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class ArrayListPersonalizado<T> {
    private Object[] elementos;
    private int tamaño;

    public ArrayListPersonalizado() {
        elementos = new Object[10];
        tamaño = 0;
    }

    public void agregar(T elemento) {
        if (tamaño == elementos.length) {
            redimensionar();
        }
        elementos[tamaño++] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        return (T) elementos[indice];
    }

    public int tamaño() {
        return tamaño;
    }

    private void redimensionar() {
        Object[] nuevoArray = new Object[elementos.length * 2];
        System.arraycopy(elementos, 0, nuevoArray, 0, elementos.length);
        elementos = nuevoArray;
    }
}
