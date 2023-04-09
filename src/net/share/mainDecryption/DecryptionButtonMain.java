package net.share.mainDecryption;

import java.io.File;

import net.share.encryption.Decryptor;
import net.share.encryption.SymmetricEncryption;

public class DecryptionButtonMain {
	public static void main(String args[]) {
		Decryptor de = Decryptor.getDecrypter(true);
		File src = new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/encrypted_file");
		File dest=new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/dest_file");
		de.decrypt(src, dest);
	}

}
