package com.test.until;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * 读取文件
 * 
 * @author zcr
 * 
 */
public class ImportFile
{
	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 *            文件路径[到达文件:如： D:\aa.txt]
	 * @return 将这个文件按照每一行切割成数组存放到list中。
	 */
	public static List<String> readTxtFileIntoStringArrList(String filePath)
	{
		List<String> list = new ArrayList<String>();
		try
		{
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists())
			{ // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null)
				{
					if (isRightFormat(lineTxt))
					{
						list.add(lineTxt.substring(lineTxt.indexOf("{"),lineTxt.lastIndexOf(',')));
					}
				}
				read.close();
			}
			else
			{
				System.out.println("找不到指定的文件");
			}
		}
		catch (Exception e)
		{
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		return list;
	}

	
	/**
	 * 读取文件，将文件中的电话号码读取出来，保存在Set中。
	 * @param filePath	文件的绝对路径
	 * @return			文件中包含的电话号码
	 */
	public static Set<String> getPhoneNumFromFile(String filePath)
	{
		Set<String> phoneSet = new HashSet<String>();
		
		try
		{
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists())
			{ // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null)
				{
					//读取文件中的一行，将其中的电话号码添加到phoneSet中
					CheckIfIsPhoneNumber.getPhoneNumFromStrIntoSet(lineTxt, phoneSet);
				}
				read.close();
			}
			else
			{
				System.out.println("找不到指定的文件");
			}
		}
		catch (Exception e)
		{
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		
		return phoneSet;
	}
	
	
	
	
	
	public static void main(String argv[])
	{
		 /* String filePath = "C:\\Users\\owner\\Desktop\\卓信科技实习\\stores.json";
		  List<String> dataList = readTxtFileIntoStringArrList(filePath);
		  
		  for(int i = 0 ; i < dataList.size() ; i ++) 
		  {
			  System.out.println(dataList.get(i)); 
		  }
		  System.out.println(dataList.size());*/
		String filePath = "F:\\three.txt";
		
		
		Set<String> phoneSet = getPhoneNumFromFile(filePath);
		
		System.out.println("电话集合：" + phoneSet);
	}

	/**
	 * 判断数据是否是合法的格式
	 * @param jsonStr	带判断的数据
	 * @return			返回该行是否是正确的格式
	 */
	public static boolean isRightFormat(String jsonStr)
	{
		return jsonStr.matches("[\\p{Space}]*[{]{1}.*[}]{1}[,]{1}");
	}

}
