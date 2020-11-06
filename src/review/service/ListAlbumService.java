package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import review.dao.AlbumDao;
import review.model.Album;

public class ListAlbumService {
	private AlbumDao albumDao = new AlbumDao();
	private int size = 9;
	
	public AlbumPage getAlbumPage(int pageNum, String keyword) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total;
			List<Album> content = null;
			if(keyword==null || keyword.trim().equals("")) {
				total = albumDao.selectCount(conn);
				content = albumDao.select(conn, (pageNum -1)*size, size);
			}
			else {
				total = albumDao.searchCount(conn, keyword);
				content = albumDao.search(conn, keyword, (pageNum - 1)*size, size);
			}
			return new AlbumPage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
