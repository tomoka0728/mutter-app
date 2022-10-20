package model;

/**
 *
 * ユーザー情報を保持するMODELクラス
 *
 */
public class User {

	/** メールアドレス */
	private String mailAddress;
	/** ユーザー名 */
	private String userName;
	/** 性別（１：男、２；女） */
	private String genderKbn;
	/** 生年月日 */
	private String birthDate;
	/** パスワード */
	private String password;

	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGenderKbn() {
		return genderKbn;
	}
	public void setGenderKbn(String genderKbn) {
		this.genderKbn = genderKbn;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
