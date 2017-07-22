package servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.FavoritesBean;
import bean.Product;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import dao.FavoritesDao;
import dao.ProductDao;

/*
 *  GET	/favorites	需要先登录获取userid，在将userid添加到请求头
	参数名称	描述	样例
	page	第几页	0
	pageNum	每页个数	10
    RequestParams params = new RequestParams();
    params.addHeader("userid", "20428");
    params.addBodyParameter("page", "0");
	params.addBodyParameter("pageNum", "10");
    mHttpUtils.send(HttpMethod.POST, HOST + "/favorites", params,
				new RequestCallBack<String>() {}
 */

public class Favorites extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HashMap<String, Object> data = new HashMap<String, Object>();
		String userid = request.getHeader("userid");
		if (userid == null) {
			data.put("response", "error");
			data.put("error", "userid请求头为空");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_NO_USERID_ON_REQUEST_HEAD);
			CommonUtil.renderJson(response, data);
		}

		String page = request.getParameter("page");
		String pageNum = request.getParameter("pageNum");

		if (page == null || page == "" || pageNum == null || pageNum == "") {
			data.put("response", "error");
			data.put(CommonUtil.ERROR_CODE,
					CommonUtil.ERROR_CODE_REQUEST_PARAMETER_ERROR);
			data.put("error", "请求参数错误或缺失");
			CommonUtil.renderJson(response, data);
		}

		FavoritesDao dao = new FavoritesDao();
		List<FavoritesBean> list = dao.getFavorites(userid,
				Integer.valueOf(page), Integer.valueOf(pageNum));
		ProductDao productDao = new ProductDao();
		ArrayList<Product> arrayList = new ArrayList<Product>();
		for (FavoritesBean favoritesBean : list) {
			Product product = productDao.getProduct(Integer
					.parseInt(favoritesBean.getPid()));
			arrayList.add(product);
		}

		data.put("response", "favorites");
		data.put("productList", arrayList);
		data.put("listCount", arrayList.size());
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Product.class, "id", "name", "pic", "marketPrice", "price");

		CommonUtil.renderJsonWithFilter(response, data, filter);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
