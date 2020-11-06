package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.CommentDao;
import review.model.Comment;

public class DeleteCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public Comment delete(int commentNo, String userId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment comment = commentDao.select(conn, commentNo);
			if(comment==null) {
				throw new CommentNotFoundException();
			}
			if(!canDelete(userId, comment)) {
				throw new PermissionDeniedException();
			}
			
			commentDao.delete(conn, commentNo);
			conn.commit();
			return comment;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
		
	private boolean canDelete(String modifyingUserId, Comment comment) {
		return comment.getWriter().getId().equals(modifyingUserId);
	}
}
