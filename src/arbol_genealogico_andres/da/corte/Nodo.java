/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol_genealogico_andres.da.corte;

/**
 *
 * @author dacor
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
    
    public Nodo(String valor, String nombre, String posicion, String mote, String titulo, String ojos, String cabello, Nodo padre, Lista<Nodo> hijos, String nota, String muerte){
        
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
    
    public String getNombre() {
        return nombre;
    }
    
    public String getValor() {
        return valor;
    }
    
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Lista<Nodo> getHijos() {
        return hijos;
    }
    
    public String getMote(){
        return mote;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public String getOjos(){
        return ojos;
    }
    
    public String getCabello(){
        return cabello;
    }
    
    public String getNota(){
        return nota;
    }
    
    public String getMuerte(){
        return muerte;
    }
    
    public String getPosicion(){
        return posicion;
    }

    public void agregarHijo(Nodo hijo) {
    if (esAncestro(hijo)) {
        throw new IllegalStateException("Ciclo detectado: No se puede agregar un ancestro como hijo.");
    }
    hijo.setPadre(this);
    hijos.agregar(hijo);
}

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


    public boolean eliminarHijo(Nodo hijo) {
        return hijos.eliminar(hijo);
    }
    
    
}
