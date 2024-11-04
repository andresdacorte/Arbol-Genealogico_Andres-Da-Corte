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

public class CargarArchivoJSON {
    public static void cargarDesdeJSON(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder jsonContent = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                jsonContent.append(linea);
            }
            JSONObject jsonObj = new JSONObject(jsonContent.toString());
            String nombreCasa = jsonObj.keys().next();
            JSONArray integrantes = jsonObj.getJSONArray(nombreCasa);

            TablaDispersion tablaDispersion = new TablaDispersion(500);
            ListaEnlazada nodosTemporales = new ListaEnlazada();

            // Primera pasada: Crear todos los nodos sin preocuparse por las relaciones
            for (int i = 0; i < integrantes.length(); i++) {
                JSONObject integrante = integrantes.getJSONObject(i);
                String nombreCompleto = integrante.keys().next();
                JSONArray detalles = integrante.getJSONArray(nombreCompleto);

                String mote = null;
                String titulo = null;
                String nombreConNumeral = nombreCompleto;

                for (int j = 0; j < detalles.length(); j++) {
                    JSONObject detalle = detalles.getJSONObject(j);
                    if (detalle.has("Of his name")) {
                        String numeral = detalle.getString("Of his name");
                        nombreConNumeral = nombreCompleto + ", " + numeral;
                    } else if (detalle.has("Known throughout as")) {
                        mote = detalle.getString("Known throughout as");
                    } else if (detalle.has("Held title")) {
                        titulo = detalle.getString("Held title");
                    }
                }

                Nodo nodo = new Nodo(nombreConNumeral, titulo, mote);
                nodosTemporales.agregar(nodo);
                if (mote != null) {
                    nodosTemporales.agregar(new Nodo(mote, titulo, mote));
                }
            }

            // Segunda pasada: Insertar nodos en la tabla de dispersión
            ListaEnlazada.NodoLista actual = nodosTemporales.getCabeza();
            while (actual != null) {
                tablaDispersion.insertar(actual.dato.getNombreCompleto(), actual.dato);
                actual = actual.siguiente;
            }

            // Tercera pasada: Establecer las relaciones de padres e hijos
            for (int i = 0; i < integrantes.length(); i++) {
                JSONObject integrante = integrantes.getJSONObject(i);
                String nombreCompleto = integrante.keys().next();
                JSONArray detalles = integrante.getJSONArray(nombreCompleto);

                String nombreConNumeral = nombreCompleto;
                String padreNombre = null;
                ListaEnlazada hijos = new ListaEnlazada();

                for (int j = 0; j < detalles.length(); j++) {
                    JSONObject detalle = detalles.getJSONObject(j);
                    if (detalle.has("Of his name")) {
                        String numeral = detalle.getString("Of his name");
                        nombreConNumeral = nombreCompleto + ", " + numeral;
                    } else if (detalle.has("Born to")) {
                        if (padreNombre == null) {
                            padreNombre = detalle.getString("Born to");
                        }
                    } else if (detalle.has("Father to")) {
                        JSONArray hijosArray = detalle.getJSONArray("Father to");
                        for (int k = 0; k < hijosArray.length(); k++) {
                            String nombreHijo = hijosArray.getString(k);
                            String nombreHijoConNumeral = buscarNombreCompletoConNumeral(nombreHijo, integrantes);
                            Nodo hijo = tablaDispersion.buscar(nombreHijoConNumeral);
                            if (hijo != null) {
                                hijos.agregar(hijo);
                            }
                        }
                    }
                }

                Nodo nodo = tablaDispersion.buscar(nombreConNumeral);
                if (nodo != null) {
                    nodo.hijos = hijos;
                    if (padreNombre != null && !padreNombre.equals("[Unknown]")) {
                        String padreNombreConNumeral = buscarNombreCompletoConNumeral(padreNombre, integrantes);
                        Nodo padre = tablaDispersion.buscar(padreNombreConNumeral);
                        if (padre != null) {
                            padre.agregarHijo(nodo);
                            nodo.setPadre(padre);
                        }
                    }
                }
            }

            // Establecer la raíz del árbol (asumiendo que el primer nodo sin padre es la raíz)
            Nodo raiz = null;
            actual = nodosTemporales.getCabeza();
            while (actual != null) {
                if (actual.dato.getPadre() == null) {
                    raiz = actual.dato;
                    break;
                }
                actual = actual.siguiente;
            }

            Arbol arbolGenealogico = new Arbol(raiz);
            ArbolGlobal.setArbolGenealogicoGlobal(arbolGenealogico);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para buscar el nombre completo con numeral en los detalles del JSON
    private static String buscarNombreCompletoConNumeral(String nombre, JSONArray integrantes) {
        for (int i = 0; i < integrantes.length(); i++) {
            JSONObject integrante = integrantes.getJSONObject(i);
            String nombreCompleto = integrante.keys().next();
            JSONArray detalles = integrante.getJSONArray(nombreCompleto);
            String nombreConNumeral = nombreCompleto;
            for (int j = 0; j < detalles.length(); j++) {
                JSONObject detalle = detalles.getJSONObject(j);
                if (detalle.has("Of his name")) {
                    String numeral = detalle.getString("Of his name");
                    nombreConNumeral = nombreCompleto + ", " + numeral;
                }
            }
            if (nombre.equals(nombreCompleto)) {
                return nombreConNumeral;
            }
        }
        return nombre;
    }
}


