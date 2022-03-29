package cc.arithmetic.use;

import cc.constant.ConstantFile;
import cc.core.io.base.PrintWriterUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

import static cc.arithmetic.basenum.Base64Utils.getbase64Url;

/**
 * @author c.c.
 * @date 2020/12/17
 */
public class EncoderFile {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        String path = ConstantFile.L1_javaFilePath + "\\java\\test\\invoice";

        String fileName="base64.png";

        String strImg = getbase64Url(path + fileName);
        Map<String,String> map=new HashMap<String,String>();
        map.put("imageStreamStr",strImg);
        map.put("sessionID","122701");
        map.put("userCode","suixueyan");
        map.put("imageName",fileName);

        list.add(JSONObject.fromObject(map).toString());
        System.out.println(JSONObject.fromObject(map).toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String sd = sdf.format(new Date(System.currentTimeMillis())); // 时间戳转换日期

        try {

            System.out.println("JSONArray.fromObject(list).toString():"+ JSONArray.fromObject(list).toString());
            PrintWriterUtils.printWriter(path + fileName + sd + ".txt",JSONArray.fromObject(list).toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
