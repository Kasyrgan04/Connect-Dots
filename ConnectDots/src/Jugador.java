import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Jugador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaInicio ventana1=new VentanaInicio();
		
		ventana1.setVisible(true);
		ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
class VentanaInicio extends JFrame{
	public VentanaInicio() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
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

class VentanaJuego extends JFrame{
	public VentanaJuego() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Connect Dots");
		//reconoce el medio en el cual se ejecuta la pantalla
		Toolkit pantalla=Toolkit.getDefaultToolkit();
		//Cambia el icono predeterminado de la ventana
		Image Icono=pantalla.getImage("src/Connect Dots.png");
		setIconImage(Icono);
	}
}

class Lamina extends JPanel{
	private Image logo;
	private JButton inicio,cierre;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			logo=ImageIO.read(new File("src/Inicial.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(logo, 500, 0, null);
		
		
		
	}
	public Lamina() {
		setLayout(null);
		inicio=new JButton("Empezar"); 
		cierre=new JButton("Salir");
		inicio.setBounds(720, 600, 100, 40);
		cierre.setBounds(720, 650, 100, 40);
		//inicio.setBackground(new Color(253,230,53).brighter().brighter().brighter());
		Inicio launch=new Inicio();
		add(inicio);
		add(cierre);
		inicio.addActionListener(launch);
		
		
	}
	private class Inicio implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)  {
			// TODO Auto-generated method stub
			VentanaJuego ventana2=new VentanaJuego();
			ventana2.setVisible(true);
			//dispose();
		}
		
	}
	
}