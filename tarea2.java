/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mauricio
 */
public class tarea2 {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) throws InterruptedException, IOException {
  
        if(args.length < 8){
            System.out.println("Ejecucion termianda. Error en la cantidad de parametros");
            System.out.println("La ejecucion debe ser: tarea2 --subte S --especial E --maxEst M --filename output.txt < input.txt");
            System.exit(0);
        }
        
        int argEspecial = 0;  // cantidad espacios especiales en el primer subterraneo
        int argMaxEst = 0;        // maximo de espacios en cualquiera de los pisos
        int argSubte = 0;         // numero de subterraneos
        String argFilename = null;   // nombre del archivo de salidaVehiculo
        
        Scanner in = new Scanner(System.in);
          
        // se recogen valores por linea de comandos
        for(int i=0; i<args.length; i++) {
            switch (args[i]) {
                case "--especial":
                    argEspecial = Integer.parseInt (args[i+1]);
                    break;
                case "--maxEst":
                    argMaxEst = Integer.parseInt (args[i+1]);
                    break;
                case "--subte":
                    argSubte = Integer.parseInt (args[i+1]);
                    break;
                case "--filename":
                    argFilename = args[i+1];
                    break;
            }
        }
    
        
        Estacionamiento estacionamiento = new Estacionamiento (argEspecial, argSubte, argMaxEst, argFilename);  // estacionamiento con espacio para gente normal y especial
        
        String accion = null;
        String tipo = null;
        int piso = 0;
        int espacio = 0;
        
        while(in.hasNext()) {
            switch (accion = in.next()){  // salidaVehiculo o ingresoVehiculo
                case "I":   // ingresa un vehiculo
                    tipo = in.next();
                    if(tipo.equals("e")){  // vehiculo especial
                        estacionamiento.ingresoVehiculo("e");
                    }
                    if(tipo.equals("n")){   // vehiculo normal
                        estacionamiento.ingresoVehiculo("n");
                    }
                    break;
                    
                case "S":
                    piso = Integer.parseInt (in.next());     
                    espacio = Integer.parseInt (in.next());
                    estacionamiento.salidaVehiculo(piso, espacio);
                    break;
            }
            Thread.sleep(300); // pausa
        }
        estacionamiento.cerrarReporte();
        estacionamiento.terminarSimulacion();
    }
}
