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
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Brand;

@Controller
@RequestMapping("CountCardController")
public class TestBrandDepotManagerController
{
	@Autowired
	private BrandDepotManagerOutsideService brandDepotManagerOutsideService;
	
	@RequestMapping("addBrand")
	// 上传的文件的name属性要为file,看 @requParam的value属性值
	public ModelAndView addBrand(@RequestParam(value = "file", required = false) MultipartFile editfile,HttpServletRequest request, ModelMap model)
	{
		String brand_backgorund_color = request.getParameter("brand_backgorund_color");
		/*String isExist_code = request.getParameter("isExist_code");*/
		String bar_code = request.getParameter("bar_code");
		
		
		brandDepotManagerOutsideService.addBrand(editfile,request,brand_backgorund_color,bar_code);
		return new ModelAndView("test");
	}
	
	
	/**
	 * 
	 * @param iCurrentPage
	 * @param iPageSize
	 * @return
	 */
	@RequestMapping("pagingBrandData")
	@ResponseBody
	public List<Brand> getPigingBrandData(String iCurrentPage,String iPageSize)
	{
		List<Brand> dataList = brandDepotManagerOutsideService.getPigingBrandData(iCurrentPage,iPageSize);
		for(int i = 0 ; i < dataList.size() ; i ++)
		{
			System.out.println(dataList.get(i));
		}
		return dataList;
	}
	
	@RequestMapping("editBrandWithoutPicture")
	@ResponseBody
	public void editBrandWithoutPicture(HttpServletRequest request)
	{
		String brand_id = request.getParameter("brand_id");
		String brand_backgorund_color = request.getParameter("brand_backgorund_color");
		String bard_code = request.getParameter("bard_code");
		
		brandDepotManagerOutsideService.editBrandWithoutPicture(brand_backgorund_color,bard_code,brand_id);
	}
	
	@RequestMapping("editBrandWithPicture")
	public void editBrandWithPicture(@RequestParam(value = "edit_brand_logo_picture", required = false) MultipartFile logo_picture,HttpServletRequest request)
	{
		String brand_id = request.getParameter("edit_brand_id");
		String brand_backgorund_color = request.getParameter("edit_brand_backgorund_color");
		String bar_code = request.getParameter("edit_bar_code");
		
		brandDepotManagerOutsideService.editBrandWithPicture(logo_picture,request,brand_id,brand_backgorund_color,bar_code);
	}
	
	
	@RequestMapping("editBrandLogo")
	public void editBrandLogo(@RequestParam(value = "edit_brand_logo", required = false) MultipartFile logo_picture,HttpServletRequest request)
	{
		String brand_id = request.getParameter("edit_brand_id2233");
		brandDepotManagerOutsideService.editBrandLogo(logo_picture,request,brand_id);
	}
	
	
	
	
	
	
	
}
