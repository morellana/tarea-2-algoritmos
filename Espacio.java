/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
class Espacio {
    
    int piso;
    int numero;
    int prioridad;

    public Espacio( int piso, int numero, int maxEst) {
        this.piso = piso;
        this.numero = numero;
        this.prioridad = this.calcularPrioridad(piso, numero, maxEst);
    }
    
    public Espacio(int prioridad) {
        this.prioridad = prioridad;
    }

    private int calcularPrioridad( int piso, int numero, int maxEst ) {
        return ((piso*-1)-1)*maxEst + numero;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad( int prioridad ) {
        this.prioridad = prioridad;
    }
    
    
    
    
}
