package net.share.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.share.loginSystem.Login;


public class GenerateKeys{

		public static PrivateKey privateKey;
		static PublicKey publicKey;
		private static DataOutputStream dataOutputStream = null;
		private static DataInputStream dataInputStream = null;
		static String project_root = System.getProperty("user.dir");
		static String public_file_path= project_root+"/sendPublicPrivateKey";
		static String groupManager="192.168.1.11";  //send to GroupManager
		
		public void getKeys() throws Exception {
		 Map<String, Object> keys = getRSAKeys();
		 
	      privateKey = (PrivateKey) keys.get("private");
	      publicKey = (PublicKey) keys.get("public");
		  
       
		try (FileOutputStream out = new FileOutputStream( public_file_path+"/PublicKey"+ ".pub")) {
				out.write(publicKey.getEncoded());
			}
		catch(Exception e)
		{System.out.println(e);
		}
		  //save private key 
		  try (FileOutputStream out = new FileOutputStream(public_file_path+"/PrivateKey"+ ".key")) {
			out.write(privateKey.getEncoded());
		}
		catch(Exception e){
			System.out.println(e);
		}
		  
		  sendClientID();
		
		}
		//generate keys
	     private static Map<String,Object> getRSAKeys() throws Exception {
	         KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	         keyPairGenerator.initialize(2048);
	         KeyPair keyPair = keyPairGenerator.generateKeyPair();
	         PrivateKey privateKey = keyPair.getPrivate();
	         PublicKey publicKey = keyPair.getPublic();
	  
	         Map<String, Object> keys = new HashMap<String,Object>();
	         keys.put("private", privateKey);
	         keys.put("public", publicKey);
	        
	         return keys;
	     }
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
        
		 //send public key to GM ---> GM will send to sender
	     private static void sendClientID() throws Exception {
			 
			System.out.println("Connecting GM");
			 Socket s = new Socket(groupManager, 5110);
             //send client ID
			 	System.out.println("Sending client ID:");
             PrintStream ps = new PrintStream(s.getOutputStream());
             Login l = new Login();
             String clientID = l.getUserText();
             ps.println(clientID.toCharArray());
			 ps.close();
			 s.close();
			 sendPublicKeyFile();
		 }

            
		 	public static void sendPublicKeyFile() throws Exception{
             //send publickey file to group manager
			 Socket s = new Socket(groupManager, 5120);
			 dataInputStream = new DataInputStream(s.getInputStream());
			 dataOutputStream = new DataOutputStream(s.getOutputStream());
	         System.out.println( "Sending the File to the GroupManager");
	         sendFile(public_file_path+"/PublicKey.pub");
	         dataInputStream.close();
	         dataOutputStream.close(); 
			 System.out.println("Public Key sent");
             s.close();

		 }
	      
	    	 
	     }
	      



