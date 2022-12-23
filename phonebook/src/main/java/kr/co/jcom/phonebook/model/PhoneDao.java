package kr.co.jcom.phonebook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import kr.co.jcom.phonebook.dto.LoginDto;
import kr.co.jcom.phonebook.dto.PhoneDto;

@Component
public class PhoneDao {
//	연결 커넥션
	public Connection getConnection() {
		Connection conn   = null;
		String 	   driver = "oracle.jdbc.driver.OracleDriver";
		String	   url    = "jdbc:oracle:thin:@localhost:1521:xe";
		String 	   userid = "ora_user";
		String 	   userpw = "hong";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, userpw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	클로즈 1
	public void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	클로즈 2
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	회원가입 -> 로그인 테이블에 추가
	public void insert(LoginDto loginDto) {
		Connection		  conn  = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO login VALUES (?,?,?) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginDto.getId());
			pstmt.setString(2, loginDto.getPw());
			pstmt.setString(3, loginDto.getMembernm());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt);
		}
	}
	
//	연락처 추가 -> 연락처 테이블에 추가
	public void insert(PhoneDto phoneDto) {
		Connection		  conn  = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO phone VALUES (?,?,?,?,?) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneDto.getId());
			pstmt.setString(2, phoneDto.getName());
			pstmt.setString(3, phoneDto.getTelnum());
			pstmt.setString(4, phoneDto.getAddr());
			pstmt.setString(5, phoneDto.getGubun());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt);
		}
	}
	
//	로그인 하면 로그인 테이블 조회
	public LoginDto selectLogin(String id,String pw){
		Connection 		  conn	= getConnection();
		PreparedStatement pstmt = null;
		ResultSet		  rs    = null;
		
		String sql =" SELECT * FROM login WHERE id=? AND pw=? ";
		LoginDto n = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				n = new LoginDto();
				n.setId(rs.getString("id"));
				n.setPw(rs.getString("pw"));
				n.setMembernm(rs.getString("membernm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}
		return n;
	}
	
//	이름 검색 받아 해당 연락처 조회
	public ArrayList<PhoneDto> select(String name){
		Connection 		  conn 	= getConnection();
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		
		ArrayList<PhoneDto> phoneArr = new ArrayList<>();
		
		String sql = " SELECT * FROM phone WHERE NAME = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneDto n = new PhoneDto();
				n.setName(rs.getString("name"));
				n.setTelnum(rs.getString("telnum"));
				n.setAddr(rs.getString("addr"));
				n.setGubun(rs.getString("gubun"));
				
				phoneArr.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}
		return phoneArr;
	}
	
//	로그인 아이디와 같은 연락처 테이블 전체 목록 조회
	public ArrayList<PhoneDto> selectAll(String id){
		Connection 		  conn  = getConnection();
		PreparedStatement pstmt = null;
		ResultSet		  rs    = null;
		
		ArrayList<PhoneDto> phoneArr = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.name, a.telnum ");
		sql.append(" , a.addr, a.gubun		");
		sql.append(" FROM phone a  			");
		sql.append("WHERE a.id = ?			");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneDto n = new PhoneDto();
				n.setName(rs.getString("name"));
				n.setTelnum(rs.getString("telnum"));
				n.setAddr(rs.getString("addr"));
				n.setGubun(rs.getString("gubun"));
				
				phoneArr.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}
		return phoneArr;
	}
	
//	전화번호를 파라미터로 받아 해당 연락처 정보 수정
	public void update(String id, String telnum,PhoneDto phoneDto) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE phone 				 ");
		sql.append("   SET name = ?, TELNUM = ?  ");
		sql.append("     , ADDR = ?, GUBUN = ?	 ");
		sql.append(" WHERE id =? AND telnum =?   ");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, phoneDto.getName());
			pstmt.setString(2, phoneDto.getTelnum());
			pstmt.setString(3, phoneDto.getAddr());
			pstmt.setString(4, phoneDto.getGubun());
			pstmt.setString(5, id);
			pstmt.setString(6, telnum);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	전화번호를 파라미터로 받아 해당 연락처 삭제
	public void delete(String id,String telnum) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		String sql = " DELETE phone WHERE id =? AND telnum =? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, telnum);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
