package servlet.category;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.Comment;
import dao.CommentDao;

/*
 *  1.5.5 商品评论
	发送方式	发送URL	备注
	GET	/product/comment	
	参数名称	描述	样例
	pId	商品ID	1102539
	page	第几页	1
	pageNum	每页个数	10

 *  http://localhost:8080/market/product/comment?pId=1&page=0&pageNum=10
 */

public class CommentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pId =Integer.parseInt(request.getParameter("pId"));
		int page =Integer.parseInt(request.getParameter("page"));
		int pageNum =Integer.parseInt(request.getParameter("pageNum"));
		
		CommentDao dao = new CommentDao();
		List<Comment> list = dao.getComment(pId,page,pageNum);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("response", "productComment");
		data.put("comment",list);
		CommonUtil.renderJson(response, data);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
