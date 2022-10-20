package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.RegisterDAO;

public class RegistLogic {

	public String executeRegist(User user) {


		Pattern p = Pattern.compile("^[a-zA-Z0-9]");
		Matcher m = p.matcher(user.getPassword());


		if(m.find() == false) {
			// 英数字判定NG
			return "1";
		} else if (user.getPassword().length() < 4) {
			// 文字数判定NG
			return "2";
		}

		RegisterDAO registerDAO = new RegisterDAO();
		if(registerDAO.doMatchMailAddress(user.getMailAddress())) {
			// メールアドレスが存在している場合、登録せずにエラーを表示する。
			return "3";
		}

		// メールアドレスが未存在の場合、ユーザー情報を登録する。
		registerDAO.executeInsertUser(user);
		return "0";

	}
}
