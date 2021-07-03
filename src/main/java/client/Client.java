package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client (String address){
        try{
            socket = new Socket(address, 443);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        }catch (UnknownHostException u){
            System.out.println(u);
        }catch (IOException i){
            System.out.println(i);
        }

        String line = "";
        while (line!="over"){
            try{
                line = input.readUTF();
                out.writeUTF(line);
            }catch (IOException i){
                System.out.println(i);
            }
        }

        try{
            input.close();
            out.close();
            socket.close();
        }catch(IOException i){
            System.out.println(i);
        }


    }
}
