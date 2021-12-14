package cc.utils;

import cc.core.date.utils.DateUtils;
import cc.core.io.base.PrintWriterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * Yukino
 * 2020/3/9
 */
@Slf4j
public class Print_Record {

    /* 调整为输出加上 包名 类名，方法名 */


    private static Print_Record print_record;

    private String filePath;
    private String fileName;

    // private static String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
    // private static String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名

    private Print_Record(String filePath,String fileName){
        this.filePath = filePath;
        this.fileName = fileName ;
    };

    public static synchronized  Print_Record getInstanse(){
        if(print_record == null){
            print_record = new Print_Record("",DateUtils.now() + ".txt");
        }
        return print_record;
    }

    public static synchronized  Print_Record getInstanse(String filePath){
        if(print_record == null){
            print_record = new Print_Record(filePath,DateUtils.now() + ".txt");
        }
        return print_record;
    }

    public static synchronized  Print_Record getInstanse(String filePath,String fileName){
        if(print_record == null){
            print_record = new Print_Record(filePath,DateUtils.now() + fileName);
        }
        return print_record;
    }



    public void println(String msg,String... formatMsg){
        print(msg,"cor",formatMsg);
    }

    public void printErrln(String msg,String... formatMsg){
        print(msg,"err",formatMsg);
    }

    private void print(String msg,String type,String... formatMsg){
        String location="";
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        // 这里还可以根据包名 来分类 保存日志
        location = "[["+stacks[2].getClassName() + "](" + stacks[2].getMethodName() + ")" + "" + stacks[2].getLineNumber() + "]";

        msg = formatMsg.length == 0 ?msg : String.format(msg,formatMsg) ;
        msg = DateUtils.nowTimeRegex("yyyy-MM-dd hh:mm:ss ") + type + " : " + location + " --- " + msg ;
        if("err".equals(type)) {
            System.err.println( msg );
        }else {
            System.out.println( msg );
        }

        if(!StringUtils.isBlank(filePath)) {
            PrintWriterUtils.printWriter(filePath, fileName, msg + "\r\n");
        }
    }

}
