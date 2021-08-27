package cn.cc.use.file;


import cc.core.date.utils.DateTimestamp;
import cn.cc.entity.FileSysMsg;

import java.io.File;
import java.io.IOException;

public class FileMsg {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\java\\rename\\ep\\cc00.png");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.getCanonicalPath());
    }

    /**
     * 记录文件大概需要哪些信息，还不全，需要了再补全
     */

    //private static List<fileMain> returnFileList=new ArrayList<fileMain>();

    // 依次寻找文件夹,根据指定路径

    /**
     * 持久化
     * 将file的信息存到实体类
     * @param fileSysMsg
     * @param file
     * @return
     */
    public FileSysMsg saveFileMsg(FileSysMsg fileSysMsg,File file,String path){

        /**
         * 文件大小 取得时候在进行转换 /1024KB
         */
        fileSysMsg.setFileSize(String.valueOf(file.length()));

        fileSysMsg.setFileRealName(file.getName());
        fileSysMsg.setFileVirtualName(file.getName());

        /**
         * 文件磁盘真实路径fileRealRoute
         */
        fileSysMsg.setFileRealRoute(path);

        /**
         * 虚拟路径，用于页面展示，去掉盘符D：，替换\,/
         */
        fileSysMsg.setFileVirtualRoute(path.replaceAll("\\\\","/").replace("D:",""));

        /**
         * 获取文件后缀名，文件类型，随后区分，但没必要
         */
        fileSysMsg.setFileType(file.getName().substring(file.getName().lastIndexOf(".")+1,file.getName().length()));

        /**
         * 保存文件最后一次改变的时间
         */
        fileSysMsg.setFileCreateTime(DateTimestamp.timestampToDateStr(file.lastModified()));

        return fileSysMsg;
    }


}
