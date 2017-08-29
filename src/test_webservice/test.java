package test_webservice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.itextpdf.text.pdf.codec.Base64.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@WebService
public class test {

	public String getValue(String name, String xxx) {
		////////// ����PDF
		PrintInsuredProof tPDFPractise = new PrintInsuredProof();
		try {
			tPDFPractise.createPdfFile();

			// tPDFPractise.createPDFFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String file_url = "d:/PrintInsuredProof.pdf";
		File file = new File(file_url);
		String BASE64String = null;
		BASE64String = getPDFBinary(file);// �����BASE64
		base64StringToPDF(BASE64String);// ���벢����PDF�ļ�
		
//		testOracle();//JDBC��ѯ���ݿ�
//		
//		oracle_jdbc or=new oracle_jdbc();
//		
//		System.out.println(or.getRes());//JDBC��ѯ���ݿ�
		return "my name:" + BASE64String;
	}

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8099/Service/Test", new test());
		System.out.println("success");

		// TODO Auto-generated method stub

	}

	/**
	 * ��PDFת����base64���� 1.ʹ��BufferedInputStream��FileInputStream��Fileָ�����ļ��ж�ȡ���ݣ�
	 * 2.Ȼ����д�뵽ByteArrayOutputStream�ײ����������Ļ��������BufferedOutputStream
	 * 3.�ײ������ת�����ֽ����飬Ȼ����BASE64Encoder�Ķ���������б���
	 */
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();// ����
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();// ����

	static String getPDFBinary(File file) {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ByteArrayOutputStream baos = null;
		BufferedOutputStream bout = null;
		try {
			// ������ȡ�ļ����ļ������
			fin = new FileInputStream(file);
			// ���ļ�������ϰ�װ�ڵ���������Ч�ʶ�ȡ��
			bin = new BufferedInputStream(fin);
			// ����һ���µ� byte �����������������ָ����С�Ļ���������
			baos = new ByteArrayOutputStream();
			// ����һ���µĻ�����������Խ�����д��ָ���ĵײ������
			bout = new BufferedOutputStream(baos);
			byte[] buffer = new byte[1024];
			int len = bin.read(buffer);
			while (len != -1) {
				bout.write(buffer, 0, len);
				len = bin.read(buffer);
			}

			// ˢ�´��������ǿ��д�����л��������ֽڣ��������д��룬�����п���������
			bout.flush();
			byte[] bytes = baos.toByteArray();
			// sun��˾��API
			return encoder.encodeBuffer(bytes).trim();
			// apache��˾��API
			// return Base64.encodeBase64String(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				bin.close();
				// �ر� ByteArrayOutputStream ��Ч�������еķ����ڹرմ������Կɱ����ã�����������κ�
				// IOException
				// baos.close();
				bout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ��base64����ת����PDF 1.ʹ��BASE64Decoder�Ա�����ַ���������ֽ�����
	 * 2.ʹ�õײ�������ByteArrayInputStream������ֽ������л�ȡ���ݣ�
	 * 3.�����ӵײ��������ж�ȡ���ݵ�BufferedInputStream�������������
	 * 4.ʹ��BufferedOutputStream��FileOutputSteam������ݵ�ָ�����ļ���
	 * 
	 * @param base64sString
	 */
	static void base64StringToPDF(String base64sString) {
		BufferedInputStream bin = null;
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		try {
			// ��base64������ַ���������ֽ�����
			byte[] bytes = decoder.decodeBuffer(base64sString);
			// apache��˾��API
			// byte[] bytes = Base64.decodeBase64(base64sString);
			// ����һ����bytes��Ϊ�仺������ByteArrayInputStream����
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			// �����ӵײ��������ж�ȡ���ݵĻ�������������
			bin = new BufferedInputStream(bais);
			// ָ��������ļ�
			File file = new File("d:/test111.pdf");
			// ������ָ���ļ��������
			fout = new FileOutputStream(file);
			// Ϊ�ļ�������Խӻ������������
			bout = new BufferedOutputStream(fout);

			byte[] buffers = new byte[1024];
			int len = bin.read(buffers);
			while (len != -1) {
				bout.write(buffers, 0, len);
				len = bin.read(buffers);
			}
			// ˢ�´��������ǿ��д�����л��������ֽڣ��������д��룬�����п���������
			bout.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				bin.close();
				fout.close();
				bout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * һ���ǳ���׼������Oracle���ݿ��ʾ������
	 */
	public void testOracle() {
		Connection con = null;// ����һ�����ݿ�����
		PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
		ResultSet result = null;// ����һ�����������
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
			System.out.println("��ʼ�����������ݿ⣡");
			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
			String user = "cxywk";// �û���,ϵͳĬ�ϵ��˻���
			String password = "cxywk";// �㰲װʱѡ���õ�����
			con = DriverManager.getConnection(url, user, password);// ��ȡ����
			System.out.println("���ӳɹ���");
			String sql = "select * from aa10 where aaz093=?";// Ԥ������䣬�������������
			pre = con.prepareStatement(sql);// ʵ����Ԥ�������
			pre.setLong(1, 12l);// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
			result = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
			while (result.next())
				// ���������Ϊ��ʱ
				System.out.println("ѧ��:" + result.getInt("aaz093") + "����:" + result.getString("aaa103")+ "����1:" + result.getString(4));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
				// ע��رյ�˳�����ʹ�õ����ȹر�
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("���ݿ������ѹرգ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
