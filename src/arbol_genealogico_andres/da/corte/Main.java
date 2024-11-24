/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Clase principal que inicia la ejecución del programa de Árbol Genealógico.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 */
public class Main {

    /**
     * Método principal que sirve como punto de entrada para la aplicación.
     * 
     * @param args Los argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        // Crear una instancia de la clase Inicio, que representa el menú principal
        Inicio menu = new Inicio();
        
        // Configurar la ventana del menú principal para que aparezca centrada
        menu.setLocationRelativeTo(null);
        
        // Hacer visible la ventana del menú principal
        menu.setVisible(true);
    }
}

