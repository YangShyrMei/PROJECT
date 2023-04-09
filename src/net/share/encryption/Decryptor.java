package net.share.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import net.share.aesAlgorithm.AeEncryption;
import net.share.aesAlgorithm.Decryption;


public class Decryptor
{
	private static Decryptor decrypter = new Decryptor();

	private static boolean deleteOriginal;
	
	private Decryptor()
	{
	}

	public static Decryptor getDecrypter(boolean originalFileDeleted)
	{
		deleteOriginal = originalFileDeleted;
		
		return decrypter;
	}
	
	public void decrypt(File src, File dst)
	{
		if (!dst.exists())
			dst.mkdir();
		if (!dst.isDirectory())
			return;

		try
		{
			if (!src.isDirectory())
			{
				copyDecrypted(src, dst);
			} else
			{
				File[] files = src.listFiles();

				System.out.println("Decrypting...");

				for (File f : files)
				{
					copyDecrypted(f, dst);
					//if(deleteOriginal) f.delete();
				}

				System.out.println(files.length + " files are decrytped");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void copyDecrypted(File source, File dest) throws IOException
	{
		FileInputStream fis = new FileInputStream(source);
		byte[] buffer = new byte[10];
		StringBuilder sb = new StringBuilder();
		while (fis.read(buffer) != -1) {
			sb.append(new String(buffer));
			buffer = new byte[10];
		}
		fis.close();
		String content = sb.toString();
		String fileName=dest.getPath().concat("/file1");
		File f = new File(fileName);
		System.out.println(content);
            try {

			Decryption de=new Decryption();
			//System.out.println("hi");
			String decryptedData = de.decrypt(content);
			
			System.out.println(decryptedData);
			FileWriter myWriter = new FileWriter(f);
	           
            myWriter.write(decryptedData);
            myWriter.close();
		}
		catch(Exception igngored) {}
	}
	public String bytesToString(byte[] data)
	{
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < data.length / 2; i++)
		{
			char c = (char) ((data[i * 2] << 8) | data[i * 2 + 1]);
			res.append(c);
		}

		return res.toString();
	}

	/*private void decryptBytes(byte[] data) // Decryption Algorithm is written into here
	{
		for (int i = 0; i < data.length; i++)
		{
			data[i] = (byte) ~data[i];
		}
	}*/

	public void copy(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;

		try
		{
			dest = new File(dest.getPath().concat("/").concat(source.getName()));

			is = new FileInputStream(source);
			os = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			int tl = 0;

			while ((length = is.read(buffer)) > 0)
			{
				tl += length;
				os.write(buffer, 0, length);
			}

			System.out.println(tl + " bytes");
		} finally
		{
			is.close();
			os.close();
		}
	}
}