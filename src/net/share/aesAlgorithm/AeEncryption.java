package net.share.aesAlgorithm;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import net.share.rsaAlgorithm.AsymEncrypt;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class AeEncryption {

	    private SecretKey key;
	    public final int KEY_SIZE = 128;
	    public final int DATA_LENGTH = 128;
	    public static Cipher encryptCipher;
	    public static byte[] iv;
	    
	   
	   /* public SecretKey init() throws Exception {
	        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	        keyGenerator.init(KEY_SIZE);
	        key = keyGenerator.generateKey();
	        return key;
	    }*/
	    public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
	        byte[] rawData = secretKey.getEncoded();
	        String encodedKey = Base64.getEncoder().encodeToString(rawData);
	        return encodedKey;
	    }
	   
	    public String encrypt(String data) throws Exception {
	    	try {
				System.out.println("1");
	    		byte[] dataInBytes = data.getBytes();
				System.out.println("1");
	    		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				System.out.println("1");
		        keyGenerator.init(KEY_SIZE);
				System.out.println("1");
		        key = keyGenerator.generateKey();
				System.out.println("1");
	    		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
				System.out.println("1");
	    		byte[] iv=new byte[12];
				System.out.println("1");
	    		random.nextBytes(iv);
				System.out.println("1");
	    		AsymEncrypt as=new AsymEncrypt();
	    		as.asymEncry(convertSecretKeyToString(key));
				System.out.println("1");
	    		encryptCipher=Cipher.getInstance("AES/GCM/NoPadding");
				System.out.println("1");
	    		encryptCipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(DATA_LENGTH, iv));
				System.out.println("1");
	            byte[] encrypted = encryptCipher.doFinal(dataInBytes);
				System.out.println("1");
	            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encrypted.length);
				System.out.println("1");
	            byteBuffer.put(iv);
				System.out.println("1");
	            byteBuffer.put(encrypted);
				System.out.println("1");
	            return encode(byteBuffer.array());

	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println(e);
	    	}
	    	return "";
	    }
	  /*  public String decrypt(String encryptedData) throws Exception {
	        byte[] dataInBytes = decode(encryptedData);
	        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
	        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
	        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
	        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
	        return new String(decryptedBytes);
	    }*/
	    private String encode(byte[] data) {
	        return Base64.getEncoder().encodeToString(data);
	    }

	    private byte[] decode(String data) {
	        return Base64.getDecoder().decode(data);
	    }
	    
	   /* public static void main(String[] args) {
	        try {
	            AeEncryption aes_encryption = new AeEncryption();
	            aes_encryption.init();
	            String encryptedData = aes_encryption.encrypt("Hello, welcome to the encryption world");
	            String decryptedData = aes_encryption.decrypt(encryptedData);

	            System.out.println("Encrypted Data : " + encryptedData);
	            System.out.println("Decrypted Data : " + decryptedData);
	        } catch (Exception ignored) {
	        }
	    }*/
	}

