package com.googleplay.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googleplay.util.PropertiesUtils;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("/app")
public class AppServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setCharacterEncoding("UTF-8");
		
		String path1 = PropertiesUtils.getDir() + "/" + "WebInfos/app/applist1";
		String path2 = PropertiesUtils.getDir() + "/" + "WebInfos/app/applist2";
		String path3 = PropertiesUtils.getDir() + "/" + "WebInfos/app/applist3";

		String path = null;
		int index = (Integer.valueOf(req.getParameter("index")) / 20) % 3;
		if (index == 0) {
			path = path1;
		} else if (index == 1) {
			path = path2;
		} else {
			path = path3;
		}

		File file = new File(path);
		long length = file.length();
		resp.setContentLength((int) length);
		OutputStream out = resp.getOutputStream();
		FileInputStream stream = new FileInputStream(file);
		int count = -1;
		byte[] buffer = new byte[1024];
		while ((count = stream.read(buffer)) != -1) {
			out.write(buffer, 0, count);
			out.flush();
		}
		stream.close();
		out.close();
	}
}
