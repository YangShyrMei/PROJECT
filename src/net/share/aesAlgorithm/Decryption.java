package net.share.aesAlgorithm;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import net.share.aesAlgorithm.AeEncryption;



public class Decryption {
	private SecretKey key;
    public final int KEY_SIZE = 128;
    public final int DATA_LENGTH = 128;
	static String decrypted_key_path = System.getProperty("user.dir")+"/decrypted_key";
  
	
	public static SecretKey convertStringToSecretKeyto(String encodedKey) {
	    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	    return originalKey;
	}
	  private byte[] decode(String data)throws Exception {
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
		  
	    
	public String decrypt(String encryptedData) throws Exception{
		try {
			byte[] ciphertext = decode(encryptedData);
			ByteBuffer byteBuffer = ByteBuffer.wrap(ciphertext);
            byte[] iv = new byte[12];
            byteBuffer.get(iv);
            byte[] encrypted = new byte[byteBuffer.remaining()];
            byteBuffer.get(encrypted);
			
            Path fileName
            = Path.of(decrypted_key_path+"/secretKey.txt");
            String str = Files.readString(fileName);
            key=convertStringToSecretKeyto(str);
            
            Cipher decryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(DATA_LENGTH, iv));
            return new String(decryptCipher.doFinal(encrypted)); // here was the mistake
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return "";
        
	}
	
/*	public String decrypt(String encryptedData) throws Exception {
		System.out.println(encryptedData);
		byte[] dataInBytes = decode(encryptedData);
        System.out.println(encryptedData);
        SecretKey key=convertStringToSecretKeyto("TXsooaHyksC3pIlhtioPqA==");
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        
		GCMParameterSpec spec = new GCMParameterSpec(128, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
        return new String(decryptedBytes);
    }
	
	public static SecretKey convertStringToSecretKeyto(String encodedKey) {
	    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
	    return originalKey;
	}
	 private byte[] decode(String data) {
	        return Base64.getDecoder().decode(data);
	    }
	  
	  */  

}
