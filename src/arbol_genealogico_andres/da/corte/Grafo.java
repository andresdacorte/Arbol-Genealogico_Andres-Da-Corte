/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Clase de tipo GraphStream para la implementacion del Grafo.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 */

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Grafo {
    private Graph grafo;

    /**
     * Constructor de la clase Grafo.
     * Inicializa un grafo con el título "Árbol Genealógico" y aplica un estilo predeterminado.
     */
    
    public Grafo() {
        grafo = new SingleGraph("Árbol Genealógico");
        grafo.setAttribute("ui.stylesheet", "node { text-size: 15px; }");
    }

    /**
     * Agrega un nodo al grafo junto con su relación padre-hijo.
     * Si el nodo ya existe, no lo agrega nuevamente.
     *
     * @param nodo       El nodo actual a agregar.
     * @param clavePadre La clave del nodo padre, si existe.
     * @param hijo       El nodo hijo que será asociado al nodo actual.
     */
    
    public void agregarNodo(Nodo nodo, String clavePadre, Nodo hijo) {
        String claveNodo = generarClave(nodo.getNombre(), nodo.getPosicion());

        // Agregar nodo si no existe
        if (!hasNode(claveNodo)) {
            grafo.addNode(claveNodo).setAttribute("ui.label", nodo.getNombre() + " (" + nodo.getPosicion() + ")");
        }

        // Agregar arista si tiene padre
        if (clavePadre != null) {
            String claveArista = clavePadre + "->" + claveNodo;
            if (!hasEdge(claveArista)) {
                grafo.addEdge(claveArista, clavePadre, claveNodo, true);
            }
        }
    }

    /**
     * Construye el grafo a partir de la raíz de un árbol genealógico.
     *
     * @param raiz La raíz del árbol genealógico desde donde se construirá el grafo.
     * @throws IllegalArgumentException Si la raíz proporcionada es nula.
     */
    
    public void construirDesdeArbol(Nodo raiz) {
        if (raiz == null) {
            throw new IllegalArgumentException("La raíz del árbol no puede ser nula.");
        }

        // Agregar el nodo raíz al grafo con etiqueta personalizada
        grafo.addNode(generarClave(raiz.getNombre(), raiz.getPosicion()))
            .setAttribute("ui.label", raiz.getNombre() + " (" + raiz.getPosicion() + ")");

        // Construir recursivamente para los hijos
        for (Nodo hijo : raiz.getHijos()) {
            construirDesdeArbolRecursivo(raiz, hijo);
        }
    }

    /**
     * Método recursivo para construir el grafo a partir de los hijos de un nodo.
     *
     * @param padre El nodo padre en el grafo.
     * @param hijo  El nodo hijo que se agregará al grafo.
     */
    
    private void construirDesdeArbolRecursivo(Nodo padre, Nodo hijo) {
        if (hijo == null) {
            return;
        }

        // Agregar el nodo hijo al grafo con etiqueta personalizada
        grafo.addNode(generarClave(hijo.getNombre(), hijo.getPosicion()))
            .setAttribute("ui.label", hijo.getNombre() + " (" + hijo.getPosicion() + ")");

        // Crear la arista entre el padre y el hijo
        String clavePadre = generarClave(padre.getNombre(), padre.getPosicion());
        String claveHijo = generarClave(hijo.getNombre(), hijo.getPosicion());
        grafo.addEdge(clavePadre + "->" + claveHijo, clavePadre, claveHijo, true);

        // Llamada recursiva para los hijos del nodo actual
        for (Nodo nieto : hijo.getHijos()) {
            construirDesdeArbolRecursivo(hijo, nieto);
        }
    }

    /**
     * Imprime el árbol en forma jerárquica comenzando desde la raíz.
     *
     * @param raiz El nodo raíz del árbol.
     */
    
    public void imprimir(Nodo raiz) {
        imprimirRecursivo(raiz, 0);
    }

    /**
     * Método recursivo para imprimir los nodos del árbol con sangría según el nivel.
     *
     * @param actual El nodo actual a imprimir.
     * @param nivel  El nivel jerárquico del nodo en el árbol.
     */
    
    private void imprimirRecursivo(Nodo actual, int nivel) {
        if (actual == null) {
            return; // Nada que imprimir
        }

        String sangria = " ".repeat(nivel * 2);
        System.out.println(sangria + actual.getNombre() + " (" + actual.getPosicion() + ")");

        for (Nodo nodoHijo : actual.getHijos()) {
            imprimirRecursivo(nodoHijo, nivel + 1);
        }
    }

    /**
     * Limpia todos los nodos y aristas del grafo.
     */
    
    public void limpiarGrafo() {
        grafo.clear(); // Limpiar todos los nodos y aristas
        System.out.println("El grafo ha sido limpiado.");
    }

    /**
     * Verifica si un nodo con la clave especificada ya existe en el grafo.
     *
     * @param clave La clave del nodo a verificar.
     * @return true si el nodo existe, false en caso contrario.
     */
    
    public boolean hasNode(String clave) {
        return grafo.getNode(clave) != null;
    }

    /**
     * Verifica si una arista con la clave especificada ya existe en el grafo.
     *
     * @param clave La clave de la arista a verificar.
     * @return true si la arista existe, false en caso contrario.
     */
    
    public boolean hasEdge(String clave) {
        return grafo.getEdge(clave) != null;
    }

    /**
     * Genera una clave única combinando el nombre y la posición del nodo.
     *
     * @param nombre   El nombre del nodo.
     * @param posicion La posición del nodo (e.g., "First", "Second").
     * @return Una cadena única que representa la clave del nodo.
     * @throws IllegalArgumentException Si el nombre o la posición son nulos o vacíos.
     */
    
    public String generarClave(String nombre, String posicion) {
        if (nombre == null || posicion == null || nombre.isEmpty() || posicion.isEmpty()) {
            throw new IllegalArgumentException("Nombre o posición inválidos para generar clave.");
        }
        return nombre.trim() + "-" + posicion.trim();
    }

    /**
     * Muestra el grafo en una ventana gráfica.
     * Si el grafo está vacío, emite un mensaje de error.
     */
    
    public void mostrarGrafo() {
        if (grafo.getNodeCount() == 0) {
            System.err.println("El grafo está vacío. Asegúrate de construir el grafo antes de mostrarlo.");
            return;
        }

        // Mostrar el grafo en una ventana
        Viewer viewer = grafo.display();

        // Configurar la política al cerrar la ventana
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

        // Verificar y mostrar el título
        if (grafo.hasAttribute("ui.title")) {
            System.out.println("Título del Grafo: " + grafo.getAttribute("ui.title"));
        }
    }

    /**
     * Configura el título del grafo.
     *
     * @param titulo El título a establecer en el grafo.
     */
    
    public void configurarTitulo(String titulo) {
        grafo.setAttribute("ui.title", titulo);
    }
}



