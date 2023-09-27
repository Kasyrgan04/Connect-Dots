import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;


    private InputStreamReader inputStreamReader;
    
  
    private InputStream inputStream;
    private JSONObject jsonObject;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.inputStreamReader =  new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8);
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUsername +" has entered");
            
        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);

        }
    }
    
    

    @Override
    public void run() {
        String messageFromClient;
        JSONParser parser = new JSONParser();

        while(socket.isConnected()){
            try {           

                messageFromClient = bufferedReader.readLine();
                JSONObject json =(JSONObject)parser.parse(messageFromClient);
                
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }       

    }

    public void broadcastMessage ( String messageToSend){
            for(ClientHandler clientHandler: clientHandlers){
                try {
                    if(!clientHandler.clientUsername.equals(clientUsername)){
                        clientHandler.bufferedWriter.write(messageToSend);
                        clientHandler.bufferedWriter.newLine();
                        clientHandler.bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    closeEverything(socket,bufferedReader,bufferedWriter);
                }
            }
        }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " has left" );
    }

    public void closeEverything (Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();
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

}