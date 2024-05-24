package ru.netology;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("netology.homework", 8083)) {
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправка строки приветствия серверу
            String greeting = "Handshake";
            out.write((greeting + "\n").getBytes());

            // Чтение ответа от сервера
            String serverResponse = in.readLine();
            System.out.println("Ответ от сервера: " + serverResponse);

            if ("Ок, 200".equals(serverResponse)) {
                // Отправка информации об ОС серверу
                String osInfo = getOSInfo();
                out.write((osInfo + "\n").getBytes());
                System.out.println(osInfo);
                // Чтение окончательного ответа от сервера
                String finalResponse = in.readLine();

                System.out.println("Окончательный ответ от сервера: " + finalResponse);
            }
            if (serverResponse.equals("I’m a teapot, 418")){
                System.out.println("мы что-то не то запросили: " + serverResponse);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static String getOSInfo() {
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        long freeMemory = Runtime.getRuntime().freeMemory();
        String hostAddress = "N/A";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostAddress = inetAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("Host mane: %s, ОС: %s, OS Version: %s, RAM: %d, IP addr: %s",
                System.getenv("COMPUTERNAME"), osName, osVersion, freeMemory, hostAddress);
    }
}