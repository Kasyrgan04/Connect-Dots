
import javax.swing.*;
//import org.json.simple.JSONOBject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Clase principal Maneja el control del juego, 
 */
public class Servidor {
	public ServerSocket serverSocket;

	/**
	 * Constructor de clase servidor
	 * @param serverSocket Socket a utilizar para conexiones
	 */
	public Servidor(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	/**
	 * Metodo de iniciacion del servidor
	 */
	public void startServer() {

		try {
			while (!serverSocket.isClosed()) {

				Socket socket = serverSocket.accept();
				System.out.println("New client");
				ClientHandler clientHandler = new ClientHandler(socket);

				Thread thread = new Thread(clientHandler);
				thread.start();

			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	/**
	 * Metodo para cerrar El servidor
	 */
	public void closeServerSocket() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/**
	 * Metodo principal, ejecuta el servidor.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234);
		Servidor server = new Servidor(serverSocket);
		server.startServer();

	}

}
/*
 * class Marco extends JFrame{
 * public Marco() {
 * setBounds(1200,300,280,350);
 * 
 * }
 * 
 * }
 */