/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mauricio
 */
class EstacionamientoEspecial {

    private Espacio espacios[];   // maneja las posiciones libres. tienen valor null las ocupadas
    int posicionador;
    int cantidadEspacios;
    
    public EstacionamientoEspecial( int cantidadEspacios ) {
        this.espacios = new Espacio[cantidadEspacios];
        this.cantidadEspacios = cantidadEspacios;
        this.posicionador = 0;
        prepararEstacionamiento();
    }
    
    public int estacionar() {        
        if(posicionador == this.cantidadEspacios)
            posicionador = 0;
        
        if(hayDisponible()){
            if(this.espacios[posicionador] != null){
                this.espacios[posicionador] = null;
                this.posicionador++;
                return posicionador;
            }
            
            else {
                for(int i = posicionador+1; i<cantidadEspacios; i++) {  // busca espacio disponible hacia adelante
                    if(this.espacios[i] != null) {
                        this.espacios[i] = null;
                        posicionador = i+1;
                        return posicionador;
                    }
                }
                
                for(int i = 0; i<posicionador; i++) {    // si no quedan espacios disponibles hacia adelante
                    if(this.espacios[i] != null){        // se busca espacio disponible desde atrAs (circularidad)
                        this.espacios[i] = null;
                        this.posicionador++;
                        return posicionador;
                    }
                }
            }
            
        }
        return -1; // no se ha podido estacionar
    }
    
    public boolean abandonarEstacionamiento(int pos) {
        if(this.espacios[pos-1] == null) {
            this.espacios[pos-1] = new Espacio(-1, --pos, 0);
            return true;
        }
        return false;
    }

    public boolean hayDisponible() {
        for(int i=0; i<this.cantidadEspacios; i++) {
            if(this.espacios[i] != null)
                return true;
        }
        return false;
    }

    private void prepararEstacionamiento() {
        for(int i=0; i<cantidadEspacios; i++) {
            espacios[i] = new Espacio(-1, i+1, 0);
        }
    }
    
}
