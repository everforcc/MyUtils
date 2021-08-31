package cc.core.file.utils;

import java.io.File;

/**
 * @author c.c.
 * @date 2020/12/7
 */
public interface IFileUtils {

    // 接口， 递归文件夹的不同的行为
    // fileList[] 可以确定当前文件的信息，i确定位置
    // string 确定行为和数据
    boolean accept(File[] fileList,int i,String... strings);

}
