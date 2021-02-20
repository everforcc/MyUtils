package cn.cc.core.file.utils;

import cn.cc.entity.FileSysMsg;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtils {

    private static List<FileSysMsg> fileSysMsgList=new ArrayList<FileSysMsg>();
    private FileSysMsg fileSysMsg;

    private static Set<String> stringSet = new HashSet<>();

    /**
     * 遍历指定文件夹下的所有文件
     * @param path
     * @return
     */
    public List<FileSysMsg> getFileList(String path) {

        // 先判断给的是否是文件夹?

        // 根据路径获取文件夹
        File file = new File(path);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件
                if (fileList[i].isFile()) {
                    //每一轮都要重新实例化
                    fileSysMsg=new FileSysMsg();

                    /***********************************************/
                    // 具体问题重新定制

                    //将每一轮的数据都存到集合中
                    fileSysMsgList.add(fileSysMsg);
                    // 放到里面来，外面每次都会重新new
                }

                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    // 递归寻找第一级目录下的所有文件
                    getFileList(path+"\\" + fileList[i].getName());
                }
            }
        }
        return fileSysMsgList;
    }

    public static void recursion(String path, IFileUtils iFileUtils,String... strAry) {
        // 先判断给的是否是文件夹?
        // 根据路径获取文件夹
        File file = new File(path);
        // 获取该文件夹下的所有文件
        File[] fileList = file.listFiles();
        // 便利所有的文件夹
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                // 如果是文件
                if (fileList[i].isFile()) {
                    // 具体问题重新定制,用接口来操作
                    iFileUtils.accept(fileList,i,strAry);
                    //System.err.println(path + " >>>总数: " + fileList.length + " >>>当前: " + i + fileList[i].getAbsolutePath());
                }
                //如果是文件夹
                if (fileList[i].isDirectory()) {
                    //System.out.println(" --- " + fileList[i].getAbsolutePath());
                    // 递归寻找第一级目录下的所有文件
                    recursion(fileList[i].getAbsolutePath(),iFileUtils,strAry);
                    // 那就会进入下一个文件夹，一直重复动作,当前路径加上文件夹的名字组成新的路径
                }
            }
        }

        //stringSet.stream().forEach(System.out::println);
    }

    public static String getFileSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));//例如：abc.png  截取后：.png
    }

    // 关于最后是否有文件分隔符的问题还要在确定 还有win和linux的起始路径问题
    // 检查文件路径
    public static String checkFilePath(String filePath){
        // 需要根据 / 分割校验是否符合命名规则，文件最后是否咦/ 结尾，禁止写死，必须使用File.split
        File saveFile = new File(filePath);
        /**
         * 创建文件夹和文件名
         */

        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        return filePath;
    }

    // 系统命名问题，文件夹也不行
    public static String checkFileName(String fileName){
        //在Windows系统中，文件名命名规则如下：
        //1）文件名最长可以使用255个字符；
        //2）可以使用扩展名，扩展名用来表示文件类型，也可以使用多间隔符的扩展名（如win.ini.txt是一个合法的文件名，但其文件类型由最后一个扩展名决定）；
        //3）文件名中允许使用空格，但不允许使用下列字符（英文输入法状态）：< > / \ | : " * ?；
        //4）windows系统对文件名中字母的大小写在显示时有不同，但在使用时不区分大小写。
        String pattern="\\<*\\>*\\/*\\\\*\\|*\\:*\"*\\**\\?*\\；*\\ *";

        fileName=fileName.replaceAll(pattern,"");
        if(fileName.length()>255){
            return fileName.substring(0,250);
        }
        return fileName;
    }

    // 系统命名问题，文件夹也不行
    public static String replaceFileName(String fileName){
        //在Windows系统中，文件名命名规则如下：
        //1）文件名最长可以使用255个字符；
        //2）可以使用扩展名，扩展名用来表示文件类型，也可以使用多间隔符的扩展名（如win.ini.txt是一个合法的文件名，但其文件类型由最后一个扩展名决定）；
        //3）文件名中允许使用空格，但不允许使用下列字符（英文输入法状态）：< > / \ | : " * ?；
        //4）windows系统对文件名中字母的大小写在显示时有不同，但在使用时不区分大小写。
        fileName = fileName.replaceAll("|","_");
        return fileName;
    }

}
