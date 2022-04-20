package cc.core.file.use;

import cc.sysconstant.ConstantFile;
import cc.core.file.utils.FileUtils;
import cc.core.file.utils.IFileUtils;
import cc.core.io.base.PrintReaderUtils;
import cc.sysresource.PropertiesHeader;
import cc.advanced.web.http.httpurlconnect.HttpURLConnectionUtil;
import cc.core.io.base.StreamInputUtils;
import cc.core.io.base.PrintWriterUtils;
import cc.core.regex.utils.RegexUtils;
import cc.sysutils.Print_Record;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author c.c.
 * @date 2020/12/13
 */
public class WenkuNovelMenu implements IFileUtils,Runnable {

    private String path ;

    static Print_Record print_record = Print_Record.getInstanse(ConstantFile.L1_javaFilePath + "\\craw\\www.wenku8.net\\log","log.txt");
    static Print_Record print_record_error = Print_Record.getInstanse(ConstantFile.L1_javaFilePath + "\\craw\\www.wenku8.net\\log","log_err.txt");

    // 需要单个测试通过，才批量开始， 文件命名多个.不通过 可能..表示上级 so
    public static void main(String[] args){
        //String filePath =ConstantFile.javaFilePath + "\\java\\novel\\www.wenku8.net\\欢迎来到实力至上主义的教室5";
        //String filePath = ConstantFile.javaFilePath + "\\craw\\www.wenku8.net\\我的青春恋爱物语果然有问题";
        //String filePath = ConstantFile.javaFilePath + "\\craw\\www.wenku8.net\\Fate Zero";
       addFileMenu(ConstantFile.L1_javaFilePath + "/craw/www.wenku8.net/富士见文库/","");

        /*InputStream inputStream = HttpURLConnectionUtil.getStream("http://picture.wenku8.com/pictures/1/1538/51571/63712.jpg","GET","", Header.pictureWenku8());
        InputStreamUtils.downFileByStream(inputStream,ConstantFile.javaFilePath + "/","1.jpg");*/

    }

    public static void addFileMenu(String filePath,String fileName){
        // String filePath = ConstantFile.javaFilePath + "\\craw\\www.wenku8.net\\叛逆的鲁路修";
        FileUtils.recursion(filePath,new WenkuNovelMenu());
    }


    @Override
    public boolean accept(File[] fileList, int i, String... strings) {
        //fileList[i] = new File(ConstantFile.javaFilePath + "\\craw\\www.wenku8.net\\MF文库J\\零之使魔\\0020.第三卷始祖的祈祷书.第一章第零号系统.md");

        if(fileList[i].getName().endsWith(".md")){
            // 说明是md文件
            String tempUrl = "";
            try {
                String content = PrintReaderUtils.bufferReader(fileList[i]);
                List<String> stringSet = RegexUtils.matcheList("\\<img src=.*\\>",content);
                if(stringSet!=null&&stringSet.size()!=0){
                    print_record.println("stringSet.size():" + stringSet.size() + " >>> " + fileList[i].getAbsolutePath());
                    //stringSet.forEach(System.out::println);
                    for(String str:stringSet){
                        //System.out.println(str);
                        str = str.replace("<img src=\"","").replace("\" border=\"0\" class=\"imagecontent\">","");
                        String fileName = str.replace("//",".").replace("/",".").replace(":","");
                        tempUrl = str;

                        // http.picture.wenku8.com.pictures.1.1538.51571.63712.jpg
                        // http.picture.wenku8.com.pictures.1.1538.51571.63712.jpg
                        String filePath = fileList[i].getAbsolutePath().replace(".md","") + File.separator;
                        if(new File(filePath + fileName).exists()){
                            // 如果文件存在那么不在写入?
                            //print_record.println(filePath + fileName+"文件存在");
                            return false;
                        }
                        print_record.println(str);
                        print_record.println(fileName);
                        InputStream inputStream = HttpURLConnectionUtil.getStream(str,"GET","", PropertiesHeader.pictureWenku8());
                        print_record.println(filePath);
                        StreamInputUtils.streamToFile(inputStream,filePath,fileName);
                }
                }
            } catch (Exception e) {
                PrintWriterUtils.printWriter(ConstantFile.L1_javaFilePath + "\\craw\\www.wenku8.net\\log","log_err.txt","fileList[i].getName():" + e + "\r\n");
                // print_record_error.printErrln("fileList[i].getName():" + fileList[i].getName());
                PrintWriterUtils.printWriter(ConstantFile.L1_javaFilePath + "\\craw\\www.wenku8.net\\log","log_err.txt","fileList[i].getName():" + fileList[i].getName() + ": " + tempUrl + "\r\n");
                //accept(fileList,i,"");
            }
        }

        return false;
    }

    public boolean accept1(File[] fileList, int i, String... strings) {
        // [0001.第一卷.KEYWORDS](./欢迎来到实力至上主义的教室5/0001.第一卷.KEYWORDS.md)
        String menu = "[%s](%s)\r\n";
        String ml = String.format(menu,"目录","./" + strings[0]);

        String page = "\r\n";

        // 目录的命名问题

        int length = fileList.length;
        //System.out.println("当前:" + fileList[i].getName());
        if(i==0&&i<length){
            /*System.out.print("目录:" + fileList[i].getName());
            System.out.println("下一章:" + fileList[i + 1].getName());*/
            page += ml;
            page += String.format(menu,"下一章:" + fileList[i + 1].getName(),fileList[i + 1].getName());
        }else if(i<length-1){
            /*System.out.print("上一章:" + fileList[i - 1].getName());
            System.out.print("目录:");
            System.out.println("下一章:" + fileList[i + 1].getName());*/
            page += String.format(menu,"上一章:" + fileList[i - 1].getName(),fileList[i - 1].getName());
            page += ml;
            page += String.format(menu,"下一章:" + fileList[i + 1].getName(),fileList[i + 1].getName());
        }else {
            /*System.out.print("上一章:" + fileList[i - 1].getName());
            System.out.print("目录:");*/
            page += ml;
            page += String.format(menu,"上一章:" + fileList[i - 1].getName(),fileList[i - 1].getName());
        }
        saveMDMenu(fileList[i].getAbsolutePath(),page);
        return false;
    }

    private static void saveMDMenu(String fileName,String content){
        PrintWriterUtils.printWriter(fileName,content);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public WenkuNovelMenu() {
    }

    public WenkuNovelMenu(String path) {
        this.path = ConstantFile.L1_javaFilePath + "\\craw\\www.wenku8.net\\" + path;
    }

    @Override
    public void run() {
        addFileMenu(this.path,"");
    }
}
