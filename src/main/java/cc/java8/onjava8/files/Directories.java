package cc.java8.onjava8.files;

// files/Directories.java

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Directories {
    static String path = "E:\\onjava8\\test";
    static Path test = Paths.get(path);
    static String sep = FileSystems.getDefault().getSeparator();
    static List<String> parts = Arrays.asList("foo", "bar", "baz", "bag");

    static Path makeVariant() {
        // 这个方法是每次把最后的放到最前面去
        // 这个里面应该有很多方法可以直接用，也可以看实现方法
        Collections.rotate(parts, 1);
        parts.forEach(System.out::println);
        return Paths.get(path, String.join(sep, parts));
    }

    static void refreshTestDir() throws Exception {
        if (Files.exists(test)){
            System.out.println("exists");
            RmDir.rmdir(test); // 这里会删除所有的文件
        }
        if(!Files.exists(test)) {
            System.out.println("not exists");
            Files.createDirectory(test);
        }
    }

    public static void main(String[] args) throws Exception {
        // 创建和删除文件
        refreshTestDir();
        // 在上面的路径追加文件名，然后创建，比之前的 new File好点，之前的会创建个目录出来
        Files.createFile(test.resolve("Hello.txt"));
        Path variant = makeVariant();
        try {
            // 只能创建单层目录
            Files.createDirectory(variant);
        } catch(Exception e) {
            System.out.println("Nope, that doesn't work.");
        }
        populateTestDir();
        Path tempdir = Files.createTempDirectory(test, "DIR_");
    }

    public static void main1(String[] args) throws Exception {
        refreshTestDir();
        Files.createFile(test.resolve("Hello.txt"));
        Path variant = makeVariant();
        // Throws exception (too many levels):
        try {
            Files.createDirectory(variant);
        } catch(Exception e) {
            System.out.println("Nope, that doesn't work.");
        }
        populateTestDir();
        Path tempdir = Files.createTempDirectory(test, "DIR_");
        Files.createTempFile(tempdir, "pre", ".non");
        Files.newDirectoryStream(test).forEach(System.out::println);
        System.out.println("*********");
        Files.walk(test).forEach(System.out::println);
    }

    static void populateTestDir() throws Exception  {
        for(int i = 0; i < parts.size(); i++) {
            Path variant = makeVariant();
            if(!Files.exists(variant)) {
                Files.createDirectories(variant);
                System.out.println(Paths.get("").toAbsolutePath());
                // 项目中的Paths是当前项目
                Files.copy(Paths.get("pom.xml"),
                        variant.resolve("1.xml"));
                Files.createTempFile(variant, null, null);
            }
        }
    }
}

/* 输出:
Nope, that doesn't work.
test\bag
test\bar
test\baz
test\DIR_5142667942049986036
test\foo
test\Hello.txt
*********
test
test\bag
test\bag\foo
test\bag\foo\bar
test\bag\foo\bar\baz
test\bag\foo\bar\baz\8279660869874696036.tmp
test\bag\foo\bar\baz\File.txt
test\bar
test\bar\baz
test\bar\baz\bag
test\bar\baz\bag\foo
test\bar\baz\bag\foo\1274043134240426261.tmp
test\bar\baz\bag\foo\File.txt
test\baz
test\baz\bag
test\baz\bag\foo
test\baz\bag\foo\bar
test\baz\bag\foo\bar\6130572530014544105.tmp
test\baz\bag\foo\bar\File.txt
test\DIR_5142667942049986036
test\DIR_5142667942049986036\pre7704286843227113253.non
test\foo
test\foo\bar
test\foo\bar\baz
test\foo\bar\baz\bag
test\foo\bar\baz\bag\5412864507741775436.tmp
test\foo\bar\baz\bag\File.txt
test\Hello.txt
*/
