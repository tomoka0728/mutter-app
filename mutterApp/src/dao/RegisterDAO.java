package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 * ユーザー情報を登録するDAOクラス
 *
 */
public class RegisterDAO {

	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_0e8e6bdd3571123?reconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
	private static final String DB_USER = "b426587c3e4751";
	private static final String DB_PASS = "e5d59fb3";

	/**  カラム名（ユーザー情報） */
//	private static final String MAIL_ADDRESS = "MAIL_ADDRESS";
//	private static final String USER_NAME = "USER_NAME";
//	private static final String GENDER_KBN = "GENDER_KBN";
//	private static final String BIRTH_DATE = "BIRTH_DATE";
//	private static final String PASSWORD = "PASSWORD";

	/**
	 * 引数のメールアドレスでユーザーマスタを検索し、1件でもレコードが存在すればtrueを返却する。
	 * @param mailAddress メールアドレス
	 * @return 判定結果（true：存在）
	 */
	public Boolean doMatchMailAddress(String mailAddress) {

		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);
			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT分を準備する。
			String sql = "SELECT COUNT(MAIL_ADDRESS) AS COUNT FROM USER_MASTER WHERE MAIL_ADDRESS = ?";
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setString(1, mailAddress);
			System.out.println("mysql    > " + pStm.toString());

			// SELECT文を実行する。
			ResultSet rs = pStm.executeQuery();
			int count = 0;
			// 結果を格納する。
			while(rs.next()) {
				count = rs.getInt("COUNT");
			}

			// 1件以上の場合trueを返却する。
			return count >= 1;


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			CommonDAO commonDAO = new CommonDAO();
			commonDAO.dbClose(conn);
		}
	}
	/**
	 * ユーザー情報を登録する。
	 * @param user ユーザー情報
	 * @return 登録結果
	 */
	public Boolean executeInsertUser(User user) {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "INSERT INTO USER_MASTER(MAIL_ADDRESS, USER_NAME, GENDER_KBN, BIRTH_DATE,PASSWORD) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStm = conn.prepareStatement(sql);

			// ?の値を設定する
			pStm.setString(1, user.getMailAddress());
			pStm.setString(2,user.getUserName());
			pStm.setString(3, user.getGenderKbn());
			pStm.setString(4, user.getBirthDate());
			pStm.setString(5, user.getPassword());
			System.out.println("mysql    > " + pStm.toString());

			// INSERT文を実行する。
			int result = pStm.executeUpdate();

			if(result != 1) {
				// 戻り値が1以外の場合、エラー
				return false;
			}


		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			CommonDAO commonDAO = new CommonDAO();
			commonDAO.dbClose(conn);
		}
		return true;

	}
}