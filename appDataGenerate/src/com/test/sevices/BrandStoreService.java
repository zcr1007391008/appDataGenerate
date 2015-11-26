package com.test.sevices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.BrandStoreMapper;
import com.test.model.BrandStore;
import com.test.model.City;
import com.test.model.Country;

@Service
public class BrandStoreService
{
	@Autowired
	private BrandStoreMapper brandStoreMapper;
	
	/**
	 * 添加品牌
	 * @param name
	 * @param barcode_format
	 * @param barcode
	 * @param homepage
	 * @param regions
	 * @param other_stores
	 * @param typos
	 * @param logo
	 * @param android_banner_url
	 * @param ios_banner_url
	 * @param ios_logo_url
	 * @param background_color
	 * @param download_message
	 */
	public void insertIntoBranStore(String name, /*String has_barcode,*/String barcode_format,String barcode,String homepage,String regions,
			String other_stores,String typos,String logo,String android_banner_url,String ios_banner_url,
			String ios_logo_url,String background_color/*,String status*/,String download_message)
	{
		int has_barcode = 0;
		if(StringUtils.isNotBlank(barcode))
		{
			has_barcode = 1;
		}
		else
		{
			barcode_format = "";
		}
		
		BrandStore brandStore = new BrandStore(name,has_barcode,barcode_format, barcode, homepage, regions,
				 other_stores, typos, logo, android_banner_url, ios_banner_url,
				 ios_logo_url, background_color, download_message);
		
		brandStoreMapper.insertIntoBranStore(brandStore);
	}
	
	
	/**
	 * 分页查询品牌库
	 * @param iCurrentPage 当前页数
	 * @param iPageSize	         页大小
	 * @return			         第iCurrentPage页数据
	 */
	public List<BrandStore> selectBrandStoreInPage(String iCurrentPage,String iPageSize)
	{
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("beforeNum",(Integer.parseInt(iCurrentPage) - 1) * Integer.parseInt(iPageSize));
		map.put("size",Integer.parseInt(iPageSize));
		return brandStoreMapper.selectBrandStoreInPage(map);
	}
	
	/**
	 * 根据id查询出品牌信息
	 * @param id	待查询品牌编号
	 * @return		id品牌信息
	 */
	public BrandStore selectBrandStoreById(int id)
	{
		return brandStoreMapper.selectBrandStoreById(id);
	}
	
	/**
	 * 更新品牌库信息
	 * @param brandStore
	 */
	public void updateBrandStore(BrandStore brandStore)
	{
		brandStoreMapper.updateBrandStore(brandStore);
	}
	
	/**
	 * 根据id查询出品牌的logo地址
	 * @param id	待查询logo的id 
	 * @return		该id所有的logo地址
	 */
	public Map selectLogoUrlById(int id)
	{
		return brandStoreMapper.selectLogoUrlById(id);
	}
	
	/**
	 * 查询出所有的城市信息
	 * @return 所有的城市信息
	 */
	public List<City> selectAllCityMessage()
	{
		return brandStoreMapper.selectAllCityMessage();
	}
	
	/**
	 * 查询出所有的国家信息 
	 * @return	所有的国家信息 
	 */
	
	public List<Country> selectAllCountryMessage()
	{
		return brandStoreMapper.selectAllCountryMessage();
	}
	
}
