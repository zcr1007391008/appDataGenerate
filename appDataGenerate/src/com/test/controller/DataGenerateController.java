package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mapper.GenerateDataMapper;
import com.test.model.User;
import com.test.sevices.GenerateDataService;

@Controller
@RequestMapping("dataGenerate")
public class DataGenerateController
{
	
	@Autowired
	private DataImportService dataImportService;
	
	@Autowired
	private GenerateDataService generateDataService;
	
	

	
	
	@RequestMapping("importDataToDB")
	@ResponseBody
	public String importDataToDB()
	{
		System.out.println("--------------------");
		System.out.println("-----------进去");
		System.out.println(null == dataImportService);
		System.out.println("---------------");
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		return dataImportService.importDataToDB(list,"zhangsan",head);
	}
	
	@ResponseBody
	@RequestMapping("test")
	public int test()
	{
		return generateDataService.insertAndGetPrimaryKey("bb");
	}
	

	@ResponseBody
	@RequestMapping("insertAndFormat")
	public int insertAndGenerateFormatAndGetPrimaryKey()
	{
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		return generateDataService.addGenerateFormatAndGetPrimaryKey(head,2);
	}
	
	@ResponseBody
	@RequestMapping("insertDatas")
	public int insertGenerateDatas()
	{
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		generateDataService.addGeneratedData(list,2,head);
		return 1;
	}
	
}
