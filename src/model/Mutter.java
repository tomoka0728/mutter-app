package model;

import java.util.List;

/**
 *
 * つぶやき情報を保持するMODELクラス
 *
 */
public class Mutter {

	/** つぶやきID */
	private Long mutterId;
	/** メールアドレス */
	private String mailAddress;
	/** ユーザー名 */
	private String userName;
	/** つぶやき内容 */
	private String mutter;
	/** つぶやき時間 */
	private String mutterYMDHM;
	/** いいね数 */
	private Long likeCount;
	/** コメント数 */
	private Long commentCount;
	/** コメント一覧 */
	private List<Comment> commentList;
	/** 削除表示フラグ */
	private String delVisFlg;
	/** イイネ済みフラグ */
	private String favOnFlg;

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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMutter() {
		return mutter;
	}
	public void setMutter(String mutter) {
		this.mutter = mutter;
	}
	public String getMutterYMDHM() {
		return mutterYMDHM;
	}
	public void setMutterYMDHM(String mutterYMDHM) {
		this.mutterYMDHM = mutterYMDHM;
	}
	public Long getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public String getDelVisFlg() {
		return delVisFlg;
	}
	public void setDelVisFlg(String delVisFlg) {
		this.delVisFlg = delVisFlg;
	}
	public String getFavOnFlg() {
		return favOnFlg;
	}
	public void setFavOnFlg(String favOnFlg) {
		this.favOnFlg = favOnFlg;
	}






}
