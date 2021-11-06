package cc.core.file.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author c.c. 2021-11-06
 */
public class FileSplitUtils {

    public static void main(String[] args) {
        try {
            //split("C:\\Users\\Administrator\\Desktop\\boc\\test\\boc.zip","C:\\Users\\Administrator\\Desktop\\boc\\test\\",4L);
            //Long size = 3000L*(1024*1024);
//            int size_i = 3000*(1024*1024);
//            System.out.println(size_i);
//            long size_l = 3000L*(1024*1024);
//            System.out.println(size_l);
//            Long size_L = 3000L*(1024*1024);
//            System.out.println(size_L);
            //System.out.println(size);
            //System.out.println(Long.MAX_VALUE);
            String path = "C:\\Users\\Administrator\\Desktop\\boc\\test\\split";
            merge(path,path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //拆分文件指定大小
    public static void split(String srcFilePath,Long fileSizeMB){
        try {
            split(srcFilePath,srcFilePath,fileSizeMB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void split(String srcFilePath,String targetFilePath,Long fileSizeMB) throws IOException {
        File srcFile = new File(srcFilePath);
        File targetFile = new File(targetFilePath);
        // 判断目标文件是否存在
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //计算： 拆多少份？  拆分后的文件名， partSize(MB)
        Long len = srcFile.length();
        Long size = fileSizeMB * (1024 * 1024);
        boolean isOdd=false;//是否：不能整除

        int fileCount = 0;
        //boolean
        if(len%size==0){
            fileCount=(int)(len/size);
        }else {
            fileCount=(int)(len/size)+1;
            isOdd=true;//不能整除
        }

        // 使用：RandomAccessFile--->读全部文件---->写部分文件到： files
        RandomAccessFile rf = new RandomAccessFile(srcFile,"rw");
        // 创建: fileCount个文件
        File[] files=new File[fileCount];
        for(int i=1;i<=fileCount;i++) {
            files[i-1]=new File(targetFile,srcFile.getName()+"_"+i);
            //byte[]---数组大小==内容大小， 最后一个文件特殊： 大小待定？
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(files[i-1]));

            //容器： rf放内容到数组中
            byte[] bytes;
            if(i==fileCount && isOdd==true) {// 最后一个文件
                bytes = new byte[new Long(len-(fileCount-1)*size).intValue()];
            }else {
                bytes = new byte[new Long(size).intValue()];
            }
            rf.read(bytes);
            out.write(bytes);

            System.out.println("fileName -->"+files[i-1].getName() +",\t fileSize: "+files[i-1].length());
        }
        System.out.println("拆分 完成....");
    }

// String srcFilePath,String targetFilePath
    public static void merge(String srcFilePath, String targetFilePath) throws Exception {
        // 判断目标文件夹：是否存在
        File srcDir = new File(srcFilePath);
        File todir = new File(targetFilePath);
        if (!todir.exists()) {
            todir.mkdirs();
        }

        // 遍历： 该目录的所有子文件， 合并为一个文件
        File[] fs = srcDir.listFiles();

        // -------------------查找目标文件名=> 原文件名 start-------------------
        String name = null;
        for (int i = 0; i < fs.length; i++) {

            // 获取文件名a.tar.gz_1
            String thisname = fs[i].getName();
            if (fs[i].isFile() && thisname.lastIndexOf("_") != -1 && thisname.endsWith("" + 1)) {
                name = fs[i].getName().replaceAll("_[a-zA-Z0-9]+", "");
                break;
            }
        } // -------------------查找目标文件名=>原文件名 end-------------------

        // 按照文件名排序
        Arrays.sort(fs, new Comparator<File>() {// 使得数组有序： 按后缀_1,_2, _3...排序

            @Override
            public int compare(File o1, File o2) {
                // 文件排序： 1,2,3,4
                int index1 = o1.getName().indexOf("_");
                int num1 = Integer.parseInt(o1.getName().substring(index1 + 1));

                int index2 = o2.getName().indexOf("_");
                int num2 = Integer.parseInt(o2.getName().substring(index2 + 1));

                return num1 - num2;
            }
        });

        // -------------顺序合并：多个文件 start -----------------------
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(todir, name)));
        for (int i = 0; i < fs.length; i++) {

            // 文件拷贝： 合并为一个文件
            String thisname = fs[i].getName();

            if (fs[i].isFile() && thisname.endsWith("" + (i + 1))) {// 再次检验： 文件后缀 是否以 i+1结尾
                System.out.println("文件顺序：  " + (i + 1) + ", file name====>" + thisname);
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fs[i]));

                int len = 0;
                byte[] bs = new byte[1024];
                while ((len = in.read(bs)) != -1) {
                    out.write(bs, 0, len);
                    out.flush();
                }
                // 关闭资源
                in.close();
            } // if
        } // for
        // -------------顺序合并：多个文件 end -----------------------
        out.close();
        System.out.println("合并 完成....");
    }

}
