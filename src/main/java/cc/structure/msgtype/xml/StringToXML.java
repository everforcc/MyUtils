package cc.structure.msgtype.xml;

import cc.core.file.utils.ConstantCharSet;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StringToXML {
    /**
     *
     * 格式化String为Xml
     *
     * @param inputXML
     * @return
     * @throws Exception
     */
    public static String formatXml(String inputXML) throws Exception {
        String xml = null;
        SAXReader reader = new SAXReader();
        XMLWriter writer = null;
        org.dom4j.Document document = reader.read(new StringReader(inputXML));
        try {
            if (document != null) {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setNewLineAfterDeclaration(false);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                xml = stringWriter.getBuffer().toString();
            }
        } finally {
            if (writer != null) {
                try {
                    writer.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return xml;
    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.newDocument();
            org.w3c.dom.Element root = document.createElement("xml");
            document.appendChild(root);
            for (String key: data.keySet()) {
                String value = data.get(key);
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                org.w3c.dom.Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, ConstantCharSet.UTF_8);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
            writer.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String,String> map=new HashMap<String,String>();
        map.put("a","aa");
        map.put("b","bb");
        map.put("c", "cc");

        System.out.println(mapToXml(map));
    }

}
