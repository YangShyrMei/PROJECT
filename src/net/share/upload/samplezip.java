package net.share.upload;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class samplezip {
    String project_root = System.getProperty("user.dir");
    String zipped_path = System.getProperty("user.dir")+"/zipped";
    String encrypted_key_path = project_root+"/encrypted_key";
    String encrypted_file_path = project_root+"/encrypted_file";

    public samplezip() throws IOException {
        String file1 = encrypted_file_path+"/encryptedFile.encrypt";
        String file2 = encrypted_key_path+"/encryptedPublickey.txt";
        final List<String> srcFiles = Arrays.asList(file1, file2);

        final FileOutputStream fos = new FileOutputStream(zipped_path+"/compressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }

        zipOut.close();
        fos.close();
    }
}