package cc.design.design13proxy.type1;

import cc.constant.ConstantFile;

/**
 * @author c.c.
 * @date 2021/3/25
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        proxyImg();
        //realImg();
    }

    public static void proxyImg(){
        Image image = new ProxyImage(ConstantFile.javaFilePath + "\\craw\\临时\\webmagic.png");

        // 图像将从磁盘加载
        image.display();
        // 图像不需要从磁盘加载
        //image.display();
    }

    public static void realImg(){
        Image image = new RealImage(ConstantFile.javaFilePath + "\\craw\\临时\\webmagic.png");

        // 图像将从磁盘加载
        image.display();
        // 图像不需要从磁盘加载
        image.display();
    }

}