package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.CommentDao;
import review.model.Comment;

public class ModifyCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment comment = commentDao.select(conn, modReq.getCommentNumber());
			if(comment==null) {
				throw new CommentNotFoundException();
			}
			if(!canModify(modReq.getUserId(), comment)) {
				throw new PermissionDeniedException();
			}
			
			commentDao.update(conn, modReq.getCommentNumber(), modReq.getContent());
			conn.commit();
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
		
	private boolean canModify(String modifyingUserId, Comment comment) {
		return comment.getWriter().getId().equals(modifyingUserId);
	}
}
