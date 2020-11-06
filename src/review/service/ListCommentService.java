package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import review.dao.AlbumDao;
import review.dao.CommentDao;
import review.model.Album;
import review.model.Comment;

public class ListCommentService {
	private AlbumDao albumDao = new AlbumDao();
	private CommentDao commentDao = new CommentDao();
	private int size = 5;
		
	public CommentPage getCommentPage(int pageNum, int no) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Album album = null;
			album = albumDao.selectById(conn, no);
			if(album == null) {
				throw new AlbumNotFoundException();
			}
				
			int total = commentDao.countOfAlbum(conn, no);
			List<Comment> content = commentDao.select(conn, no, (pageNum-1)*size, size);
			
			return new CommentPage(total, pageNum, size, album, content);	
		} catch(SQLException e) {
				throw new RuntimeException(e);
		}	
	}
}
