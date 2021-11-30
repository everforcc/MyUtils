package cc.use.file;

import cc.constant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.file.utils.IFileUtils;
import cc.core.io.PrintWriterUtils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @author c.c.
 * @date 2020/12/7
 */
public class RenameFile implements IFileUtils {

    /**
     * 目的是为了隐藏当前文件名，备份对应名称, name.txt
     * 正则来处理新增文件问题?  cc/d{} ?
     */

    // 新增文件怎么办,就按照日期再加分类
    public static void main(String[] args) {
        listFile();
    }

    public static void listFile(){
        String filePath = ConstantFile.L1_javaFilePath + "";
        FileUtils.recursion(filePath,new RenameFile());
    }


    @Override
    public boolean accept(File[] fileList,int i,String... strings) {
        System.out.println("文件数:"+fileList.length);
        // 命名规则
        DecimalFormat decimalFormat = new DecimalFormat("00");
        if(fileList.length > 100){
            decimalFormat = new DecimalFormat("000");
        }else if(fileList.length > 1000){
            decimalFormat = new DecimalFormat("0000");
        }
        // 新文件名
        String fileName = "cc" + decimalFormat.format(i) + FileUtils.getFileSuffix(fileList[i].getName());
        // 当前文件
        File oldFile = fileList[i];
        // 不处理txt文件，单独在别的地方处理
        if(fileName.endsWith(".url")||fileName.endsWith(".lnk")) {
            System.out.println("fileName:" + fileName);
            oldFile.delete();
        }else if(!fileName.endsWith(".txt")) {
            //System.out.print("oldName:" + oldFile.getAbsolutePath() + ".");
            // 文件改名
            oldFile.renameTo(new File(oldFile.getParent() + File.separator + fileName));
            // 输出旧文件名和新文件名
            //System.out.println(" >>> new name:" + oldFile.getParent() + File.separator + fileName);
            /*if(".db".equals(fileName)) {
                //System.out.println("---" + pathname.getAbsolutePath());
            }*/
            if (oldFile.getName().equals(fileName)) {
                // 如果旧文件名和新文件相同则不处理，也不做记录
                // PrintWriterUtils.fileWriter(oldFile.getParent(), "name.txt", fileName + " >>> 文件名相同 ");
            } else {
                PrintWriterUtils.fileWriter(oldFile.getParent(), "name.txt", fileName + " >>> " + oldFile.getName() + "\r\n");
            }
        }
        return false;
    }

    public static void someCase(){
        /**
         *  1.在历史的基础上新增文件，留下一个index.txt，表示当前的文件的命名的最后一个值例如index.txt cc001
         *  2.每次先读取然后改名，然后修改这个值
         *  3.设定每个文件夹最多999个文件，再多了也看不过来
         *
         *  5.还是sql方便，最后修改为非web项目的mybatis，目前先这样吧
         */
    }

}
