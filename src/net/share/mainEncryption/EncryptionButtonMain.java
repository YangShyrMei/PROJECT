package net.share.mainEncryption;

import java.io.File;

import net.share.encryption.SymmetricEncryption;

public class EncryptionButtonMain {
	public static void main(String args[]) {
		SymmetricEncryption en = SymmetricEncryption.getEncrypter(true);
		File src = new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/src_file");
		File dest = new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/encrypted_file");
		en.encrypt(src, dest);
	}

}
