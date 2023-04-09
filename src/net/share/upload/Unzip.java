package net.share.upload;

import java.io.File;
import java.io.FileInputStream ;  
import java.io.FileOutputStream ;  
import java.io.IOException ;  
import java.util.zip.* ; 
public class Unzip {

        public void unzipFile( String zipFilePath, String destDir ) {  
	        File dir = new File( destDir ) ;  
	        // creating an output directory if it doesn't exist already  
	        if( !dir.exists( ) ) dir.mkdirs( ) ;  
	        FileInputStream FiS ;  
	        // buffer to read and write data in the file  
	        byte[ ] buffer = new byte[ 1024 ] ;  
	        try {  
	            FiS = new FileInputStream( zipFilePath ) ;  
	            ZipInputStream zis = new ZipInputStream( FiS ) ;  
	            ZipEntry ZE = zis.getNextEntry( ) ;  
	            while( ZE != null ) {  
	                String fileName = ZE.getName( ) ;  
	                File newFile = new File( destDir + File.separator + fileName ) ;  
	                System.out.println( " Unzipping to " + newFile.getAbsolutePath( ) ) ;  
	                // create directories for sub directories in zip  
	                new File( newFile.getParent( ) ).mkdirs( ) ;  
	                FileOutputStream fos = new FileOutputStream( newFile ) ;  
	                int len ;  
	                while ( ( len = zis.read( buffer ) )  > 0 ) {  
	                fos.write( buffer, 0, len ) ;  
	                }  
	                fos.close( ) ;  
	                // close this ZipEntry  
	                zis.closeEntry( ) ;  
	                ZE = zis.getNextEntry( ) ;  
	            }  
	            // close last ZipEntry  
	            zis.closeEntry( ) ;  
	            zis.close( ) ;  
	            
	        } catch ( IOException e ) {  
	            e.printStackTrace( ) ;  
	        }  
	      }  
          
	}  


    
