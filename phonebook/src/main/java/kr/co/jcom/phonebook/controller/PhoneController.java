package kr.co.jcom.phonebook.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.jcom.phonebook.dto.LoginDto;
import kr.co.jcom.phonebook.dto.PhoneDto;
import kr.co.jcom.phonebook.model.PhoneDao;

@Controller
@RequestMapping("/phone")
public class PhoneController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private PhoneDao dao;
	
	@Autowired
	public PhoneController(PhoneDao dao) {
		this.dao = dao;
	}

//	로그인 페이지 맵핑
	@RequestMapping("/loginPage")
	public String loginPage(HttpSession session) {
		if(session != null && session.getAttribute("id") != null) {
			session.invalidate();
		}
		return "/jsp/LoginPage";
	}
	
//	로그아웃 
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("id") != null) {
			session.invalidate();
		}
		return "redirect:/phone/loginPage";
	}

//	로그인 체크 select
	@PostMapping("/login")
	public String loginCheck(@ModelAttribute LoginDto loginDto
						   , RedirectAttributes ra
						   , HttpSession session) {
		if (dao.selectLogin(loginDto.getId(),loginDto.getPw()) == null) {
			ra.addFlashAttribute("f", "잘못된 ID 또는 PW입니다");
			return "redirect:/phone/loginPage";
		}
		LoginDto login = dao.selectLogin(loginDto.getId(),loginDto.getPw());
		session.setAttribute("id", login.getId());
		session.setAttribute("pw", login.getPw());
		session.setAttribute("membernm", login.getMembernm());
		
		return "redirect:/phone/select";
	}
	
//	연락처 추가 insert
	@PostMapping("/insertphone")
	public String insert(@ModelAttribute PhoneDto phoneDto
						, RedirectAttributes ra
						, HttpSession session) {
		phoneDto.setId((String) session.getAttribute("id"));
		dao.insert(phoneDto);
		ra.addFlashAttribute("addphone", "추가되었습니다.");
		return "redirect:/phone/select";
	}
	
//	회원가입 insert
	@PostMapping("/insertmember")
	public String insert(@ModelAttribute LoginDto loginDto
					   , RedirectAttributes ra) {
		dao.insert(loginDto);
		ra.addFlashAttribute("add", "회원가입 되었습니다.");
		return "redirect:/phone/loginPage";
	}

//	전체 목록 검색 
	@RequestMapping("/select")
	public String selectAll(Model model
						   , HttpSession session) {
		
		if(session.getAttribute("id") == null) {
			return "redirect:/phone/loginPage";
		}
		
		ArrayList<PhoneDto> phoneArr;

		phoneArr = dao.selectAll((String)session.getAttribute("id"));
		model.addAttribute("name", session.getAttribute("membernm"));
		model.addAttribute("list", phoneArr);
		return "/jsp/MainPage";
	}
	
//	목록중 이름으로 검색
	@GetMapping("/selectname/{name}")
	public String selectName() {
		return "";
	}
	
//	연락처 수정 update
	@PostMapping("/update/{telnum}")
	public String update(@ModelAttribute PhoneDto phoneDto
						, @PathVariable String telnum
						, RedirectAttributes rs
						, HttpSession session) {
		phoneDto.setId((String)session.getAttribute("id"));
		dao.update((String)session.getAttribute("id"), telnum, phoneDto);
		logger.info(telnum);
		rs.addFlashAttribute("update", "수정이 완료 되었습니다");
		return "redirect:/phone/select";
	}
//	연락처 삭제 delete
	@GetMapping("/delete/{telnum}")
	public String delete(@PathVariable String telnum
						, RedirectAttributes rs
						, HttpSession session) {
		dao.delete((String)session.getAttribute("id"),telnum);
		rs.addFlashAttribute("delete", "삭제되었습니다");
		return "redirect:/phone/select";
	}

}
