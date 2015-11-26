package com.test.mapper;

import com.test.model.Brand;

public interface BrandDepotAppMapper
{
	public void updateBrandDepotFromApp(Brand brand);
	
	public String selectBrandLogoFromApp(int brand_id);
	
}
