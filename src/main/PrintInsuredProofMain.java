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
 * ������
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
		hashmap=xmljx.GetxmlElements(inputstr);//��������xml
		InsuredProofBS bs= new InsuredProofBS();
		Long aac001=bs.CheckPeople(hashmap);//�ж���Ա��Ϣ������AAC001
		CallOracleProcedure call=new CallOracleProcedure();
		call.BuildPrintInsuredProof(aac001);//���ù�����������
		//////////����PDF
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
        String date=sdf.format(new Date());
		String file_url = "d:/createPDF/"+date+"_"+aac001+".pdf";//�ļ������ڿ�ͷ����ɾ��
		String newfile_url = "d:/createPDF/"+date+"_"+aac001+"d.pdf";//ǩ�����pdf��ַ
		String saveurl = "d:/createPDF/";
		bs.judeDirExists("d:/createPDF");///�ж��ļ����Ƿ���ڣ��������½�
		bs.DeleteFileDate(saveurl);//ɾ���ǽ����PDF�ļ�
		PrintInsuredProof tPDFPractise = new PrintInsuredProof();
		tPDFPractise.createPdfFile(aac001,file_url);//����PDF
		
		ESignPdf esignpdf=new ESignPdf();////��ʼ����ǩ��������
		esignpdf.GetESignPdf(file_url, newfile_url);
		
		/////BASE64����
		File file = new File(newfile_url);
		
		BASE64String = Base64_Coder.getPDFBinary(file);// �����BASE64
		//Base64_Coder.base64StringToPDF(BASE64String);//BASE64����ת��pdf
		
		
		}catch (Exception e) {
			remsg="1";//������Ϣ
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
		//��������
		Endpoint.publish("http://0.0.0.0:8398/Service/PrintInsuredProof", new PrintInsuredProofMain());
		System.out.println("InFo: webservice is ok!");
		System.out.println("InFo: ���ڵ������ݿ������Ƿ����������Ժ�...");
		oracle_jdbc db=new oracle_jdbc();//�������ݿ��Ƿ�����
		db.TestConnection();
		System.out.println("���õ�ַ  http://0.0.0.0:8398/Service/PrintInsuredProof?wsdl");
		// TODO Auto-generated method stub

	}
}
