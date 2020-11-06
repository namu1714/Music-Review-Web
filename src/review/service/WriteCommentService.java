package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.CommentDao;
import review.model.Comment;

public class WriteCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public void write(WriteCommentRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			commentDao.insert(conn, toComment(req));
		} catch(SQLException e){
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public Comment toComment(WriteCommentRequest req) {
		LocalDateTime now = LocalDateTime.now();
		return new Comment(null, req.getAlbumNo(), req.getWriter(), 0, now, now, req.getContent());
	}
}
