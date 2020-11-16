package cn.cc.onjava8.files;

// files/AddAndSubtractPaths.java
import java.nio.file.*;
import java.io.IOException;

public class AddAndSubtractPaths {
    // normalize()方法可以标准化路径，它会处理路径中的相对路径，去除“.”“..”

    //D:\environment\code\idea\github 这个表示根目录，项目的根目录 第二个参数是可变长参数， .. 代表上一级
    //static Path base = Paths.get("..", "").toAbsolutePath().normalize();
    // "..", ".."
    static Path base = Paths.get("..", "").toAbsolutePath().normalize();

    static void show(int id, Path result) {
        // Tells whether or not this path is absolute. 是否为绝对路径
        if(result.isAbsolute())
            // Constructs a relative path between this path and a given path. 在此路经构造一个绝对路径
            // base 路径 D:\environment\code\idea\github
            // 结果路径 MyUtils\src\main\java\cn\cc\onjava8\files\AddAndSubtractPaths.java
            System.out.println("1--(" + id + ")r " + base.relativize(result));
        else
            System.out.println("2--(" + id + ") " + result);
        try {
            // 真实全路径
            System.out.println("3--RealPath: " + result.toRealPath());
        } catch(IOException e) {
            System.out.println("4--"+e);
        }
    }

    public static void main1(String[] args) {
        /*System.out.println(base);
        Path p = Paths.get("D:\\environment\\code\\idea\\github\\MyUtils\\src\\main\\java\\cn\\cc\\onjava8\\files\\AddAndSubtractPaths.java").toAbsolutePath();
        show(1, p);

        // getParent()上一级路径
        Path convoluted = p.getParent().getParent();
        show(2, convoluted);
        convoluted = convoluted.resolve("strings").resolve("..");
        show(2, convoluted);
        convoluted = convoluted.resolve(p.getParent().getFileName());
        show(2, convoluted);*/
       /* Path p0 = Paths.get("");
        show(1,p0);
        Path p1 = Paths.get("..");
        show(1,p1);
        Path p2 = Paths.get("..", "..");
        show(1,p2);*/
        Path p3 = Paths.get(".").toAbsolutePath();
        show(1,p3);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(base);
        Path p = Paths.get("D:\\environment\\code\\idea\\github\\MyUtils\\src\\main\\java\\cn\\cc\\onjava8\\files\\AddAndSubtractPaths.java").toAbsolutePath();
        show(1, p);

        // getParent()上一级路径 .resolve("strings") 在路径后面追加\strings 组装路径有用  p.getParent().getFileName()文件或目录名
        Path convoluted = p.getParent().getParent()
                .resolve("strings").resolve("..")
                .resolve(p.getParent().getFileName());
        show(2, convoluted);
        // 返回一个路径，该路径是冗余名称元素的消除。  冗余元素 可能是这个 strings\.. 先进入strings目录再..就又返回上一级了
        show(3, convoluted.normalize());
        // Paths 表示 D:\environment\code\idea\github\MyUtils  .. .. 上回两级
        Path p2 = Paths.get("..", "..");
        show(4, p2);
        show(5, p2.normalize());
        show(6, p2.toAbsolutePath().normalize());
        // . 表示在当前项目根目录下追加 MyUtils\. 但是绝对路径不变
        Path p3 = Paths.get(".").toAbsolutePath(); // 疑问，追加这个点并不能算路径
        // p3 . p2 .. .. 合并 p4 .\..\..
        Path p4 = p3.resolve(p2);
        show(7, p4);
        // p3 的点并不能算路径 所以 根路径 和 .. 抵消后剩下一个..
        show(8, p4.normalize());
        Path p5 = Paths.get("").toAbsolutePath();
        show(9, p5);
        // 将给定的路径字符串转换为 Path ，并按照 resolveSibling方法指定的方式将其解析为该路径的 parent路径。
        show(10, p5.resolveSibling("strings"));
        // 将路径字符串或从路径字符串连接起来的一系列字符串转换为Path 。 如
        show(11, Paths.get("nonexistent"));
    }
}

/* 输出:
Windows 10
C:\Users\Bruce\Documents\GitHub
(1)r onjava\
ExtractedExamples\files\AddAndSubtractPaths.java
RealPath: C:\Users\Bruce\Documents\GitHub\onjava\
ExtractedExamples\files\AddAndSubtractPaths.java
(2)r on-java\ExtractedExamples\strings\..\files
RealPath: C:\Users\Bruce\Documents\GitHub\onjava\
ExtractedExamples\files
(3)r on-java\ExtractedExamples\files
RealPath: C:\Users\Bruce\Documents\GitHub\onjava\
ExtractedExamples\files
(4) ..\..
RealPath: C:\Users\Bruce\Documents\GitHub\on-java
(5) ..\..
RealPath: C:\Users\Bruce\Documents\GitHub\on-java
(6)r on-java
RealPath: C:\Users\Bruce\Documents\GitHub\on-java
(7)r on-java\ExtractedExamples\files\.\..\..
RealPath: C:\Users\Bruce\Documents\GitHub\on-java
(8)r on-java
RealPath: C:\Users\Bruce\Documents\GitHub\on-java
(9)r on-java\ExtractedExamples\files
RealPath: C:\Users\Bruce\Documents\GitHub\onjava\
ExtractedExamples\files
(10)r on-java\ExtractedExamples\strings
RealPath: C:\Users\Bruce\Documents\GitHub\onjava\
ExtractedExamples\strings
(11) nonexistent
java.nio.file.NoSuchFileException:
C:\Users\Bruce\Documents\GitHub\onjava\
ExtractedExamples\files\nonexistent
*/

