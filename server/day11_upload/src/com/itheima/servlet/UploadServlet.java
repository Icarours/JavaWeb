package com.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *    �����ļ��ϴ������servlet ���� 
 * 
 */
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ��� �ύ����������, 
		// ���� �� ��ͨ�ı�������(�������ƺͲ�����ֵ ) , ���� �ļ��ϴ������� (�ϴ����ļ�������, �Լ��ϴ����ļ�������� )
		
		//��ô�������? 
		
		String name = request.getParameter("name");
		String file = request.getParameter("file");
		ServletInputStream in = request.getInputStream();
		
		System.out.println("name : " + name);
		System.out.println("file : " + file);
		System.out.println("file ��: " + in);
		
		
		//��ʾ��, �������ϴ��벻��ʹ, ��������, ���� �ļ����ϴ���һ���ǳ�����������, ���� �ļ��Ľ������鷳... 
		
		//���һ���¶�����Ҫ��, ���� ͦ�鷳��, ��ô������ д�õ�ͨ�õ� jar��, ����ֻ��Ҫ�����˵�jar���������, 
		// ѧϰѧϰ�Ϳ�����...
		
		// apache (������) --ASF  --- ��Դ��jar��  (dbutils, commons* , hadoop , ssh ...)
		// T2 ������   ---- openresty
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
