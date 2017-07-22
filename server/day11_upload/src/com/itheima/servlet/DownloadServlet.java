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
 *    �����ļ���servlet
 * 
 *			��һ��:
				��Ҫ�� Ҫ���ص�Ŀ���ļ�(�����ļ�) ����һ������������.  Ȼ���� �������� response.getOutputStream��������.
			
			
			�ڶ���:
				����Ҫ����������������ļ��ķ�ʽȥ������... 
				ͨ��http����Ӧͷ������
				
				��Ҫ ʹ�� ��  һ������  :   content-disposition:attachment;filename=aaa.txt
				
 */
public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String filename ="japanese_girl.jpg";
		String filename ="�ձ��.jpg";
				
		//���� ������������ļ��ķ�ʽȥ������
		//���� http����Ӧ, ��Ӧͷ���ǲ�����������ĵ�, �����������,��Ҫ ����url ����, �����֮��, ��������Զ��Ľ���.
		
		//  %AB%5F%AA
		filename= URLEncoder.encode(filename, "UTF-8");
		
		response.setHeader("content-disposition", "attachment;filename="+filename);
		
		// �� �ļ��� ������ʽ������
		InputStream in = getServletContext().getResourceAsStream("/�ձ��.jpg");
		
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
