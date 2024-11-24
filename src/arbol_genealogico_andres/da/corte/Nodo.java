/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 * Clase Nodo que representa un nodo en el árbol genealógico.
 * 
 * Cada nodo contiene información personal como nombre, posición, mote, título,
 * características físicas, información del padre, hijos, y detalles adicionales como notas o muerte.
 * 
 * @author Andres Da Corte
 * @since 2024-11-24
 */

public class Nodo {
    private String valor;
    private String nombre;
    private String posicion;
    private String mote;
    private String titulo;
    private String ojos;
    private String cabello;
    private Nodo padre;
    private Lista<Nodo> hijos;
    private String nota;
    private String muerte;

    /**
     * Constructor de la clase Nodo.
     * 
     * @param valor Identificador único del nodo.
     * @param nombre Nombre del integrante del árbol.
     * @param posicion Posición dentro del linaje (e.g., "First", "Second").
     * @param mote Mote único del integrante.
     * @param titulo Título nobiliario del integrante.
     * @param ojos Color de ojos del integrante.
     * @param cabello Color de cabello del integrante.
     * @param padre Nodo que representa al padre del integrante.
     * @param hijos Lista de nodos hijos asociados a este nodo.
     * @param nota Información adicional o notas sobre el integrante.
     * @param muerte Información sobre la muerte del integrante.
     */
    
    public Nodo(String valor, String nombre, String posicion, String mote, String titulo, String ojos, String cabello, Nodo padre, Lista<Nodo> hijos, String nota, String muerte) {
        this.valor = valor;
        this.nombre = nombre;
        this.posicion = posicion;
        this.mote = mote;
        this.titulo = titulo;
        this.ojos = ojos;
        this.cabello = cabello;
        this.padre = null;
        this.hijos = new Lista<>();
        this.nota = nota;
        this.muerte = muerte;
    }

    /**
     * Obtiene el nombre del nodo.
     * 
     * @return Nombre del nodo.
     */
    
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el valor del nodo.
     * 
     * @return Valor único del nodo.
     */
    
    public String getValor() {
        return valor;
    }

    /**
     * Establece el valor del nodo.
     * 
     * @param valor Nuevo valor del nodo.
     */
    
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el nodo padre.
     * 
     * @return Nodo que representa al padre.
     */
    
    public Nodo getPadre() {
        return padre;
    }

    /**
     * Establece el nodo padre.
     * 
     * @param padre Nodo que representa al padre.
     */
    
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    /**
     * Obtiene la lista de nodos hijos.
     * 
     * @return Lista de hijos.
     */
    
    public Lista<Nodo> getHijos() {
        return hijos;
    }

    /**
     * Obtiene el mote del nodo.
     * 
     * @return Mote del nodo.
     */
    
    public String getMote() {
        return mote;
    }

    /**
     * Obtiene el título nobiliario del nodo.
     * 
     * @return Título del nodo.
     */
    
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el color de ojos del nodo.
     * 
     * @return Color de ojos del nodo.
     */
    
    public String getOjos() {
        return ojos;
    }

    /**
     * Obtiene el color de cabello del nodo.
     * 
     * @return Color de cabello del nodo.
     */
    
    public String getCabello() {
        return cabello;
    }

    /**
     * Obtiene las notas adicionales del nodo.
     * 
     * @return Notas del nodo.
     */
    
    public String getNota() {
        return nota;
    }

    /**
     * Obtiene la información de muerte del nodo.
     * 
     * @return Información sobre la muerte.
     */
    
    public String getMuerte() {
        return muerte;
    }

    /**
     * Obtiene la posición del nodo en el linaje.
     * 
     * @return Posición del nodo.
     */
    
    public String getPosicion() {
        return posicion;
    }

    /**
     * Agrega un nodo hijo a la lista de hijos.
     * 
     * @param hijo Nodo hijo a agregar.
     * @throws IllegalStateException Si se detecta un ciclo al intentar agregar un ancestro como hijo.
     */
    
    public void agregarHijo(Nodo hijo) {
        if (esAncestro(hijo)) {
            throw new IllegalStateException("Ciclo detectado: No se puede agregar un ancestro como hijo.");
        }
        hijo.setPadre(this);
        hijos.agregar(hijo);
    }

    /**
     * Verifica si el nodo proporcionado es un ancestro de este nodo.
     * 
     * @param posibleAncestro Nodo que se verificará como ancestro.
     * @return {@code true} si el nodo es un ancestro; {@code false} en caso contrario.
     */
    
    private boolean esAncestro(Nodo posibleAncestro) {
        Nodo actual = this;
        while (actual != null) {
            if (actual == posibleAncestro) {
                return true;
            }
            actual = actual.getPadre();
        }
        return false;
    }

    /**
     * Elimina un nodo hijo de la lista de hijos.
     * 
     * @param hijo Nodo hijo a eliminar.
     * @return {@code true} si el hijo fue eliminado; {@code false} en caso contrario.
     */
    
    public boolean eliminarHijo(Nodo hijo) {
        return hijos.eliminar(hijo);
    }
}
