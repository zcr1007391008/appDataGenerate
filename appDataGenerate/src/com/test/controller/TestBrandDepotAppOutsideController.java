package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.until.Base64EncodeDecode;

@Controller
@RequestMapping("TestBrandDepotAppOutsideController")
public class TestBrandDepotAppOutsideController
{
	@Autowired
	private BrandDepotAppOutsideService brandDepotAppOutsideService;
	
	@RequestMapping("updateBrandByApp")
	public void updateBrandByApp(HttpServletRequest request)
	{
		 try
		{
			//base64
			String logo_url_base64_str =  Base64EncodeDecode.encodeBase64File("E:\\1.jpg");
			System.out.println(logo_url_base64_str);
			String brand_backgorund_color = "灰色";
			String bar_code = "122333445457676 ";
			String brand_id = "30";
			brandDepotAppOutsideService.updateBrandDepotFromApp(logo_url_base64_str,brand_backgorund_color,bar_code,brand_id, request);
		
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
