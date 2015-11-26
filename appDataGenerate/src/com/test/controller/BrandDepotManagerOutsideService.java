package com.test.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Brand;
import com.test.sevices.BrandDepotManagerService;
import com.test.until.GetFilePlace;


/*@Controller
@RequestMapping("CountCardController")*/
@Service
public class BrandDepotManagerOutsideService
{
	@Autowired
	BrandDepotManagerService brandDepotManagerService ;
	
	/*@RequestMapping("addBrand")*/
	// 上传的文件的name属性要为file,看 @requParam的value属性值
	public void  addBrand( MultipartFile editfile,HttpServletRequest request,String brand_backgorund_color,String bar_code)
	{
		String logo_url = null;
		try
		{
			if(null != editfile && ! editfile.isEmpty())
			{
				System.out.println("文件不为空");
				//上传文件并返回文件存储的名称
				logo_url = uploadPicture(editfile,request, "brandLogo","picture.properties");
			}
			else
			{
				System.out.println("没有文件上传");
			}
			
			/*String brand_backgorund_color = request.getParameter("brand_backgorund_color");
			String isExist_code = request.getParameter("isExist_code");
			String bar_code = request.getParameter("bar_code");*/
			
			brandDepotManagerService.addBrand(logo_url,brand_backgorund_color,bar_code);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		/*return new ModelAndView("test");*/
	}
	
	
	/**
	 * 
	 * @param iCurrentPage
	 * @param iPageSize
	 * @return
	 */
	/*@RequestMapping("pagingBrandData")
	@ResponseBody*/
	public List<Brand> getPigingBrandData(String iCurrentPage,String iPageSize)
	{
		List<Brand> dataList = brandDepotManagerService.selectBrandInPage(iCurrentPage,iPageSize);
		for(int i = 0 ; i < dataList.size() ; i ++)
		{
			System.out.println(dataList.get(i));
		}
		return dataList;
	}
	
	
	/*@RequestMapping("editBrandWithoutPicture")
	@ResponseBody*/
	public void editBrandWithoutPicture(String brand_backgorund_color,String  bard_code,String  brand_id)
	{
		try
		{
			/*System.out.println("进入更新品牌不更新logo");
			String brand_id = request.getParameter("brand_id");
			System.out.println("brand_id = " + brand_id);
			String brand_backgorund_color = request.getParameter("brand_backgorund_color");
			String bard_code = request.getParameter("bard_code");*/
			brandDepotManagerService.updateBrandWithoutLogo(brand_backgorund_color, bard_code, brand_id);
		}
		catch (Exception e)
		{
			System.out.println("更新失败！");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/*@RequestMapping("editBrandWithPicture")*/
	public void editBrandWithPicture(MultipartFile logo_picture,HttpServletRequest request,String brand_id,String brand_backgorund_color,String bar_code)
	{
		/*String brand_id = request.getParameter("edit_brand_id");
		String brand_backgorund_color = request.getParameter("edit_brand_backgorund_color");
		String bar_code = request.getParameter("edit_bar_code");*/
				
		String logoPicture = "";
		String oldPictureUrl = brandDepotManagerService.selectBrandLogo(brand_id);
		if(null != logo_picture && !logo_picture.isEmpty())
		{
			if(StringUtils.isNotBlank(oldPictureUrl))
			{
				//更新图片
				boolean isSuccess = updatePicture(logo_picture,request,"brandLogo","picture.properties",oldPictureUrl);
			}
			else
			{
				//上传图片
				logoPicture =  uploadPicture(logo_picture,request,"brandLogo","picture.properties");
				System.out.println("logo照片为空");
			}
		}
		
		brandDepotManagerService.updateBrand( logoPicture, brand_backgorund_color, bar_code,brand_id);
		
		
	}
	
	
	/*@RequestMapping("editBrandLogo")*/
	public void editBrandLogo(MultipartFile logo_picture,HttpServletRequest request,String brand_id)
	{
		/*String brand_id = request.getParameter("edit_brand_id2233");*/
		String oldPictureUrl = brandDepotManagerService.selectBrandLogo(brand_id);
		String logoPicture = "";
		if(null != logo_picture && !logo_picture.isEmpty())
		{
			if(StringUtils.isNotBlank(oldPictureUrl))
			{
				//更新图片
				boolean isSuccess = updatePicture(logo_picture,request,"brandLogo","picture.properties",oldPictureUrl);
			}
			else
			{
				//上传图片
				logoPicture =  uploadPicture(logo_picture,request,"brandLogo","picture.properties");
				brandDepotManagerService.updateBrandOnlyLogo(brand_id,logoPicture);
				System.out.println("logo照片为空");
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 上传图片
	 * @param editfile 添加的图片
	 * @param request  
	 * @return			文件存储的相对路径
	 */
	public String uploadPicture(@RequestParam(value = "file", required = false)MultipartFile editfile,HttpServletRequest request,String filePathName,String fileProperties)
	{
		System.out.println("222");
		String pathSavePath = new GetFilePlace().getFileDirFromProperties(filePathName,fileProperties);
		
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); 
		
		System.out.println("path = "+path);
		System.out.println("pathSavePath = "+ pathSavePath);
		UUID uuid = UUID.randomUUID();  //全球唯一编码
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = format.format(new Date()) + uuid.toString() + ".jpg";
		String logo_url = pathSavePath + "/" + fileName;
		File targetFile = new File(path, fileName);  
           
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
        	System.out.println("进入添加图片");
        	editfile.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
		System.out.println(fileName);
		
		return logo_url;
	}
	
	
	
	
	/**
	 * 上传图片
	 * @param editfile
	 * @param request
	 * @param model
	 * @return
	 *//*
	public String uploadPicture(@RequestParam(value = "file", required = false)MultipartFile editfile,HttpServletRequest request, ModelMap model)
	{
		System.out.println("222");
		String pathSavePath = new GetFilePlace().getFileDirFromProperties("brandLogo","picture.properties");
		
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); 
		
		System.out.println("path = "+path);
		System.out.println("pathSavePath = "+ pathSavePath);
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String fileName = format.format(new Date()) + ".jpg";
		String logo_url = pathSavePath + "/" + fileName;
		File targetFile = new File(path, fileName);  
           
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
        	System.out.println("进入添加图片");
        	editfile.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
		System.out.println(fileName);
		
		return logo_url;
	}*/
	
	
	
	
	/**
	 * 更改图片
	 * @param editfile 更改的图片
	 * @param request  
	 * @param model
	 * @return			更新是否成功
	 */
	public boolean updatePicture(MultipartFile editfile,HttpServletRequest request,String filePathName,String fileProperties,String oldFileName)
	{
		System.out.println("222");
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
			 fileName = oldFileName.substring(oldFileName.lastIndexOf("/"));
		}
		
		System.out.println("更新的图片名称:" + fileName);
		File targetFile = new File(path, fileName);  
           
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
        	System.out.println("进入添加图片");
        	editfile.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return true;
	}
}
