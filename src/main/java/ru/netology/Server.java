package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8083)) {
            System.out.println("Сервер ожидает подключения...");

            try (Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
//               OutputStream out = clientSocket.getOutputStream();
            ) {

                System.out.println("Клиент подключен с порта: " + clientSocket.getPort());

                // Чтение строки приветствия от клиента
                String greeting = in.readLine();
                System.out.println("Получено сообщение от клиента: " + greeting);
                if (greeting.equals("Handshake")) {
//                   Ответ клиенту
                    out.println("Ок, 200");
//                  out.write("Ок, 200\n".getBytes());
//                   Чтение информации об ОС клиента
                    String osInfo = in.readLine();
                    System.out.println("System About Information: " + osInfo);

                    // Ответ клиенту
                    out.println("Ок, 200");
//                  out.write("Ок, 201\n".getBytes());
                } else {
                    out.println("I’m a teapot, 418");
//                  out.write("I’m a teapot, 418\n".getBytes());
                }


            }

        } catch (Exception e) {
            System.err.println(e);

        }
    }
}