package cc.core.file.utils;

import cc.comp.ByteUtils;
import cc.constant.ConstantFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author c.c.
 * @date 2020/12/14
 */
public class FileTypeUtils {

    /** https://www.cnblogs.com/jiangyang/p/4881586.html
     1. 通过后缀名，如exe,jpg,bmp,rar,zip等等。
     2. 通过读取文件，获取文件的Content-type来判断。
     3. 通过读取文件流，根据文件流中特定的一些字节标识来区分不同类型的文件。
     4. 若是图片，则通过缩放来判断，可以缩放的为图片，不可以的则不是。
     */

    public static void main(String[] args) throws Exception
    {
        File f = new File(ConstantFile.javaFilePath + "/test/十大错觉.jpg");
        if (f.exists())
        {
            // 判断是否为图片
            String filetype1 = getImageFileType(f);
            System.out.println(filetype1);

            // 遍历已有map, 太慢了修改为二叉树查找
            String filetype2 = getFileByFile(f);
            System.out.println(filetype2);
        }

    }

    public final static Map<String, String> file_type_map = new HashMap<String, String>();
    static{
        file_type_map.put("jpg", "FFD8FF"); //JPEG (jpg)
        file_type_map.put("png", "89504E47");  //PNG (png)
        file_type_map.put("gif", "47494638");  //GIF (gif)
        file_type_map.put("tif", "49492A00");  //TIFF (tif)
        file_type_map.put("bmp", "424D"); //Windows Bitmap (bmp)
        file_type_map.put("dwg", "41433130"); //CAD (dwg)
        file_type_map.put("html", "68746D6C3E");  //HTML (html)
        file_type_map.put("rtf", "7B5C727466");  //Rich Text Format (rtf)
        file_type_map.put("xml", "3C3F786D6C");
        file_type_map.put("zip", "504B0304");
        file_type_map.put("rar", "52617221");
        file_type_map.put("psd", "38425053");  //Photoshop (psd)
        file_type_map.put("eml", "44656C69766572792D646174653A");  //Email [thorough only] (eml)
        file_type_map.put("dbx", "CFAD12FEC5FD746F");  //Outlook Express (dbx)
        file_type_map.put("pst", "2142444E");  //Outlook (pst)
        file_type_map.put("xls", "D0CF11E0");  //MS Word
        file_type_map.put("doc", "D0CF11E0");  //MS Excel 注意：word 和 excel的文件头一样
        file_type_map.put("mdb", "5374616E64617264204A");  //MS Access (mdb)
        file_type_map.put("wpd", "FF575043"); //WordPerfect (wpd)
        file_type_map.put("eps", "252150532D41646F6265");
        file_type_map.put("ps", "252150532D41646F6265");
        file_type_map.put("pdf", "255044462D312E");  //Adobe Acrobat (pdf)
        file_type_map.put("qdf", "AC9EBD8F");  //Quicken (qdf)
        file_type_map.put("pwl", "E3828596");  //Windows Password (pwl)
        file_type_map.put("wav", "57415645");  //Wave (wav)
        file_type_map.put("avi", "41564920");
        file_type_map.put("ram", "2E7261FD");  //Real Audio (ram)
        file_type_map.put("rm", "2E524D46");  //Real Media (rm)
        file_type_map.put("mpg", "000001BA");  //
        file_type_map.put("mov", "6D6F6F76");  //Quicktime (mov)
        file_type_map.put("asf", "3026B2758E66CF11"); //Windows Media (asf)
        file_type_map.put("mid", "4D546864");  //MIDI (mid)
    }

    public final static String getImageFileType(File f)
    {
        if (isImage(f))
        {
            try
            {
                ImageInputStream iis = ImageIO.createImageInputStream(f);
                Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                if (!iter.hasNext())
                {
                    return null;
                }
                ImageReader reader = iter.next();
                iis.close();
                return reader.getFormatName();
            }
            catch (IOException e)
            {
                return null;
            }
            catch (Exception e)
            {
                return null;
            }
        }
        return null;
    }

    public static final boolean isImage(File file){
        boolean flag = false;
        try
        {
            BufferedImage bufreader = ImageIO.read(file);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if(width==0 || height==0){
                flag = false;
            }else {
                flag = true;
            }
        }
        catch (IOException e)
        {
            flag = false;
        }catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public final static String getFileByFile(File file)
    {
        String filetype = null;
        byte[] b = new byte[50];
        try
        {
            InputStream is = new FileInputStream(file);
            is.read(b);
            filetype = getFileTypeByStream(b);
            is.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return filetype;
    }

    public final static String getFileTypeByStream(byte[] b)
    {
        String filetypeHex = String.valueOf(ByteUtils.bytesToHexString(b));
        Iterator<Map.Entry<String, String>> entryiterator = file_type_map.entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String,String> entry =  entryiterator.next();
            String fileTypeHexValue = entry.getValue();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
