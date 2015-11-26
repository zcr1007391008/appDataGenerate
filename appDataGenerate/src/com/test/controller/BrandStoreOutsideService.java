package com.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.BrandStore;
import com.test.model.City;
import com.test.model.Country;
import com.test.model.ImportBrand;
import com.test.model.Region;
import com.test.sevices.BrandStoreService;
import com.test.sevices.RedisCacheUtil;
import com.test.until.FileUploadtoTomcatUseBase64;
import com.test.until.FormatJson;
import com.test.until.URLConnectionDownloader;

/**
 * 品牌库相关操作
 * @author zcr
 *
 */

@Service
public class BrandStoreOutsideService
{
	@Autowired
	private BrandStoreService brandStoreService;
	
	@Autowired
	private RedisCacheUtil<Object> redisCache;
	
	//log4j 日志
	private final Logger log= Logger.getLogger(BrandStoreOutsideService.class);
	
	
	/**
	 * 读取文件中json格式数据，将文件中的数据导入到数据库中，并将文件下载到本地
	 * @param request	HttpServletRequest对象，用于获得tomcat存储的路径
	 * @param filePath	待读取文件的路径（需要在本地）
	 */
	public void importBrandStoreFromFile(HttpServletRequest request,String filePath)
	{
		//读取文件，将文件中的json格式数据转换为ImportBrand集合对象
		List<ImportBrand> brandList = FormatJson.formatFileListToBrand(filePath);
		
		int size = brandList.size();
		
		//根据url将文件下载到相应的文件夹，并且将记录加入到数据库中
		for(int i = 0 ; i < size; i ++)
		{
			ImportBrand brand = brandList.get(i);
			String download_message ="";
			String android_banner_url = "";
			String ios_banner_url = "";
			String ios_logo_url =  "";
			
				Map android_banner_url_map = 
				 URLConnectionDownloader.downloadToSelectedFolder("android_banner_url","picture.properties",brand.getAndroid_banner_url(),getFileNameFromUrl(brand.getAndroid_banner_url()),request);
			if((Boolean) android_banner_url_map.get("isSuccess"))
			{
				android_banner_url = (String) android_banner_url_map.get("path") ;
			}
			else
			{
				download_message += " android_banner_url 的图片下载失败！";
			}
			
				Map ios_banner_url_map =  URLConnectionDownloader.downloadToSelectedFolder("ios_banner_url","picture.properties",brand.getIos_banner_url(),getFileNameFromUrl(brand.getIos_banner_url()),request);
			if((Boolean) ios_banner_url_map.get("isSuccess"))
			{
				ios_banner_url = (String) ios_banner_url_map.get("path");
			}
			else
			{
				download_message += " ios_banner_url 的图片下载失败！";
			}
			
			Map ios_logo_url_map =  URLConnectionDownloader.downloadToSelectedFolder(
						"ios_logo_url", "picture.properties",
						brand.getIos_logo_url(),
						getFileNameFromUrl(brand.getIos_logo_url()), request);
			if((Boolean) ios_logo_url_map.get("isSuccess"))
			{
				ios_logo_url = (String) ios_logo_url_map.get("path");
			}
			else
			{
				download_message += " ios_logo_url 的图片下载失败！";
			}
			
			System.out.println("size = " + size +"，当前i=" + i);
			//这里的region由于和数据库表结构不同，暂时统一使用3代替
			brandStoreService.insertIntoBranStore(brand.getName(),brand.getBarcode_format()," ",brand.getHomepage(),"3",brand.getOther_stores().toString(),
					brand.getTypos().toString(),brand.getLogo(),android_banner_url,ios_banner_url,ios_logo_url," ",download_message);
		}
	}
	
	
	/**
	 * 添加品牌到数据库，下面是数据库中相应字段的数据
	 * @param request
	 * @param name
	 * @param barcode_format
	 * @param barcode
	 * @param homepage
	 * @param regions
	 * @param other_stores
	 * @param typos
	 * @param logo
	 * @param android_banner_url_json_str	base64位数据字符串
	 * @param ios_banner_url_json_str		base64位数据字符串
	 * @param ios_logo_url_json_str			base64位数据字符串
	 * @param background_color				
	 * @param download_message				下载信息，当是导入数据的时候才需要此字段
	 */
	public void insertIntoBranStore(HttpServletRequest request,String name,String barcode_format,String barcode,String homepage,String regions,
			String other_stores,String typos,String logo,String android_banner_url_json_str,String ios_banner_url_json_str,
			String ios_logo_url_json_str,String background_color,String download_message)
	{
		String android_banner_url = "";
		String ios_banner_url = "";
		String ios_logo_url = "";
		
		//上传图片
		if(StringUtils.isNotBlank(android_banner_url_json_str))
		{
			android_banner_url = FileUploadtoTomcatUseBase64.uploadPicture(request, "android_banner_url", "picture.properties", android_banner_url_json_str);
		}
		if(StringUtils.isNotBlank(ios_banner_url_json_str))
		{
			ios_banner_url = FileUploadtoTomcatUseBase64.uploadPicture(request, "ios_banner_url", "picture.properties", ios_banner_url_json_str);
		}
		if(StringUtils.isNotBlank(ios_logo_url_json_str))
		{
			ios_logo_url = FileUploadtoTomcatUseBase64.uploadPicture(request, "ios_logo_url", "picture.properties", ios_logo_url_json_str); 
		}
		
		System.out.println("android_banner_url = " + android_banner_url);
		
		brandStoreService.insertIntoBranStore( name, barcode_format, barcode, homepage, regions,
				 other_stores, typos, logo, android_banner_url, ios_banner_url,
				 ios_logo_url, background_color, download_message);
	}
	
	
	
	/**
	 * 分页查询
	 * @param iCurrentPage	当前页
	 * @param iPageSize		页面大小
	 * @return
	 */
	public List<BrandStore> selectBrandStoreInPage(String iCurrentPage,String iPageSize)
	{
		List<BrandStore> brandStoresList = brandStoreService.selectBrandStoreInPage(iCurrentPage,iPageSize);
		int size = brandStoresList.size();
		//添加地域（region）信息
		for(int i = 0 ; i < size ;i ++)
		{
			List<Region> regionList = new ArrayList<Region>();
			if(StringUtils.isNotBlank(brandStoresList.get(i).getRegions()))  
			{
				String[] regionArr = brandStoresList.get(i).getRegions().split(",");
				//从缓存里面取地域信息
				if(null != regionArr)
				{
					int arrLength = regionArr.length;
					for(int j = 0 ; j < arrLength ; j ++)
					{
						if(StringUtils.isNotBlank(regionArr[j]))
						{
							int city_id = Integer.parseInt(regionArr[j]);
							City city = (City) redisCache.getCacheIntegerMap("cityMap").get(city_id);
							Country country = (Country) redisCache.getCacheIntegerMap("countryMap").get(city.getCountry_id());
							Region region = new Region(city_id,country,city);
							regionList.add(region);
						}
					}
				}
				
			}
			brandStoresList.get(i).setRegionList(regionList);
		}
		
		return brandStoresList;
	}
	
	
	/**
	 * 根据id查询出品牌信息
	 * @param id	待查询品牌编号
	 * @return		id品牌信息
	 */
	public BrandStore selectBrandStoreById(int id)
	{
		BrandStore brandStore = brandStoreService.selectBrandStoreById(id);
		
		//添加地域信息
		
		List<Region> regionList = new ArrayList<Region>();
		if(StringUtils.isNotBlank(brandStore.getRegions()))  
		{
			String[] regionArr = brandStore.getRegions().split(",");
			//从缓存里面取
			if(null != regionArr)
			{
				int arrLength = regionArr.length;
				for(int j = 0 ; j < arrLength ; j ++)
				{
					if(StringUtils.isNotBlank(regionArr[j]))
					{
						int city_id = Integer.parseInt(regionArr[j]);
						City city = (City) redisCache.getCacheIntegerMap("cityMap").get(city_id);
						Country country = (Country) redisCache.getCacheIntegerMap("countryMap").get(city.getCountry_id());
						Region region = new Region(city_id,country,city);
						regionList.add(region);
					}
				}
			}
			
		}
		brandStore.setRegionList(regionList);
		
		return brandStore;
	} 
	
	
	
	/**
	 * 更新品牌库信息，传入一个brandStore对象，该对象的字段与数据库中字段值相一致，假如不更改，不给他设置值（默认是null或0），或者手动设置为null或0
	 * @param request		HttpServletRequest对象，用于获得tomcat的保存位置
	 * @param brandStore	
	 */
	public void updateBrandStore(HttpServletRequest request,BrandStore brandStore)
	{
		Map<String,String> logoMap = brandStoreService.selectLogoUrlById(brandStore.getId());
		System.out.println(logoMap);
		if(StringUtils.isNotBlank(brandStore.getAndroid_banner_url()))  //更改Android_banner_url
		{
			System.out.println("是否为空:" + logoMap.get("android_banner_url"));
			
			if(StringUtils.isNotBlank(logoMap.get("android_banner_url")))   //原先不为空，这里只是修改图片
			{
				//更改图片
				FileUploadtoTomcatUseBase64.updatePicture(request,"android_banner_url","picture.properties",logoMap.get("android_banner_url"),brandStore.getAndroid_banner_url());
			
				brandStore.setAndroid_banner_url(null);  //设置为null，使数据库不更新
			}
			else  //原先没有图片，需上传图片，并修改
			{
				//上传图片
				String uploadFileName = FileUploadtoTomcatUseBase64.uploadPicture(request,"android_banner_url","picture.properties",brandStore.getAndroid_banner_url());
			
				//设置文件的名字
				brandStore.setAndroid_banner_url(uploadFileName);
			}
		}
		
		if(StringUtils.isNotBlank(brandStore.getIos_banner_url()))  //更改Ios_banner_url
		{
			if(StringUtils.isNotBlank(logoMap.get("ios_banner_url")))   //原先不为空，这里只是修改图片
			{
				//更改图片
				FileUploadtoTomcatUseBase64.updatePicture(request,"ios_banner_url","picture.properties",logoMap.get("ios_banner_url"),brandStore.getIos_banner_url());
			
				brandStore.setIos_banner_url(null);  //设置为null，使数据库不更新
			}
			else  //原先没有图片，需上传图片，并修改
			{
				//上传图片
				String uploadFileName = FileUploadtoTomcatUseBase64.uploadPicture(request,"ios_banner_url","picture.properties",brandStore.getIos_banner_url());
			
				//设置文件的名字
				brandStore.setIos_banner_url(uploadFileName);
			}
		}
		
		
		if(StringUtils.isNotBlank(brandStore.getIos_logo_url()))  //更改Ios_logo_url
		{
			if(StringUtils.isNotBlank(logoMap.get("ios_logo_url")))   //原先不为空，这里只是修改图片
			{
				//更改图片
				FileUploadtoTomcatUseBase64.updatePicture(request,"ios_logo_url","picture.properties",logoMap.get("ios_logo_url"),brandStore.getIos_banner_url());
			
				brandStore.setIos_logo_url(null);  //设置为null，使数据库不更新
			}
			else  //原先没有图片，需上传图片，并修改
			{
				//上传图片
				String uploadFileName = FileUploadtoTomcatUseBase64.uploadPicture(request,"ios_logo_url","picture.properties",brandStore.getIos_banner_url());
			
				System.out.println("上传的文件名字为:" + uploadFileName);
				//设置文件的名字
				brandStore.setIos_logo_url(uploadFileName);
			}
		}
		
		
		if(StringUtils.isNotBlank(brandStore.getBarcode()))
		{
			brandStore.setHas_barcode(1);
			
		}
		else
		{
			brandStore.setHas_barcode(0);
			brandStore.setBarcode_format("");
		}
		
		/*System.out.println(brandStore);*/
		brandStoreService.updateBrandStore(brandStore);
	}
	
	
	
	/**
	 * 从url中截取出文件名称
	 * @param httpUrlStr	待截取的url
	 * @return
	 */
	public static String getFileNameFromUrl(String httpUrlStr)
	{
		return httpUrlStr.substring(httpUrlStr.lastIndexOf("/") + 1);
	}
}
