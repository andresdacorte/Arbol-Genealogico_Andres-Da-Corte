/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Clase ArbolGlobal que representa un árbol genealógico global y centralizado.
 * Administra un árbol con una estructura de nodos y un grafo asociado.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 */
public class ArbolGlobal {

    /** Instancia estática del árbol global. */
    
    public static Arbol ArbolGlobal = new Arbol();

    /** Nodo raíz del árbol genealógico. */
    
    public static Nodo raiz;

    /** Mapa personalizado que almacena los nodos del árbol usando claves únicas. */
    
    public static HashMapPersonalizado<String, Nodo> nodos = new HashMapPersonalizado<>();

    /** Grafo asociado para la representación gráfica del árbol. */
    
    private Grafo grafo;

    /**
     * Constructor de la clase.
     * Inicializa un nuevo mapa de nodos y un grafo.
     */
    
    public ArbolGlobal() {
        this.nodos = new HashMapPersonalizado<>();
        this.grafo = new Grafo();
    }

    /**
     * Limpia el árbol global.
     * Reinicia la raíz, limpia el mapa de nodos y elimina todos los nodos y aristas del grafo.
     */
    
    public void limpiarArbol() {
        this.raiz = null; // Reiniciar la raíz
        this.nodos.limpiar(); // Limpiar el mapa de nodos
        this.grafo.limpiarGrafo(); // Limpiar el grafo asociado
        System.out.println("El árbol y el grafo han sido limpiados.");
    }

    /**
     * Obtiene la raíz del árbol genealógico.
     *
     * @return El nodo raíz del árbol, o {@code null} si el árbol está vacío.
     */
    
    public static Nodo getRaiz() {
        return raiz;
    }
}

