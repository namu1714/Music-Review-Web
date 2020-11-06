package review.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import review.model.Album;
import review.model.Comment;
import review.service.AlbumNotFoundException;
import review.service.CommentNotFoundException;
import review.service.ReadReviewService;

public class ReadCommentHandler implements CommandHandler {
	ReadReviewService reviewService = new ReadReviewService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int commentNo = Integer.parseInt(noVal);
		try {
			Comment comment = reviewService.getComment(commentNo);
			Album album = reviewService.getAlbum(comment.getAlbum());
			req.setAttribute("comment", comment);
			req.setAttribute("album", album);
			req.setAttribute("regDate", dateFormat(comment.getRegDate()));
			req.setAttribute("modifiedDate", dateFormat(comment.getModifiedDate()));
			
			User user = (User)req.getSession().getAttribute("authUser");
			if(user!=null)
				req.setAttribute("user", user);
			
			return "/WEB-INF/view/readComment.jsp";
		} catch(CommentNotFoundException|AlbumNotFoundException e) {
			req.getServletContext().log("no review", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	public String dateFormat(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
}
