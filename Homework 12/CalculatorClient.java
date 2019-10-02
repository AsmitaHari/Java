/*
    ClassName: StorageInterface
    @authors: Anmol Jaising, Asmita Hari

    This class defines the Calculator Client.
 */

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class CalculatorClient {

    // Main method.
    public static void main(String[] args) {

        // Create a datagram socket
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        java.util.Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the operation to be performed in num operator num format");
        String operation = sc.nextLine();

        byte[] sending = operation.getBytes();
        InetAddress ip = null;

        {
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        // Create two datagram packet and send and receive them through the datagram socket.
        DatagramPacket dp1 = new DatagramPacket(sending, sending.length, ip, 1234);
        try {
            ds.send(dp1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] sen = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(sen, sen.length);
        try {
            ds.receive(dp2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the response and display it.
        String str = new String(dp2.getData());

        System.out.println(operation + " = " + str);
    }
}
