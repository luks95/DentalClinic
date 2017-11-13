package swing.splash;

import java.awt.BorderLayout; 
import javax.swing.JWindow;
import consultorio.swing.login.LoginForm;

public class RunSplash extends JWindow{ 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SplashP p; // clase Panel, la que nos creamos 
     //para cargar la otra ventana
    /** Creates new form RunSplash  
     * @throws InterruptedException */ 
    public RunSplash() throws InterruptedException{ // constructor 
        p = new SplashP(); // se crea el objeto Panel 
        LoginForm ventana = new LoginForm();//creamos la ventana        
        ventana.setLocationRelativeTo(null);
        this.add(p, BorderLayout.CENTER); // se añade el panel en el centro 
        this.setSize(p.getWidth(), p.getHeight());// se establece el tamaño del RunSplash 
        // ahora y lo mas importante se quita toda()+75 la decoracion de la ventana  
        //marco, botones minimizar, cerrar con el metodo setUndecorated(true) 
        //para dar el efecto de que solo se muestra la imagen con la barra... 
        this.setLocationRelativeTo(null);// se muestra el splash en el centro 
        setVisible(true); // se visualiza el Splash Screen 
        p.velocidadDeCarga();//se invoca al metodo velocidadDeCarga de la clase Panel 
        //para establecer el recorrido de la barra 
        this.dispose();// se cierra el Splash cuando termina el recorrido de la barra
        ventana.main(null);;
        /********************** 
         * Aqui pueden llamar a la ventana pricipal de sus aplicaciones 
         * y asi esto dará presencia a sus aplicaciones 
         */ 
    } 
     
    public static void main(String args[]) throws InterruptedException { 
        new RunSplash(); // se invoca a la ventana RunSplash         
    } 
}  