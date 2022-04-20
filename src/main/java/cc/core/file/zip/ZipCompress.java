package cc.core.file.zip;

// compression/ZipCompress.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Uses Zip compression to compress any
// number of files given on the command line
// {java ZipCompress ZipCompress.java}
// {VisuallyInspectOutput}

import cc.sysconstant.ConstantFile;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author onjava8
 * @date 2020/12/15
 */
public class ZipCompress {

    private static String zipFilePath = ConstantFile.L1_javaFilePath + "/java/onjava8/zip/test.zip";
    private static String[] strings = {ConstantFile.L1_javaFilePath + "/java/onjava8/zip/testzip.txt"};
    public static void main(String[] args) {
        /*t1(strings);
        t2(strings);*/
        t3();
    }

    static void t1(String[] args){
        try (
                FileOutputStream f =new FileOutputStream(zipFilePath);
                CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
                ZipOutputStream zos = new ZipOutputStream(csum);
                BufferedOutputStream out = new BufferedOutputStream(zos)
        ) {
            zos.setComment("A test of Java Zipping");
            // No corresponding getComment(), though.
            for (String sourceFile : args) {
                System.out.println("Writing file " + sourceFile);
                try (InputStream in = new BufferedInputStream(new FileInputStream(sourceFile))) {
                    zos.putNextEntry(new ZipEntry("parentPath/targetFile.txt"));
                    int c;
                    while ((c = in.read()) != -1) {
                        out.write(c);
                    }
                }
                out.flush();
            }
            // Checksum valid only after the file is closed!
            System.out.println("Checksum: " + csum.getChecksum().getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void t2(String[] args){
        // Now extract the files:
        System.out.println("Reading file : " + zipFilePath);
        try (
                FileInputStream fi = new FileInputStream(zipFilePath);
                CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
                ZipInputStream in2 = new ZipInputStream(csumi);
                BufferedInputStream bis = new BufferedInputStream(in2)
        ) {
            ZipEntry ze;
            while ((ze = in2.getNextEntry()) != null) {
                System.out.println("Reading file " + ze);
                int x;
                System.out.printf("content:");
                while ((x = bis.read()) != -1) {
                    // 读取文件内容
                    System.out.write(x);
                }
            }
            System.out.println();
            if (args.length == 1) {
                System.out.println("Checksum: " + csumi.getChecksum().getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void t3(){
        // Alternative way to open and read Zip files:
        try (
                ZipFile zf = new ZipFile(zipFilePath)
        ) {
            Enumeration e = zf.entries();
            while (e.hasMoreElements()) {
                ZipEntry ze2 = (ZipEntry) e.nextElement();
                System.out.println("File: " + ze2);
                // ... and extract the data as before
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

