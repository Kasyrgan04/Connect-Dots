
import javax.swing.*;
//import org.json.simple.JSONOBject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public ServerSocket serverSocket;

	public Servidor(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

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