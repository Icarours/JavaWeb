package com.itheima.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 处理文件上传的请求的servlet 程序
 * 
 * 一般如果要使用到 开源的第三方的jar包,首先要 导入到工程中去 commons-fileupload-1.2.1.jar 和
 * commons-io-1.4.jar
 * 
 */
public class UploadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			// 写代码...
			
			//判断 请求 是否 合乎 规范... 如果不合乎规范, 你应该给用户一个友好提示...
			boolean isQualified = ServletFileUpload.isMultipartContent(request);
			
			if(!isQualified){
				
				//进来则表示 不合乎规范...
				request.setAttribute("message", "亲, 您的表单 格式有问题, 请检查... 不要 瞎搞...");
				request.getRequestDispatcher("/upload.jsp").forward(request, response);
				return;
			}
			
			//准备好 允许上传的文件的类型 
			String[] extensions = {".txt",".jpg","png",".avi",".rmvb",".doc",".zip"};
			

			// 首先要拿到一个工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();

			//可以通过工厂去设置 临时缓冲区的大小以及临时文件存放的位置
			factory.setSizeThreshold(1024*1024*2);  //缓冲区 就是 2M 
			
			// 假设 临时文件防盗   /tmp 文件夹
			//使用 serlvetContext获得 路径
			String tmpPath = getServletContext().getRealPath("/tmp");
			
			factory.setRepository(new File(tmpPath));
			
			// 拿到 解析器 对象
			ServletFileUpload uploader = new ServletFileUpload(factory);

			//在解析前,设置  解析头时 使用到的码表
			uploader.setHeaderEncoding("UTF-8");
			
			//在解析之前, 去 限定 上传的文件的大小 
			uploader.setFileSizeMax(1024*1024*2000);  //限定单个文件的大小, 不超过 20M
			
			uploader.setSizeMax(1024*1024*2000);  //限定 总的文件的大小 , 不超过 100M
			
			
			//设置一个监听器, 监听上传的进度
			// 设置了监听器之后, 那么 系统就会不定时的去调用监听器的 update方法,... 
			uploader.setProgressListener(new ProgressListener() {
				
				// pBytesRead: 实时的读到了 多少个 字节 
				// pContentLength: 文件的总大小多少 
				// pItems:   当前是 第几个item  
				public void update(long pBytesRead, long pContentLength, int pItems) {
					
					System.out.println("是 第" + pItems+"个 ,  总大小是 : " + pContentLength+", 实时 读到了"+pBytesRead+"字节...");
					
				}
			});
			
			// 开启解析 request
			List<FileItem> list = uploader.parseRequest(request);

			//遍历 list
			for (FileItem fileItem : list) {
				
				//判断是否是普通输入项 
				if(fileItem.isFormField()){
					
					//进来是 普通输入项 ----  参数名称 和 参数的值 
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");
					
					System.out.println(fieldName+" = " +fieldValue);
					
				}else{
					//文件上传 输入项 --- 上传的文件的名称,  上传的文件里的数据 
					
					// 丽丽.txt
					// c:\\aa\\bb\\cc\\aaa.txt
					String fileName = fileItem.getName();
					
					//注意: 对于 一些浏览器, 有的浏览器获得的文件名称是一种,有的获得的是另一种
					
					//判断到底 是获得的文件是哪种形式 
					
					//根据这个 值 是否是  -1 去判断 是哪种形式 
					int lastIndexOf = fileName.lastIndexOf("\\");
					
					if(lastIndexOf!=-1){
						
						//就是   c:\\aa\\bb\\cc\\aaa.txt, 需要去手动的截取
						fileName = fileName.substring(lastIndexOf+1);
					}
					
					System.out.println("文件名:" + fileName);
					
					int index = fileName.lastIndexOf(".");
					
					String ext = fileName.substring(index);   // .txt
					
					String exts = Arrays.toString(extensions); //  [".txt",".jpg","png",".avi",".rmvb",".doc"]
					
					if(!exts.contains(ext)){
						
						//进来, 则表示上传的文件不符合 规范...
						//给用户友好提示...
						request.setAttribute("message", "亲, 您上传的文件类型不允许...");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
						return;
					}
					
					InputStream in = fileItem.getInputStream();
					
					//生成全球唯一的名字,使用 UUID类 
					String uniqueFileName = generateUUIDName(fileName);
														//   d:\\ c:\\aa\\bb\\cc\\aaa.txt
					
					//注意: 具体上传文件, 实际上通常会放到 web 应用程序的子目录下... 好处在于, 可以很方便去读取文件,
					// 之前在介绍 serlvetContext, 可以很方便去获得文件的路径... 
					
					//例如, 现在要上传的文件保存到WebRoot下的 upload文件夹 
					// 获得路径
					
					// D:\tomcat\apache-tomcat-7.0.40\webapps\day11_upload\\upload
					String savepath = getServletContext().getRealPath("/upload");
					
					// D:\tomcat\apache-tomcat-7.0.40\webapps\day11_upload\\upload\\5\\14
					savepath = generateSavePath(savepath,uniqueFileName);
					
					OutputStream out = new FileOutputStream(new File(savepath,uniqueFileName));
					
					int len=0;
					byte[] buf = new byte[1024];
					while((len=in.read(buf))>0){
						out.write(buf, 0, len);
					}
					
					in.close();
					out.close();
					
					//走到这里, 意味着 文件上传也完成, 所以 可以去将临时文件给删掉了. 
					fileItem.delete();
					
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {  //单个文件大小超过限制 
			
			//给用户友好提示 
			request.setAttribute("message", "亲, 上传的 单个文件大小不允许 超过 20M ");
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
			return;
			
		}catch (FileUploadBase.SizeLimitExceededException e) {  //总 文件大小超过限制 
		
			//给用户友好提示 
			request.setAttribute("message", "亲, 上传的总 文件大小不允许 超过 100M ");
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
			return;
			
		}catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	//生成 随机的文件夹, 去保存 文件,避免 文件过多导致的性能的问题
	private String generateSavePath(String savepath, String uniqueFileName) {
		// D:\tomcat\apache-tomcat-7.0.40\webapps\day11_upload\\upload
		
		//拿到文件的哈希值
		int hashCode = uniqueFileName.hashCode();
		
		// 一级 目录   
		int dir1 = hashCode&0xf;   // 5
		
		// 二级 目录   
		int dir2 = (hashCode>>4) &0xf;   // 14
		
		// D:\tomcat\apache-tomcat-7.0.40\webapps\day11_upload\\upload\\4\\15
		String path = savepath+ "/"+dir1+"/"+dir2;
		
		File file = new File(path);
		
		//判断 文件夹是否存在... 如果不存在, 就创建...
		if(!file.exists()){
			file.mkdirs();
		}
		
		return path;
	}

	//生成全球唯一的名字
	private String generateUUIDName(String fileName) {
		
//		String id = UUID.randomUUID().toString();
		String name = "itheima-"+UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
		
		return name;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
