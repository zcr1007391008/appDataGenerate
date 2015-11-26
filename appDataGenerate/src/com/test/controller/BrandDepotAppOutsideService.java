package com.test.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.mapper.BrandDepotAppMapper;
import com.test.model.ImportBrand;
import com.test.sevices.BrandDepotAppService;
import com.test.until.Base64EncodeDecode;
import com.test.until.FormatJson;
import com.test.until.GetFilePlace;
import com.test.until.URLConnectionDownloader;

@Service
public class BrandDepotAppOutsideService
{
	@Autowired
	private BrandDepotAppService brandDepotAppService;
	
	public String updateBrandDepotFromApp(String logo_url_base64_str,String brand_backgorund_color,String bar_code,String brand_id,HttpServletRequest request)
	{
		try
		{
			String old_logo_url = brandDepotAppService
					.selectBrandLogoFromApp(brand_id);
			System.out.println("old_logo_url = " + old_logo_url);
			
			String logo_url = null;
			if (StringUtils.isNotBlank(old_logo_url)) //更新logo
			{
				if (StringUtils.isNotBlank(logo_url_base64_str))
				{
					boolean isSuceess = updatePicture(request, "brandLogo",
							"picture.properties", old_logo_url,
							logo_url_base64_str);
				}
			}
			else
			//没有旧的logo
			{
				if (StringUtils.isNotBlank(logo_url_base64_str))
				{
					//上传图片,
					logo_url = uploadPicture(request, "brandLogo",
							"picture.properties", logo_url_base64_str);
				}
			}
			brandDepotAppService.updateBrandDepotFromApp(logo_url,
					brand_backgorund_color, bar_code, brand_id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "更新失败!";
		}
		
		return "更新成功！ ";
	}
	
	
	/**
	 * 更改图片
	 * @param editfile 更改的图片
	 * @param request  
	 * @param model
	 * @return			更新是否成功
	 */
	public boolean updatePicture(HttpServletRequest request,String filePathName,String fileProperties,String oldFileName,String logoUrlBase64Str)
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
		/*File targetFile = new File(path, fileName);  
           
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        } */ 
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
	public String uploadPicture(HttpServletRequest request,String filePathName,String fileProperties,String logoUrlBase64Str)
	{
		String pathSavePath = new GetFilePlace().getFileDirFromProperties(filePathName,fileProperties);
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); 
		
		System.out.println("path = "+path);
		System.out.println("pathSavePath = "+ pathSavePath);
		UUID uuid = UUID.randomUUID();  //全球唯一编码
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = format.format(new Date()) + uuid.toString() + ".jpg";
		String logo_url = pathSavePath + "/" + fileName;
		/*File targetFile = new File(path, fileName);  
        */
		String absuluteFilePath = path + "\\" + fileName;
		
		System.out.println("absuluteFilePath = " + absuluteFilePath);
		
       /* if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  */
  
        //保存  
        try {  
        	System.out.println("进入添加json图片");
        	//将logoUrlBase64Str 转换成文件（图片）
        	Base64EncodeDecode.decoderBase64File(logoUrlBase64Str,absuluteFilePath);
        	
        	
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
	/*	System.out.println(fileName);*/
		
		return logo_url;
	}
	
	
	
	/**
	 * @param filePath
	 * @param request
	 */
	public void importDataFromJsonFile(String filePath,HttpServletRequest request)
	{
		List<ImportBrand> brandList = FormatJson.formatFileListToBrand(filePath);
		
		int size = brandList.size();
		
		/*String pathSavePath = new GetFilePlace().getFileDirFromProperties("android_banner_url","picture.properties");
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); */
		
		URLConnectionDownloader.downloadToSelectedFolder("android_banner_url","picture.properties","http://stocardapp.s3-external-3.amazonaws.com/ios/icons/1001tur@2x.png","2x.png",request);
		
		for(int i = 0 ; i < size; i ++)
		{
			ImportBrand brand = brandList.get(i);
			
			URLConnectionDownloader.downloadToSelectedFolder("android_banner_url","picture.properties",brand.getAndroid_banner_url(),getFileNameFromUrl(brand.getAndroid_banner_url()),request);
			
			URLConnectionDownloader.downloadToSelectedFolder("ios_banner_url","picture.properties",brand.getIos_banner_url(),getFileNameFromUrl(brand.getIos_banner_url()),request);
			
			URLConnectionDownloader.downloadToSelectedFolder("ios_logo_url","picture.properties",brand.getIos_logo_url(),getFileNameFromUrl(brand.getIos_logo_url()),request);
			
			/*URLConnectionDownloader.downloadToSelectedFolder("logo","picture.properties",brand.getLogo(),getFileNameFromUrl(brand.getLogo()),request);
			*/
		}
		
	}
	
	/**
	 * 从url中截取出文件名称
	 * @param httpUrlStr	待截取的url
	 * @return
	 */
	public static String getFileNameFromUrl(String httpUrlStr)
	{
		return httpUrlStr.substring(httpUrlStr.lastIndexOf("/") + 1);
	}
	
	public static void main(String[] args)
	{
		System.out.println(getFileNameFromUrl("jjfkdjfkl\\fjdkfjsadkl\\jfdkljfkdsl\\a.jpg"));
	}
	
	
}
