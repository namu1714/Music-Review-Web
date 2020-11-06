package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import review.dao.AlbumDao;
import review.dao.CommentDao;
import review.model.Album;
import review.model.Comment;

public class ReadReviewService {
	private AlbumDao albumDao = new AlbumDao();
	private CommentDao commentDao = new CommentDao();
	
	public Comment getComment(int commentNo) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Comment comment = commentDao.select(conn, commentNo);
			if(comment == null) {
				throw new CommentNotFoundException();
			}
			return comment;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Album getAlbum(int albumNo) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Album album = albumDao.selectById(conn, albumNo);
			if(album == null) {
				throw new AlbumNotFoundException();
			}
			return album;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
