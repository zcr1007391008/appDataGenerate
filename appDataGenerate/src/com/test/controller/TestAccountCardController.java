package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.AccountCard;
import com.test.until.Base64EncodeDecode;

@Controller
@RequestMapping("AccountCard")
public class TestAccountCardController
{
	@Autowired
	private AccountCardAppService accountCardAppService;
	
	/**
	 * 添加会员卡
	 * @param font_picture 正面图片
	 * @param request	   
	 * @param back_picture 背面图片
	 * @return			
	 */
	@RequestMapping("addAccountCard")
	@ResponseBody
	public void addAccountCard(@RequestParam(value = "font_picture", required = false) MultipartFile font_picture,HttpServletRequest request,
			@RequestParam(value = "back_picture", required = false) MultipartFile back_picture)
	{
		String account_id = request.getParameter("account_id");
		String card_id = request.getParameter("card_id");
		String brand_id = request.getParameter("brand_id");
		String remark = request.getParameter("remark");
		
		accountCardAppService.addAccountCard(font_picture,request,back_picture,account_id,card_id,brand_id,remark);
	}
	
	
	@RequestMapping("deleteAccountCard")
	@ResponseBody
	public String delteAccountCard(String user_id)
	{
		return accountCardAppService.delteAccountCard(user_id);
	}
	
	
	/**
	 * 分页查找用户卡
	 * @param iCurrentPage 当天页数
	 * @param iPageSize    每页数据量
	 * @return			         该页的数据
	 */
	@RequestMapping("getAccountCardInPage")
	@ResponseBody
	public List<AccountCard> getAccountCardInPage(String iCurrentPage,String iPageSize)
	{
		return accountCardAppService.getAccountCardInPage(iCurrentPage,iPageSize);
	}
	
	
	@RequestMapping("updateAccountCardWithoutPicture")
	public void updateAccountCardWithoutPicture(HttpServletRequest request)
	{
		String account_id = request.getParameter("account_id");
		System.out.println("accoutnid = " + account_id);
		String user_id = request.getParameter("user_id");
		System.out.println("userid=" + (null == user_id) + "," + user_id);
		String card_id = request.getParameter("card_id");
		System.out.println("card_id= " + card_id);
		String brand_id = request.getParameter("brand_id");
		String remark = request.getParameter("remark");
		
		accountCardAppService.updateAccountCardWithoutPicture(account_id, user_id, card_id, brand_id, remark);
	}
	
	
	/**
	 * 更改用户卡的图片
	 * @param font_picture
	 * @param request
	 * @param back_picture
	 */
	@RequestMapping("updateAccountCardPicture")
	public void updateAccountCardPicture(@RequestParam(value = "update_font_picture", required = false) MultipartFile font_picture,HttpServletRequest request,
			@RequestParam(value = "update_back_picture", required = false) MultipartFile back_picture)
	{
		String user_id = request.getParameter("update_user_id");
		accountCardAppService.updateAccountCardPicture(font_picture,request,back_picture,user_id);
	}
	
	
	
	@RequestMapping("updateAccountCardWithPictureByUserId")
	public String updateAccountCardWithPictureByUserId(@RequestParam(value = "font_picture_update", required = false) MultipartFile font_picture,HttpServletRequest request,
			@RequestParam(value = "back_picture_update", required = false) MultipartFile back_picture)
	{
		String user_id = request.getParameter("user_id_update");
		String account_id = request.getParameter("account_id_update");
		String card_id = request.getParameter("card_id_update");
		String brand_id = request.getParameter("brand_id_update");
		String remark = request.getParameter("remark_update");
		
		return accountCardAppService.updateAccountCardWithPictureByUserId(font_picture,request,back_picture,user_id,account_id,card_id,brand_id,remark);
		
	}
	
	@RequestMapping("updateAccountCardWithPictureByUserIdUseJson")
	public void updateAccountCardWithPictureByUserIdUseJson(HttpServletRequest request)
	{
		String font_picture_str;
		try
		{
			font_picture_str = Base64EncodeDecode.encodeBase64File("F:\\2.jpg");
			String back_picture_str = Base64EncodeDecode.encodeBase64File("F:\\1.jpg");
			String user_id = "3";
			String account_id = "5";
			String card_id = null;
			String brand_id = null;
			String remark = null;
			
			accountCardAppService.updateAccountCardWithPictureByUserIdUseJson( font_picture_str, request,
					 back_picture_str, user_id, account_id, card_id, brand_id, remark);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@RequestMapping("testAddAccountCardUseJson")
	public void addAccountCardUseJson(HttpServletRequest request)
	{
		String font_picture_json_str;
		try {
			font_picture_json_str = Base64EncodeDecode.encodeBase64File("F:\\2.jpg");
			String back_picture_json_str = Base64EncodeDecode.encodeBase64File("F:\\1.jpg");
			String account_id = "111";
			String card_id = "222";
			String brand_id = "1012";
			String remark = "";
			accountCardAppService.addAccountCardUseJson(font_picture_json_str, request, back_picture_json_str, account_id, card_id, brand_id, remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
