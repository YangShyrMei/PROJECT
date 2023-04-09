package net.share.rsaAlgorithm;

import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
 
import javax.crypto.Cipher;
 
// Java 8 example for RSA encryption/decryption.
// Uses strong encryption with 2048 key size.
public class AsymEncrypt {
    static String project_root = System.getProperty("user.dir");
    static String received_public_key_path = project_root+"/receivedPublicKey";
    static String encrypted_key_path = project_root+"/encrypted_key";
  
	 private static byte[] decode(String data)throws Exception {
		  byte[] res=null;
		  try {
			  Base64.Decoder decoder=Base64.getMimeDecoder();
			  res=decoder.decode(data);
			  
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
	        return res;
	        }
		  
    public static void asymEncry(String plainText) throws Exception {
       
 
        // Generate public and private keys using RSA - Group Manager
       /*Map<String, Object> keys = getRSAKeys();
 
        PrivateKey privateKey = (PrivateKey) keys.get("private");
        PublicKey publicKey = (PublicKey) keys.get("public");
        */
        byte[] bytes = Files.readAllBytes(Paths.get(received_public_key_path+"/PublicKey.pub"));
        X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pub = kf.generatePublic(ks);

    
        String encryptedText = encryptMessage(plainText, pub);
       // String descryptedText = decryptMessage(encryptedText, privateKey);
 
        System.out.println("input:" + plainText);
        System.out.println("encrypted:" + encryptedText);
        File f1 = new File(encrypted_key_path+"/encryptedPublicKey.txt");
        
        try{
            FileWriter fw=new FileWriter(encrypted_key_path+"/encryptedPublicKey.txt");    
           fw.write(encryptedText);    
           fw.close();    
        }
        catch(Exception e){
            System.out.println(e);
        }
 
    } 
 
    // Encrypt using RSA private key
    private static String encryptMessage(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }
 
}