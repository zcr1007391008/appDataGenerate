package com.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcr.test.User;
import com.test.model.City;
import com.test.model.Country;
import com.test.model.ImportBrand;
import com.test.sevices.CreatePdf;
import com.test.sevices.GenerateExcel;
import com.test.sevices.RedisCacheUtil;
import com.test.until.FormatJson;
import com.test.until.GetFilePlace;
import com.test.until.ImportFile;
import com.test.until.URLConnectionDownloader;

@Controller
@RequestMapping("mytests")
public class TestCache
{
	@Autowired
	private RedisCacheUtil<User> redisCache;
	
	@Autowired
	private BrandDepotAppOutsideService brandDepotAppOutsideService;
	
	
	@Autowired
	RedisCacheUtil<City> redisCacheUtil;
	
	@Autowired
	RedisCacheUtil<Country> redisCacheUtil1;
	
	@Autowired
	DataImportService dataImportService;
	
	
	
	@RequestMapping("tests")
	public void test(HttpServletRequest request)
	{
		/*String pathSavePath = new GetFilePlace().getFileDirFromProperties("android_banner_url","picture.properties");
		
		
		String path = request.getSession().getServletContext().getRealPath(pathSavePath); 
		
		System.out.println("path = " + path);
		
		
		URLConnectionDownloader.downloadToSelectedFolder("android_banner_url","picture.properties","http://stocardapp.s3-external-3.amazonaws.com/ios/icons/1001tur@2x.png","2x.png",request);
		
		*/
		
		//RedisCacheUtil<User> redisCache = new RedisCacheUtil<User>();
		System.out.println(null == redisCache);
		System.out.println(null == redisCache.redisTemplate);
		User u1 = new User("zhangsan",12);  
	    User u2 = new User("lisi",25);  
	     
	    ValueOperations<String,User> test  = redisCache.setCacheObject("u1",u1);
		test = redisCache.setCacheObject("u2",u2);
		
		System.out.println("u1 :" + ((User) redisCache.getCacheObject("u1")).getAge() +"," + ((User) redisCache.getCacheObject("u1")).getName());
		System.out.println("u2 :" + ((User) redisCache.getCacheObject("u2")).getAge() +"," + ((User) redisCache.getCacheObject("u1")).getName());
		
		
		RedisCacheUtil<String> redisCacheStr = new RedisCacheUtil<String>();
		
		/*ValueOperations<String,String> aa ;
		
		aa = redisCacheStr.setCacheObject("uu1","uu1");
		
		System.out.println(redisCacheStr.getCacheObject("uu1")  instanceof String);
		System.out.println("str 测试:" +  redisCacheStr.getCacheObject("uu1"));*/
		
		
		System.out.println("------------TestList--------------");
		List<User> userList = new ArrayList<User>();
		User userList1 = new User("xiaoming",11);
		User userList2 = new User("小溪",22);
		
		userList.add(userList1);
		userList.add(userList2);
		
		ListOperations<String,User> listOperations = redisCache.setCacheList("userList",userList);
		
		List<User> getUserList = redisCache.getCacheList("userList");
		for(int i = 0 ; i < getUserList.size() ; i ++)
		{
			System.out.println(getUserList.get(i));
		}

		System.out.println("----------------\n");
		
		System.out.println("-------------------TestSet----------");
		Set<User> set = new HashSet<User>();
		User userList3 = new User("xiaomi1111ng",11);
		User userList42 = new User("小溪11",22);
		set.add(userList3);
		set.add(userList42);
		/*RedisCacheUtil<User> util = new RedisCacheUtil<User>();*/
		BoundSetOperations op = redisCache.setCacheSet("myset",set);
		Set<User> getSet = redisCache.getCacheSet("myset");
		for(User u: getSet)
		{
			System.out.println(u);
		}
		
		
		System.out.println("\n-------------------------测试获得Map-----------");
		Map<String,User> map  = new HashMap<String,User>();
		
		User userMap1 = new User("换换",11);
		User userMap2 = new User("洗洗",22);
		
		map.put("userMap1",userMap1);
		map.put("userMap2",userMap2);
		/*RedisCacheUtil<User> mapUtil = new RedisCacheUtil<User>();*/
		HashOperations<String,String,User> hashOperation = redisCache.setCacheMap("mapCache",map);
		Map<String,User> getmap =  redisCache.getCacheMap("mapCache");
		for(Map.Entry<String,User> entry : getmap.entrySet())
		{
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		}
	}
	
	
	@RequestMapping("importBrand")
	public void importBrand(HttpServletRequest request)
	{
		String filePath = "C:\\Users\\owner\\Desktop\\卓信科技实习\\stores.json";
		
		brandDepotAppOutsideService.importDataFromJsonFile(filePath,request);
		
		
		/*List<ImportBrand> brandList = FormatJson.formatFileListToBrand(filePath);
		for(int i = 0 ; i < brandList.size() ; i ++)
		{
			System.out.println(brandList.get(i));
		}
		System.out.println(brandList.size());*/
		/*String filePath = "C:\\Users\\owner\\Desktop\\卓信科技实习\\stores.json";
		List<String> dataList = ImportFile.readTxtFileIntoStringArrList(filePath);
		List<ImportBrand> brandList = FormatJson.formatStringListToBrand(dataList);
		for(int i = 0 ; i < brandList.size() ; i ++)
		{
			System.out.println(brandList.get(i));
		}
		System.out.println(brandList.size());*/
	}
	
	
	
	
	@RequestMapping("testGetCache")
	public void testGetCache()
	{
		/*Map<String,Country> countryMap = redisCacheUtil1.getCacheMap("country");
		Map<String,City> cityMap = redisCacheUtil.getCacheMap("city");*/
		Map<Integer,Country> countryMap = redisCacheUtil1.getCacheIntegerMap("countryMap");
		Map<Integer,City> cityMap = redisCacheUtil.getCacheIntegerMap("cityMap");
		
		for(int key : countryMap.keySet())
		{
			System.out.println("key = " + key + ",value=" + countryMap.get(key));
		}
		
		System.out.println("------------city");
		for(int key : cityMap.keySet())
		{
			System.out.println("key = " + key + ",value=" + cityMap.get(key));
		}
	}
	
	
	@RequestMapping("testGenerateFile")
	public void testGenerateFile(HttpServletRequest request)
	{
		System.out.println("begin");
		
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		
		
		String filePath = new CreatePdf().generatePDFs(request,head,list);
		System.out.println(filePath);
		System.out.println("end");
		
		
		
		/*String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);*/
		
		System.out.println(new GenerateExcel().generateExcel(request,head,list));
	}
	
	
	
	
	@RequestMapping("testGenerateExcelAndImportToDB")
	public void testGenerateExcelAndImportToDB(HttpServletRequest request)
	{
		System.out.println("begin");
		
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		
		
		dataImportService.importExcelDataToDB(request, list, "张强", head);
		
		System.out.println("end");
		
		
		
		/*String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);*/
		
		
	}
	
	@RequestMapping("importDataToDBandGeneratePDF")
	public void importDataToDBandGeneratePDF(HttpServletRequest request)
	{
		System.out.println("begin");
		
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		
		
		dataImportService.importPdfDataToDB(request, list, "黎明", head);
		
		System.out.println("end");
		
		
		
		/*String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);*/
		
		
	}
	
}
