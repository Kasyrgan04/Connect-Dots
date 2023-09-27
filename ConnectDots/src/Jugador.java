import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.JSONObject;

public class Jugador {

	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	



	
	private String jsonString;
	private OutputStreamWriter outputStreamWriter;
	private JSONObject jsonObject;
	
	

	public Jugador (Socket socket, String username){
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
			this.username = username;

			this.jsonObject = new JSONObject();
			this.jsonObject.put("Username", username);

			System.out.println(this.jsonObject.toJSONString());
			System.out.println("Json object created");


			this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8);
			
			
			



		} catch (IOException e) {
			closeEverything (socket,bufferedReader,bufferedWriter);

		}
	}

	public void sendMessage(){
		try {
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();

			Scanner scanner = new Scanner(System.in);
			while(socket.isConnected()){
				String messageToSend = scanner.nextLine();
				//jsonObject.put("Message", messageToSend);
				System.out.println(jsonObject.toJSONString());
				bufferedWriter.write(jsonObject.toString());
				bufferedWriter.newLine();
				bufferedWriter.flush();
				

				//bufferedWriter.write(messageToSend);
				//bufferedWriter.newLine();
				//bufferedWriter.flush();

			}

		} catch (IOException e) {
			closeEverything(socket,bufferedReader,bufferedWriter);
		}
	}

	public void listenForMessage(){
		new Thread(new Runnable() {
			public void run(){
				String msgFromGroup;
				while(socket.isConnected()){
					try {
						msgFromGroup = bufferedReader.readLine();
						System.out.println(msgFromGroup);
					} catch (IOException e) {
						closeEverything(socket,bufferedReader,bufferedWriter);

					}
				}
			}
		}).start();
	}
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
		try {
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//VentanaInicio ventana1=new VentanaInicio();
		
		//ventana1.setVisible(true);
		//ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter your username");
		String username = scanner.nextLine();
		Socket socket = new Socket("localhost",1234);
		Jugador jugador = new Jugador(socket,username);
		jugador.listenForMessage();
		jugador.sendMessage();
		
	}

}/* 
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
		Lamina2 lamina2=new Lamina2();
		add(lamina2);
		lamina2.setBackground(new Color(0,28,115));
	}
}

class Lamina extends JPanel{
	private Image logo;
	private JButton inicio,cierre;
	private JTextField nick;
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
		JLabel name=new JLabel("Nombre:");
		name.setForeground(Color.WHITE);
		Font font=new Font("Bungee", Font.BOLD,20);
		name.setFont(font);
		nick=new JTextField();
		inicio=new JButton("Empezar"); 
		cierre=new JButton("Salir");
		inicio.setBounds(720, 600, 100, 40);
		cierre.setBounds(720, 650, 100, 40);
		nick.setBounds(720, 700, 100, 20);
		name.setBounds(630, 690, 100, 40);
		//inicio.setBackground(new Color(253,230,53).brighter().brighter().brighter());
		Inicio launch=new Inicio();
		Cierre close=new Cierre();
		add(inicio);
		add(cierre);
		add(name);
		add(nick);
		inicio.addActionListener(launch);
		cierre.addActionListener(close);
		
		
	}
	private class Inicio implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)  {
			// TODO Auto-generated method stub
			VentanaJuego ventana2=new VentanaJuego();
			ventana2.setVisible(true);
			Window w =SwingUtilities.getWindowAncestor(Lamina.this);
			w.dispose();
		}
		
	}
	private class Cierre implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Window v= SwingUtilities.getWindowAncestor(Lamina.this);
			v.dispose();
		}
		
	}
}
class Lamina2 extends JPanel implements MouseListener{
	private JButton connect,send,back ;
	private ListaEnlazada nodo;
	public Lamina2 () {
		setLayout(null);
		connect=new JButton();
		send=new JButton();
		back=new JButton();
		nodo=new ListaEnlazada();
		this.addMouseListener(this);
		
	}
	
	/*public void paint(Graphics e) {
		for (Nodo nodos:nodo) {
			nodos.pintar(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}*/