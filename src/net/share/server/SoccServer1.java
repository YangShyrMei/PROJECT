package net.share.server;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SoccServer1 {
	private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    public static void receiveFile(String fileName)throws Exception
        {
            int bytes = 0;
            FileOutputStream fileOutputStream= new FileOutputStream(fileName);
     
            long size= dataInputStream.readLong(); // read file size
            byte[] buffer = new byte[4 * 1024];
            while (size > 0&& (bytes = dataInputStream.read(
                           buffer, 0,
                           (int)Math.min(buffer.length, size)))
                          != -1) {
                // Here we write the file using write method
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes; // read upto file size
            }
            // Here we received file
            System.out.println("File is Received");
            fileOutputStream.close();
        }
	
    

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Server is started");
        ServerSocket ss = new ServerSocket(5110);
        InetAddress iAddress= InetAddress.getLocalHost();
        String server_IP=iAddress.getHostAddress();
        System.out.println(server_IP);
        
        System.out.println("Server is waiting for client request");
        Socket s = ss.accept();
        
        System.out.println("Client Connected");
        
        /*BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = br.readLine();
        
        System.out.println("Client Data:" + str );
        
        String nickName = str.substring(0,3);
        
        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out =new PrintWriter(os);
        out.println(str);*/
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
            
            receiveFile("NewFile1.pdf");
            System.out.println("File received");
 
            dataInputStream.close();
            dataOutputStream.close();
            s.close();
        }
       
	}


