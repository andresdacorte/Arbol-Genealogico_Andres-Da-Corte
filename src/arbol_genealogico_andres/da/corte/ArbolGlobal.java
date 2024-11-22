/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
public class ArbolGlobal {
    
    public static Arbol ArbolGlobal = new Arbol();
    
    public static Nodo raiz; // Raíz del árbol genealógico
    public static HashMapPersonalizado<String, Nodo> nodos = new HashMapPersonalizado<>();
    private Grafo grafo;
    
    public ArbolGlobal() {
        this.nodos = new HashMapPersonalizado<>();
        this.grafo = new Grafo();
    }
    
    public void limpiarArbol() {
        this.raiz = null; // Reiniciar la raíz
        this.nodos.limpiar(); // Limpiar el mapa de nodos
        this.grafo.limpiarGrafo(); // Limpiar el grafo asociado
        System.out.println("El árbol y el grafo han sido limpiados.");
    }
    

    

    public static Nodo getRaiz() {
        return raiz;
    }
    
}
