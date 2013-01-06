

/**
 *
 * @author mauricio
 */
public class EstacionamientoNormal {
    
    private Espacio espacios[];    // HEAP implementado con un arreglo
    int ultimo;                    // apunta al ultimo ocupado
    int pisos;
    int maxEst;
    
    public EstacionamientoNormal(int cantidadEspacios, int cantidadPisos, int maxEst) {
        this.espacios = new Espacio[cantidadEspacios+1]; // uno mas por la raiz
        this.espacios[0] = new Espacio (Integer.MIN_VALUE);  // nodo raiz: con la menor prioridad posible
        this.ultimo = 0;
        this.pisos = cantidadPisos;
        this.maxEst = maxEst;
    }
    
    public void agregarEspacioDisponible(Espacio espacio) {
        this.ultimo += 1;
        int aux = ultimo;
        int padre = aux/2;
        while(espacio.prioridad < espacios[padre].prioridad) {
            espacios[aux] = espacios[padre];
            aux = padre;
            padre = aux/2;
        }
        espacios[aux] = espacio;
    }
    
    public Espacio extraerEspacioMasCercano() {
        if(this.ultimo == 0) return null;
        
        if(this.ultimo == 1) {
            Espacio aux = this.espacios[1];
            this.ultimo -= 1;
            return aux;
        }
        
        Espacio aux = this.espacios[1];
        int vacio = 1;
        int menor;
        
        // parte 1: bajamos el vacio
        while(true) {
            int hijoIzquierdo = 2*vacio;
            int hijoDerecho = hijoIzquierdo+1;
            if(hijoIzquierdo > ultimo) break;
            if(hijoIzquierdo == ultimo || this.espacios[hijoIzquierdo].prioridad < this.espacios[hijoDerecho].prioridad) {
                menor = hijoIzquierdo;
            }
            else {
                menor = hijoDerecho;
            }
            this.espacios[vacio] = this.espacios[menor];
            vacio = menor;
        }
        
        // parte 2: recuperar la condicion de heap. rellenar el vacio.
        while(true) {
            int padre = vacio/2;
            if(this.espacios[ultimo].prioridad < this.espacios[padre].prioridad) {
                this.espacios[vacio] = this.espacios[padre];
                vacio = padre;
            }
            else {
                this.espacios[vacio] = this.espacios[ultimo];
                ultimo -= 1;
                break;
            }
        }
        return aux;
    }
    
    public Espacio minimo() {
        return this.espacios[1];
    }

    void prepararEstacionamiento( int primerNormal) {  // llena el HEAP
        for(int numero = primerNormal; numero <= this.maxEst; numero++){
            agregarEspacioDisponible (new Espacio (-1, numero, this.maxEst));  // primer piso (con especiales)
        }
        for(int piso = (-2); piso >= (this.pisos*-1); piso--) { // desde el -2 hasta el ultimo piso
            for(int numero = 1; numero <= this.maxEst; numero++){
                agregarEspacioDisponible (new Espacio (piso, numero, this.maxEst));  // resto de pisos
            }
        }
    }
    
    private int calcularPrioridad( int piso, int numero ) {
        return ((piso*-1)-1)*this.pisos + numero;  // prioridad en funcion de piso y numero
    }
    
 
}
