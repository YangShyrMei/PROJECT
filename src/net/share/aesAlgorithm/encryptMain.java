package net.share.aesAlgorithm;

import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import net.share.rsaAlgorithm.AsymEncrypt;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
public class encryptMain {
	 private SecretKey key;
	    public final int KEY_SIZE = 128;
	    public final int DATA_LENGTH = 128;
	    public Cipher encryptionCipher;
	   
	public static void main(String[] args) {
		try {
            encryptMain ec=new encryptMain();
            SecretKey key=ec.init();
            
            String encryptedData =ec.encrypt("Hello, welcome to the encryption world");
          
            String decryptedData = ec.decrypt(encryptedData);
         

            System.out.println("Encrypted Data : " + encryptedData);
            System.out.println("Decrypted Data : " + decryptedData);
        } catch (Exception ignored) {
        }
    }

		
		
		
			   

			    public SecretKey init() throws Exception {
			        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			        keyGenerator.init(KEY_SIZE);
			        key = keyGenerator.generateKey();
			        return key;
			    }
			    public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
			        byte[] rawData = secretKey.getEncoded();
			        String encodedKey = Base64.getEncoder().encodeToString(rawData);
			        return encodedKey;
			    }
			    
			    public String encrypt(String data) throws Exception {
			        byte[] dataInBytes = data.getBytes();
			        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
			        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
			        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
			        //AsymEncrypt a1=new AsymEncrypt();
			       //a1.asymEncry(convertSecretKeyToString(key));

			 	        return encode(encryptedBytes);
			    }
			    public String decrypt(String encryptedData) throws Exception {
			        byte[] dataInBytes = decode(encryptedData);
			        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
			        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
			        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
			        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
			        return new String(decryptedBytes);
			    }
			    private String encode(byte[] data) {
			        return Base64.getEncoder().encodeToString(data);
			    }

			    private byte[] decode(String data) {
			        return Base64.getDecoder().decode(data);
			    }
			  

        
}
