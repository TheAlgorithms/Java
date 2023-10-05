package com.thealgorithms.processcomunication;

import java.net.*;
import java.io.*;

import java.security.MessageDigest;

public class ServerTCP {
    public static ServerTCP.User[] users;

    public static void main (String args[]) {
        try {
            users = new ServerTCP.User[] {
                new ServerTCP.User("admin", "admin"),
                new ServerTCP.User("user", "user")
            };

            int serverPort = 7896; // porta do servidor
            try (ServerSocket listenSocket = new ServerSocket(serverPort)) {
                while (true) {
                    System.out.println("Server waiting for connection...");
                    Socket clientSocket = listenSocket.accept();
                    
                    System.out.println("Client connected... Creating thread...");
                    new Connection(clientSocket);
                }
            }

        } catch (IOException e) {
            System.out.println("Listen socket: " + e.getMessage());
        }
    }

    static class Connection extends Thread {
        DataInputStream in;
        DataOutputStream out;
        Socket clientSocket;

        public Connection(Socket aClientSocket) {
            try {
                clientSocket = aClientSocket;
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                this.start();  
            } catch (IOException e) {
                System.out.println("Connection: " + e.getMessage());
            }
        }

        public void run() {
            try {
                boolean isUserAuthenticated = false;
                while (true) {
                    String data = in.readUTF(); 
                    String[] command = data.split(" ");
                    
                    if (command[0].equalsIgnoreCase("connect")) {
                        String[] credentials = command[1].split(",");

                        for (User user : users) {
                            if (user.authenticate(credentials[0], credentials[1])) {
                                isUserAuthenticated = true;
                                break;
                            }
                        }

                        if (isUserAuthenticated) {
                            out.writeUTF("SUCCESS");
                        } else {
                            out.writeUTF("ERROR");
                        }
                    } else if (command[0].equalsIgnoreCase("pwd")) {
                        if (!isUserAuthenticated) {
                            out.writeUTF("NOT_AUTHENTICATED");
                            continue;  
                        }

                        out.writeUTF(System.getProperty("user.dir"));
                    } else if (command[0].equalsIgnoreCase("chdir")){ 
                        if (!isUserAuthenticated) {
                            out.writeUTF("NOT_AUTHENTICATED");
                            continue;  
                        }
                        String path = command[1];

                        if (path.equals("..")) {
                            File currentDir = new File(System.getProperty("user.dir"));
                            String parentDir = currentDir.getParent();
                            if (parentDir != null) {
                                System.setProperty("user.dir", parentDir);
                                out.writeUTF("SUCCESS");
                            } else {
                                out.writeUTF("ERROR");
                            }
                        } else {
                            File file = new File(System.getProperty("user.dir"), path);

                            if (file.exists()) {
                                System.setProperty("user.dir", file.getAbsolutePath());
                                out.writeUTF("SUCCESS");
                            } else {
                                out.writeUTF("ERROR");
                            }
                        }
                    } else if (command[0].equalsIgnoreCase("getfiles")) {
                        if (!isUserAuthenticated) {
                            out.writeUTF("NOT_AUTHENTICATED");
                            continue;  
                        }
                        File currentDir = new File(System.getProperty("user.dir"));
                        File[] files = currentDir.listFiles();

                        String filesList = "";
                        Integer filesCount = 0;

                        for (File file : files) {
                            if (file.isFile()){
                                filesList += file.getName() + "\n";
                                filesCount++;
                            }
                        }

                        out.writeUTF(filesCount + "\n" + filesList);
                    } else if (command[0].equalsIgnoreCase("getdirs")) { 
                        if (!isUserAuthenticated) {
                            out.writeUTF("NOT_AUTHENTICATED");
                            continue;  
                        }

                        File currentDir = new File(System.getProperty("user.dir"));
                        File[] files = currentDir.listFiles();


                        String filesList = "";
                        Integer dirsCount = 0;
                        for (File file : files) {
                            if (file.isDirectory()) {
                                filesList += file.getName() + "\n";
                                dirsCount++;
                            }
                        }

                        out.writeUTF(dirsCount + "\n" + filesList);
                    } else if (command[0].equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        out.writeUTF("Comando inválido.");
                    }
                }

                System.out.println("Cliente desconectado.");
                in.close();
                out.close();
                clientSocket.close();
            } catch (EOFException e) {
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erro de leitura: " + e.getMessage());
            }
        }
    }

    static class User {
        String name;
        String password;
        
        public User(String name, String password) {
            this.name = name;
            this.password = this.convertSHA512(password);
        }

        public boolean authenticate(String name, String password) {
            return this.name.equals(name) && this.password.equals(this.convertSHA512(password));
        }

        public String getName() {
            return this.name;
        }

        public String getPassword() {
            return this.password;
        }

        public String convertSHA512(String password){
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-512");
                byte[] hash = digest.digest(password.getBytes("UTF-8"));
                StringBuilder hexString = new StringBuilder();

                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if(hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }

                return hexString.toString();
            } catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
