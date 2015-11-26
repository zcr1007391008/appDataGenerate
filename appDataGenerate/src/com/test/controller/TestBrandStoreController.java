package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.model.BrandStore;
import com.test.model.City;
import com.test.model.Country;
import com.test.sevices.BrandStoreService;
import com.test.sevices.RedisCacheUtil;
import com.test.until.Base64EncodeDecode;

@Controller
@RequestMapping("testBrandStore")
public class TestBrandStoreController
{
	
	@Autowired
	private BrandStoreService brandStoreService;
	
	@Autowired
	private BrandStoreOutsideService brandStoreOutsideService;
	
	@Autowired
	RedisCacheUtil<City> redisCacheUtil;
	
	@Autowired
	RedisCacheUtil<Country> redisCacheUtil1;
	
	
	
	@RequestMapping("importDataFromFile")
	@ResponseBody
	public String importDataFromFile(HttpServletRequest request)
	{
		/*String name = "aa";
		String barcode_format = "en";
		String barcode = "1111";
		String homepage = "http:\\\\";
		String regions = "3";
		String other_stores = "aa";
		String typos = "22";
		String logo = " 22";
		String android_banner_url = "aaa.jpg";
		String ios_banner_url = "bb.jpg";
		String ios_logo_url = "cc.jpg";
		String background_color = "红色";
		String download_message = "chengg";
		System.out.println("导入数据");
		brandStoreService.insertIntoBranStore( name, barcode_format, barcode, homepage, regions,
				 other_stores, typos, logo, android_banner_url, ios_banner_url,
				 ios_logo_url, background_color, download_message);
		System.out.println("导入结束");*/
		String filePath = "C:\\Users\\zcr\\Desktop\\stores.json";
		brandStoreOutsideService.importBrandStoreFromFile(request,filePath);
		
		return "添加成功";
		
	}
	
	
	@RequestMapping("addBrandStore")
	public void addBrandStore(HttpServletRequest request)
	{
		
		try {
			String name = request.getParameter("name");
			String barcode_format = request.getParameter("barcode_format");
			String barcode = request.getParameter("barcode");
			String homepage = request.getParameter("homepage");
			String regions = request.getParameter("regions");
			String other_stores = request.getParameter("other_stores");
			String typos = request.getParameter("typos");
			
			String logo = request.getParameter("logo");
			String android_banner_url_json_str = new Base64EncodeDecode().encodeBase64File("F:\\1.jpg");
			String ios_banner_url_json_str = new Base64EncodeDecode().encodeBase64File("F:\\2.jpg");
			String ios_logo_url_json_str = new Base64EncodeDecode().encodeBase64File("F:\\xiazai.jpg");
			String background_color = request.getParameter("background_color");
			String download_message = request.getParameter("download_message");
			
			System.out.println("logo=" + logo);
			
			brandStoreOutsideService.insertIntoBranStore( request,name, barcode_format, barcode, homepage, regions,
					 other_stores, typos, logo, android_banner_url_json_str, ios_banner_url_json_str,
					 ios_logo_url_json_str, background_color, download_message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@RequestMapping("getDataInPage")
	public void testPaging()
	{
		List<BrandStore> list = brandStoreOutsideService.selectBrandStoreInPage("1","10");
		
		for(int i = 0 ; i < list.size() ; i ++)
		{
			System.out.println(list.get(i));
		}
	}
	
	
	@RequestMapping("getBrandStoreById")
	public void getBrandStoreById()
	{
		BrandStore brandStore = brandStoreOutsideService.selectBrandStoreById(110);
		System.out.println(null == brandStore);
		System.out.println(brandStore);
	}
	
	
	@RequestMapping("updateBrand")
	public void updateBrand(HttpServletRequest request)
	{
		BrandStore brandStore = new BrandStore();
		
		brandStore.setId(3104);
		brandStore.setBarcode("987654321");
		try
		{
			String base64Code = new Base64EncodeDecode().encodeBase64File("F:\\1.jpg");
			/*System.out.println(base64Code);*/
			String base64Code2 = new Base64EncodeDecode().encodeBase64File("F:\\2.jpg");
			brandStore.setAndroid_banner_url(base64Code);
			brandStore.setIos_banner_url(base64Code2);
			brandStoreOutsideService.updateBrandStore(request,brandStore);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@RequestMapping("cacheCity")
	public void cacahe()
	{
		/*RedisCacheUtil<City> redisCacheUtil = RedisCacheUtil.getInstance();
		RedisCacheUtil<Country> redisCacheUtil1 = RedisCacheUtil.getInstance();*/
		if(null == redisCacheUtil)
		{
			System.out.println("redisCacheUtil 为空");
		}
		if(null == redisCacheUtil1)
		{
			System.out.println("redisCacheUtil1 = 空" );
		}
		City city1 = new City(1,"广州",1);
		City city2 = new City(2,"武汉",2);
		
		Map<String,City> cityMap = new HashMap<String,City>();
		cityMap.put("1",city1);
		cityMap.put("2",city2);
		
		Country country1 = new Country("中国",1);
		Country country2 = new Country("美国",2);
		
		Map<String,Country> countryMap = new HashMap<String,Country>(); 
		countryMap.put("1",country1);
		countryMap.put("2",country2);
		
		redisCacheUtil.setCacheMap("city",cityMap);
		redisCacheUtil1.setCacheMap("country",countryMap);
	}
	
	@RequestMapping("getCache")
	public void getCache()
	{
	/*	RedisCacheUtil<City> redisCacheUtil = RedisCacheUtil.getInstance();
		RedisCacheUtil<Country> redisCacheUtil1 = RedisCacheUtil.getInstance();
		
		Map<String,Country> countryMap = redisCacheUtil1.getCacheMap("country");
		Map<String,City> cityMap = redisCacheUtil.getCacheMap("city");
		
		for(String key : countryMap.keySet())
		{
			System.out.println("key = " + key + ",value=" + countryMap.get(key));
		}
		
		System.out.println("------------city");
		for(String key : cityMap.keySet())
		{
			System.out.println("key = " + key + ",value=" + cityMap.get(key));
		}*/
		
	}
	
	
	
}
