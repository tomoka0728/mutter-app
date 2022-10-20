package model;

import dao.LoginDAO;

/**
 *
 * ログイン処理に関するロジックを記載するクラス
 *
 */
public class LoginLogic {

	/**
	 * 画面で入力されたメールアドレスとパスワードからユーザー情報を取得する。
	 * @param user メールアドレスとパスワードのみを保持したユーザークラス
	 * @return ユーザー情報
	 */
	public User doLogin(User user) {

		// ユーザー情報を取得する。
		LoginDAO loginDAO = new LoginDAO();

		return loginDAO.doSelectUser(user);


	}
}
