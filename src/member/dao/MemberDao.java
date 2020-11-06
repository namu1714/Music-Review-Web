package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
	public Member selectById(Connection conn, String id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from member where memberid = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if(rs.next()) {
				member = new Member(
						rs.getString("memberid"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getTimestamp("regdate").toLocalDateTime());
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void insert(Connection conn, Member mem) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("insert into member values(?,?,?,?,?)")){
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setString(4, mem.getEmail());
			pstmt.setTimestamp(5, Timestamp.valueOf(mem.getRegDate()));
			pstmt.executeUpdate();
		}
	}
	
	public void update(Connection conn, Member member) throws SQLException{
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update member set name = ?, password = ?, email = ? where memberid = ?")){
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());			
			pstmt.setString(3, member.getId());
			pstmt.setString(4, member.getEmail());
			pstmt.executeUpdate();
		}
	}
}
