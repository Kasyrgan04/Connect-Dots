import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
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
		//reconoce el medio en el cual se ejecuta la pantalla
		Toolkit pantalla=Toolkit.getDefaultToolkit();
		//Cambia el icono predeterminado de la ventana
		Image Icono=pantalla.getImage("src/Connect Dots.png");
		setIconImage(Icono);
		Lamina lamina1=new Lamina();
		add(lamina1);
		lamina1.setBackground(new Color(0,28,115));
	}
}
class Lamina extends JPanel{
	private Image logo;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		try {
			logo=ImageIO.read(new File("src/Inicial.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(logo, 500, 0, null);
	}
}