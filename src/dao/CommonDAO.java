package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * データベース操作の共通処理
 *
 */
public class CommonDAO {

	/**
	 * データベースを閉じる
	 * @param conn
	 */
	public void dbClose(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
