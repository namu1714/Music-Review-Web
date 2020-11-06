package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import review.model.Comment;
import review.model.Writer;

public class CommentDao {
	public Comment insert(Connection conn, Comment comment) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into comment (album_no, writer_id, writer_name, likes, regdate, moddate, content) values(?,?,?,0,?,?,?)");
			pstmt.setInt(1, comment.getAlbum());
			pstmt.setString(2, comment.getWriter().getId());
			pstmt.setString(3, comment.getWriter().getName());
			pstmt.setTimestamp(4, toTimeStamp(comment.getRegDate()));
			pstmt.setTimestamp(5, toTimeStamp(comment.getModifiedDate()));
			pstmt.setString(6, comment.getContent());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return comment;
			} else {
				return null;
			}
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Comment select(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from comment where comment_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Comment comment = null;
			if(rs.next()) {
				comment = convertComment(rs);
			}
			return comment;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Comment> select(Connection conn, int albumNo, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from comment where album_no = ? order by comment_no desc limit ?, ?");
			pstmt.setInt(1, albumNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<Comment> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertComment(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int countOfAlbum(Connection conn, int albumNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from comment where album_no = ?");
			pstmt.setInt(1, albumNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, int no, String content) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement(
				"update comment set content = ?, moddate = now() where comment_no = ?")){
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("delete from comment where comment_no = ?")){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
	
	public Comment convertComment(ResultSet rs) throws SQLException {
		return new Comment(
				rs.getInt("comment_no"),
				rs.getInt("album_no"),
				new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getInt("likes"),
				rs.getTimestamp("regdate").toLocalDateTime(),
				rs.getTimestamp("moddate").toLocalDateTime(),
				rs.getString("content")
				);
				
	}
	
	public void increaseLike(Connection conn, int no) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement(
				"update comment set likes = likes + 1 where comment_no = ?")){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} 
	}
	
	private Timestamp toTimeStamp(LocalDateTime dateTime) {
		return Timestamp.valueOf(dateTime);
	}
}
