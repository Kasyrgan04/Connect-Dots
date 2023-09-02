import java.awt.*;

import javax.swing.*;
public class Jugador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana ventana1=new Ventana();
		ventana1.setVisible(true);
		ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
class Ventana extends JFrame{
	public Ventana() {
		setBounds(500,300,400,400);
		setTitle("Connect Dots");
		Toolkit pantalla=Toolkit.getDefaultToolkit();
		//Cambia el icono predeterminado de la ventana
		Image Icono=pantalla.getImage("src/Connect Dots.png");
		setIconImage(Icono);
	}
}