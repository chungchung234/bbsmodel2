package com.example.model2.dao;

import com.example.model2.db.DBClose;
import com.example.model2.db.DBConnection;
import com.example.model2.dto.BbsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BbsDao {
	
	private static BbsDao dao = new BbsDao();
	
	private BbsDao() {
	}
	
	public static BbsDao getInstance() {
		return dao;
	}
	
	public List<BbsDto> getBbslist() {
		
		String sql = " select seq, id, ref, step, depth, "
				   + "			title, content, wdate, del, readcount "
				   + " from bbs "
				   + " order by ref desc, step asc ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbslist success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getBbslist success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbslist success");
			
			while(rs.next()) {
				
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				list.add(dto);
			}
			System.out.println("4/4 getBbslist success");
			
		} catch (SQLException e) {
			System.out.println("getBbslist fail");
			e.printStackTrace();
		} finally {
			
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}

	public boolean writeBbs(BbsDto dto) {
		
		String sql = " INSERT INTO BBS(ID, REF, STEP, DEPTH,"
				   + "                 TITLE, CONTENT, WDATE,"
				   + "                 DEL, READCOUNT) "
				   + " VALUES(?, (select ifnull(max(ref), 0)+1 from bbs b), 0, 0, "
				   + "					?, ?, NOW(), "
				   + "					0, 0) ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/3 writeBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("2/3 writeBbs success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 writeBbs success");
			
		} catch (SQLException e) {
			System.out.println("writeBbs fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	
	public List<BbsDto> getBbsSearchlist(String choice, String search) {
		
		String sql = " select seq, id, ref, step, depth, "
				   + "			title, content, wdate, del, readcount "
				   + " from bbs ";
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " where title like '%" + search + "%' "; 
		} else if(choice.equals("content")) {
			sWord = " where content like '%" + search + "%' ";
		} else if(choice.equals("writer")) {
			sWord = " where id='" + search + "' "; 
		}  
		sql = sql + sWord;
		
		sql = sql + " order by ref desc, step asc ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbslist success");
				
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getBbslist success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbslist success");
			
			while(rs.next()) {
				
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				list.add(dto);
			}
			System.out.println("4/4 getBbslist success");
			
		} catch (SQLException e) {
			System.out.println("getBbslist fail");
			e.printStackTrace();
		} finally {
			
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	// 글의 총수
	public int getAllBbs(String choice, String search) {
		
		String sql = " select count(*) from bbs ";
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " where title like '%" + search + "%' "; 
		} else if(choice.equals("content")) {
			sWord = " where content like '%" + search + "%' ";
		} else if(choice.equals("writer")) {
			sWord = " where id='" + search + "' "; 
		}  
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
		
		try {
			conn = DBConnection.getConnection();
			
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				len = rs.getInt(1);
			}			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return len;		
	}
	
	public List<BbsDto> getBbsPagelist(String choice, String search, int pageNumber) {
		
		String sql = " select seq, id, ref, step, depth, "
				   + "			title, content, wdate, del, readcount "
				   + " from ";
		
		sql += "(	select row_number()over(order by ref desc, step asc) as rnum, "
				+ "		seq, id, ref, step, depth, title, content, wdate, del, readcount "
				+ "		from bbs";
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " where title like '%" + search + "%' "; 
		} else if(choice.equals("content")) {
			sWord = " where content like '%" + search + "%' ";
		} else if(choice.equals("writer")) {
			sWord = " where id='" + search + "' "; 
		}  
		sql = sql + sWord;
		
		sql = sql + " order by ref desc, step asc) a ";
		
		sql = sql + " where rnum between ? and ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int start, end;
		start = 1 + 10 * pageNumber;	// 0 1	
		end = 10 + 10 * pageNumber;		
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbslist success");
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getBbslist success");
						
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbslist success");
			
			while(rs.next()) {
				
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10));
				list.add(dto);
			}
			System.out.println("4/4 getBbslist success");
			
		} catch (SQLException e) {
			System.out.println("getBbslist fail");
			e.printStackTrace();
		} finally {
			
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	public BbsDto getBbs(int seq) {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ "			TITLE, CONTENT, WDATE, DEL, READCOUNT "
				+ "	   FROM BBS "
				+ "    WHERE SEQ=? ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		BbsDto dto = null;		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 getBbs success");
			
			rs = psmt.executeQuery();	
			if(rs.next()) {
				int n = 1;
				dto = new BbsDto(	rs.getInt(n++), 
									rs.getString(n++), 
									rs.getInt(n++), 
									rs.getInt(n++), 
									rs.getInt(n++), 
									rs.getString(n++), 
									rs.getString(n++), 
									rs.getString(n++), 
									rs.getInt(n++), 
									rs.getInt(n++));
			}	
			System.out.println("3/3 getBbs success");
			
		} catch (SQLException e) {
			System.out.println("getBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
	}
	
	public void readcount(int seq) {
		String sql = " UPDATE BBS "
				   + " SET READCOUNT=READCOUNT+1 "
				   + " WHERE SEQ=? ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		
		try {
			conn = DBConnection.getConnection();
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			psmt.execute();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}		
	}
	
	public boolean updateBbs(int seq, String title, String content) {
		String sql = "  UPDATE BBS "
					+ " SET TITLE=?, CONTENT=? "
					+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			
			System.out.println("2/3 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S updateBbs");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(conn, psmt, null);			
		}		
		
		return count>0?true:false;
	}
	
	public boolean deleteBbs(int seq) {
		
		String sql = " UPDATE BBS "
					+ " SET DEL=1 "
					+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S deleteBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 S deleteBbs");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S deleteBbs");
			
		} catch (Exception e) {		
			System.out.println("fail deleteBbs");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);			
		}
		
		return count>0?true:false;
	}
	
	//					  부모글번호  새로운 댓글
	public boolean answer(int seq, BbsDto bbs) {
		
		// update
		String sql1 = " update bbs "
					+ " set step=step+1 "
					+ " where ref = (select ref from (select ref from bbs a where seq=?) A) "
					+ "   and step > (select step from (select step from bbs b where seq=?) B) ";
		
		// insert
		String sql2 = " insert into bbs(id, "
					+ "					ref, step, depth, "
					+ "					title, content, wdate, del, readcount) "
					+ " values(?,"
					+ "                 (select ref from bbs a where seq=?), "
					+ "                 (select step from bbs b where seq=?) + 1, "
					+ "                 (select depth from bbs c where seq=?) + 1, "
					+ "                 ?, ?, now(), 0, 0)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();		
			conn.setAutoCommit(false);
			
			// update
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			
			count = psmt.executeUpdate();
			
			// psmt 초기화
			psmt.clearParameters();
			
			// insert
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, bbs.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, bbs.getTitle());
			psmt.setString(6, bbs.getContent());
			
			count += psmt.executeUpdate();
			
			conn.commit();			
			
		} catch (SQLException e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			e.printStackTrace();
		} finally {			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;		
	}
	
}













