package net.share.GroupManager;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendPublicKey {
private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    static String received_public_key_path = System.getProperty("user.dir")+"/receivedPublicKey";
    public static void sendFile(String path) throws Exception
    {
        int bytes = 0;
        // Open the File where he located in your pc
        File file = new File(path);
        FileInputStream fileInputStream
            = new FileInputStream(file);
 
        // Here we send the File to Server
        dataOutputStream.writeLong(file.length());
        // Here we  break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer))
               != -1) {
          // Send the file to Server Socket 
          dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        // close the file here
        fileInputStream.close();
    }

public SendPublicKey() throws Exception {
Socket s= new Socket("192.168.43.88",5880);
BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
String clientID=br.readLine();

//get publicKey using client ID received from sender
System.out.println(clientID);
clientID=clientID.trim();
   
//send public key of requested ID
dataInputStream = new DataInputStream(s.getInputStream());
dataOutputStream = new DataOutputStream(s.getOutputStream());
        System.out.println( "Sending the File to the Server");
        sendFile(received_public_key_path+"/"+clientID+".txt");
        dataInputStream.close();
        dataInputStream.close();
        s.close();
        

}
public static void main(String args[]) throws Exception {
SendPublicKey s1 = new SendPublicKey();
}
}

    

