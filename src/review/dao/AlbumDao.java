package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import review.model.Album;

public class AlbumDao {
	public Album selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from album where album_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Album album = null;
			if(rs.next()) {
				album = new Album(rs.getInt("album_no"), rs.getString("title"), rs.getString("artist"), rs.getInt("release_year"), rs.getString("cover_image"));
			}
			return album;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Album selectByInfo(Connection conn, String title, String artist) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from album where title = ? and artist = ?");
			pstmt.setString(1, title);
			pstmt.setString(2, artist);
			rs = pstmt.executeQuery();
			Album album = null;
			if(rs.next()) {
				album = new Album(rs.getInt("album_no"), rs.getString("title"), rs.getString("artist"), rs.getInt("release_year"), rs.getString("cover_image"));
			}
			return album;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Album album) throws SQLException {
		try(PreparedStatement pstmt = conn.prepareStatement("insert into album (title, artist, release_year, cover_image) values(?,?,?,?)")){
			pstmt.setString(1, album.getTitle());
			pstmt.setString(2, album.getArtist());
			pstmt.setInt(3, album.getReleaseYear());
			pstmt.setString(4, album.getImage());
			pstmt.executeUpdate();
		}
	}
	
	public List<Album> select(Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from album order by album_no desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Album> result = new ArrayList<>();
			while(rs.next()) {
				result.add(new Album(
						rs.getInt("album_no"), rs.getString("title"), rs.getString("artist"), rs.getInt("release_year"), rs.getString("cover_image")));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Album> search(Connection conn, String keyword, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from album where " + "(title like ? or artist like ?) " + "order by album_no desc limit ?, ?");
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();
			List<Album> result = new ArrayList<>();
			while(rs.next()) {
				result.add(new Album(
						rs.getInt("album_no"), rs.getString("title"), rs.getString("artist"), rs.getInt("release_year"), rs.getString("cover_image")));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from album");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public int searchCount(Connection conn, String keyword) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from album where " + "(title like ? or artist like ?)");
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
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
}
