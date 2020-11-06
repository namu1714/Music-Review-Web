package review.command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.command.CommandHandler;
import review.service.AddAlbumRequest;
import review.service.AddAlbumService;

public class AddAlbumHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/albumUpload.jsp";
	private AddAlbumService addAlbumService = new AddAlbumService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		AddAlbumRequest addReq;
		
		//파일 업로드
		String path = req.getSession().getServletContext().getRealPath("albumImage");
		String file = "";
		String title = "";
		String artist = "";
		int releaseYear;
		

		int maxSize = 1024 * 1024 * 10;
		
	    try{
	    	MultipartRequest multi = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	    	
	        Enumeration<?> files = multi.getFileNames();
	        String str = (String)files.nextElement();
	        file = multi.getFilesystemName(str);
	        
		    addReq = new AddAlbumRequest(
		    		multi.getParameter("title"), multi.getParameter("artist"), Integer.parseInt(multi.getParameter("releaseYear")), file);
		    addReq.validate(errors);
		    req.setAttribute("addReq", addReq);
		    if(!errors.isEmpty()) {
		    	return FORM_VIEW;
		    }
		    
	    } catch (Exception e) {
			req.getServletContext().log("file error", e);
			return FORM_VIEW;
	    }
		    
	    addAlbumService.add(addReq);
	    
	    res.sendRedirect(req.getContextPath()+"/index.do");
		return null;
	}

}
