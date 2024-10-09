package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Quest;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member loginMember(Connection conn, String memberId, String memberPwd) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member(
						rset.getInt("member_number"),
						rset.getString("member_id"),
						rset.getString("member_pwd"),
						rset.getString("member_nickname"),
						rset.getInt("member_exp"),
						rset.getString("member_image"),
						rset.getDate("member_join_date"),
						rset.getInt("member_check_continuecount"),
						rset.getString("member_status"),
						rset.getString("member_introduce"),
						rset.getString("member_email"),
						rset.getString("address"),
						rset.getString("phone")
						);
			} else {
				System.out.println("일치하는 정보가 없습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}
	

	public int insertMember(Connection conn, Member m) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberNickName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
			
			
	public int updatePwdMember(Connection conn, String memberId, String memberPwd, String updatePwd) {
		//update => 처리된 행수
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, memberId);
			pstmt.setString(3, memberPwd);
			
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, String memberId){
		//select -> Member조회 -> ResultSet객체
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery(); // 조회결과가 있다면 한행 반환 | 없다면 반환X
			if(rset.next()) {
				m = new Member(
						rset.getInt("member_number"),
						rset.getString("member_id"),
						rset.getString("member_pwd"),
						rset.getString("member_nickname"),
						rset.getInt("member_exp"),
						rset.getString("member_image"),
						rset.getDate("member_join_date"),
						rset.getInt("member_check_continuecount"),
						rset.getString("member_status"),
						rset.getString("member_introduce"),
						rset.getString("member_email"),
						rset.getString("address"),
						rset.getString("phone")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}
	
	public Member selectMember(Connection conn, int MemberNo){
		//select -> Member조회 -> ResultSet객체
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, MemberNo);
			
			rset = pstmt.executeQuery(); // 조회결과가 있다면 한행 반환 | 없다면 반환X
			if(rset.next()) {
				m = new Member(
						rset.getInt("member_number"),
						rset.getString("member_id"),
						rset.getString("member_pwd"),
						rset.getString("member_nickname"),
						rset.getInt("member_exp"),
						rset.getString("member_image"),
						rset.getDate("member_join_date"),
						rset.getInt("member_check_continuecount"),
						rset.getString("member_status"),
						rset.getString("member_introduce"),
						rset.getString("member_email"),
						rset.getString("address"),
						rset.getString("phone")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}
	
	public int attendanceCheck(Connection conn, int memberNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("attendanceCheck");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int attendanceInsert(Connection conn, int memberNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("attendanceInsert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int totalAttendance(Connection conn, int memberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("totalAttendance");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int deforeAttendCheck(Connection conn, Member loginMember) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("deforeAttendCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMember.getMemberNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int resetAttend(Connection conn, Member loginMember) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("resetAttend");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, loginMember.getMemberNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateContinueCount(Connection conn, int MemberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateContinueCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, MemberNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId, String memberPwd) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberNickName());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateProfile(Connection conn, Member p) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProfile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getMemberNickName());
			pstmt.setString(2, p.getMemberImg());
			pstmt.setString(3, p.getIntroduce());
			pstmt.setString(4,  p.getMemberId());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Quest> selectDailyQuest(Connection conn, Member loginMember) {
		ArrayList<Quest> questList = new ArrayList<>();
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectDailyQuest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, loginMember.getMemberNo());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Quest quest = new Quest(
							rset.getInt("MEMBER_QUEST_NUMBER"),
							rset.getInt("MEMBER_QUEST_SUCCESS"),
							rset.getString("MEMBER_QUEST_DATE"),
							rset.getInt("MEMBER_NUMBER"),
							rset.getInt("QUEST_NUMBER"),
							rset.getString("QUEST_CONTENT")
						);
				
				questList.add(quest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return questList;
	}

	public int insertDailyQuest(Connection conn, Member loginMember, int questNo) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertDailyQuest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setInt(2, questNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectQuestCount(Connection conn) {
		int result = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectQuestCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
}



