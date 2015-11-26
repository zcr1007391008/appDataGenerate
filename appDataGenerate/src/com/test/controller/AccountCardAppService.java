package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.AccountCard;
import com.test.sevices.AccountCardService;
import com.test.until.FileUploadtoTomcatUseBase64;
import com.test.until.GetFilePlace;


/**
 * 用户卡相关操作
 * @author zcr
 *
 */

@Service
public class AccountCardAppService
{
	//log4j日志
	private final Logger log= Logger.getLogger(AccountCardAppService.class);
	
	@Autowired
	private AccountCardService accountCardService;
	
	
	/**
	 * 添加会员卡
	 * @param font_picture 正面图片
	 * @param request	   
	 * @param back_picture 背面图片
	 * @return			
	 */
	
	public String addAccountCard( MultipartFile font_picture,HttpServletRequest request,
			 MultipartFile back_picture,String account_id ,String card_id,String brand_id,String remark)
	{
		
		
		
		try {
			System.out.println(null == font_picture);
			System.out.println(null == back_picture);
			System.out.println(font_picture.isEmpty());
			String back_picture_url = null;
			String font_picture_url = null;
			if(null != back_picture && !back_picture.isEmpty())
			{
				//生成图片
				back_picture_url = uploadPicture(back_picture,request,"accountPicture","picture.properties");
			}
			if(null != font_picture  && !font_picture.isEmpty())
			{
				//生成图片
				font_picture_url = uploadPicture(font_picture,request,"accountPicture","picture.properties");
			}
			
			System.out.println("account_id=" + account_id + ",card_id = " + card_id +",brand_id = " + brand_id + " ,  remark = " + remark) ;
			
			accountCardService.insertAccountCard(account_id,card_id,brand_id,font_picture_url,back_picture_url,remark);
			
			return "添加成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败";
		}
	}

	/**
	 * 删除用户卡
	 * @param user_id 待删除用户卡id
	 * @return
	 */
	
	public String delteAccountCard(String user_id)
	{
		try
		{
			accountCardService.deleteAccountCardByUserId(user_id);
			return "删除成功"; 
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "删除失败";
		}
	}
	
	
	/**
	 * 分页查找用户卡
	 * @param iCurrentPage 当天页数
	 * @param iPageSize    每页数据量
	 * @return			         该页的数据
	 */
	public List<AccountCard> getAccountCardInPage(String iCurrentPage,String iPageSize)
	{
		for(AccountCard a : accountCardService.selectAccountCardInPage(iCurrentPage,iPageSize))
		{
			System.out.println(a);
		}
			
		return accountCardService.selectAccountCardInPage(iCurrentPage,iPageSize);
	}
	
	/**
	 * 更新用户卡，没有图片
	 * @param account_id
	 * @param user_id
	 * @param card_id
	 * @param brand_id
	 * @param remark
	 */
	public void updateAccountCardWithoutPicture(String account_id,String user_id,String card_id,String brand_id,String remark)
	{

		accountCardService.updateAccountCardByUserId(user_id,account_id, card_id, brand_id, remark);
		System.out.println("更新");
	}
	
	/**
	 * 更改用户卡的图片
	 * @param font_picture
	 * @param request
	 * @param back_picture
	 */
	public void updateAccountCardPicture( MultipartFile font_picture,HttpServletRequest request,
			 MultipartFile back_picture,String user_id)
	{

		System.out.println( "user_id = "+ user_id);
		Map<String,String> pictureUrlMap =  accountCardService.selectAccountCardPictureUrl(user_id);
		
		String  fontPicture = "";
		String backPicture = "";
		
		if(null != font_picture && !font_picture.isEmpty())
		{
			System.out.println("Map = " + pictureUrlMap);
			if(StringUtils.isNotBlank(pictureUrlMap.get("front_picture_url")))
			{
				boolean isSuccess = updatePicture(font_picture,request,"accountPicture","picture.properties",pictureUrlMap.get("front_picture_url"));
				System.out.println(pictureUrlMap.get("front_picture_url"));
			}
			else
			{
				fontPicture =  uploadPicture(font_picture,request,"accountPicture","picture.properties");
				System.out.println("正面照片为空");
			}
		}
		
		if(null != back_picture && !back_picture.isEmpty())
		{
			if(StringUtils.isNotBlank(pictureUrlMap.get("back_picture_url")))
			{
				boolean isSuccess = updatePicture(back_picture,request,"accountPicture","picture.properties",pictureUrlMap.get("back_picture_url"));
				System.out.println(pictureUrlMap.get("back_picture_url"));
			}
			else
			{
				backPicture = uploadPicture(back_picture,request,"accountPicture","picture.properties");
				System.out.println("背面照片为空");
			}
		}
		
		Map map = new HashMap();
		String sql = "update account_card set ";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sql += " last_edit_time=\""+ format.format(new Date()) +"\" ";
		if(StringUtils.isNotBlank(fontPicture) || StringUtils.isNotBlank(backPicture))
		{
			
			if(StringUtils.isNotBlank(fontPicture))
			{
				sql += " ,front_picture_url = \"" + fontPicture +"\"";
			}
			if(StringUtils.isNotBlank(backPicture))
			{
				sql += " ,back_picture_url=\"" + backPicture +"\"";
			}

		}
		sql += " where user_id= " + user_id ;
		map.put("sql",sql);
		accountCardService.updateAccountCardPicture(map);
	}
	
	/**
	 *  更新用户卡信息和图片
	 * @param font_picture
	 * @param request
	 * @param back_picture
	 * @param user_id
	 * @param account_id
	 * @param card_id
	 * @param brand_id
	 * @param remark
	 * @return
	 */
	public String updateAccountCardWithPictureByUserId(MultipartFile font_picture,HttpServletRequest request,
			MultipartFile back_picture,String user_id,String account_id,String card_id,String brand_id,String remark)
	{

		String front_picture_url = "";
		String back_picture_url = "";
		
		Map<String,String> pictureUrlMap =  accountCardService.selectAccountCardPictureUrl(user_id);
		
		
		if(null != font_picture && !font_picture.isEmpty())
		{
			if(StringUtils.isNotBlank(pictureUrlMap.get("front_picture_url")))
			{
				boolean isSuccess = updatePicture(font_picture,request,"accountPicture","picture.properties",pictureUrlMap.get("front_picture_url"));
			}
			else
			{
				front_picture_url =  uploadPicture(font_picture,request,"accountPicture","picture.properties");
				System.out.println("正面照片为空");
			}
		}
		
		if(null != back_picture && !back_picture.isEmpty())
		{
			if(StringUtils.isNotBlank(pictureUrlMap.get("back_picture_url")))
			{
				boolean isSuccess = updatePicture(back_picture,request,"accountPicture","picture.properties",pictureUrlMap.get("back_picture_url"));
			}
			else
			{
				
				back_picture_url = uploadPicture(back_picture,request,"accountPicture","picture.properties");
			}
		}
		accountCardService.updateAccountCardWithPictureByUserId( user_id, account_id, card_id, brand_id, remark, front_picture_url, back_picture_url);
		
		return "更新成功！";
	}
	
	
	/**
	 * 通过json格式数据添加品牌库（文件传输格式是base64）
	 * @param font_picture_json_str	base64的正面图片   
	 * @param request				HttpServletRequest 对象，用来获取服务器tomcat的所在位置
	 * @param back_picture_json_str	base64背面图片
	 * @param account_id			
	 * @param card_id				
	 * @param brand_id				品牌
	 * @param remark				备注
	 * @return
	 */
	public String addAccountCardUseJson(String font_picture_json_str,HttpServletRequest request,
			 String back_picture_json_str,String account_id ,String card_id,String brand_id,String remark)
	{
		String front_picture = null;
		String back_picture = null;
		
		String sql = "insert into account_card (";
		String dataSql = "values ( ";
		if(StringUtils.isNotBlank(font_picture_json_str))
		{
			sql += " front_picture_url,";
			
			//上传图片
			front_picture = FileUploadtoTomcatUseBase64.uploadPicture(request,"accountPicture","picture.properties",font_picture_json_str);
			
			dataSql += "\"" + front_picture + "\",";
		}
		if(StringUtils.isNotBlank(back_picture_json_str))
		{
			sql += "back_picture_url,";
			back_picture = FileUploadtoTomcatUseBase64.uploadPicture(request,"accountPicture","picture.properties",back_picture_json_str);
			dataSql += "\"" + back_picture + "\",";
		}
		if(StringUtils.isNotBlank(account_id))
		{
			sql += "account_id,";
			dataSql += account_id + ",";
		}
		if(StringUtils.isNotBlank(card_id))
		{
			sql += "card_id,";
			dataSql += "\"" + card_id + "\",";
		}
		if(StringUtils.isNotBlank(brand_id))
		{
			sql += "brand_id,";
			dataSql += brand_id +",";
		}
		if(StringUtils.isNotBlank(remark))
		{
			sql += "remark,";
			dataSql += "\"" + remark + "\",";
		}
		sql += "last_edit_time,";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		dataSql += "\"" + format.format(now) + "\",";
		sql += "add_dates) ";
		dataSql += "\"" + format.format(now) + "\")"; 
		
		sql += dataSql;
		
		System.out.println("sql = " + sql);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("sql", sql);
		accountCardService.addAccountCardUseJson(map);
		
		return "添加成功";
	}
	 
	
	
	
	
	/**
	 * 修改用户卡信息，图片传输过来的数据是base64数据，假如有数据不修改，则传入一个null即可。
	 * @param font_picture_str	base64正面图片信息
	 * @param request			
	 * @param back_picture_str	base64背面图片信息
	 * @param user_id
	 * @param account_id
	 * @param card_id
	 * @param brand_id
	 * @param remark
	 * @return
	 */
	public String updateAccountCardWithPictureByUserIdUseJson(String font_picture_str,HttpServletRequest request,
			String back_picture_str,String user_id,String account_id,String card_id,String brand_id,String remark)
	{
		try {
			String front_picture_url = "";
			String back_picture_url = "";
			Map<String, String> pictureUrlMap = accountCardService
					.selectAccountCardPictureUrl(user_id);
			if (StringUtils.isNotBlank(font_picture_str)) {
				if (StringUtils.isNotBlank(pictureUrlMap
						.get("front_picture_url"))) {
					//更新图片
					boolean isSuccess = FileUploadtoTomcatUseBase64
							.updatePicture(request, "accountPicture",
									"picture.properties",
									pictureUrlMap.get("front_picture_url"),
									font_picture_str);
				} else {
					front_picture_url = FileUploadtoTomcatUseBase64
							.uploadPicture(request, "accountPicture",
									"picture.properties", font_picture_str);
					System.out.println("正面照片为空");
				}
			}
			if (StringUtils.isNotBlank(back_picture_str)) {
				if (StringUtils.isNotBlank(pictureUrlMap
						.get("back_picture_url"))) {

					boolean isSuccess = FileUploadtoTomcatUseBase64
							.updatePicture(request, "accountPicture",
									"picture.properties",
									pictureUrlMap.get("back_picture_url"),
									back_picture_str);
				} else {

					back_picture_url = FileUploadtoTomcatUseBase64
							.uploadPicture(request, "accountPicture",
									"picture.properties", back_picture_str);
				}
			}
			accountCardService.updateAccountCardWithPictureByUserId(user_id,
					account_id, card_id, brand_id, remark, front_picture_url,
					back_picture_url);
		} catch (Exception e) {
			return "更新失败!";
		}
		return "更新成功！";
	}
	
	
	
	
	
	
	/**
	 * 上传图片(提交表单，文件传输的是MultipartFile对象，上面实现了base64文件的方式)
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
	 * 更改图片(需要提交表单，文件传输的是MultipartFile对象，上面实现了base64文件的方式)
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
