package kr.co.jcom.phonebook.model;

import java.util.ArrayList;

import kr.co.jcom.phonebook.dto.PhoneDto;

public class test {

	public static void main(String[] args) {
		PhoneDao dao = new PhoneDao();
		ArrayList<PhoneDto> n = dao.selectAll("aa");
		System.out.println(n);
	}

}
