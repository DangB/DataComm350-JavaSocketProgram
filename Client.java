import java.net.*;
import java.io.*;
import java.util.*;

public class Cosc350client {
    
    public static void connectWeb (String address) throws IOException {
        Socket socket = new Socket(address, 80);

	DataOutputStream output = 
                new DataOutputStream(socket.getOutputStream());
        
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("GET / HTTP/1.1");
        writer.println("Host: " + address);
        writer.println("Connection: close");
        writer.println(); 
        
        BufferedReader serverIn = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        System.out.println("Server: " + serverIn.readLine());
        System.out.println("Server: " + serverIn.readLine());
        System.out.println("Server: " + serverIn.readLine());
        
        socket.close();
    }

    public static void connectLocal (String address) throws IOException {
        Socket socket = new Socket("localhost", 12337);
        
        DataOutputStream toServer = new DataOutputStream(
                socket.getOutputStream());
         toServer.writeBytes(address + "\n");
    }

    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
	String address = "";
	int choice;
	System.out.println("Input web server name\nExample: www.w3.org");
	address = userInput.nextLine();
	System.out.println("Input 1 or 2\n1: Connect to webserver"
		+ "\n2: Connect to local host");
	choice = Integer.parseInt(userInput.nextLine());
	switch (choice) {
            case 1: connectWeb(address);
                    break;
            case 2: connectLocal(address);
                    break;
            default: System.err.println("Error Invalid Choice");
        }
    }
}
