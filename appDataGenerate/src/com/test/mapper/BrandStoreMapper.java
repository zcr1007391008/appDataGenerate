package com.test.mapper;

import java.util.List;
import java.util.Map;

import com.test.model.BrandStore;
import com.test.model.City;
import com.test.model.Country;

public interface BrandStoreMapper
{
	public void insertIntoBranStore(BrandStore brandStore);
	
	public List<BrandStore> selectBrandStoreInPage(Map map);
	
	public void updateBrandStore(BrandStore brandStore);
	
	public BrandStore selectBrandStoreById(int id);
	
	public Map selectLogoUrlById(int id);
	
	
	public List<City> selectAllCityMessage();
	
	public List<Country> selectAllCountryMessage();
	
}	
