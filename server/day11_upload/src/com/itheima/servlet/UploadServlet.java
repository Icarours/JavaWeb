package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *    处理文件上传请求的servlet 程序 
 * 
 */
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获得 提交过来的数据, 
		// 包括 有 普通的表单的数据(参数名称和参数的值 ) , 还有 文件上传的数据 (上传的文件的名称, 以及上传的文件里的数据 )
		
		//怎么样获得呢? 
		
		String name = request.getParameter("name");
		String file = request.getParameter("file");
		ServletInputStream in = request.getInputStream();
		
		System.out.println("name : " + name);
		System.out.println("file : " + file);
		System.out.println("file 流: " + in);
		
		
		//演示后, 发现以上代码不好使, 可以这样, 由于 文件的上传是一个非常常见的需求, 并且 文件的解析很麻烦... 
		
		//如果一个事儿经常要做, 并且 挺麻烦的, 那么就有人 写好的通用的 jar包, 我们只需要将别人的jar包引入进来, 
		// 学习学习就可以了...
		
		// apache (阿帕奇) --ASF  --- 开源的jar包  (dbutils, commons* , hadoop , ssh ...)
		// T2 发布会   ---- openresty
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
