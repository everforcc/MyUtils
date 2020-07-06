package cn.cc.java9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Yukino
 * 2020/6/1
 */
public class tt {

    public static void main(String[] args) throws FileNotFoundException {
        /*String path = "/tmp";
        File file = new File(path+"/a.txt");
        FileOutputStream fop = new FileOutputStream(file);
        System.out.println("0:"+file.exists());*/

        String path1 = File.separator+"tmp";
        File file1 = new File(path1+File.separator+"a.txt");
        FileOutputStream fop = new FileOutputStream(file1);
        System.out.println("1:"+file1.exists());
        /*String path2 = "D:/a.txt";
        File file2 = new File(path2+"/a.txt");
        FileOutputStream  fop = new FileOutputStream(file2);
        System.out.println("2:"+file2.exists());*/
        /*String path2 = "D:";
        File file3 = new File(path2+"/a.txt");
        FileOutputStream fop = new FileOutputStream(file3);
        System.out.println("3:"+file3.exists());*/

    }
}
