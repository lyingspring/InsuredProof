package comm;

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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64_Coder {
	/**
	 * @author maoxj
	 * ��PDFת����base64����
	 * 1.ʹ��BufferedInputStream��FileInputStream��Fileָ�����ļ��ж�ȡ���ݣ�
	 * 2.Ȼ����д�뵽ByteArrayOutputStream�ײ����������Ļ��������BufferedOutputStream
	 * 3.�ײ������ת�����ֽ����飬Ȼ����BASE64Encoder�Ķ���������б���
	 */
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();// ����
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();// ����

	public static String getPDFBinary(File file) {
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
	public static void base64StringToPDF(String base64sString) {
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


}
