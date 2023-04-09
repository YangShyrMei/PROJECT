package net.share.client;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocClient {
	private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    static String src_file_path = System.getProperty(("user.dir"))+"/src_file";
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

	public static void main(String[] args) throws Exception {
		Socket s= new Socket("192.168.43.88",5252); //sender to receiver
		/* String str="hello Server";
		
		OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
		PrintWriter out= new PrintWriter(os);
		out.println(str);
		os.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String nickName = br.readLine();
		
		System.out.println("C : Data from Server " + nickName);
		*/
		dataInputStream = new DataInputStream(s.getInputStream());
		dataOutputStream = new DataOutputStream(s.getOutputStream());
        System.out.println( "Sending the File to the Server");
        sendFile(src_file_path+"/abcdef.txt");
        dataInputStream.close();
        dataInputStream.close();
        
       
		// TODO Auto-generated method stub

	}

}
