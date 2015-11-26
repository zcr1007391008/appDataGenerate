package com.test.until;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.test.model.ImportBrand;

/**
 * 格式化json数据
 * @author zcr
 *
 */
public class FormatJson
{
	public static void main(String[] args)
	{
		/*String message = "{\"id\": \"1403\",\"name\": \"1.2.3 Diva\",\"has_barcode\": true,\"barcode_format\": \"EAN_13\",\"homepage\": \"http://1-2-3.fr\",\"regions\": [\"DE\",\"FR\"],\"other_stores\": [],\"typos\": [\"un deux trois\",\"un1deux2trois3\"],\"logo\": \"undeuxtrois\",\"android_banner_url\": \"http://stocardapp.s3-external-3.amazonaws.com/android/banner/undeuxtrois.png\",\"ios_banner_url\": \"http://stocardapp.s3-external-3.amazonaws.com/ios/banners/undeuxtrois.png\",\"ios_logo_url\": \"http://stocardapp.s3-external-3.amazonaws.com/ios/icons/undeuxtrois@2x.png\"}";
		System.out.println("message = " + message);
		Gson gson = new Gson();
		ImportBrand brand = gson.fromJson(message,ImportBrand.class);
		
		System.out.println(brand);*/
		String filePath = "C:\\Users\\owner\\Desktop\\卓信科技实习\\stores.json";
		/*List<String> dataList = ImportFile.readTxtFileIntoStringArrList(filePath);
		List<ImportBrand> brandList = formatStringListToBrand(dataList);*/
		List<ImportBrand> brandList = FormatJson.formatFileListToBrand(filePath);
		for(int i = 0 ; i < brandList.size() ; i ++)
		{
			System.out.println(brandList.get(i));
		}
		System.out.println(brandList.size());
	}
	
	/**
	 * 将json格式数据转换成Import对象
	 * @param jsonStr	带转换的json对象
	 * @return			json格式数据对应的对象
	 */
	public static ImportBrand formatFromJsonToObject(String jsonStr)
	{
		Gson gson = new Gson();
		ImportBrand brand = gson.fromJson(jsonStr,ImportBrand.class);
		
		return brand;
	}
	
	/**
	 * 将String类型的json格式转换为ImportBrand类型的集合
	 * @param jsonStrList	待转换的json格式List对象
	 * @return				json格式对象转换而成的ImportBrand对象集合
	 */
	public static List<ImportBrand> formatStringListToBrand(List<String> jsonStrList)
	{
		List<ImportBrand> listImportBrand = new ArrayList<ImportBrand>();
		int size = jsonStrList.size();
		for(int i = 0 ; i < size ; i ++)
		{
			listImportBrand.add(formatFromJsonToObject(jsonStrList.get(i)));
		}
		
		return listImportBrand;
	}
	
	/**
	 * 读取文件，将json格式的数据转换成List对象的ImportBrand
	 * @param filePath	读取的文件路径
	 * @return			
	 */
	public static List<ImportBrand> formatFileListToBrand(String filePath)
	{
		List<String> dataList = ImportFile.readTxtFileIntoStringArrList(filePath);
		List<ImportBrand> brandList = formatStringListToBrand(dataList);
		/*for(int i = 0 ; i < brandList.size() ; i ++)
		{
			System.out.println(brandList.get(i));
		}*/
		return brandList;
	}
}
