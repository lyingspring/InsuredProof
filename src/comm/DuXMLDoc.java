package comm;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class DuXMLDoc {
	
	//////////测试用
	public List xmlElements(String xmlDoc) {
		SAXReader reader = new SAXReader();
        StringReader sr = new StringReader(xmlDoc);
        InputSource is = new InputSource(sr);
        try {
            Document document = reader.read(is);
         // 获取根元素
            Element root = document.getRootElement();

            //获取名字是head的集合
            List<Element> phoneList = root.elements("head");
            List<Element> typeList = phoneList.get(0).elements("aac002");//获取HEAD集合中的子集 名字是aac002
            for (int i=0;i<typeList.size();i++){
                Element element = typeList.get(i);
                String phoneName = element.attributeValue("aac002");//属性
                System.out.println("phonename = "+phoneName+" "+element.getName()+" "+element.getTextTrim());
                //element.getName() 字段名   element.getTextTrim() 字段内容
                
                List<Element> childList = element.elements();
                for (int j=0;j<childList.size();j++){
                    Element e = childList.get(j);
                    System.out.println(e.getName()+"="+e.getText());
                }
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
	
	
	/**
	 * maoxj
	 * 解析参保证明交易的xml字符串
	 * @param xmlDoc
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> GetxmlElements(String xmlDoc) throws Exception {
		SAXReader reader = new SAXReader();
        StringReader sr = new StringReader(xmlDoc);
        InputSource is = new InputSource(sr);
        HashMap<String, String> hashmap =new HashMap<String, String>();
        try {
            Document document = reader.read(is);
         // 获取根元素
            Element root = document.getRootElement();

            //获取名字是head的集合
            List<Element> RootList = root.elements("head");
          //  List<Element> peopleList = RootList.get(0).elements("head");
            for (Iterator<?> iter = RootList.get(0).elementIterator(); iter.hasNext();){
                Element element = (Element) iter.next();
                
                //element.getName() 字段名   element.getTextTrim() 字段内容
             if (element.getName().equals("aac002")){
            	 hashmap.put("aac002", element.getTextTrim());
             }
             if (element.getName().equals("aac003")){
            	 hashmap.put("aac003", element.getTextTrim());
             }
             if (element.getName().equals("printtype")){
            	 hashmap.put("printtype", element.getTextTrim());
             }
             if (element.getName().equals("sqbm")){
            	 hashmap.put("sqbm", element.getTextTrim());
             }
             if (element.getName().equals("czy")){
            	 hashmap.put("czy", element.getTextTrim());
             }
             
                
                
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            throw new Exception("xml解析出错:"+e.getMessage());
        }
        return hashmap;
    }
	
	
//	public static void main(String[] args) {
//		String xmlDoc="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
//"<request>"+
//	"<head>"+
//		"<aac002>身份证</aac002>"+
//		"<aac003>姓名</aac003>"+
//		"<printtype>打印类型</printtype>"+ 
//		"<sqbm>申请部门</sqbm>"+
//		"<czy>操作员</czy>"+
//	"</head>"+
//"</request>";
//		DuXMLDoc ss=new DuXMLDoc();
//		 HashMap<String, String> hashmap =new HashMap<String, String>();
//		 hashmap=ss.GetxmlElements(xmlDoc);
//		 System.out.println(hashmap.get("aac003").toString());
//	}
	
}
