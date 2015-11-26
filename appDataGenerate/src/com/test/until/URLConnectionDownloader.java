package com.test.until;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过url下载文件
 * @author zcr
 *
 */
public class URLConnectionDownloader
{	
	public static void main(String[] args)
	{
		download("http://stocardapp.s3-external-3.amazonaws.com/ios/icons/1001tur@2x.png", "E:\\xiazai.jpg");
	}
	

	/**
	 * 将urlString的文件下载到
	 * @param filePathName		properties文件中的文件存储名
	 * @param fileProperties	查找的properties文件	
	 * @param urlString			待下载的文件url
	 * @param fileName 			生成的文件名称
	 */
	public static Map downloadToSelectedFolder(String filePathName,String fileProperties,String urlString,String fileName,HttpServletRequest request)
	{
		//获得picture.properties 文件中，key为android_banner_url的值
		String pathSavePath = new GetFilePlace().getFileDirFromProperties(filePathName,fileProperties);
		
		//获得服务器（tomcat）pathSavePath的相对位置
		String path = request.getSession().getServletContext().getRealPath(pathSavePath);
		
		//获得文件存储的绝对路径
		String generateFileName = path + File.separator + fileName;
		
		
		System.out.println("下载的文件绝对路径为：" + generateFileName);
		System.out.println("下载的url = " + urlString);
		
		boolean flag = download(urlString,generateFileName);
		
		Map map = new HashMap();
		map.put("path",pathSavePath + "/" + fileName);
		map.put("isSuccess",flag);
		
		return map;
	}
	
	
	
	/**
	   * 下载文件到本地
	   *
	   * @param urlString
	   *          被下载的文件地址
	   * @param filename
	   *          本地文件名
	   */
	public static boolean download(String urlString, String filename)  
	{
	    // 构造URL
	    URL url;
		try
		{
			url = new URL(urlString);
			 // 打开连接
		    URLConnection con = url.openConnection();
		    // 输入流
		    InputStream is = con.getInputStream();
		    // 1K的数据缓冲
		    byte[] bs = new byte[1024];
		    // 读取到的数据长度
		    int len;
		    // 输出的文件流s
		    
		    System.out.println("-------------------输出到的文件名称：" + filename);
		    OutputStream os = new FileOutputStream(filename);
		    // 开始读取
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
		    // 完毕，关闭所有链接
		    os.close();
			is.close();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		return true;
	}   
}
