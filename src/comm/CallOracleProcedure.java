package comm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * 调用存储过程
 * @author maoxj
 *
 */
public class CallOracleProcedure {
///生成数据
	public String BuildPrintInsuredProof(Long aac001) throws SQLException {
		// TODO Auto-generated method stub
		oracle_jdbc jdbc = new oracle_jdbc();
		CallableStatement proc1 = null;
		CallableStatement proc2 = null;
		try {
			proc1 = jdbc.getConn().prepareCall("{ call sbp_fundcollect.f_personcollect(?) }");
			proc1.setLong(1, aac001);
			proc1.execute();
			proc2 = jdbc.getConn().prepareCall("{ call personlandinsert.p_insertPerson(?) }");
			proc2.setLong(1, aac001);
			proc2.execute();
			jdbc.getConn().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jdbc.getConn() != null) {
				jdbc.getConn().close();
			}
			if (proc1 != null) {
				proc1.close();
				proc1 = null;
			}
			if (proc2 != null) {
				proc2.close();
				proc2 = null;
			}

		}
		return null;
	}
	/*
	public static void main(String[] args) throws SQLException {
		CallOracleProcedure bb=new CallOracleProcedure();
		bb.BuildPrintInsuredProof(1206051203l);
	}*/

}
