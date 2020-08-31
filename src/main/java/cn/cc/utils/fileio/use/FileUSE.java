package cn.cc.utils.fileio.use;



import cn.cc.core.date.utils.Date_Timestamp;
import cn.cc.entity.FileSysMsg;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUSE {

    /**
     * 用来找文件
     * 可以存到数据库
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
        fileSysMsg.setFileCreateTime(Date_Timestamp.timestampToDateStr(file.lastModified()));

        return fileSysMsg;
    }


}
