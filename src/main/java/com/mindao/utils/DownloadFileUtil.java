package com.mindao.utils;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownloadFileUtil {
	private static Logger logger = Logger.getLogger(DownloadFileUtil.class);
	
	
	public static  void downloadFile(HttpServletResponse response,HttpServletRequest request,String fileToDownload,InputStream inputStream,int fileLength) throws Exception {

		OutputStream os =null;
		try {
 
			logger.debug("fileName：" + fileToDownload);
			String fileName = new String(fileToDownload.getBytes("GBK"), "ISO-8859-1");
			logger.debug("fileName" + fileName);
		
			response.setContentType("file/plain");
			
			response.setHeader("Content-disposition", "attachment; filename="  
                    +  fileName);  
 
           
	        response.reset();
	        response.setContentType("application/octet-stream;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename=" +new String(fileToDownload.getBytes("gbk"),"iso8859-1") );
	        response.setHeader("Content-Length", "" + fileLength);
	        
	        
	        os = response.getOutputStream();  
 
	        byte[] buffer = new byte[1024];
			int r;
			while ((r = inputStream.read(buffer)) > 0) {
				os.write(buffer, 0, r);
				os.flush();
			}
 
  
		} catch (Exception e) {
			//logger.error(e.getMessage(),e);
			throw e;
		}finally{
			if (inputStream!=null){
				inputStream.close();
			}	
			if (os != null) {
				os.close();
			}
		}
	}
 	
}
