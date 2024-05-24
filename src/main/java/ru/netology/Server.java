package ru.netology;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8083)) {
            System.out.println("Сервер ожидает подключения...");

            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Клиент подключен с порта: " + clientSocket.getPort());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream();

                // Чтение строки приветствия от клиента
                String greeting = in.readLine();
                System.out.println("Получено сообщение от клиента: " + greeting);
                if (greeting.equals("Handshake")) {
                    // Ответ клиенту
                    out.write("Ок, 200\n".getBytes());
                    String osInfo = in.readLine();

                    System.out.println("Информация о системе клиента: " + osInfo);

                    // Ответ клиенту
                    out.write("Ок, 201\n".getBytes());
                } else {
                    out.write("I’m a teapot, 418\n".getBytes());
                }
                // Чтение информации об ОС клиента

            }

        } catch (Exception e) {
            System.err.println(e);

        }
    }
}