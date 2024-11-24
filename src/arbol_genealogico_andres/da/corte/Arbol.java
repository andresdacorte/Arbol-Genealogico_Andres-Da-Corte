/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

import com.google.gson.*;
import java.io.*;

/**
 * Clase de tipo Arbol utilizada como la estructura principal para representar un árbol genealógico.
 * Administra nodos y relaciones jerárquicas, con soporte para operaciones de agregar, eliminar, buscar,
 * y cargar desde un archivo JSON.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 */

public class Arbol {
    public Nodo raiz;
    private HashTable<String, Nodo> nodos;
    
    /**
     * Constructor de la clase.
     * Inicializa el árbol con una tabla hash de capacidad inicial fija.
     */
    
    public Arbol() {
        this.raiz = null;
        this.nodos = new HashTable<>(50); // Tamaño inicial de la tabla hash
    }
    
     /**
     * Agrega la raíz del árbol.
     * Verifica si ya existe una raíz y, si no, agrega una nueva raíz con clave generada a partir del nombre y la posición.
     *
     * @param valor Clave única del nodo raíz.
     * @param nombre Nombre del nodo raíz.
     * @param posicion Posición o numeral del nodo raíz.
     * @param mote Mote del nodo raíz.
     * @param titulo Título nobiliario del nodo raíz.
     * @param ojos Color de ojos del nodo raíz.
     * @param cabello Color de cabello del nodo raíz.
     * @param nota Nota asociada al nodo raíz.
     * @param muerte Fecha o causa de muerte del nodo raíz.
     * @throws IllegalStateException Si ya existe una raíz en el árbol.
     */

    public void agregarRaiz(String valor, String nombre, String posicion, String mote, String titulo, String ojos, String cabello, String nota, String muerte) {
        if (raiz == null) {
            raiz = new Nodo(valor, nombre, posicion, mote, titulo, ojos, cabello, null, new Lista<>(), nota, muerte);
            String clave = generarClave(nombre, posicion);
            nodos.put(clave, raiz); // Usa nombre+posicion como clave única
        } else {
            throw new IllegalStateException("El árbol ya tiene una raíz.");
        }
    }
    
    /**
     * Agrega un nodo hijo a un nodo padre existente en el árbol.
     *
     * @param nombrePadre Nombre del nodo padre.
     * @param posicionPadre Posición del nodo padre.
     * @param valor Clave única del nodo hijo.
     * @param nombre Nombre del nodo hijo.
     * @param posicion Posición o numeral del nodo hijo.
     * @param mote Mote del nodo hijo.
     * @param titulo Título nobiliario del nodo hijo.
     * @param ojos Color de ojos del nodo hijo.
     * @param cabello Color de cabello del nodo hijo.
     * @param nota Nota asociada al nodo hijo.
     * @param muerte Fecha o causa de muerte del nodo hijo.
     * @throws IllegalArgumentException Si el nodo padre no existe o ya existe un nodo con la misma clave.
     */

    public void agregarNodo(String nombrePadre, String posicionPadre, String valor, String nombre, String posicion, String mote, String titulo, String ojos, String cabello, String nota, String muerte) {
        String clavePadre = generarClave(nombrePadre, posicionPadre);
        Nodo padre = nodos.get(clavePadre);
        if (padre == null) {
            throw new IllegalArgumentException("El nodo padre con clave " + clavePadre + " no existe.");
        }

        String clave = generarClave(nombre, posicion);
        if (nodos.get(clave) != null) {
            throw new IllegalArgumentException("Ya existe un nodo con la clave " + clave + ".");
        }

        Nodo nuevoNodo = new Nodo(valor, nombre, posicion, mote, titulo, ojos, cabello, null, new Lista<>(), nota, muerte);
        padre.agregarHijo(nuevoNodo);
        nodos.put(clave, nuevoNodo);
    }
    
    /**
     * Busca un nodo específico en el árbol utilizando su clave única.
     *
     * @param nombre Nombre del nodo a buscar.
     * @param posicion Posición del nodo a buscar.
     * @return El nodo encontrado, o {@code null} si no existe.
     */

    public Nodo buscarNodo(String nombre, String posicion) {
        String clave = generarClave(nombre, posicion);
        return nodos.get(clave);
    }
    
    /**
     * Elimina un nodo específico del árbol.
     * Si el nodo tiene descendientes, estos también se eliminan.
     *
     * @param nombre Nombre del nodo a eliminar.
     * @param posicion Posición del nodo a eliminar.
     * @throws IllegalArgumentException Si el nodo no existe.
     */

    public void eliminarNodo(String nombre, String posicion) {
        String clave = generarClave(nombre, posicion);
        Nodo nodo = nodos.get(clave);
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo con clave " + clave + " no existe.");
        }

        // Si el nodo tiene padre, eliminarlo de los hijos del padre
        if (nodo.getPadre() != null) {
            nodo.getPadre().eliminarHijo(nodo);
        }

        // Eliminar el nodo y sus descendientes de la tabla hash
        eliminarRecursivo(nodo);
    }
    
    /**
     * Elimina un nodo y todos sus descendientes del árbol.
     *
     * @param nodo Nodo a eliminar.
     */

    private void eliminarRecursivo(Nodo nodo) {
        Lista<Nodo> hijos = nodo.getHijos();
        for (int i = 0; i < hijos.tamaño(); i++) {
            Nodo hijo = hijos.obtener(i);
            eliminarRecursivo(hijo);
        }
        String clave = generarClave(nodo.getNombre(), nodo.getPosicion());
        nodos.remove(clave);
    }
    
    /**
     * Imprime el árbol en la consola, mostrando su jerarquía (solo como confirmacion).
     */

    public void imprimirArbol() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(Nodo actual, int nivel) {
    if (actual == null) {
        return; // No hay nada que imprimir
    }

    // Imprimir el nodo actual con sangría para indicar la jerarquía
    String sangria = " ".repeat(nivel * 2); // Dos espacios por nivel
    System.out.println(sangria + actual.getNombre() + " (" + actual.getPosicion() + ")");

    // Recorrer los hijos del nodo actual
    for (Nodo nodoHijo : actual.getHijos()) {
        imprimirRecursivo(nodoHijo, nivel + 1); // Aumentar el nivel para los hijos
    }
    }
    
    /**
     * Carga un árbol desde un archivo JSON.
     * Extrae datos de genealogías y los almacena en la estructura del árbol.
     *
     * @param archivoJSON Ruta del archivo JSON.
     * @throws RuntimeException Si ocurre un error durante la carga.
     */

    
    public void cargarDesdeJSON(String archivoJSON) {
    try {
        // Leer el archivo JSON
        Gson gson = new Gson();
        Reader reader = new FileReader(archivoJSON);
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        // Iterar sobre las casas (por ejemplo, "House Targaryen", "House Baratheon")
        for (String casa : jsonObject.keySet()) {
            JsonArray genealogia = jsonObject.getAsJsonArray(casa);
            System.out.println("Cargando genealogía de: " + casa);

            // Primera pasada: Crear todos los nodos
            for (JsonElement elemento : genealogia) {
                JsonObject personaJSON = elemento.getAsJsonObject();
                String nombre = personaJSON.entrySet().iterator().next().getKey(); // El nombre está en la clave
                JsonArray atributos = personaJSON.getAsJsonArray(nombre);

                // Extraer atributos
                String posicion = obtenerAtributo(atributos, "Of his name");
                String mote = obtenerAtributo(atributos, "Known throughout as");
                String titulo = obtenerAtributo(atributos, "Held title");
                String ojos = obtenerAtributo(atributos, "Of eyes");
                String cabello = obtenerAtributo(atributos, "Of hair");
                String nota = obtenerAtributo(atributos, "Notes");
                String muerte = obtenerAtributo(atributos, "Fate");

                // Generar clave única
                String clave = generarClave(nombre, posicion);

                // Verificar si la clave ya existe
                if (nodos.containsKey(clave)) {
                    System.err.println("Error: Clave duplicada encontrada: " + clave);
                    continue; // Ignorar nodos duplicados
                }

                // Crear el nodo y almacenarlo
                Nodo nodo = new Nodo("", nombre, posicion, mote, titulo, ojos, cabello, null, new Lista<>(), nota, muerte);
                nodos.put(clave, nodo);
            }

            // Segunda pasada: Establecer relaciones
            for (JsonElement elemento : genealogia) {
                JsonObject personaJSON = elemento.getAsJsonObject();
                String nombre = personaJSON.entrySet().iterator().next().getKey();
                JsonArray atributos = personaJSON.getAsJsonArray(nombre);

                // Extraer atributos
                String posicion = obtenerAtributo(atributos, "Of his name");
                String bornTo = obtenerAtributo(atributos, "Born to");

                String claveActual = generarClave(nombre, posicion);
                Nodo nodoActual = nodos.get(claveActual);

                // Si es raíz
                if (bornTo.equals("[Unknown]")) {
                    if (raiz != null) {
                        throw new IllegalStateException("Más de una raíz encontrada.");
                    }
                    raiz = nodoActual;
                    continue;
                }

                // Buscar al padre
                Nodo padre = null;

                // 1. Intentar buscar por mote
                for (Nodo posiblePadre : nodos.values()) {
                    if (posiblePadre.getMote().equalsIgnoreCase(bornTo)) {
                        padre = posiblePadre;
                        break;
                    }
                }

                // 2. Intentar buscar por nombre completo y posición
                if (padre == null && bornTo.contains("of his name")) {
                    String[] partes = bornTo.split(", ");
                    if (partes.length == 2) {
                        String nombrePadre = partes[0].trim();
                        String posicionPadre = partes[1].replace("of his name", "").trim();
                        String clavePadre = generarClave(nombrePadre, posicionPadre);
                        padre = nodos.get(clavePadre);
                    }
                }

                // Establecer la relación si se encontró al padre
                if (padre != null) {
                    padre.agregarHijo(nodoActual);
                } else {
                    System.err.println("No se encontró padre para: " + claveActual + " (Born to: " + bornTo + ")");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error al cargar el archivo JSON: " + e.getMessage());
    }
    }
    
    /**
     * Método auxiliar para extraer un atributo de un nodo en formato JSON.
     *
     * @param atributos Lista de atributos del nodo en formato JSON.
     * @param clave Clave del atributo a extraer.
     * @return El valor del atributo como cadena, o una cadena vacía si no existe.
     */

    private String obtenerAtributo(JsonArray atributos, String clave) {
        for (JsonElement atributo : atributos) {
            JsonObject obj = atributo.getAsJsonObject();
            if (obj.has(clave)) {
                return obj.get(clave).getAsString();
            }
        }
        return ""; // Si no existe el atributo, devolver una cadena vacía
    }
    
    /**
     * Genera una clave única combinando el nombre y la posición de un nodo.
     *
     * @param nombre Nombre del nodo.
     * @param posicion Posición del nodo.
     * @return Clave única generada.
     * @throws IllegalArgumentException Si el nombre o la posición son inválidos.
     */

    private String generarClave(String nombre, String posicion) {
        if (nombre == null || posicion == null || nombre.isEmpty() || posicion.isEmpty()) {
            throw new IllegalArgumentException("Nombre o posición inválidos para generar clave.");
        }
        return nombre.trim() + "-" + posicion.trim(); // Combina nombre y posición como clave única
    }

    /**
     * Limpia todo el árbol, incluyendo la raíz y el mapa de nodos.
     */

    public void limpiarArbol() {
        this.raiz = null; // Reiniciar la raíz
        this.nodos.limpiar(); // Limpiar el mapa de nodos
        System.out.println("El árbol y el grafo han sido limpiados.");
    }

    /**
     * Obtiene todos los nodos integrantes de un árbol, incluyendo descendientes.
     *
     * @param nodo Nodo inicial.
     * @param lista Lista donde se almacenarán los nodos.
     */

    public void obtenerIntegrantes(Nodo nodo, Lista<Nodo> lista) {
        if (nodo == null) return;

        lista.agregar(nodo); // Agregar el nodo actual a la lista

        for (Nodo hijo : nodo.getHijos()) {
        obtenerIntegrantes(hijo, lista); // Recorrer los hijos recursivamente
        }
    }

    /**
     * Devuelve la raíz del árbol.
     *
     * @return Nodo raíz del árbol.
     */

    public Nodo getRaiz() {
        return raiz; // Devuelve la raíz del árbol
    }

    }