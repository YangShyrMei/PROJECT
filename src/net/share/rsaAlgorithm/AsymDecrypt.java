package net.share.rsaAlgorithm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import net.share.client.GenerateKeys;

import javax.crypto.Cipher;
import javax.sound.midi.SysexMessage;

import net.share.encryption.Decryptor;

public class AsymDecrypt {
	

	public void asymDecrypt(String encryptedKey) throws Exception{

		//read file into String
		System.out.println(Path.of(encryptedKey));
		String encrypt_public_key = new String(Files.readAllBytes(Path.of(encryptedKey)));
		String project_root = System.getProperty("user.dir");
		String send_public_private_key = System.getProperty("user.dir")+"/sendPublicPrivateKey";
		String decrypted_key_path = project_root+"/decrypted_key";
		String encrypted_file_path = project_root+"/encrypted_file";
		String dest_file_path = project_root+"/dest_file";

		//decrypt string using own private key
		//String privateKey = new String(Files.readAllBytes(Path.of("/Users/yangshyrmei/Documents/Project/PublicBasedEncryptionFileSharingInClouds/sendPublicPrivateKey/PrivateKey.txt")));
		
		//convert string into private key
		//privateKey = privateKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
		//KeyFactory kf = KeyFactory.getInstance("RSA");
		//PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        //PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);
		System.out.println("Going for decrypting key");
		//getting private key file

		byte[] bytes = Files.readAllBytes(Paths.get(send_public_private_key+"/PrivateKey.key"));
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey pvt = kf.generatePrivate(ks);
		String decryptedKey = decryptMessage(encrypt_public_key, pvt);
        System.out.println("Decrpyted Key");
		Files.writeString(Paths.get(decrypted_key_path+"/secretKey.txt"),decryptedKey,StandardCharsets.UTF_8);
		Decryptor de = Decryptor.getDecrypter(true);
		File src = new File(encrypt_public_key);
		File dest=new File(dest_file_path);
		de.decrypt(src, dest);


	}


	 private static String decryptMessage(String encryptedText, PrivateKey privateKey) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
	    }
}
