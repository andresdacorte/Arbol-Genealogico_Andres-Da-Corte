/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CargarArchivo = new javax.swing.JButton();
        MostrarGrafo = new javax.swing.JButton();
        BuscarPorNombre = new javax.swing.JButton();
        VerRegistro = new javax.swing.JButton();
        MostrarAntepasados = new javax.swing.JButton();
        BuscarPorTitulo = new javax.swing.JButton();
        BuscarGeneracion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CargarArchivo.setText("Cargar Archivo");
        CargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarArchivoActionPerformed(evt);
            }
        });
        getContentPane().add(CargarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 275, 158, -1));

        MostrarGrafo.setText("Mostrar Grafo");
        MostrarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarGrafoActionPerformed(evt);
            }
        });
        getContentPane().add(MostrarGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 53, 180, 39));

        BuscarPorNombre.setText("Buscar por Nombre");
        BuscarPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarPorNombreActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarPorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 158, -1));

        VerRegistro.setText("Ver Registro");
        VerRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(VerRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 98, 180, 39));

        MostrarAntepasados.setText("Mostrar Antepasados");
        MostrarAntepasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarAntepasadosActionPerformed(evt);
            }
        });
        getContentPane().add(MostrarAntepasados, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 234, 158, -1));

        BuscarPorTitulo.setText("Buscar por Titulo");
        BuscarPorTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarPorTituloActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarPorTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 180, 40));

        BuscarGeneracion.setText("Buscar Generación");
        BuscarGeneracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarGeneracionActionPerformed(evt);
            }
        });
        getContentPane().add(BuscarGeneracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 234, 158, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarArchivoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar archivo JSON");
                int resultado = fileChooser.showOpenDialog(null);
                
                ArbolGlobal.ArbolGlobal.limpiarArbol();
                

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    try {
                        // Pasar el archivo JSON al método cargarDesdeJSON
                        ArbolGlobal.ArbolGlobal.cargarDesdeJSON(archivoSeleccionado.getAbsolutePath());
                        JOptionPane.showMessageDialog(null, "Archivo cargado correctamente.");
                        System.out.println("Árbol cargado:");
                        ArbolGlobal.ArbolGlobal.imprimirArbol();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al cargar el archivo JSON: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
    }//GEN-LAST:event_CargarArchivoActionPerformed

    private void MostrarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarGrafoActionPerformed
        try {
        if (ArbolGlobal.ArbolGlobal != null && ArbolGlobal.ArbolGlobal.getRaiz() != null) {
            Grafo grafo = new Grafo(); // Crear instancia de Grafo
            grafo.construirDesdeArbol(ArbolGlobal.ArbolGlobal.getRaiz()); // Construir el grafo a partir de la raíz del árbol
            grafo.mostrarGrafo(); // Mostrar el grafo
        } else {
            JOptionPane.showMessageDialog(this, "El árbol global no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al mostrar el grafo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_MostrarGrafoActionPerformed

    private void BuscarPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarPorNombreActionPerformed
        try {
        if (ArbolGlobal.ArbolGlobal != null && ArbolGlobal.ArbolGlobal.getRaiz() != null) {
            String cadenaBusqueda = JOptionPane.showInputDialog(this, "Introduce el nombre y apellido o mote de la persona:");
            if (cadenaBusqueda == null || cadenaBusqueda.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Buscar nodos que coincidan con la cadena de búsqueda
            Lista<Nodo> resultados = new Lista<>();
            buscarPorNombreOMote(ArbolGlobal.ArbolGlobal.getRaiz(), cadenaBusqueda.trim(), resultados);

            if (resultados.esVacia()) {
                JOptionPane.showMessageDialog(this, "No se encontraron registros que coincidan con la búsqueda.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Construir una lista para mostrar al usuario
            String[] opciones = new String[resultados.tamaño()];
            Nodo[] nodos = new Nodo[resultados.tamaño()];
            int index = 0;

            for (Nodo nodo : resultados) {
                opciones[index] = nodo.getNombre() + " (" + nodo.getPosicion() + ")";
                nodos[index] = nodo;
                index++;
            }

            // Mostrar opciones al usuario
            String seleccionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona un registro:",
                    "Resultados de la Búsqueda",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    null);

            if (seleccionado != null) {
                for (int i = 0; i < opciones.length; i++) {
                    if (opciones[i].equals(seleccionado)) {
                        Nodo nodoSeleccionado = nodos[i];
                        construirArbolDeDescendencia(nodoSeleccionado); // Mostrar el árbol de descendencia gráficamente
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "El árbol global no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BuscarPorNombreActionPerformed

    private void VerRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerRegistroActionPerformed
        try {
        if (ArbolGlobal.ArbolGlobal != null && ArbolGlobal.ArbolGlobal.getRaiz() != null) {
            // Obtener todos los integrantes del árbol
            Lista<Nodo> integrantes = new Lista<>();
            obtenerIntegrantes(ArbolGlobal.ArbolGlobal.getRaiz(), integrantes);

            // Mostrar la lista en un cuadro de diálogo
            String[] nombres = new String[integrantes.tamaño()];
            Nodo[] nodos = new Nodo[integrantes.tamaño()];
            int index = 0;

            for (Nodo nodo : integrantes) {
                nombres[index] = nodo.getNombre() + " (" + nodo.getPosicion() + ")";
                nodos[index] = nodo;
                index++;
            }

            String seleccionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona un integrante:",
                    "Lista de Integrantes",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    nombres,
                    null);

            if (seleccionado != null) {
                for (int i = 0; i < nombres.length; i++) {
                    if (nombres[i].equals(seleccionado)) {
                        mostrarDatosNodo(nodos[i]);
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "El árbol global no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al mostrar los integrantes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_VerRegistroActionPerformed

    private void MostrarAntepasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarAntepasadosActionPerformed
        try {
        if (ArbolGlobal.ArbolGlobal != null && ArbolGlobal.ArbolGlobal.getRaiz() != null) {
            String cadenaBusqueda = JOptionPane.showInputDialog(this, "Introduce el nombre o mote:");
            if (cadenaBusqueda == null || cadenaBusqueda.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Buscar el nodo correspondiente al nombre o mote
            Lista<Nodo> resultados = new Lista<>();
            buscarPorNombreOMote(ArbolGlobal.ArbolGlobal.getRaiz(), cadenaBusqueda.trim(), resultados);

            if (resultados.esVacia()) {
                JOptionPane.showMessageDialog(this, "No se encontraron registros que coincidan con la búsqueda.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Si hay más de un resultado, pedir al usuario que seleccione
            Nodo nodoSeleccionado;
            if (resultados.tamaño() == 1) {
                nodoSeleccionado = resultados.get(0);
            } else {
                String[] opciones = new String[resultados.tamaño()];
                Nodo[] nodos = new Nodo[resultados.tamaño()];
                int index = 0;

                for (Nodo nodo : resultados) {
                    opciones[index] = nodo.getNombre() + " (" + nodo.getPosicion() + ")";
                    nodos[index] = nodo;
                    index++;
                }

                String seleccionado = (String) JOptionPane.showInputDialog(
                        this,
                        "Selecciona un registro:",
                        "Resultados de la Búsqueda",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opciones,
                        null);

                if (seleccionado == null) return;

                nodoSeleccionado = null;
                for (int i = 0; i < opciones.length; i++) {
                    if (opciones[i].equals(seleccionado)) {
                        nodoSeleccionado = nodos[i];
                        break;
                    }
                }
            }

            // Obtener la lista de antepasados
            Lista<Nodo> antepasados = obtenerAntepasados(nodoSeleccionado);

            if (antepasados.esVacia()) {
                JOptionPane.showMessageDialog(this, "El nodo especificado no tiene antepasados.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Mostrar la lista de antepasados en un grafo
            Grafo grafo = new Grafo();
            construirGrafoDeAntepasados(antepasados, grafo);
            grafo.mostrarGrafo();

        } else {
            JOptionPane.showMessageDialog(this, "El árbol global no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_MostrarAntepasadosActionPerformed

    private void BuscarPorTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarPorTituloActionPerformed
        try {
        if (ArbolGlobal.ArbolGlobal != null && ArbolGlobal.ArbolGlobal.getRaiz() != null) {
            String tituloBusqueda = JOptionPane.showInputDialog(this, "Introduce el título nobiliario:");
            if (tituloBusqueda == null || tituloBusqueda.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un título válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Buscar nodos que coincidan con el título
            Lista<Nodo> resultados = new Lista<>();
            buscarPorTitulo(ArbolGlobal.ArbolGlobal.getRaiz(), tituloBusqueda.trim(), resultados);

            if (resultados.esVacia()) {
                JOptionPane.showMessageDialog(this, "No se encontraron registros con el título especificado.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Construir lista de opciones para el usuario
            String[] opciones = new String[resultados.tamaño()];
            Nodo[] nodos = new Nodo[resultados.tamaño()];
            int index = 0;

            for (Nodo nodo : resultados) {
                opciones[index] = nodo.getNombre() + " (" + nodo.getPosicion() + ")";
                nodos[index] = nodo;
                index++;
            }

            // Mostrar opciones al usuario
            String seleccionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona un registro:",
                    "Resultados de la Búsqueda",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    null);

            if (seleccionado == null) return;

            // Mostrar los datos del nodo seleccionado
            for (int i = 0; i < opciones.length; i++) {
                if (opciones[i].equals(seleccionado)) {
                    Nodo nodoSeleccionado = nodos[i];
                    mostrarDatosNodo(nodoSeleccionado);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "El árbol global no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BuscarPorTituloActionPerformed

    private void BuscarGeneracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarGeneracionActionPerformed
        try {
        if (ArbolGlobal.ArbolGlobal != null && ArbolGlobal.ArbolGlobal.getRaiz() != null) {
            // Obtener el número máximo de generaciones en el árbol
            int maxGeneracion = obtenerMaxGeneracion(ArbolGlobal.ArbolGlobal.getRaiz(), 0);

            // Crear opciones de generación para el usuario
            String[] generaciones = new String[maxGeneracion + 1];
            for (int i = 0; i <= maxGeneracion; i++) {
                generaciones[i] = "Generación " + i;
            }

            // Mostrar opciones al usuario
            String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona una generación:",
                    "Lista de Generaciones",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    generaciones,
                    null);

            if (seleccion == null) return;

            // Obtener el número de la generación seleccionada
            int numeroGeneracion = Integer.parseInt(seleccion.replace("Generación ", "").trim());

            // Obtener los integrantes de la generación seleccionada
            Lista<Nodo> integrantes = new Lista<>();
            obtenerIntegrantesDeGeneracion(ArbolGlobal.ArbolGlobal.getRaiz(), numeroGeneracion, 0, integrantes);

            if (integrantes.esVacia()) {
                JOptionPane.showMessageDialog(this, "No se encontraron integrantes en la generación seleccionada.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Mostrar los integrantes al usuario
            StringBuilder listaIntegrantes = new StringBuilder("Integrantes de la generación " + numeroGeneracion + ":\n");
            for (Nodo nodo : integrantes) {
                listaIntegrantes.append(nodo.getNombre()).append(" (").append(nodo.getPosicion()).append(")\n");
            }

            JOptionPane.showMessageDialog(this, listaIntegrantes.toString(), "Integrantes de la Generación", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, "El árbol global no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BuscarGeneracionActionPerformed
    
    private void mostrarDatosNodo(Nodo nodo) {
    if (nodo != null) {
        StringBuilder datos = new StringBuilder();

        datos.append("Nombre: ").append(validarDato(nodo.getNombre())).append("\n");
        datos.append("Posición: ").append(validarDato(nodo.getPosicion())).append("\n");
        datos.append("Mote: ").append(validarDato(nodo.getMote())).append("\n");
        datos.append("Título: ").append(validarDato(nodo.getTitulo())).append("\n");
        datos.append("Ojos: ").append(validarDato(nodo.getOjos())).append("\n");
        datos.append("Cabello: ").append(validarDato(nodo.getCabello())).append("\n");
        datos.append("Nota: ").append(validarDato(nodo.getNota())).append("\n");
        datos.append("Muerte: ").append(validarDato(nodo.getMuerte())).append("\n");

        // Mostrar el padre con nombre y posición entre paréntesis
        if (nodo.getPadre() != null) {
            datos.append("Padre: ").append(validarDato(nodo.getPadre().getNombre()))
                 .append(" (").append(validarDato(nodo.getPadre().getPosicion())).append(")").append("\n");
        } else {
            datos.append("Padre: [Sin Padre]\n");
        }

        // Mostrar los hijos con nombre y posición entre paréntesis
        if (!nodo.getHijos().esVacia()) {
            datos.append("Hijos: ");
            for (Nodo hijo : nodo.getHijos()) {
                datos.append(validarDato(hijo.getNombre())).append(" (")
                     .append(validarDato(hijo.getPosicion())).append("), ");
            }
            datos.setLength(datos.length() - 2); // Eliminar la última coma y espacio
            datos.append("\n");
        } else {
            datos.append("Hijos: [Sin Hijos]\n");
        }

        JOptionPane.showMessageDialog(this, datos.toString(), "Información del Nodo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No hay información disponible para este nodo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private String validarDato(String dato) {
    return (dato == null || dato.isEmpty()) ? "[Sin datos]" : dato;
}




    
    public void obtenerIntegrantes(Nodo nodo, Lista<Nodo> lista) {
    if (nodo == null) return;

    lista.agregar(nodo); // Agregar el nodo actual a la lista

    for (Nodo hijo : nodo.getHijos()) {
        obtenerIntegrantes(hijo, lista); // Recorrer los hijos recursivamente
    }
}
    
    private void buscarPorNombreOMote(Nodo nodo, String cadenaBusqueda, Lista<Nodo> resultados) {
    if (nodo == null) return;

    // Verificar si el nodo coincide con la cadena de búsqueda
    if (nodo.getNombre().equalsIgnoreCase(cadenaBusqueda) || nodo.getMote().equalsIgnoreCase(cadenaBusqueda)) {
        resultados.agregar(nodo);
    }

    // Recorrer los hijos del nodo actual
    for (Nodo hijo : nodo.getHijos()) {
        buscarPorNombreOMote(hijo, cadenaBusqueda, resultados);
    }
}
    
    private void construirArbolDeDescendencia(Nodo nodoSeleccionado) {
    if (nodoSeleccionado == null) return;

    Grafo grafo = new Grafo();
    grafo.construirDesdeArbol(nodoSeleccionado); // Construir solo desde el nodo seleccionado
    grafo.mostrarGrafo(); // Mostrar el grafo
}
    
    private Lista<Nodo> obtenerAntepasados(Nodo nodo) {
    Lista<Nodo> antepasados = new Lista<>();
    Nodo actual = nodo.getPadre();

    while (actual != null) {
        antepasados.agregar(actual);
        actual = actual.getPadre();
    }

    return antepasados;
}
    
    private void construirGrafoDeAntepasados(Lista<Nodo> antepasados, Grafo grafo) {
    Nodo anterior = null;

    for (Nodo actual : antepasados) {
        String claveActual = grafo.generarClave(actual.getNombre(), actual.getPosicion());

        // Agregar nodo actual al grafo
        if (!grafo.hasNode(claveActual)) {
            grafo.agregarNodo(actual, anterior != null ? grafo.generarClave(anterior.getNombre(), anterior.getPosicion()) : null, actual);
        }

        anterior = actual; // Actualizar el nodo anterior
    }
}
    
    private void buscarPorTitulo(Nodo nodo, String tituloBusqueda, Lista<Nodo> resultados) {
    if (nodo == null) return;

    // Verificar si el título coincide
    if (nodo.getTitulo().equalsIgnoreCase(tituloBusqueda)) {
        resultados.agregar(nodo);
    }

    // Recorrer los hijos del nodo actual
    for (Nodo hijo : nodo.getHijos()) {
        buscarPorTitulo(hijo, tituloBusqueda, resultados);
    }
}
    
    private int obtenerMaxGeneracion(Nodo nodo, int nivel) {
    if (nodo == null) return nivel - 1;

    int maxNivel = nivel;
    for (Nodo hijo : nodo.getHijos()) {
        maxNivel = Math.max(maxNivel, obtenerMaxGeneracion(hijo, nivel + 1));
    }

    return maxNivel;
}
    
    private void obtenerIntegrantesDeGeneracion(Nodo nodo, int generacionObjetivo, int nivelActual, Lista<Nodo> resultados) {
    if (nodo == null) return;

    if (nivelActual == generacionObjetivo) {
        resultados.agregar(nodo);
    }

    for (Nodo hijo : nodo.getHijos()) {
        obtenerIntegrantesDeGeneracion(hijo, generacionObjetivo, nivelActual + 1, resultados);
    }
}




    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarGeneracion;
    private javax.swing.JButton BuscarPorNombre;
    private javax.swing.JButton BuscarPorTitulo;
    private javax.swing.JButton CargarArchivo;
    private javax.swing.JButton MostrarAntepasados;
    private javax.swing.JButton MostrarGrafo;
    private javax.swing.JButton VerRegistro;
    // End of variables declaration//GEN-END:variables
}
