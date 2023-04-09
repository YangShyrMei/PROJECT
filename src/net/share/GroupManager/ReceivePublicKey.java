package net.share.GroupManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ReceivePublicKey {
//receive public key from a particular ID
private static DataOutputStream dataOutputStream = null;
   private static DataInputStream dataInputStream = null;
   public static String clientID;
   static String received_public_key_path = System.getProperty("user.dir")+"/receivedPublicKey";
   
  
   public static void receiveFile(String clientID,String fileName)throws Exception
       {
           int bytes = 0;
           String path=received_public_key_path+"/"+clientID+".txt";
       
           
           File file = new File(path);
           FileOutputStream fileOutputStream= new FileOutputStream(file);
    
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

public ReceivePublicKey() throws Exception{
// establish connection
System.out.println("Group Manager is waiting for client");
       ServerSocket ss = new ServerSocket(5110);
       
    
       Socket s = ss.accept();
       
       System.out.println("Client Connected");
       InputStream is = s.getInputStream();
       InputStreamReader isr = new InputStreamReader(is);
       
       //receive client ID
       BufferedReader br= new BufferedReader(isr);
       clientID=br.readLine();
       System.out.println("Client ID received");
       
       br.close();
       isr.close();
       is.close();
       s.close();
       ss.close();
       getFile();
}

public static void getFile() throws Exception {
       //receive public key file
ServerSocket ss = new ServerSocket(5120);
Socket s = ss.accept();
            System.out.println("Group Manager waiting for Public Key");
   
       dataInputStream = new DataInputStream(s.getInputStream());
       dataOutputStream = new DataOutputStream(s.getOutputStream());
       receiveFile(clientID,"PublicKey.txt");
       System.out.println("File received");
       dataInputStream.close();
       dataOutputStream.close();
       s.close();
       ss.close();
       
}
     
public static void main(String args[]) throws Exception {
ReceivePublicKey r = new ReceivePublicKey();

      
}
}

