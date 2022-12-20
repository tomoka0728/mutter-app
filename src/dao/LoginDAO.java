package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 *
 * ログイン情報の取得を行うDAOクラス
 *
 */
public class LoginDAO {

	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_0e8e6bdd3571123?reconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
	private static final String DB_USER = "b426587c3e4751";
	private static final String DB_PASS = "e5d59fb3";

	/**  カラム名（ユーザー情報） */
	private static final String MAIL_ADDRESS = "MAIL_ADDRESS";
	private static final String USER_NAME = "USER_NAME";
	private static final String GENDER_KBN = "GENDER_KBN";
	private static final String BIRTH_DATE = "BIRTH_DATE";
	private static final String PASSWORD = "PASSWORD";


	/**
	 *画面で入力されたメールアドレスとパスワードからユーザー情報をDBから取得する。
	 * @param user メールアドレスとパスワードのみを保持したユーザークラス
	 * @return ユーザー情報
	 */
	public User doSelectUser(User user) {

		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);
			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT文を準備する。
			String sql = "SELECT MAIL_ADDRESS, USER_NAME, GENDER_KBN, BIRTH_DATE, PASSWORD FROM USER_MASTER"
					+ " WHERE MAIL_ADDRESS = ? AND PASSWORD = ?";
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setString(1, user.getMailAddress());
			pStm.setString(2, user.getPassword());
            System.out.println("mysql    > " + pStm.toString());

			// SELECT文を実行する。
			ResultSet rs = pStm.executeQuery();
			// 結果を格納する。
			while(rs.next()) {
				user.setMailAddress(rs.getString(MAIL_ADDRESS));
				user.setUserName(rs.getString(USER_NAME));
				user.setGenderKbn(rs.getString(GENDER_KBN));
				user.setBirthDate(rs.getString(BIRTH_DATE));
				user.setPassword(rs.getString(PASSWORD));
			}
			return user;

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


}
