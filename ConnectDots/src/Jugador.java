import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;
/**
 *Clase principal para objeto jugador
 *
 */
public class Jugador {

	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	



	
	private String jsonString;
	private OutputStreamWriter outputStreamWriter;
	private JSONObject jsonObject;
	
	
	/**
	 * Constructor de Clase jugador
	 * @param socket Socket al que conectarse
	 * @param username Nombre del usuario a utilizar
	 */
	public Jugador (Socket socket, String username){
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
			this.username = username;
			/**
			 * Adds the username to the JSON objecto to be sent to the server on initial connection. 
			 */
			this.jsonObject = new JSONObject();
			this.jsonObject.put("Username", username);

			//System.out.println(this.jsonObject.toJSONString());
			//System.out.println("Json object created");


			this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8);
			
			
			



		} catch (IOException e) {
			closeEverything (socket,bufferedReader,bufferedWriter);

		}
	}
	/**
	 * Metodo para enviar informacion desde el cliente al servidor
	 */
	public void sendMessage(){
		try {
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();

			Scanner scanner = new Scanner(System.in);
			
			while(socket.isConnected()){
				String messageToSend = scanner.nextLine();
				//jsonObject.put("Message", messageToSend);

				//Envia la informacion del cliente en formato Json. 
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

	/**
	 * Metodo para recibir informacion desde el servidor
	 */
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

	/**
	 * Metodo para hacer un cierre en caso de error
	 * @param socket Socket a cerrar
	 * @param bufferedReader Buffered Reader a cerrar 
	 * @param bufferedWriter Buffered Writer a cerrar
	 */
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
	
	/**
	 * Metodo principal de la clase
	 * 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		VentanaInicio ventana1=new VentanaInicio();
		
		ventana1.setVisible(true);
		ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Scanner scanner = new Scanner(System.in);
		System.out.println("enter your username");
		String username = scanner.nextLine();
		Socket socket = new Socket("localhost",1234);
		Jugador jugador = new Jugador(socket,username);
		jugador.listenForMessage();
		jugador.sendMessage();*/
		
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
			ventana2.setBackground(new Color(0,28,115));
			ventana2.setVisible(true);
			ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
class Lamina2 extends JPanel {
	private Cola malla;
	private JButton connect,send,back ;
	public Lamina2 () {
		setLayout(null);
		connect=new JButton();
		send=new JButton();
		back=new JButton();
		malla= new Cola();
		int x=550;
		int y=205;
		while(x!=1030 && y!=680) {
			JRadioButton n = new JRadioButton();
			malla.enqueue(n);
			n.setBounds(x, y, 20, 20);
			add(n);
			x=x+60;
			y=y+60;
			System.out.println(x);
			System.out.println(y);
		}
	}
	
	public void paint(Graphics e) {
		e.drawRect(540, 200, 515, 510);
		e.setColor(Color.WHITE);
		e.fillRect(540, 200, 515, 510);
		/*e.setColor(Color.BLACK);
		e.drawOval(550, 205, 20, 20);
		e.drawOval(610, 205, 20, 20);
		e.drawOval(670, 205, 20, 20);
		e.drawOval(730, 205, 20, 20);
		e.drawOval(790, 205, 20, 20);
		e.drawOval(850, 205, 20, 20);
		e.drawOval(910, 205, 20, 20);
		e.drawOval(970, 205, 20, 20);
		e.drawOval(1030, 205, 20, 20);
		e.drawOval(550, 260, 20, 20);
		e.drawOval(550, 320, 20, 20);
		e.drawOval(550, 380, 20, 20);
		e.drawOval(550, 440, 20, 20);
		e.drawOval(550, 500, 20, 20);
		e.drawOval(550, 560, 20, 20);
		e.drawOval(550, 620, 20, 20);
		e.drawOval(550, 680, 20, 20);*/
	}
	
}
class Cola{
	public LinkedList lista=new LinkedList();
	
	public void enqueue(Object element) {
		this.lista.insertLast(element);
	}

}