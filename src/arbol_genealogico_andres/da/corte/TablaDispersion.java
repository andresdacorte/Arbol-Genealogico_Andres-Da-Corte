/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class TablaDispersion {
    private Nodo[] tabla;
    private int tamano;

    public TablaDispersion(int tamano) {
        this.tamano = tamano;
        tabla = new Nodo[tamano];
    }

    private int hash(String clave) {
        int hashValue = 0;
        for (int i = 0; i < clave.length(); i++) {
            hashValue = (hashValue * 31 + clave.charAt(i)) % tamano;
        }
        return hashValue;
    }

    public void insertar(String clave, Nodo valor) {
        int indice = hash(clave);
        while (tabla[indice] != null) {
            indice = (indice + 1) % tamano;
        }
        tabla[indice] = valor;
    }

    public Nodo buscar(String clave) {
        int indice = hash(clave);
        while (tabla[indice] != null) {
            if (tabla[indice].getNombreCompleto().equals(clave) || (tabla[indice].mote != null && clave != null && tabla[indice].mote.equals(clave))) {
                return tabla[indice];
            }
            indice = (indice + 1) % tamano;
        }
        return null;
    }
}
