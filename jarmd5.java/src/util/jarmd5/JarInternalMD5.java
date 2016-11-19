/**
 * JarInternalMD5.java
 * Created on May 27, 2004
 * @author Amol Dharmadhikari <adharma@cs.usfca.edu>
 *
 * Computes MD5 checksums of an entire directory, expanding jar files, if there exist any in
 * the directory structure.
 */
package util.jarmd5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.jar.JarInputStream;
import java.util.jar.JarEntry;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JarInternalMD5 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage:\n\tjava JarInternalMD5 <dir-name>\n");
            System.exit(0);
        }

        JarInternalMD5 jarInternalMD5 = new JarInternalMD5(args[0]);
        jarInternalMD5.getAllFiles();
        jarInternalMD5.calculateChecksums();
    }

    public JarInternalMD5(String dirName) {
        setDirName(dirName);
        files = new LinkedList();
    }

    public void getAllFiles() {
        File file = new File(dirName);
        recurseDir(file, files);
    }

    public void recurseDir(File file, List files) {
        File[] list;
        if (file.isDirectory()) {
            list = file.listFiles();
            for (int i = 0; i < list.length; i++) {
                recurseDir(list[i], files);
            }
        } else {
            files.add(file);
        }
    }
    
    public void calculateChecksums() {
        Iterator it = files.listIterator(); 
        while (it.hasNext()) {
            File f = (File)it.next();
            if (isJarFile(f)) {
                System.out.println(f);
                getJarInternalCheckSum(f);
            } else {
                getCheckSum(f);
            }
        }        
    }
    
    public boolean isJarFile(File f) {
        Pattern p = Pattern.compile("(.*)jar");
        Matcher m = p.matcher(f.toString());
        return m.matches();
    }
    
    
    public void getJarInternalCheckSum(File f) {
        try {
            JarInputStream jin = new JarInputStream(new FileInputStream(f));
            JarEntry jEntry = null;
            while ( (jEntry = jin.getNextJarEntry()) != null) {
                getJarEntryCheckSum(jin, jEntry);
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
    
    public void getJarEntryCheckSum(JarInputStream jin, JarEntry jEntry) {
    	//Obtain a message digest object
    	MessageDigest md = null;
    	try {
    	    md = MessageDigest.getInstance("MD5");
    	} catch (NoSuchAlgorithmException e) {
    	    System.out.println("No Such Algorithm Exception occured");
    	}

    	byte[] buffer = new byte[8192];
    	int length;
    	try {
    	    while ( (length = jin.read(buffer)) != -1 )
    		md.update(buffer, 0, length);
    	} catch (IOException e) {
    		System.out.println("IO Exception occured");
    	}
    	
    	byte[] raw = md.digest();

    	//Print out the digest in base64
    	BASE64Encoder encoder = new BASE64Encoder();
    	String base64 = encoder.encode(raw);
    	//System.out.println(base64);

    	System.out.println("\t" + jEntry + " --> " + base64);
    	//System.out.println(base64);
    	return;
    }
    
    public void getCheckSum(File f) {
    	//Obtain a message digest object
    	MessageDigest md = null;
    	try {
    	    md = MessageDigest.getInstance("MD5");
    	} catch (NoSuchAlgorithmException e) {
    	    System.out.println("No Such Algorithm Exception occured");
    	}

    	//Calculate the digest for the given file
    	FileInputStream fin = null;
    	try {
    	    fin = new FileInputStream(f);
    	} catch (FileNotFoundException e) {
    	    System.out.println("File Not Found Exception occured");
    	}
            
    	byte[] buffer = new byte[8192];
    	int length;
    	try {
    	    while ( (length = fin.read(buffer)) != -1 )
    		md.update(buffer, 0, length);
    	} catch (IOException e) {
    		System.out.println("IO Exception occured");
    	}
    	
    	byte[] raw = md.digest();

    	//Print out the digest in base64
    	BASE64Encoder encoder = new BASE64Encoder();
    	String base64 = encoder.encode(raw);
    	//System.out.println(base64);
    	System.out.println(f + " --> " + base64);
    	
    }
    
    public String getDirName() {
        return this.dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    private String dirName;
	private List files;
	public static final String[] ext = {"jar", "class", "exe", "pdf"};

}