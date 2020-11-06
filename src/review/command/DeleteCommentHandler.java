package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import review.model.Comment;
import review.service.CommentNotFoundException;
import review.service.DeleteCommentService;
import review.service.PermissionDeniedException;

public class DeleteCommentHandler implements CommandHandler {
	DeleteCommentService deleteService = new DeleteCommentService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int commentNo = Integer.parseInt(noVal);
		try {
			Comment comment = deleteService.delete(commentNo, authUser.getId());
			req.setAttribute("comment", comment);
			return "/WEB-INF/view/deleteSuccess.jsp";
		}catch(CommentNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
