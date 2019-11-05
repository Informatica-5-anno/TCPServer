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
         BufferedReader ibr= new BufferedReader(new InputStreamReader(socket.getInputStream()));
         DataOutputStream os = new DataOutputStream(socket.getOutputStream());
         String ur,userInput="";
         do {
        	 ur=ibr.readLine();
        	 userInput+="\n"+ur;
        	
         } while (!ur.isEmpty());
           System.out.println("Il Client ha scritto: " + userInput);
           String data=
        		"<HTML>\r\n" + 
           		"          <HEAD>\r\n" + 
           		"             <TITLE>Esempio HTML</TITLE>\r\n" + 
           		"          </HEAD>\r\n" +  
           		"          <BODY> \r\n" + 
           		"            PAGINA HTML DEMO\r\n" + 
           		"          </BODY>\r\n" + 
           		"</HTML>\r\n";
           
           os.writeBytes("HTTP/1.1 200 OK\r\n" + 
           		"Date: Tue, 05 Nov 2019 08:37:43 GMT\r\n" + 
           		"Server: Apache/2.4.6 (Scientific Linux) OpenSSL/1.0.2k-fips mod_fcgid/2.3.9 PHP/5.4.16\r\n" + 
           		"Last-Modified: Thu, 23 Oct 1997 20:34:37 GMT\r\n" + 
           		"ETag: \"378-31e3539813140\"\r\n" + 
           		"Accept-Ranges: bytes\r\n" + 
           		"Content-Length: "+data.length()+"\r\n" + 
           		"Keep-Alive: timeout=5, max=100\r\n" + 
           		"Connection: Keep-Alive\r\n" + 
           		"Content-Type: text/html\r\n" + 
           		"\r\n" + data);
         
       
         //chiusura della comunicazione con il Client
         os.close();
         ibr.close();
         System.out.println("Chiusura chiamata Client: " + client + " su porta:" + porta);
         socket.close();
       }
     }
     public static void main (String[] args) throws Exception {
       TCPServer tcpServer = new TCPServer();
       tcpServer.start();
     }
   }