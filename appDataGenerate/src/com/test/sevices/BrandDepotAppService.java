package com.test.sevices;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.BrandDepotAppMapper;
import com.test.model.Brand;

@Service
public class BrandDepotAppService
{
	@Autowired
	private BrandDepotAppMapper brandDepotAppMapper;
	
	public void updateBrandDepotFromApp(String logo_url,String brand_backgorund_color,String bar_code,String brand_id)
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
		brandDepotAppMapper.updateBrandDepotFromApp(brand);
	}
	
	public String selectBrandLogoFromApp(String brand_id)
	{
		return brandDepotAppMapper.selectBrandLogoFromApp(Integer.parseInt(brand_id));
	}
	
	
}
