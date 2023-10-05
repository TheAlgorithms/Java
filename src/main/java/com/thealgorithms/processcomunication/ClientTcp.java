package com.thealgorithms.processcomunication;

import java.net.*;
import java.io.*;

public class ClientTcp {
    public static void main(String args[]) {
        Socket s = null;
        try {
            int serverPort = 7896;   
            String ip = "localhost";
            s = new Socket(ip, serverPort); 

            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message;
            
            while (true) {
                message = reader.readLine();
                out.writeUTF(message);  

                if (message.equalsIgnoreCase("exit")) {
                    break; 
                }

                String response = in.readUTF(); 
                System.out.println(response);
            }

            in.close();
            out.close();
            s.close();  
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("leitura:" + e.getMessage());
        }
    }
}
