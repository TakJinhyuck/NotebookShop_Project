package notebook.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DB������ ���õ�
 * �ε�, ����, �ݱ� ��� ��� Ŭ����
 * @author kosta
 *
 */
public class DbUtil {
	//connection�� �̸� �����Ϸ��� ��ü
	private static DataSource ds;
	/**
	 * �ε�
	 */
	static {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:/comp/env/jdbc/myoracle");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����
	 */
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	/**
	 * �ݱ� (DML���� : insert, update, delete)
	 */
	public static void dbClose(Connection con, Statement stmt) {
		try {
			if(stmt != null)stmt.close();
			if(con != null)con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ݱ� (select����)
	 */
	public static void dbClose(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)rs.close();
			dbClose(con, stmt);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
