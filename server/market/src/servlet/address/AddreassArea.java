package servlet.address;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CommonUtil;
import bean.AreaBean;
import dao.AreaDao;

/*
    2.0.2 地址三级列表
	发送方式	发送URL	备注
	GET	/addressarea	地址在处理时可以重点一个省为例，填写几个市信息即可，重点是展示省市的联动
	参数名称	描述	样例
	id	地区id	1,当为0时获取所有省级列表(parentId)
	http://localhost:8080/market/addressarea?id=0

 */
public class AddreassArea extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		AreaDao dao = new AreaDao();
		String parent = request.getParameter("id");
		List<AreaBean> list = dao.getAreaBeanList(parent);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("response", "addressArea");
		data.put("areaList", list);
		CommonUtil.renderJson(response, data);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
