package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.AlbumDao;
import review.model.Album;

public class AddAlbumService {
	private AlbumDao albumDao = new AlbumDao();
	
	public void add(AddAlbumRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Album album = albumDao.selectByInfo(conn, req.getTitle(), req.getArtist());
			if(album != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicatedAlbumException();
			}
			albumDao.insert(conn, toAlbum(req));
			conn.commit();
		} catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public Album toAlbum(AddAlbumRequest req) {
		return new Album(null, req.getTitle(), req.getArtist(), req.getReleaseYear(), req.getCoverImage());
	}
}
