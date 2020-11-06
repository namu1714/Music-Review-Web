package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import review.service.CommentPage;
import review.service.ListCommentService;

public class ReviewHandler implements CommandHandler {
	private ListCommentService listComment = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int albumNo = Integer.parseInt(req.getParameter("album"));
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		CommentPage commentPage = listComment.getCommentPage(pageNo, albumNo);
		req.setAttribute("review", commentPage);
		
		return "/WEB-INF/view/review.jsp";
	}

}
