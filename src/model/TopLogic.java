package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.TopDAO;

public class TopLogic {

	public Boolean executeInsertMutter(User user,String strMutter) {

		// 現在日時を取得する。
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd hh:mm");
		String strTime = sdf.format(timestamp);

		// つぶやきIDを取得する。
		TopDAO tDAO = new TopDAO();
		Long mutterId = tDAO.doGetMutterId();
		// nullの場合は処理を行わない。
		if(mutterId == null) {
			return false;
		}

		// DB登録情報を生成する。
		Mutter mutter = new Mutter();
		mutter.setMutterId(++mutterId);
		mutter.setMailAddress(user.getMailAddress());
		mutter.setMutter(strMutter);
		mutter.setMutterYMDHM(strTime);

		// つぶやきを登録する。
		boolean result = tDAO.executeInsertMutter(mutter);

		return result;

	}

	public Boolean executeDeleteMutter(String mutterId, String likeCount, String commentCount) {

		// mutterIdをLong変換する。
		Long mutterIdL = Long.valueOf(mutterId);
		TopDAO tDAO = new TopDAO();

		// つぶやき，関連するコメント、イイネを削除する。
		boolean result = tDAO.executeDeleteMutter(mutterIdL,likeCount,commentCount);

		return result;

	}

	public List<Mutter> doGetMutterList(String mailAdress){

		// SQLからつぶやき一覧を取得する。
		List<Mutter> mutterList = new ArrayList<>();
		TopDAO tDAO = new TopDAO();
		mutterList = tDAO.selectMutterList(mailAdress);
		if(mutterList != null && mutterList.size() > 0) {
			// 取得したつぶやき一覧に該当するコメント一覧を取得する。
			for(Mutter mutter: mutterList) {
				List<Comment> commentList = new ArrayList<>();
				Long mutterId = mutter.getMutterId();
				commentList = tDAO.selectCommentList(mutterId);
				mutter.setCommentList(commentList);
			}
		}

		return mutterList;

	}

	/**
	 * イイネ情報の登録
	 * @param user
	 * @param mutterId
	 * @return
	 */
	public Boolean executeInsertFavorite(User user, String mutterId) {

		// mutterIdをLong変換する。
		Long mutterIdL = Long.valueOf(mutterId);

		// イイネ情報を生成する。
		Favorite favo = new Favorite();
		favo.setMutterId(mutterIdL);
		favo.setMailAddress(user.getMailAddress());
		TopDAO tDAO = new TopDAO();

		// DB登録

		return tDAO.executeInsertFavorite(favo);

	}
	/**
	 * イイネを削除
	 * @param user
	 * @return
	 */
	public Boolean executeDeleteFavorite(User user, String mutterId) {
		// mutterIdをLong変換する。
		Long mutterIdL = Long.valueOf(mutterId);
		Favorite favo = new Favorite();
		favo.setMutterId(mutterIdL);
		favo.setMailAddress(user.getMailAddress());
		TopDAO tDAO = new TopDAO();
		// データ削除
		return tDAO.executeDeleteFavorite(favo);

	}

	/**
	 * コメント情報の登録
	 * @param user
	 * @param mutterId
	 * @return
	 */
	public Boolean executeInsertComment(User user, String mutterId, String commentStr) {

		// mutterIdをLong変換する。
		Long mutterIdL = Long.valueOf(mutterId);

		// コメント情報を生成する。
		Comment comment = new Comment();
		comment.setMutterId(mutterIdL);
		comment.setMailAddress(user.getMailAddress());
		comment.setUserName(user.getUserName());
		comment.setComment(commentStr);
		// 現在日時を取得する。
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd hh:mm");
		String strTime = sdf.format(timestamp);
		comment.setCommentYMDHM(strTime);

		TopDAO tDAO = new TopDAO();

		// DB登録

		return tDAO.executeInsertComment(comment);

	}
}
