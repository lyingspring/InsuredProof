package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import PdfModel.PrintInsuredProof;
import comm.Base64_Coder;
import comm.CallOracleProcedure;
import comm.DuXMLDoc;
import comm.ESignPdf;
import comm.InsuredProofBS;
import comm.oracle_jdbc;
import test_webservice.test;
/**
 * 主程序
 * @author maoxj
 *
 */
@WebService
public class PrintInsuredProofMain {

	
	public String getValue(String inputstr) {
		String BASE64String = null;
		String erromsg = "";
		String remsg = "0";
		DuXMLDoc xmljx=new DuXMLDoc();
		HashMap<String, String> hashmap =new HashMap<String, String>();
		try{
		hashmap=xmljx.GetxmlElements(inputstr);//解析传入xml
		InsuredProofBS bs= new InsuredProofBS();
		Long aac001=bs.CheckPeople(hashmap);//判断人员信息并返回AAC001
		CallOracleProcedure call=new CallOracleProcedure();
		call.BuildPrintInsuredProof(aac001);//调用过程生成数据
		//////////生成PDF
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
        String date=sdf.format(new Date());
		String file_url = "d:/createPDF/"+date+"_"+aac001+".pdf";//文件以日期开头方便删除
		String newfile_url = "d:/createPDF/"+date+"_"+aac001+"d.pdf";//签名后的pdf地址
		String saveurl = "d:/createPDF/";
		bs.judeDirExists("d:/createPDF");///判断文件夹是否存在，不存在新建
		bs.DeleteFileDate(saveurl);//删除非今天的PDF文件
		PrintInsuredProof tPDFPractise = new PrintInsuredProof();
		tPDFPractise.createPdfFile(aac001,file_url);//生成PDF
		
		ESignPdf esignpdf=new ESignPdf();////开始电子签名及盖章
		esignpdf.GetESignPdf(file_url, newfile_url);
		
		/////BASE64编码
		File file = new File(newfile_url);
		
		BASE64String = Base64_Coder.getPDFBinary(file);// 编码成BASE64
		//Base64_Coder.base64StringToPDF(BASE64String);//BASE64重新转成pdf
		
		
		}catch (Exception e) {
			remsg="1";//错误信息
			erromsg=e.getMessage();
			System.out.println(erromsg);
		}
		StringBuffer str=new StringBuffer();
		str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		str.append("<response>");
		str.append("<head>");
		str.append("<rc>");
		str.append(remsg);
		str.append("</rc>");
		str.append("<rm>");
		str.append(erromsg);
		str.append("</rm>");
		str.append("</head>");
		str.append("<body>");
		str.append("<responsedata>");
		str.append(BASE64String);	
		str.append("</responsedata>");
		str.append("</body>");
		str.append("</response>");

		return str.toString();
	}
	
	
	public static void main(String[] args) {
		//发布服务
		Endpoint.publish("http://0.0.0.0:8398/Service/PrintInsuredProof", new PrintInsuredProofMain());
		System.out.println("InFo: webservice is ok!");
		System.out.println("InFo: 正在调试数据库连接是否正常，请稍后...");
		oracle_jdbc db=new oracle_jdbc();//测试数据库是否正常
		db.TestConnection();
		System.out.println("调用地址  http://0.0.0.0:8398/Service/PrintInsuredProof?wsdl");
		// TODO Auto-generated method stub

	}
}
