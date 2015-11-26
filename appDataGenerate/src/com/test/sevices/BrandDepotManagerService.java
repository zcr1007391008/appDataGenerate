package com.test.sevices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.BrandDepotManagerMapper;
import com.test.model.Brand;

@Service
public class BrandDepotManagerService
{
	@Autowired
	BrandDepotManagerMapper accountCardManagerMapper;
	
	public int addBrand(String logo_url,String brand_backgorund_color/*,String isExist_code*/,String bar_code)
	{
		Brand brand = new Brand();
		
		if(StringUtils.isNotBlank(logo_url))
		{
			brand.setLogo_url(logo_url);
		}
		
		if(StringUtils.isNotBlank(brand_backgorund_color))
		{
			brand.setBrand_backgorund_color(brand_backgorund_color);
		}
		if(StringUtils.isNotBlank(bar_code))
		{
			brand.setIsExist_code(1);
			/*if(StringUtils.isNotBlank(bar_code))
			{*/
				brand.setBar_code(bar_code);
			/*}*/
		}
		else
		{
			brand.setBar_code("");
			brand.setIsExist_code(0);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		brand.setAdd_dates(format.format(nowDate));
		brand.setLast_editTime(format.format(nowDate));

		accountCardManagerMapper.addBrand(brand);
		
		return brand.getBrand_id();
	}
	
	
	
	public void updateBrand(String logo_url,String brand_backgorund_color,String bar_code,String brand_id)
	{
		Brand brand = new Brand();
		brand.setBrand_id(Integer.parseInt(brand_id));
		
		if(StringUtils.isNotBlank(bar_code))
		{
			brand.setIsExist_code(1);
			brand.setBar_code(bar_code);
		}
		else
		{
			brand.setIsExist_code(0);
			brand.setBar_code("");
		}
		
		brand.setBrand_backgorund_color(brand_backgorund_color);
		if (StringUtils.isNotBlank(logo_url))
		{
			brand.setLogo_url(logo_url);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		brand.setLast_editTime(format.format(new Date()));
		
		
		System.out.println("更改的brand-"+brand);
		accountCardManagerMapper.updateBrand(brand);
	}
	
	
	public void updateBrandWithoutLogo(String brand_backgorund_color,String bar_code,String brand_id)
	{
		Brand brand = new Brand();
		brand.setBrand_id(Integer.parseInt(brand_id));
		
		if(StringUtils.isNotBlank(bar_code))
		{
			brand.setIsExist_code(1);
			brand.setBar_code(bar_code);
		}
		else
		{
			brand.setIsExist_code(0);
			brand.setBar_code("");
		}
		
		
		/*SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String nowTime = format.format(new Date());*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		brand.setLast_editTime(format.format(new Date()));
		brand.setBrand_backgorund_color(brand_backgorund_color);
		
		
		System.out.println("更改的brand-"+brand);
		accountCardManagerMapper.updateBrandWithoutLogo(brand);
	}
	
	public void updateBrandOnlyLogo(String brand_id,String logo_url)
	{
		Map map = new HashMap();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = format.format(new Date());
		map.put("logo_url",logo_url);
		map.put("last_editTime",nowTime);
		map.put("brand_id",Integer.parseInt(brand_id));
		
		accountCardManagerMapper.updateBrandOnlyLogo(map);
	}
	
	
	/**
	 * 分页查询品牌库
	 * @param iCurrentPage 当前页数
	 * @param iPageSize	         页大小
	 * @return			         第iCurrentPage页数据
	 */
	public List<Brand> selectBrandInPage(String iCurrentPage,String iPageSize)
	{
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start",(Integer.parseInt(iCurrentPage) - 1) * Integer.parseInt(iPageSize));
		map.put("end",Integer.parseInt(iPageSize));
		return accountCardManagerMapper.selectBrandInPage(map);
	}
	
	
	
	 public String selectBrandLogo(String brand_id)
	 {
		 return accountCardManagerMapper.selectBrandLogo(Integer.parseInt(brand_id));
	 }
}
