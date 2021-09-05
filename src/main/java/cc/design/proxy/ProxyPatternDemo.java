package cc.design.proxy;

import cc.constant.ConstantFile;

/**
 * @author c.c.
 * @date 2021/3/25
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage(ConstantFile.javaFilePath + "\\craw\\临时\\webmagic.png");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }

}