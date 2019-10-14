//Importo i package necessari
import java.net.*;
import java.util.Scanner;
import java.io.*;
   public class TCPServer {
     public void start() throws IOException {
       ServerSocket serverSocket = new ServerSocket(7777);
       //Informazioni sul Server in ascolto
       InetAddress indirizzo = serverSocket.getInetAddress();
       String server = indirizzo.getHostAddress();
       int port = serverSocket.getLocalPort();
       System.out.println("In ascolto Server: "+ server + " porta: " + port);
       //Ciclo infinito per ascolto dei Client
       while (true) {
         System.out.println("In attesa di chiamate dai Client... ");
         Socket socket = serverSocket.accept();
         //Informazioni sul Client che ha effettuato la chiamata
         InetAddress address = socket.getInetAddress();
         String client = address.getHostName();
         int porta = socket.getPort();
         System.out.println("In chiamata Client: "+ client + " porta: " + porta);
         //Stream di byte utilizzato per la comunicazione via socket
         //DataInputStream is = new DataInputStream(socket.getInputStream());
         Scanner is= new Scanner(socket.getInputStream());
         DataOutputStream os = new DataOutputStream(socket.getOutputStream());
         while (is.hasNext()) {
           // String userInput = is.readLine();
           String userInput = is.nextLine();
           if (userInput == null || userInput.equals("QUIT"))
             break;
           os.writeBytes(userInput + '\n');
           System.out.println("Il Client ha scritto: " + userInput);
         }
         //chiusura della comunicazione con il Client
         os.close();
         is.close();
         System.out.println("Chiusura chiamata Client: " + client + " su porta:" + porta);
         socket.close();
       }
     }
     public static void main (String[] args) throws Exception {
       TCPServer tcpServer = new TCPServer();
       tcpServer.start();
     }
   }