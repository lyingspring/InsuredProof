package comm;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * ���ݿ����ü���ط���
 * @author maoxj
 *
 */
public class oracle_jdbc {

	private Statement stmt = null;

	private ResultSet rs = null;

	private Connection conn = null;

	private PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement

	public oracle_jdbc() {
		this.getConnection();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			 String url="jdbc:oracle:thin:@10.134.127.8:1521:casi4";
			 //orclΪ���ݿ��SID
			 String user="casi";
			 String password="casiyth";
//			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"; // orclΪ���ݿ��SID
//			String user = "jyjhk";
//			String password = "jyjhk";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("jdbc: ���ݿ�����ʧ�ܣ���鿴���ݿ����ã�");
			System.out.println(e);
		}
	}
	/**
	 * ��������
	 * @return
	 */
	public String TestConnection() {
		String ss = null;
		try {
			
			String sql = "select 1 from dual";// Ԥ������䣬�������������
			pre = conn.prepareStatement(sql);// ʵ����Ԥ�������
			
			rs = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
			while (rs.next()) {
				// ���������Ϊ��ʱ
				System.out.println("jdbc: ���ݿ�����������");
				
			}

		} catch (SQLException e) {
			System.out.println("jdbc: ���ݿ�����ʧ�ܣ���鿴���ݿ����ã�");
			e.printStackTrace();
		} finally {
			this.close(conn, stmt, rs, pre);
		}
		return ss;
	}
	
	public String getRes() {
		String ss = null;
		try {
			// stmt = conn.createStatement();
			// rs = stmt.executeQuery("select * from tbmeetroomequipment");
			// while (rs.next()) {
			// Tbmeet t = new Tbmeet();
			// t.setId(rs.getLong(1));
			// t.setName(rs.getString(2));
			// t.setEcid(rs.getLong(3));
			// list.add(t);
			// }
			String sql = "select * from aa10 where aaz093=?";// Ԥ������䣬�������������
			pre = conn.prepareStatement(sql);// ʵ����Ԥ�������
			pre.setLong(1, 12l);// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
			rs = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
			while (rs.next()) {
				// ���������Ϊ��ʱ
				System.out.println(
						"ѧ��:" + rs.getInt("aaz093") + "����:" + rs.getString("aaa103") + "����1:" + rs.getString(4));
				ss = "ѧ��:" + rs.getInt("aaz093") + "����:" + rs.getString("aaa103") + "����1:" + rs.getString(4);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			this.close(conn, stmt, rs, pre);
		}
		return ss;
	}

	/**
	 * ֱ��ͨ��sql��䷵�ؽ��  ��ȡ�ֶ��ô�д
	 * @param sql
	 * @return ArrayList<HashMap<String, String>>
	 */
	public ArrayList<HashMap<String, String>> createSQLQuery(String sql) {
		ArrayList<HashMap<String, String>> list_hashmap = new ArrayList<HashMap<String, String>>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			while (rs.next()) {  
	            HashMap<String, String> map = new HashMap<String, String>();  
	            for (int i = 1; i <= data.getColumnCount(); i++) {// ���ݿ���� 1 ��ʼ  
	  
	                String c = data.getColumnName(i);  
	                String v = rs.getString(c);  
	                System.out.println(c + ":" + v + "\t");  
	                map.put(c, v);  
	            }  
	            System.out.println("======================");  
	            list_hashmap.add(map);  
	        }
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			this.close(conn, stmt, rs, pre);
		}

		return list_hashmap;
	}

	public int delete(String sql) throws SQLException {
		int number = 0;
		try {
			stmt = conn.createStatement();

			number = stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception e) {
			System.out.println(e);
			conn.rollback();
			number = 0;
		} finally {
			this.close(conn, stmt, rs, pre);
		}
		return number;
	}

	public void close(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pre) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (pre != null) {
				pre.close();
				pre = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
