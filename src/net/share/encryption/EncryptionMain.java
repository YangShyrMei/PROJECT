package net.share.encryption;
import java.io.File;

public class EncryptionMain
{
	public static void main(String[] args)
	{
		SymmetricEncryption en = SymmetricEncryption.getEncrypter(true);
		//Decryptor de = Decryptor.getDecrypter(true);
		
		File src = new File("C:/Users/Dell/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/src_file");
		//File dest=new File("C:/Users/yangs/eclipse-workspace/PublicBasedEncryptionFileSharingInClouds/dest_file");
		en.encrypt(src, src);
     	//de.decrypt(src, dest);
		
	}
}


