
import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/*
    ClassName: StorageInterface
    @authors: Anmol Jaising, Asmita Hari

    This interface defines the abstract methods operatin.
 */
interface OperationFunctionalInterface {

    int operation(int a, int b);
}

/*
      This interface defines the abstract methods operatin.
 */
interface CalculatorInterface {

    int performOperation(OperationFunctionalInterface ofi, int num1, int num2);

    static String helpMessage(int exitCode) {
        return "Usage: <num> <operation> <num>";

    }
}

/*

   This class defines the abstract methods operatin.
 */
public class CalculatorServer {

    public static void main(String[] args) {

        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(1234);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] recv = new byte[5];
        DatagramPacket dp = new DatagramPacket(recv, recv.length);
        try {
            ds.receive(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String operationString = new String(dp.getData());
        String[] data = operationString.split("\\s+");
        String error = null;

        int num1 = 0;//variables
        int num2 = 0;

        try {
            num1 = Integer.parseInt(data[0]); //convert string to int
            num2 = Integer.parseInt(data[2]);

        } catch (NumberFormatException ex) {//if the args is not a number

            error = CalculatorInterface.helpMessage(2);
            byte[] send = error.getBytes();
            InetAddress ip = null;
            //sending packes back to client
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            DatagramPacket dp1 = new DatagramPacket(send, send.length, ip, dp.getPort());
            try {
                ds.send(dp1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {//if the args is not a number

            error = CalculatorInterface.helpMessage(2);
            byte[] send = error.getBytes();
            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            DatagramPacket dp1 = new DatagramPacket(send, send.length, ip, dp.getPort());
            try {
                ds.send(dp1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] operator = new String[]{"+", "-", "/", "x", "%"};//check if the operator exists
        boolean flag = false;
        for (int i = 0; i < operator.length; i++) {
            if (operator[i].equals(data[1])) {
                flag = true;
            }

        }
        if (!flag) {
            error = CalculatorInterface.helpMessage(3);
            //sending packes back to client
            byte[] send = error.getBytes();
            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            DatagramPacket dp1 = new DatagramPacket(send, send.length, ip, dp.getPort());
            try {
                ds.send(dp1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String symbol = data[1]; //store symbol

        CalculatorInterface ci; //define functional interface
        OperationFunctionalInterface operation;
        int output = 0;

        switch (symbol) {
            case "+":
                operation = (a, b) -> a + b; //operator interface defination
                ci = (operation1, a, b) -> operation1.operation(a, b); //calculator interface defination
                output = ci.performOperation(operation, num1, num2);//print the result
                break;

            case "-":
                operation = (a, b) -> a - b;
                ci = (operation1, a, b) -> operation1.operation(a, b);
                output = ci.performOperation(operation, num1, num2);
                break;
            case "x":
                operation = (a, b) -> a * b;
                ci = (operation1, a, b) -> operation1.operation(a, b);
                output = ci.performOperation(operation, num1, num2);
                break;
            case "/":
                operation = (a, b) -> a / b;
                ci = (operation1, a, b) -> operation1.operation(a, b);
                output = ci.performOperation(operation, num1, num2);
                break;
            case "%":
                operation = (a, b) -> a % b;
                ci = (operation1, a, b) -> operation1.operation(a, b);
                output = ci.performOperation(operation, num1, num2);
                break;

        }
//sending packes back to client

        byte[] send = Integer.toString(output).getBytes();
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket dp1 = new DatagramPacket(send, send.length, ip, dp.getPort());
        try {
            ds.send(dp1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
