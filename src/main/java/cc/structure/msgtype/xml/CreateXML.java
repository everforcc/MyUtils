package cc.structure.msgtype.xml;

import cc.constant.ConstantCharSet;
import cc.constant.ConstantFile;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

/**
 * 操作xml
 */
public class CreateXML {

    private static List<Map<String, String>> students = new ArrayList<Map<String, String>>();//模拟学生集合
    private static Map<String, String> student = null;    //模拟student对象
    private static final String filePath = ConstantFile.L1_javaFilePath + "/test.xml";//存放xml文件位置

    /**
     * 初始化数据
     */
    static {
        student = new HashMap<String, String>();
        student.put("name", "麦迪");
        student.put("sex", "man");
        student.put("addr", "hlj");
        students.add(student);
        student = new HashMap<String, String>();
        student.put("name", "zwl");
        student.put("sex", "woman");
        student.put("addr", "ah");
        students.add(student);
    }

    /**
     * 创建xml 返回xml字符串
     *
     * @return
     */
    private static String createXml() {
        Document doc = DocumentHelper.createDocument();//DocumentHelper创建document对象
        Element root = doc.addElement("Results");//创建根节点
        Element studentsElem = root.addElement("students");
        for (Map<String, String> student : students) {//遍历集合 创建子节点
            Element studentElem = studentsElem.addElement("student");
            studentElem.addElement("name").addText(student.get("name"));
            //创建节点并增加文本  (<sex id="test">man</sex>   name->sex text->man attribute->(id:test(value)))
            //studentElem.addElement("sex").setText(student.get("man"));//set,add都可以添加文本
            //studentElem.addElement("addr").addText(student.get("addr"));
//    		studentElem.addAttribute("name",student.get("name") );//<student name="yz">
            System.out.println("1:" + studentElem.getName());//student
            System.out.println("2:" + studentElem.getText());//""//如果还有子节点 则text为""
            System.out.println("3:" + studentElem.getStringValue());//yzmanhlj 获取所有子节点text
            System.out.println("4:" + studentElem.elementText("sex"));//yz 获取子节点name等于sex的text 可以使用studentElem.element("sex") 获取sex节点
            System.out.println("5:" + studentElem.elements());//子节点集合
        }
        return doc.asXML();//文档对象座位xml返回
    }


    /**
     * 将xml存入文件
     */
    private static void createXmlOutAsFile() {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("Results");
        Element studentsElem = root.addElement("students");
        for (Map<String, String> student : students) {
            Element studentElem = studentsElem.addElement("student");
            studentElem.addElement("name").addText(student.get("name"));
            studentElem.addElement("sex").setText(student.get("sex"));
            ;
            studentElem.addElement("addr").addText(student.get("addr"));
        }
        //输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码
        format.setEncoding(ConstantCharSet.UTF_8);
        XMLWriter write;
        try {
            File f = new File(filePath);
            write = new XMLWriter(new FileOutputStream(f), format);
            write.write(doc);
            write.flush();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DocumentHelper解析xml DocumentHelper只能读取String 不能读取文件路径
     */
    private static void parseXMLByDocumentHelper() {
        try {
            InputStream is = new FileInputStream(filePath);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int length;
            byte[] temp = new byte[1024];
            while ((length = (is.read(temp))) > 0) {
                os.write(temp, 0, length);
            }
            StringBuffer xml = new StringBuffer(os.toString());

            /** start *********************************************************************************/
            Document doc = DocumentHelper.parseText(xml.toString());//解析xml字符串形成document对象
            Element root = doc.getRootElement();//获取根节点
            Element studentsElem = root.element("students");//获取students节点
            for (Iterator i = studentsElem.elementIterator("student"); i.hasNext(); ) {//遍历students下面的student节点
                Element elem = (Element) i.next();
                System.out.println(elem.elementText("name"));//获取子节点name的text
                System.out.println(elem.elementText("sex"));
                System.out.println(elem.elementText("addr"));
//				System.out.println(elem.getName());//student
//				System.out.println(elem.getText());//"" 因为还有子节点 所有student的text为""
//				System.out.println(elem.element("name").getText());//获取子节点name的text
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * SAXReader解析xml SAXReader读取文件路径
     */
    private static void parseXMLBySaxReader() {
        String str="";
        SAXReader sax = new SAXReader();
        try {

            sax.read(new ByteArrayInputStream(str.getBytes()));

            Document doc = sax.read(filePath);
            Element root = doc.getRootElement();
            searchDoc(root);//递归遍历节点
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * saxReader解析xml
     * @param elem
     */
    private static void searchDoc(Element elem) {
        List<Element> elems = elem.elements();
        for (Element ele : elems) {
            System.out.println("Name:" + ele.getName());
            System.out.println("Text:" + ele.getText());
            if (ele.elements().size() > 0) {
                searchDoc(ele);
            }
        }
    }

    public static void main(String[] args) {
        createXml();

    }
}
