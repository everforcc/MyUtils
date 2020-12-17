package cn.cc.core.file.zip;

// compression/ZipCompress.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Uses Zip compression to compress any
// number of files given on the command line
// {java ZipCompress ZipCompress.java}
// {VisuallyInspectOutput}

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author onjava8
 * @date 2020/12/15
 */
public class ZipCompress {

    // https://www.cnblogs.com/liaidai/p/7592788.html

    public static void main(String[] args) {
        try (
                FileOutputStream f = new FileOutputStream("E:\\java\\onjava8\\zip\\test.zip");
                CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
                ZipOutputStream zos = new ZipOutputStream(csum);
                BufferedOutputStream out = new BufferedOutputStream(zos);
        ) {
            zos.setComment("A test of Java Zipping");
            // No corresponding getComment(), though.
            //for (String arg : args) {
                System.out.println("Writing file " + "E:\\java\\onjava8\\zip\\test.zip");
                try (
                        InputStream in = new BufferedInputStream(
                                new FileInputStream("E:\\java\\onjava8\\zip\\test.zip"))
                ) {
                    zos.putNextEntry(new ZipEntry("testtest.zip"));
                    int c;
                    while ((c = in.read()) != -1)
                        out.write(c);
                }
                out.flush();
            //}
            // Checksum valid only after the file is closed!
            System.out.println(
                    "Checksum: " + csum.getChecksum().getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Now extract the files:
        System.out.println("Reading file");
        try (
                FileInputStream fi =
                        new FileInputStream("E:\\java\\onjava8\\zip\\test.zip");
                CheckedInputStream csumi =
                        new CheckedInputStream(fi, new Adler32());
                ZipInputStream in2 = new ZipInputStream(csumi);
                BufferedInputStream bis =
                        new BufferedInputStream(in2)
        ) {
            ZipEntry ze;
            while ((ze = in2.getNextEntry()) != null) {
                System.out.println("Reading file " + ze);
                int x;
                while ((x = bis.read()) != -1)
                    System.out.write(x);
            }
            if (args.length == 1)
                System.out.println(
                        "Checksum: " + csumi.getChecksum().getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Alternative way to open and read Zip files:
        try (
                ZipFile zf = new ZipFile("E:\\java\\onjava8\\zip\\test.zip")
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

