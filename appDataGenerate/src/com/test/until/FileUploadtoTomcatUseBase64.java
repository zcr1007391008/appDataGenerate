package com.test.until;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 将文件上传到tomcat本地
 * @author zcr
 *
 */
public class FileUploadtoTomcatUseBase64
{
	/**
	 * 更改图片
	 * @param editfile 更改的图片
	 * @param request  
	 * @param model
	 * @return			更新是否成功
	 */
	public static boolean updatePicture(HttpServletRequest request,String filePathName,String fileProperties,String oldFileName,String logoUrlBase64Str)
	{
		String pathSavePath = new GetFilePlace().getFileDirFromProperties(filePathName,fileProperties);
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); 
		
		System.out.println("path = "+path);
		System.out.println("pathSavePath = "+ pathSavePath);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		System.out.println(oldFileName.lastIndexOf("/") );
		String fileName = "";
		if(-1 == oldFileName.lastIndexOf("/"))
		{
			 fileName = oldFileName;
		}
		else
		{
			 fileName = oldFileName.substring(oldFileName.lastIndexOf("/") +1);
		}
		
		String absuluteFilePath = path + "/" + fileName;
		
		System.out.println("更新的图片名称:" + fileName);
		
        //保存  
        try {  
        	System.out.println("进入更新json图片");
        	Base64EncodeDecode.decoderBase64File(logoUrlBase64Str,absuluteFilePath);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return true;
	}
	
	
	
	/**
	 * 上传json图片
	 * @param editfile 添加的图片
	 * @param request  
	 * @param logoUrlBase64Str 图片的String数据
	 * @return			文件存储的相对路径
	 */
	public static String uploadPicture(HttpServletRequest request,String filePathName,String fileProperties,String logoUrlBase64Str)
	{
		String pathSavePath = new GetFilePlace().getFileDirFromProperties(filePathName,fileProperties);
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); 
		
		System.out.println("path = "+path);
		System.out.println("pathSavePath = "+ pathSavePath);
		UUID uuid = UUID.randomUUID();  //全球唯一编码
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = format.format(new Date()) + uuid.toString() + ".jpg";
		String logo_url = pathSavePath + "/" + fileName;
		
		String absuluteFilePath = path + "\\" + fileName;
		
		System.out.println("absuluteFilePath = " + absuluteFilePath);
		
     
        //保存  
        try {  
        	System.out.println("进入添加json图片");
        	//将logoUrlBase64Str 转换成文件（图片）
        	/*System.out.println("base64Str = " + logoUrlBase64Str);*/
        	Base64EncodeDecode.decoderBase64File(logoUrlBase64Str,absuluteFilePath);
        	
        	
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
	/*	System.out.println(fileName);*/
		
		return logo_url;
	}
}
