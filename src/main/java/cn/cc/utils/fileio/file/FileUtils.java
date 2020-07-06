package cn.cc.utils.fileio.file;



import cn.cc.entity.FileSysMsg;
import cn.cc.utils.date.DateUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**
     * 用来找文件
     * 可以存到数据库
     */

    //private static List<fileMain> returnFileList=new ArrayList<fileMain>();

    private static List<FileSysMsg> fileSysMsgList=new ArrayList<FileSysMsg>();

    private FileSysMsg fileSysMsg;

    // 依次寻找文件夹,根据指定路径

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
                    //将每一轮的数据都存到集合中
                    fileSysMsgList.add(saveFileMsg(fileSysMsg,fileList[i],path));
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
        fileSysMsg.setFileCreateTime(DateUtils.timestampToDateStr(file.lastModified()));

        return fileSysMsg;
    }


}
