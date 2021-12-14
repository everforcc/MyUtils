package cc.core.file.utils.java8;

import cc.core.file.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesUtils {

    /**
     * 总结下java8的api
     */

    public static void copy(InputStream inputStream, String filePath, String fileName) throws IOException {
        //上述示例代码冗长，幸运的是在Java7中Files类包含处理IO操作的助手方法。可以使用File.copy()方法从InputStream中读取所有字节，然后复制至本地文件：
        //以下代码可以正常工作，但缺点是字节被缓冲到内存中。Java为我们提供了NIO包，它有方法在两个通道之间直接传输字节，而无需缓冲。下面我们会详细讲解。
        fileName = FileUtils.checkFileName(fileName);
        filePath = FileUtils.checkFilePath(filePath);
        Files.copy(inputStream, Paths.get(filePath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
    }

}
