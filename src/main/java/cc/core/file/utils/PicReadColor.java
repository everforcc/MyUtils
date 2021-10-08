package cc.core.file.utils;

import cc.core.file.img.ImgColorDto;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author everforcc 2021-09-30
 */
public class PicReadColor {
    /**
     * 读取一张图片的RGB值
     *
     * @throws Exception
     */
    public Map<Integer,List<ImgColorDto>> getImagePixel(String image) throws Exception {
        int count = 0;
        Map<Integer,List<ImgColorDto>> integerListMap = new HashMap<>();

        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");
        for (int i = minx; i < width; i++) {
            List<ImgColorDto> imgColorDtoList = new ArrayList<>();
            for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                //System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
                ImgColorDto img = new ImgColorDto(i,j,rgb[0],rgb[1],rgb[2]);
                imgColorDtoList.add(img);
                count++;
            }
            integerListMap.put(i,imgColorDtoList);
        }
        System.out.println(integerListMap.size());
        System.out.println("count:" + count);
        return integerListMap;
    }

    public Map<Integer,List<ImgColorDto>> getImagePixel_2(String image) throws Exception {
        int count = 0;
        Map<Integer,List<ImgColorDto>> integerListMap = new HashMap<>();

        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");
        for (int j = miny; j < height; j++) {
        //for (int i = minx; i < width; i++) {
            List<ImgColorDto> imgColorDtoList = new ArrayList<>();
            for (int i = minx; i < width; i++) {
            //for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                //System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
                ImgColorDto img = new ImgColorDto(i,j,rgb[0],rgb[1],rgb[2]);
                imgColorDtoList.add(img);
                count++;
            }
            integerListMap.put(j,imgColorDtoList);
        }
        System.out.println(integerListMap.size());
        System.out.println("count:" + count);
        return integerListMap;
    }

    /**
     * 返回屏幕色彩值
     *
     * @param
     * @param
     * @return
     * @throws AWTException
     */
    public int getScreenPixel() throws AWTException { // 函数返回值为颜色的RGB值。
        int x = 100;
        int y = 345;//100, 345
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        rb = new Robot();
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
        System.out.println(di.width);
        System.out.println(di.height);
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        BufferedImage bi = rb.createScreenCapture(rec);
        int pixelColor = bi.getRGB(x, y);

        return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        int x = 0;
        PicReadColor rc = new PicReadColor();
        x = rc.getScreenPixel();
        System.out.println(x + " - ");
        rc.getImagePixel("C:\\test\\dir\\1.jpg");
    }

}
