package cn.cc.core.msgtype.xml;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: 吹静静
 * Date: 2019/7/3
 * Time: 9:31
 * Description: 解析GetAlarmHis数据
 */
public class AlarmHisXmlFileParse {

    /**
     * api
     * https://www.oschina.net/uploads/doc/dom4j-1.6.1/org/dom4j/Element.html
     *
     * @param interfaceData
     * @throws Exception
     */
    public static void dom4jXml(String interfaceData) throws Exception{
        List<Element> elementList = null;
        try {
            SAXReader sr = new SAXReader();
            Document document = sr.read(new ByteArrayInputStream(interfaceData.getBytes()));
            Element root = document.getRootElement();
            elementList = root.elements();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        for (Element e : elementList) {
            // 解析标签下一级标签
            Element e1 = e.element("GetTagValueJsonResponse");
                         e1.elements(""); //List
                         e1.elementText("");

            Element e2 = e1.element("GetTagValueJsonResult");
            List<Element> elementList1 = e2.elements();
            for (Element el:elementList1){
                el.elementText("AlarmState");


            }
        }
    }
}