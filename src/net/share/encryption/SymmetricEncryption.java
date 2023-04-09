package net.share.encryption;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;


import net.share.aesAlgorithm.AeEncryption;
import net.share.aesAlgorithm.Decryption;


public class SymmetricEncryption
{
	private static SymmetricEncryption encrypter = new SymmetricEncryption();

	private static boolean deleteOriginal;
	private static Cipher details;
	private static SecretKey key;
	
	public SymmetricEncryption()
	{
	}

	public static SymmetricEncryption getEncrypter(boolean originalFileDeleted)
	{
		deleteOriginal = originalFileDeleted;
		
		return encrypter;
	}

	public void encrypt(File src, File dst)
	{
		if (!dst.exists())
			dst.mkdir();
		if (!dst.isDirectory())
			return;

		try
		{
			if (!src.isDirectory())
			{
				copyEncrypted(src, dst);
			} else
			{
				File[] files = src.listFiles();

				System.out.println("Encrypting...");

				for (File f : files)
				{
					copyEncrypted(f, dst);
					//if(deleteOriginal) f.delete();
				}

				System.out.println( " file is encrypted");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void copyEncrypted(File source, File dest) throws IOException
	{
		//InputStream is = null;
		//OutputStream os = null;
        String fileName=dest.getPath().concat("/").concat("encryptedFile.encrypt");
		dest = new File(fileName);
		FileInputStream fis = new FileInputStream(source);
		byte[] buffer = new byte[10];
		StringBuilder sb = new StringBuilder();
		while (fis.read(buffer) != -1) {
			sb.append(new String(buffer));
			buffer = new byte[10];
		}
		fis.close();

		String content = sb.toString();
		  try {
	            AeEncryption aes_encryption = new AeEncryption();
				System.out.println("To be encrypted");
	         
	            String encryptedData =aes_encryption.encrypt(content);

	            System.out.println("Encrypted Data : " + encryptedData);
	            FileWriter myWriter = new FileWriter(fileName);
	           
	            myWriter.write(encryptedData);
	            myWriter.close();
	            
	        } catch (Exception ignored) {
	        	System.out.println(ignored);
	        }
		
		/*try
		{
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			 AeEncryption aes_encryption = new AeEncryption();

			os.write(new byte[] { (byte) source.getName().length() });
			os.write(stringToByte(source.getName()));

			byte[] buffer = new byte[1024];

			int length;

			while ((length = is.read(buffer)) > 0)
			{
				System.out.println(buffer);
				aes_encryption.encrypt(buffer);
				os.write(buffer, 0, length);
			}

		} catch(Exception e){}
		finally
		{
			is.close();
			os.close();
		}*/
	}

	/*private void encryptBytes(byte[] data) // Encryption Algorithm is written into here
	{
		for (int i = 0; i < data.length; i++)
		{
			data[i] = (byte) ~data[i];
		}
	}*/
	public byte[] stringToByte(String data)
	{
		char[] ca = data.toCharArray();
		byte[] res = new byte[ca.length * 2]; // Character.BYTES = 2;

		for (int i = 0; i < res.length; i++)
		{
			res[i] = (byte) ((ca[i / 2] >> (8 - (i % 2) * 8)) & 0xff);
		}

		return res;
	}

	public String getRandomName(int length, String extend)
	{
		Random r = new Random();
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < length; i++)
		{

			char c = 'a';
			int width = 'z' - 'a';

			if (r.nextInt(3) == 0)
			{
				c = 'A';
				width = 'Z' - 'A';
			}
			if (r.nextInt(3) == 1)
			{
				c = '0';
				width = '9' - '0';
			}

			res.append((char) (c + r.nextInt(width)));
		}

		res.append(".").append(extend);

		return res.toString();
	}

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
	public Cipher getDetails()
	{
		return details;
	}
	public SecretKey getInfoKey()
	{
		return key;
	}
}