/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.Viewer.CloseFramePolicy;

public class Grafo {
    private Graph grafo;

    public Grafo() {
        grafo = new SingleGraph("Árbol Genealógico");
        grafo.setAttribute("ui.stylesheet", "node { text-size: 15px; }");
    }

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
    
    

    private boolean agregarNodoRecursivo(Nodo actual, String clavePadre, Nodo hijo) {
        if (actual == null) {
            return false; // Nodo no encontrado
        }

        // Verificar si el nodo actual es el padre
        String claveActual = generarClave(actual.getNombre(), actual.getPosicion());
        if (claveActual.equals(clavePadre)) {
            grafo.addNode(generarClave(hijo.getNombre(), hijo.getPosicion()))
                .setAttribute("ui.label", hijo.getNombre());

            grafo.addEdge(claveActual + "->" + generarClave(hijo.getNombre(), hijo.getPosicion()),
                    claveActual, generarClave(hijo.getNombre(), hijo.getPosicion()), true);

            return true;
        }

        // Recursión: Intentar en los hijos del nodo actual
        for (Nodo nodoHijo : actual.getHijos()) {
            if (agregarNodoRecursivo(nodoHijo, clavePadre, hijo)) {
                return true; // Nodo agregado en algún hijo
            }
        }

        return false; // Nodo no encontrado en este subárbol
    }
    
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


    public void imprimir(Nodo raiz) {
        imprimirRecursivo(raiz, 0);
    }

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
    
    public void limpiarGrafo() {
        grafo.clear(); // Limpiar todos los nodos y aristas
        System.out.println("El grafo ha sido limpiado.");
    }
    
    public boolean hasNode(String clave) {
    // Verificar si el nodo ya existe en el grafo
    return grafo.getNode(clave) != null;
    }
    
    public boolean hasEdge(String clave) {
    // Verificar si la arista ya existe en el grafo
    return grafo.getEdge(clave) != null;
}



    public String generarClave(String nombre, String posicion) {
        if (nombre == null || posicion == null || nombre.isEmpty() || posicion.isEmpty()) {
            throw new IllegalArgumentException("Nombre o posición inválidos para generar clave.");
        }
        return nombre.trim() + "-" + posicion.trim();
    }

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



    
    public void configurarTitulo(String titulo) {
    grafo.setAttribute("ui.title", titulo);
}

}


