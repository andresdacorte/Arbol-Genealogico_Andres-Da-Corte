/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import java.awt.Font;
import java.awt.Color;


class GrafoArbolGenealogico {
     private Graph grafo;

    public GrafoArbolGenealogico() {
        grafo = new SingleGraph("Árbol Genealógico");
        grafo.setAttribute("ui.stylesheet", "node { fill-color: lightblue; size: 20px; text-alignment: above; text-size: 14px; } edge { fill-color: gray; } graph { padding: 50px; }");
    }

    public void mostrarGrafo(Nodo raiz) {
        agregarNodoAlGrafo(raiz);
        grafo.display();
    }

    private void agregarNodoAlGrafo(Nodo nodo) {
        if (nodo == null) return;
        if (grafo.getNode(nodo.getNombreCompleto()) == null) {
            Node n = grafo.addNode(nodo.getNombreCompleto());
            n.setAttribute("ui.label", nodo.getNombreCompleto());
        }
        ListaEnlazada.NodoLista actual = nodo.getHijos().getCabeza();
        while (actual != null) {
            agregarNodoAlGrafo(actual.dato);
            String edgeId = nodo.getNombreCompleto() + "-" + actual.dato.getNombreCompleto();
            if (grafo.getEdge(edgeId) == null) {
                grafo.addEdge(edgeId, nodo.getNombreCompleto(), actual.dato.getNombreCompleto(), true);
            }
            actual = actual.siguiente;
        }
    }
}
