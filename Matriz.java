
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mauricio
 */
class Matriz extends JFrame implements KeyListener {
    
    private JPanel paneles[];
    
    private static int SIZE_X = 30;
    private static int SIZE_Y = 30;
    private int maxEst;
    private int niveles;
    private boolean simulacionTerminada = false;

    public Matriz(int filas, int columnas) {
        super("Gestion de Estacionamiento");
        super.setLayout (new GridLayout (filas, columnas));
        super.setSize (columnas*SIZE_X, filas*SIZE_Y+25);
        super.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        super.setResizable (true);
        super.addKeyListener(this);
        this.paneles = new JPanel[filas*columnas];
        
        this.maxEst = columnas;
        this.niveles = filas;
        
        for(int f=0; f < filas; f++){
            for ( int c = 0; c < columnas; c++ ) {
                JPanel panel = new JPanel();
                panel.setBackground (Color.white);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.setBounds (c*SIZE_X, f*SIZE_Y, SIZE_X, SIZE_Y);
                paneles[(f*columnas)+c] = panel;
            }
        }
        System.out.println("Presiona enter para cerrar la ventana una vez terminada la simulacion");
        //super.repaint ();
    }
    
    @Override
     public void paint(Graphics g) {
        super.paint(g);
        
        for(int i=0; i<paneles.length; i++){
            if(paneles[i]!=null)
                super.add(paneles[i]); 
        }
        super.repaint ();
     }

    void ocupar(int nivel, int numero, String tipo) {        
        nivel = (nivel+1) *-1;
        int pos = nivel * maxEst + numero;
        int pos2 = pos-1;
        if(tipo.equals("e"))
            this.paneles[pos-1].setBackground (Color.RED); 
        if(tipo.equals("n"))
            this.paneles[pos-1].setBackground (Color.GREEN); 
        super.repaint ();
    }

    void liberar( int nivel, int espacio ) {
        nivel = (nivel+1) *-1;
        int pos = nivel * maxEst + espacio;
        this.paneles[pos-1].setBackground(Color.white);
    }

    @Override
    public void keyTyped( KeyEvent arg0 ) {
    }

    @Override
    public void keyPressed( KeyEvent k ) {
        if(k.getKeyCode() == KeyEvent.VK_ENTER && this.simulacionTerminada)
            System.exit(0);
    }

    @Override
    public void keyReleased( KeyEvent arg0 ) {
    }

    void setSimulacionTerminada( boolean b ) {
        this.simulacionTerminada = b;
    }
    
    
    
    
}
