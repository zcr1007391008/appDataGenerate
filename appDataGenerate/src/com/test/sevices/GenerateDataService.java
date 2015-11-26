package com.test.sevices;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.mapper.GenerateDataMapper;
import com.test.model.GenerateFormat;
import com.test.model.GenerateUser;
import com.test.model.GeneratedData;

@Service
public class GenerateDataService
{
	@Autowired
    private	GenerateDataMapper generateDataMapper;
	
	public int insertAndGetPrimaryKey(String username)
	{
		
		
		Date nowDate =  Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GenerateUser generateUser = new GenerateUser(username,nowDate);
		System.out.println( "之前：" + generateUser.getId());
		generateDataMapper.addGenerateAndGetPrimayKey(generateUser);
		
		System.out.println( "之后：" + generateUser.getId());
		
		
		return generateUser.getId();
	}
	
	public int addGenerateFormatAndGetPrimaryKey(String[] head,int generateId)
	{
		String pattern = "";
		
		if(null != head && head.length > 0 )
		{
			int headLength = head.length;
			int i = 0;
			for(i = 0 ; i < headLength - 1; i++)
			{
				pattern += head[i] + ",";
			}
			pattern += head[i];
			
		}
		GenerateFormat generateFormat = new GenerateFormat(pattern,generateId);
		generateDataMapper.addGenerateFormatAndGetPrimaryKey(generateFormat);
		
		System.out.println(generateFormat.getId());
		
		return generateFormat.getId();
	}
	
	
	public int addGenrateDataAndGetPrimayKey(String username,String filename)
	{
		Date nowDate =  Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GenerateUser generateUser = new GenerateUser(username,nowDate);
		generateUser.setFile_name(filename);
		System.out.println( "之前：" + generateUser.getId());
		generateDataMapper.addGenrateDataAndGetPrimayKey(generateUser);
		
		System.out.println( "之后：" + generateUser.getId());
		
		
		return generateUser.getId();
	}
	
	
	
	
	@Transactional
	public <T> void addGeneratedData(List<T> list,int data_format_id,String[] head)
	{
		int headLength = head.length;
		if(null != list && list.size() > 0)
		{
			int size = list.size(); 
		    Class classType = list.get(0).getClass();
		    for(int i = 0 ; i < size ; i ++)
		    {
		    	T t = list.get(i);
		    	String jsonData = "";
		    	//添加数据行
				for(int j = 0 ; j < headLength ; j++) 
				{
					//获得首字母
				    String firstLetter = head[j].substring(0,1).toUpperCase(); 
				    
				    //获得get方法,getName,getAge等
				    String getMethodName = "get" + firstLetter + head[j].substring(1);
				   
				    Method method;
					//通过反射获得相应的get方法，用于获得相应的属性值
					try
					{
						method = classType.getMethod(getMethodName, new Class[]{});
						try
						{
							jsonData += head[j] + ":" + method.invoke(t, new Class[]{}).toString() + ",";
							//System.out.print(getMethodName +":" + method.invoke(t, new Class[]{}) +",");
						}
						catch (IllegalArgumentException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (IllegalAccessException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (InvocationTargetException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					catch (SecurityException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (NoSuchMethodException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//		 dataCell.setCellValue(method.invoke(t, new Class[]{}).toString());
				}
				
				//删除最后一个","
				if(headLength > 0)
				{
					jsonData = jsonData.substring(0,jsonData.length()-1);
				}
				
				//System.out.println();
				System.out.println("jsonData = " +jsonData);
				GeneratedData generatedData = new GeneratedData(jsonData,i + 1,data_format_id);
				System.out.println("jsonData = " +jsonData);
				generateDataMapper.addGeneratedData(generatedData);
				
				
		    }
		}
		
	}
}
