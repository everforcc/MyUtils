package cc.java8.onjava8.files;

// files/PathAnalysis.java
import java.nio.file.*;
import java.io.IOException;

public class PathAnalysis {
    static void say(String id, Object result) {
        System.out.print(id + ": ");
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("os.name"));
        Path p = Paths.get("D:\\environment\\code\\idea\\github\\MyUtils\\src\\main\\java\\cn\\cc\\onjava8\\files\\PathAnalysis.java").toAbsolutePath();
        say("Exists", Files.exists(p));
        say("Directory", Files.isDirectory(p));
        // 检查文件是否可执行，是否存在，有没有权限操作
        say("Executable", Files.isExecutable(p));
        // 是否可读
        say("Readable", Files.isReadable(p));
        // 是否是普通的文件 /测试文件是否是具有不透明内容的常规文件。说实话，我也不太懂常规文件指的是啥
        say("RegularFile", Files.isRegularFile(p));
        // 刻度
        say("Writable", Files.isWritable(p));
        // 不存在
        say("notExists", Files.notExists(p));
        // 隐藏
        say("Hidden", Files.isHidden(p));
        say("size", Files.size(p));
        // 文件根目录
        say("FileStore", Files.getFileStore(p));
        //
        say("LastModified: ", Files.getLastModifiedTime(p));
        // 文件归属
        say("Owner", Files.getOwner(p));
        // Content-Type: text/html; charset=utf-8   猜测是可以处理网络文件
        say("ContentType", Files.probeContentType(p));
        // 测试文件是否是符号链接
        /* 不懂啊
        * 一：符bai号链接又叫软du链接,是一类特殊的文件，这zhi个文dao件包含了另一个文件版的路径名(绝对路径权或者相对路径)。
        * 路径可以是任意文件或目录，可以链接不同文件系统的文件。
        * （链接文件可以链接不存在的文件，这就产生一般称之为”断链”的现象），
        * 链接文件甚至可以循环链接自己（类似于编程中的递归）。
        * */
        say("SymbolicLink", Files.isSymbolicLink(p));
        if(Files.isSymbolicLink(p))
            say("SymbolicLink", Files.readSymbolicLink(p));
        // Java File类具有设置文件许可权的能力，但是它并不通用。 最大的缺点是，您可以将文件权限分为两组用户-所有者和其他所有用户。 您无法为网上论坛和其他用户设置不同的文件权限。
        if(FileSystems.getDefault().supportedFileAttributeViews().contains("posix"))
            say("PosixFilePermissions",
                    Files.getPosixFilePermissions(p));
    }
}

/* 输出:
Windows 10
Exists: true
Directory: false
Executable: true
Readable: true
RegularFile: true
Writable: true
notExists: false
Hidden: false
size: 1631
FileStore: SSD (C:)
LastModified: : 2017-05-09T12:07:00.428366Z
Owner: MINDVIEWTOSHIBA\Bruce (User)
ContentType: null
SymbolicLink: false
*/

