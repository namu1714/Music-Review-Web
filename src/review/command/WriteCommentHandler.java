package review.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import review.model.Album;
import review.model.Writer;
import review.service.AlbumNotFoundException;
import review.service.ReadReviewService;
import review.service.WriteCommentRequest;
import review.service.WriteCommentService;

public class WriteCommentHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/writeComment.jsp";
	WriteCommentService writeService = new WriteCommentService();
	ReadReviewService reviewService = new ReadReviewService();
	Integer albumNo = null;
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		albumNo = Integer.parseInt(req.getParameter("album"));
		try {
			Album album = reviewService.getAlbum(albumNo);
			req.setAttribute("album", album);
		} catch(AlbumNotFoundException e) {
			req.getServletContext().log("no album", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteCommentRequest writeReq = createRequest(user, req);
		writeReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		writeService.write(writeReq);
		
		res.sendRedirect(req.getContextPath()+"/review/list.do?album="+albumNo);
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private WriteCommentRequest createRequest(User user, HttpServletRequest req) {
		return new WriteCommentRequest(
			new Writer(user.getId(), user.getName()),
			albumNo,
			req.getParameter("content")
		);		
	}
}
