package com.googleplay.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googleplay.util.PropertiesUtils;

@WebServlet("/download")
public class DownloadServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String range = req.getParameter("range");
		System.out.println("name : " + name);
		System.out.println("range : " + range);

		String path = PropertiesUtils.getDir() + "/" + "WebInfos/" + name;
		File file = new File(path);
		long length = file.length();
		resp.setContentLength((int) length);
		OutputStream out = resp.getOutputStream();

		if (range == null || "".equals(range.trim())) {
			FileInputStream stream = new FileInputStream(file);
			int count = -1;
			byte[] buffer = new byte[1024];
			while ((count = stream.read(buffer)) != -1) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				out.write(buffer, 0, count);
				out.flush();
			}
			stream.close();
			out.close();
		} else {
			try {
				// RandomAccessFile raf = new RandomAccessFile(file, "r");
				// raf.seek(Long.valueOf(range));
				// int count = 0;
				// byte[] buffer = new byte[1024];
				// while ((count = raf.read(buffer)) != -1) {
				// System.out.println("@@@2");
				// try {
				// Thread.sleep(10);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// out.write(buffer, 0, count);
				// out.flush();
				// }
				// System.out.println("@@@3");
				// raf.close();
				// out.close();

				FileInputStream stream = new FileInputStream(file);
				stream.skip(Long.valueOf(range));
				int count = -1;
				byte[] buffer = new byte[1024];
				while ((count = stream.read(buffer)) != -1) {
					System.out.println("@@@2");
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					out.write(buffer, 0, count);
//					out.flush();
				}
				stream.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
