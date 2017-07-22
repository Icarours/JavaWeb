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
 * �����ļ��ϴ��������servlet ����
 * 
 * һ�����Ҫʹ�õ� ��Դ�ĵ�������jar��,����Ҫ ���뵽������ȥ commons-fileupload-1.2.1.jar ��
 * commons-io-1.4.jar
 * 
 */
public class UploadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			// д����...
			
			//�ж� ���� �Ƿ� �Ϻ� �淶... ������Ϻ��淶, ��Ӧ�ø��û�һ���Ѻ���ʾ...
			boolean isQualified = ServletFileUpload.isMultipartContent(request);
			
			if(!isQualified){
				
				//�������ʾ ���Ϻ��淶...
				request.setAttribute("message", "��, ���ı� ��ʽ������, ����... ��Ҫ Ϲ��...");
				request.getRequestDispatcher("/upload.jsp").forward(request, response);
				return;
			}
			
			//׼���� �����ϴ����ļ������� 
			String[] extensions = {".txt",".jpg","png",".avi",".rmvb",".doc",".zip"};
			

			// ����Ҫ�õ�һ������
			DiskFileItemFactory factory = new DiskFileItemFactory();

			//����ͨ������ȥ���� ��ʱ�������Ĵ�С�Լ���ʱ�ļ���ŵ�λ��
			factory.setSizeThreshold(1024*1024*2);  //������ ���� 2M 
			
			// ���� ��ʱ�ļ�����   /tmp �ļ���
			//ʹ�� serlvetContext��� ·��
			String tmpPath = getServletContext().getRealPath("/tmp");
			
			factory.setRepository(new File(tmpPath));
			
			// �õ� ������ ����
			ServletFileUpload uploader = new ServletFileUpload(factory);

			//�ڽ���ǰ,����  ����ͷʱ ʹ�õ������
			uploader.setHeaderEncoding("UTF-8");
			
			//�ڽ���֮ǰ, ȥ �޶� �ϴ����ļ��Ĵ�С 
			uploader.setFileSizeMax(1024*1024*2000);  //�޶������ļ��Ĵ�С, ������ 20M
			
			uploader.setSizeMax(1024*1024*2000);  //�޶� �ܵ��ļ��Ĵ�С , ������ 100M
			
			
			//����һ��������, �����ϴ��Ľ���
			// �����˼�����֮��, ��ô ϵͳ�ͻ᲻��ʱ��ȥ���ü������� update����,... 
			uploader.setProgressListener(new ProgressListener() {
				
				// pBytesRead: ʵʱ�Ķ����� ���ٸ� �ֽ� 
				// pContentLength: �ļ����ܴ�С���� 
				// pItems:   ��ǰ�� �ڼ���item  
				public void update(long pBytesRead, long pContentLength, int pItems) {
					
					System.out.println("�� ��" + pItems+"�� ,  �ܴ�С�� : " + pContentLength+", ʵʱ ������"+pBytesRead+"�ֽ�...");
					
				}
			});
			
			// �������� request
			List<FileItem> list = uploader.parseRequest(request);

			//���� list
			for (FileItem fileItem : list) {
				
				//�ж��Ƿ�����ͨ������ 
				if(fileItem.isFormField()){
					
					//������ ��ͨ������ ----  �������� �� ������ֵ 
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");
					
					System.out.println(fieldName+" = " +fieldValue);
					
				}else{
					//�ļ��ϴ� ������ --- �ϴ����ļ�������,  �ϴ����ļ�������� 
					
					// ����.txt
					// c:\\aa\\bb\\cc\\aaa.txt
					String fileName = fileItem.getName();
					
					//ע��: ���� һЩ�����, �е��������õ��ļ�������һ��,�еĻ�õ�����һ��
					
					//�жϵ��� �ǻ�õ��ļ���������ʽ 
					
					//������� ֵ �Ƿ���  -1 ȥ�ж� ��������ʽ 
					int lastIndexOf = fileName.lastIndexOf("\\");
					
					if(lastIndexOf!=-1){
						
						//����   c:\\aa\\bb\\cc\\aaa.txt, ��Ҫȥ�ֶ��Ľ�ȡ
						fileName = fileName.substring(lastIndexOf+1);
					}
					
					System.out.println("�ļ���:" + fileName);
					
					int index = fileName.lastIndexOf(".");
					
					String ext = fileName.substring(index);   // .txt
					
					String exts = Arrays.toString(extensions); //  [".txt",".jpg","png",".avi",".rmvb",".doc"]
					
					if(!exts.contains(ext)){
						
						//����, ���ʾ�ϴ����ļ������� �淶...
						//���û��Ѻ���ʾ...
						request.setAttribute("message", "��, ���ϴ����ļ����Ͳ�����...");
						request.getRequestDispatcher("/upload.jsp").forward(request, response);
						return;
					}
					
					InputStream in = fileItem.getInputStream();
					
					//����ȫ��Ψһ������,ʹ�� UUID�� 
					String uniqueFileName = generateUUIDName(fileName);
														//   d:\\ c:\\aa\\bb\\cc\\aaa.txt
					
					//ע��: �����ϴ��ļ�, ʵ����ͨ����ŵ� web Ӧ�ó������Ŀ¼��... �ô�����, ���Ժܷ���ȥ��ȡ�ļ�,
					// ֮ǰ�ڽ��� serlvetContext, ���Ժܷ���ȥ����ļ���·��... 
					
					//����, ����Ҫ�ϴ����ļ����浽WebRoot�µ� upload�ļ��� 
					// ���·��
					
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
					
					//�ߵ�����, ��ζ�� �ļ��ϴ�Ҳ���, ���� ����ȥ����ʱ�ļ���ɾ����. 
					fileItem.delete();
					
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {  //�����ļ���С�������� 
			
			//���û��Ѻ���ʾ 
			request.setAttribute("message", "��, �ϴ��� �����ļ���С������ ���� 20M ");
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
			return;
			
		}catch (FileUploadBase.SizeLimitExceededException e) {  //�� �ļ���С�������� 
		
			//���û��Ѻ���ʾ 
			request.setAttribute("message", "��, �ϴ����� �ļ���С������ ���� 100M ");
			request.getRequestDispatcher("/upload.jsp").forward(request, response);
			return;
			
		}catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	//���� ������ļ���, ȥ���� �ļ�,���� �ļ����ർ�µ����ܵ�����
	private String generateSavePath(String savepath, String uniqueFileName) {
		// D:\tomcat\apache-tomcat-7.0.40\webapps\day11_upload\\upload
		
		//�õ��ļ��Ĺ�ϣֵ
		int hashCode = uniqueFileName.hashCode();
		
		// һ�� Ŀ¼   
		int dir1 = hashCode&0xf;   // 5
		
		// ���� Ŀ¼   
		int dir2 = (hashCode>>4) &0xf;   // 14
		
		// D:\tomcat\apache-tomcat-7.0.40\webapps\day11_upload\\upload\\4\\15
		String path = savepath+ "/"+dir1+"/"+dir2;
		
		File file = new File(path);
		
		//�ж� �ļ����Ƿ����... ���������, �ʹ���...
		if(!file.exists()){
			file.mkdirs();
		}
		
		return path;
	}

	//����ȫ��Ψһ������
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
