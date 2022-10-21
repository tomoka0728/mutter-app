package model;

/**
 *
 * コメント情報を保持するDTOリスト
 *
 */
public class Comment {

	/** つぶやきID */
	private Long mutterId;
	/** メールアドレス（コメント者) */
	private String mailAddress;
	/** ユーザー名 */
	private String userName;
	/** コメント内容 */
	private String comment;
	/** コメント時間 */
	private String commentYMDHM;

	public Long getMutterId() {
		return mutterId;
	}
	public void setMutterId(Long mutterId) {
		this.mutterId = mutterId;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentYMDHM() {
		return commentYMDHM;
	}
	public void setCommentYMDHM(String commentYMDHM) {
		this.commentYMDHM = commentYMDHM;
	}

}
