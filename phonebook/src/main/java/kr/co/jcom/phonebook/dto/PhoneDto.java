package kr.co.jcom.phonebook.dto;

public class PhoneDto {
	private String id;
	private String name;
	private String telnum;
	private String addr;
	private String gubun;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	@Override
	public String toString() {
		return "PhoneDto [id=" + id + ", name=" + name + ", telnum=" + telnum + ", addr=" + addr + ", gubun=" + gubun
				+ "]";
	}
}