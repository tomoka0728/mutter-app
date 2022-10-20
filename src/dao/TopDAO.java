package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Favorite;
import model.Mutter;

public class TopDAO {

	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_0e8e6bdd3571123?reconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
	private static final String DB_USER = "b426587c3e4751";
	private static final String DB_PASS = "e5d59fb3";

	public Long doGetMutterId() {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);
			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT文を準備する。
			String sql = "SELECT MAX(MUTTER_ID) AS NUM FROM MUTTER";
			PreparedStatement pStm = conn.prepareStatement(sql);
			System.out.println("mysql    > " + pStm.toString());

			// SELECT文を実行する。
			ResultSet rs = pStm.executeQuery();
			Long num = Long.valueOf(0);
			// 結果を格納する。
			while(rs.next()) {
				num = rs.getLong("NUM");
			}

			return num;


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

	public Boolean executeInsertMutter(Mutter mutter) {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "INSERT INTO MUTTER(MUTTER_ID, MAIL_ADDRESS, MUTTER, MUTTER_YMDHM) VALUES(?, ?, ?, ?)";
			PreparedStatement pStm = conn.prepareStatement(sql);

			// ?の値を設定する
			pStm.setLong(1, mutter.getMutterId());
			pStm.setString(2,mutter.getMailAddress());
			pStm.setString(3, mutter.getMutter());
			pStm.setString(4, mutter.getMutterYMDHM());
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

	public Boolean executeDeleteMutter(Long mutterId,String likeCount, String commentCount) {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "DELETE FROM MUTTER WHERE MUTTER_ID = ?";
			PreparedStatement pStm = conn.prepareStatement(sql);

			// ?の値を設定する
			pStm.setLong(1, mutterId);
			System.out.println("mysql    > " + pStm.toString());

			// DELETE文を実行する。
			int result = pStm.executeUpdate();

			if(result != 1) {
				// 戻り値が1以外の場合、エラー
				return false;
			}

			// ｲｲﾈがある場合
			if(!"0".equals(likeCount)) {
				sql = "DELETE FROM FAVORITE WHERE MUTTER_ID = ?";
				pStm = conn.prepareStatement(sql);

				// ?の値を設定する
				pStm.setLong(1, mutterId);
				System.out.println("mysql    > " + pStm.toString());
				// DELETE文を実行する。
				result = pStm.executeUpdate();
				if(result != 1) {
					// 戻り値が1以外の場合、エラー
					return false;
				}
			}
			// コメントがある場合
			// ｲｲﾈがある場合
			if(!"0".equals(commentCount)) {
				sql = "DELETE FROM Comment WHERE MUTTER_ID = ?";
				pStm = conn.prepareStatement(sql);

				// ?の値を設定する
				pStm.setLong(1, mutterId);
				System.out.println("mysql    > " + pStm.toString());
				// DELETE文を実行する。
				result = pStm.executeUpdate();
				if(result != 1) {
					// 戻り値が1以外の場合、エラー
					return false;
				}
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


	public List<Mutter> selectMutterList(String mailAddress){
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);
			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT分を準備する。
			String sql = "SELECT \r\n"
					+ "    MUTTER.MUTTER_ID,\r\n"
					+ "    MUTTER.MAIL_ADDRESS,\r\n"
					+ "    USER_MASTER.USER_NAME,\r\n"
					+ "    MUTTER.MUTTER,\r\n"
					+ "    MUTTER.MUTTER_YMDHM,\r\n"
					+ "    COM.COMMENT_COUNT,\r\n"
					+ "    FAVO.LIKE_COUNT,\r\n"
					+ "    CASE\r\n"
					+ "        WHEN MUTTER.MAIL_ADDRESS = ? THEN '1'\r\n"
					+ "        ELSE '0'\r\n"
					+ "    END AS DEL_VIS_FLG,\r\n"
					+ "    IFNULL(FAV2.FAV_ON_FLG,'0') AS FAV_ON_FLG \r\n"
					+ "FROM\r\n"
					+ "    MUTTER\r\n"
					+ "        INNER JOIN\r\n"
					+ "    USER_MASTER ON USER_MASTER.MAIL_ADDRESS = MUTTER.MAIL_ADDRESS\r\n"
					+ "        LEFT JOIN\r\n"
					+ "    (SELECT \r\n"
					+ "        MUTTER_ID, COUNT(MAIL_ADDRESS) AS LIKE_COUNT\r\n"
					+ "    FROM\r\n"
					+ "        FAVORITE\r\n"
					+ "    GROUP BY MUTTER_ID) AS FAVO ON FAVO.MUTTER_ID = MUTTER.MUTTER_ID\r\n"
					+ "        LEFT JOIN\r\n"
					+ "    (SELECT \r\n"
					+ "        MUTTER_ID, COUNT(MAIL_ADDRESS) AS COMMENT_COUNT\r\n"
					+ "    FROM\r\n"
					+ "        COMMENT\r\n"
					+ "    GROUP BY MUTTER_ID) AS COM ON COM.MUTTER_ID = MUTTER.MUTTER_ID\r\n"
					+ "        LEFT JOIN\r\n"
					+ "    (SELECT \r\n"
					+ "        MUTTER_ID,\r\n"
					+ "            CASE\r\n"
					+ "                WHEN MAIL_ADDRESS = ? THEN '1'\r\n"
					+ "                ELSE '0'\r\n"
					+ "            END AS FAV_ON_FLG\r\n"
					+ "    FROM\r\n"
					+ "        FAVORITE\r\n"
					+ "        WHERE MAIL_ADDRESS = ?\r\n"
					+ "    GROUP BY MUTTER_ID) AS FAV2 ON FAV2.MUTTER_ID = MUTTER.MUTTER_ID\r\n"
					+ "ORDER BY MUTTER_ID DESC";
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setString(1,mailAddress);
			pStm.setString(2,mailAddress);
			pStm.setString(3,mailAddress);
			System.out.println("mysql    > " + pStm.toString());

			// SELECT文を実行する。
			ResultSet rs = pStm.executeQuery();
			List<Mutter> mutterList = new ArrayList<>();
			// 結果を格納する。
			while(rs.next()) {
				Mutter mutter = new Mutter();
				mutter.setMutterId(rs.getLong("MUTTER_ID"));
				mutter.setMailAddress(rs.getString("MAIL_ADDRESS"));
				mutter.setUserName(rs.getString("USER_NAME"));
				mutter.setMutter(rs.getString("MUTTER"));
				mutter.setMutterYMDHM(rs.getString("MUTTER_YMDHM"));
				mutter.setLikeCount(rs.getLong("LIKE_COUNT"));
				mutter.setCommentCount(rs.getLong("COMMENT_COUNT"));
				mutter.setDelVisFlg(rs.getString("DEL_VIS_FLG"));
				mutter.setFavOnFlg(rs.getString("FAV_ON_FLG"));
				mutterList.add(mutter);
			}
			return mutterList;

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

	public Boolean executeInsertFavorite(Favorite favo) {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "INSERT INTO FAVORITE(MUTTER_ID, MAIL_ADDRESS) VALUES(?, ?)";
			PreparedStatement pStm = conn.prepareStatement(sql);

			// ?の値を設定する
			pStm.setLong(1, favo.getMutterId());
			pStm.setString(2,favo.getMailAddress());
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

	public Boolean executeDeleteFavorite(Favorite favo) {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "DELETE FROM FAVORITE WHERE MUTTER_ID = ? AND MAIL_ADDRESS = ?";
			PreparedStatement pStm = conn.prepareStatement(sql);

			// ?の値を設定する
			pStm.setLong(1, favo.getMutterId());
			pStm.setString(2,favo.getMailAddress());
			System.out.println("mysql    > " + pStm.toString());

			// DELETE文を実行する。
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

	/**
	 * コメントの登録
	 * @param comment
	 * @return
	 */
	public Boolean executeInsertComment(Comment comment) {
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);

			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String sql = "INSERT INTO COMMENT(MUTTER_ID, MAIL_ADDRESS, USER_NAME, COMMENT, COMMENT_YMDHM) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStm = conn.prepareStatement(sql);

			// ?の値を設定する
			pStm.setLong(1, comment.getMutterId());
			pStm.setString(2,comment.getMailAddress());
			pStm.setString(3, comment.getUserName());
			pStm.setString(4, comment.getComment());
			pStm.setString(5, comment.getCommentYMDHM());
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

	/**
	 * コメントリストの取得
	 * @return
	 */
	public List<Comment> selectCommentList(Long mutterId){
		Connection conn = null;

		try {
			// JDBCドライバを読込
			Class.forName(DRIVER_NAME);
			// データベース接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// SELECT分を準備する。
			String sql = "SELECT MUTTER_ID, MAIL_ADDRESS,USER_NAME,COMMENT,COMMENT_YMDHM FROM COMMENT WHERE MUTTER_ID = ?";
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setLong(1, mutterId);

			System.out.println("mysql    > " + pStm.toString());

			// SELECT文を実行する。
			ResultSet rs = pStm.executeQuery();
			List<Comment> commentList = new ArrayList<>();
			// 結果を格納する。
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setMutterId(rs.getLong("MUTTER_ID"));
				comment.setMailAddress(rs.getString("MAIL_ADDRESS"));
				comment.setUserName(rs.getString("USER_NAME"));
				comment.setComment(rs.getString("COMMENT"));
				comment.setCommentYMDHM(rs.getString("COMMENT_YMDHM"));

				commentList.add(comment);
			}
			return commentList;

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


