package com.test.mapper;

import java.util.List;
import java.util.Map;

import com.test.model.Brand;

public interface BrandDepotManagerMapper
{
	public void addBrand(Brand brand);
	
	public void updateBrand(Brand brand);
	
	public List<Brand> selectBrandInPage(Map<String,Integer> map);

	
    public String selectBrandLogo(int brand_id);
    
    public void updateBrandWithoutLogo(Brand brand);
    
    public void updateBrandOnlyLogo(Map map);
}
