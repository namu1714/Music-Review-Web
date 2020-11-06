package review.command;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import review.service.AlbumPage;
import review.service.ListAlbumService;

public class ListAlbumHandler implements CommandHandler {
	private ListAlbumService listAlbumService = new ListAlbumService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		String keyword = req.getParameter("search");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		AlbumPage albumPage = listAlbumService.getAlbumPage(pageNo, keyword);
		req.setAttribute("albumPage", albumPage);
		if(keyword==null || keyword.trim().equals("")){
			req.setAttribute("search", "");
			req.setAttribute("inputText", "type artist or album");
		}
		else {
			req.setAttribute("search", URLEncoder.encode(keyword, "UTF-8"));
			req.setAttribute("inputText", keyword);
		}
		return "/WEB-INF/view/index.jsp";
	}
}
