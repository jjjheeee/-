package kr.co.jcom.phonebook.dto;

public class LoginDto {
	private String id;
	private String pw;
	private String membernm;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMembernm() {
		return membernm;
	}
	public void setMembernm(String membernm) {
		this.membernm = membernm;
	}
	@Override
	public String toString() {
		return "LoginDto [id=" + id + ", pw=" + pw + ", membernm=" + membernm + "]";
	}
}
