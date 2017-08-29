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
		////////// 生成PDF
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
		BASE64String = getPDFBinary(file);// 编码成BASE64
		base64StringToPDF(BASE64String);// 解码并生成PDF文件
		
//		testOracle();//JDBC查询数据库
//		
//		oracle_jdbc or=new oracle_jdbc();
//		
//		System.out.println(or.getRes());//JDBC查询数据库
		return "my name:" + BASE64String;
	}

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8099/Service/Test", new test());
		System.out.println("success");

		// TODO Auto-generated method stub

	}

	/**
	 * 将PDF转换成base64编码 1.使用BufferedInputStream和FileInputStream从File指定的文件中读取内容；
	 * 2.然后建立写入到ByteArrayOutputStream底层输出流对象的缓冲输出流BufferedOutputStream
	 * 3.底层输出流转换成字节数组，然后由BASE64Encoder的对象对流进行编码
	 */
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();// 编码
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();// 解码

	static String getPDFBinary(File file) {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ByteArrayOutputStream baos = null;
		BufferedOutputStream bout = null;
		try {
			// 建立读取文件的文件输出流
			fin = new FileInputStream(file);
			// 在文件输出流上安装节点流（更大效率读取）
			bin = new BufferedInputStream(fin);
			// 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
			baos = new ByteArrayOutputStream();
			// 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
			bout = new BufferedOutputStream(baos);
			byte[] buffer = new byte[1024];
			int len = bin.read(buffer);
			while (len != -1) {
				bout.write(buffer, 0, len);
				len = bin.read(buffer);
			}

			// 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
			bout.flush();
			byte[] bytes = baos.toByteArray();
			// sun公司的API
			return encoder.encodeBuffer(bytes).trim();
			// apache公司的API
			// return Base64.encodeBase64String(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				bin.close();
				// 关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何
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
	 * 将base64编码转换成PDF 1.使用BASE64Decoder对编码的字符串解码成字节数组
	 * 2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
	 * 3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
	 * 4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
	 * 
	 * @param base64sString
	 */
	static void base64StringToPDF(String base64sString) {
		BufferedInputStream bin = null;
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		try {
			// 将base64编码的字符串解码成字节数组
			byte[] bytes = decoder.decodeBuffer(base64sString);
			// apache公司的API
			// byte[] bytes = Base64.decodeBase64(base64sString);
			// 创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			// 创建从底层输入流中读取数据的缓冲输入流对象
			bin = new BufferedInputStream(bais);
			// 指定输出的文件
			File file = new File("d:/test111.pdf");
			// 创建到指定文件的输出流
			fout = new FileOutputStream(file);
			// 为文件输出流对接缓冲输出流对象
			bout = new BufferedOutputStream(fout);

			byte[] buffers = new byte[1024];
			int len = bin.read(buffers);
			while (len != -1) {
				bout.write(buffers, 0, len);
				len = bin.read(buffers);
			}
			// 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
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
	 * 一个非常标准的连接Oracle数据库的示例代码
	 */
	public void testOracle() {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "cxywk";// 用户名,系统默认的账户名
			String password = "cxywk";// 你安装时选设置的密码
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");
			String sql = "select * from aa10 where aaz093=?";// 预编译语句，“？”代表参数
			pre = con.prepareStatement(sql);// 实例化预编译语句
			pre.setLong(1, 12l);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			while (result.next())
				// 当结果集不为空时
				System.out.println("学号:" + result.getInt("aaz093") + "姓名:" + result.getString("aaa103")+ "姓名1:" + result.getString(4));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
