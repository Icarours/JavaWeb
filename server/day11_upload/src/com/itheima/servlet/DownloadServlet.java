package com.itheima.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *    下载文件的servlet
 * 
 *			第一个:
				需要将 要下载的目标文件(物理文件) 当作一个流给读进来.  然后将流 的数据与 response.getOutputStream关联起来.
			
			
			第二个:
				你需要告诉浏览器以下载文件的方式去打开链接... 
				通过http的响应头来控制
				
				需要 使用 到  一个叫做  :   content-disposition:attachment;filename=aaa.txt
				
 */
public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String filename ="japanese_girl.jpg";
		String filename ="日本妞.jpg";
				
		//告诉 浏览器以下载文件的方式去打开链接
		//对于 http的响应, 响应头中是不允许出现中文的, 如果出现中文,需要 进行url 编码, 你编了之后, 浏览器会自动的解码.
		
		//  %AB%5F%AA
		filename= URLEncoder.encode(filename, "UTF-8");
		
		response.setHeader("content-disposition", "attachment;filename="+filename);
		
		// 将 文件以 流的形式读进来
		InputStream in = getServletContext().getResourceAsStream("/日本妞.jpg");
		
		ServletOutputStream out = response.getOutputStream();
		
		int len=0;
		byte[] buf = new byte[1024];
		while((len=in.read(buf))>0){
			out.write(buf, 0, len);
		}

		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
