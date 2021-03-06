package test_webservice;
import java.sql.SQLException;   
import java.sql.Statement;   
import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;   
import java.util.ArrayList;   
import java.util.List;
public class oracle_jdbc {   
     
       
    private Statement stmt = null;   
       
    private  ResultSet rs = null;   
       
    private Connection conn = null;   
    
    private PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
       
    public oracle_jdbc(){   
        this.getConnection();   
    }   
       
    public void getConnection(){   
        try{   
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();    
            String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl"; //orcl为数据库的SID    
            String user="cxywk";    
            String password="cxywk"; 
            conn= DriverManager.getConnection(url,user,password);    
        }catch (Exception e) {   
            System.out.println(e);   
        }   
    }   
       
    public String getRes(){   
         String ss=null;
        try {   
//            stmt = conn.createStatement();   
//            rs = stmt.executeQuery("select * from tbmeetroomequipment");   
//            while (rs.next()) {   
//                Tbmeet t = new Tbmeet();   
//                t.setId(rs.getLong(1));   
//                t.setName(rs.getString(2));   
//                t.setEcid(rs.getLong(3));   
//                list.add(t);   
//            }   
            String sql = "select * from aa10 where aaz093=?";// 预编译语句，“？”代表参数
			pre = conn.prepareStatement(sql);// 实例化预编译语句
			pre.setLong(1, 12l);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			rs = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			while (rs.next()){
				// 当结果集不为空时
				System.out.println("学号:" + rs.getInt("aaz093") + "姓名:" + rs.getString("aaa103")+ "姓名1:" + rs.getString(4));
				ss="学号:" + rs.getInt("aaz093") + "姓名:" + rs.getString("aaa103")+ "姓名1:" + rs.getString(4);
			}
			
			
        } catch (SQLException e) {   
             
            e.printStackTrace();   
        }finally{   
            this.close(conn, stmt, rs, pre);   
        }   
        return ss;   
    }   
       
    public int delete(String sql) throws SQLException{   
        int number = 0 ;   
        try{   
            stmt = conn.createStatement();   
               
            number = stmt.executeUpdate(sql);   
               
            conn.commit();   
        }catch(Exception e){   
            System.out.println(e);   
            conn.rollback();   
            number = 0 ;   
        }finally{   
            this.close(conn, stmt, rs, pre);   
        }   
        return number;   
    }   
       
    public void close(Connection conn , Statement stmt, ResultSet rs,PreparedStatement pre){   
        try{   
            if(rs != null){   
                rs.close();   
                rs = null ;   
            }   
            if(stmt != null){   
                stmt.close();   
                stmt = null ;   
            } 
            if(pre != null){   
            	pre.close();   
            	pre = null ;   
            } 
            if(conn != null){   
                conn.close();   
                conn = null;   
            }   
               
        }catch(Exception e){   
            System.out.println(e);   
        }   
    }   
       
} 
