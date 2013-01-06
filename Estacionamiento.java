
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



/**
 *
 * @author mauricio
 */
public class Estacionamiento {
    
    private EstacionamientoEspecial sectorEspecial;
    private EstacionamientoNormal sectorNormal;
    private BufferedWriter bw;
    private Matriz grafica;
    private int maxEst;
    
    private int actionsForLine = 0;
    private static final int MAX_ACTIONS_FOR_LINE = 10;

    public Estacionamiento(int especiales, int pisos, int maxEst, String filename) throws IOException {
        this.sectorEspecial = new EstacionamientoEspecial (especiales);
        this.sectorNormal = new EstacionamientoNormal (pisos*maxEst-especiales, pisos, maxEst);
        this.grafica = new Matriz(pisos, maxEst);
        this.grafica.setVisible(true);
        this.bw = new BufferedWriter(new FileWriter(filename));
        sectorNormal.prepararEstacionamiento(especiales+1);
        this.maxEst = maxEst;
    }
    
    public void ingresoVehiculo(String tipo) throws IOException { // tipo: e: especial  n: normal
        int posicionIngreso[] = new int[2];  // [piso, numero]
        
        if(tipo.equals ("e")) {
            if(this.sectorEspecial.hayDisponible()){
                int estacionado = this.sectorEspecial.estacionar ();
                posicionIngreso[0] = -1; // piso: los estacionamientos especiales se encuentran en el piso -1
                posicionIngreso[1] = estacionado; // numero del estacionamiento
            }
            else {
                Espacio estacionado = this.sectorNormal.extraerEspacioMasCercano ();  // retorna el espacio de estacionamiento mas cercano
                posicionIngreso[0] = estacionado.piso;
                posicionIngreso[1] = estacionado.numero;
            }
        }
        
        if(tipo.equals ("n")) {
            Espacio estacionado = this.sectorNormal.extraerEspacioMasCercano ();  // retorna el espacio de estacionamiento mas cercano
            posicionIngreso[0] = estacionado.piso;
            posicionIngreso[1] = estacionado.numero;
        }
        this.escribirEvento(bw, posicionIngreso[0], posicionIngreso[1]);  // bitacora
        grafica.ocupar(posicionIngreso[0], posicionIngreso[1], tipo);     // vista grafica
    }
    
    public void salidaVehiculo(int nivel, int espacio) throws IOException {  // sale un vehiculo
        if(nivel == -1 && espacio <= sectorEspecial.cantidadEspacios){  // sale un especial
            this.sectorEspecial.abandonarEstacionamiento(espacio);
            this.grafica.liberar(nivel, espacio);
        }
        
        else { // sale un normal
            this.sectorNormal.agregarEspacioDisponible(new Espacio(nivel, espacio, maxEst));
            this.grafica.liberar(nivel, espacio);
        }
        //this.escribirEvento(bw, nivel, espacio);  // opcional en caso de querer incluir salidas en archivo de registro
    }
    
    public void cerrarReporte() throws IOException {
        this.bw.write("\n");
        this.bw.close();
    }
    
    public void escribirEvento( BufferedWriter bw, int piso, int numero ) throws IOException{
        actionsForLine++;
        if(this.actionsForLine < MAX_ACTIONS_FOR_LINE) {
            bw.write (piso + " " + numero + " ");    // formato: piso numero
        }
        else {
            bw.write(piso + " " + numero + "\n" );
            actionsForLine = 0;
        }
    }

    void terminarSimulacion() {
        grafica.setSimulacionTerminada(true);
    }
}
