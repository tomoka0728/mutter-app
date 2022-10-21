package model;

public class Favorite {

	/** つぶやきID */
	private Long mutterId;
	/** メールアドレス */
	private String mailAddress;

	public Long getMutterId() {
		return mutterId;
	}
	public void setMutterId(Long mutterId) {
		this.mutterId = mutterId;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}


}
