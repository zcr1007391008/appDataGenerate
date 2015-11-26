package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.sevices.CreatePdf;
import com.test.sevices.GenerateDataService;
import com.test.sevices.GenerateExcel;

/**
 * 将生成pdf、excel表格相应的数据导入数据
 * @author zcr
 *
 */
@Service
@Transactional
public class DataImportService
{
	@Autowired
	private GenerateDataService generateDataService;
	
	/**
	 * 将生成文件的数据导入到数据库中
	 * @param list		数据
	 * @param username	生成文件的用户	
	 * @param head		数据表头
	 * @return			导入情况：是否成功！
	 */
	@Transactional
	public <T> String importDataToDB(List<T> list,String username,String[] head)
	{
		String message = "导入成功!";
		
		try
		{
			System.out.println("username = " + username);
			//插入数据并返回生成表格人的自增长id
			int importPepoleId = generateDataService
					.insertAndGetPrimaryKey(username);
			System.out.println("importPepoleId = " + importPepoleId);
			
			//导入数据格式,插入的格式id
			int formatID = generateDataService.addGenerateFormatAndGetPrimaryKey(head, importPepoleId);
			
			System.out.println("formatID = " +formatID);
			
			//导入数据
			generateDataService.addGeneratedData(list, formatID, head);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			message = "导入错误";
			System.out.println("导入错误！");
		}
		return message; 
	}
	
	
	/**
	 * 将生成文件的数据导入到数据库中,并生成excel
	 * @param list		数据
	 * @param username	生成文件的用户	
	 * @param head		数据表头
	 * @return			导入情况：是否成功！
	 */
	@Transactional
	public <T> String importExcelDataToDB(HttpServletRequest request,List<T> list,String username,String[] head)
	{
		String message = "导入成功!";
		
		try
		{
			//生成excel
			String fileName = new GenerateExcel().generateExcel(request,head,list);
			
			
			
			System.out.println("username = " + username);
			//插入数据并返回生成表格人的自增长id
			int importPepoleId = generateDataService
					.addGenrateDataAndGetPrimayKey(username,fileName);
			System.out.println("importPepoleId = " + importPepoleId);
			
			//导入数据格式,插入的格式id
			int formatID = generateDataService.addGenerateFormatAndGetPrimaryKey(head, importPepoleId);
			
			System.out.println("formatID = " +formatID);
			
			//导入数据
			generateDataService.addGeneratedData(list, formatID, head);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			message = "导入错误";
			System.out.println("导入错误！");
		}
		return message; 
	}
	
	/**
	 * 将生成文件的数据导入到数据库中,并生成pdf
	 * @param list		数据
	 * @param username	生成文件的用户	
	 * @param head		数据表头
	 * @return			导入情况：是否成功！
	 */
	@Transactional
	public <T> String importPdfDataToDB(HttpServletRequest request,List<T> list,String username,String[] head)
	{
		String message = "导入成功!";
		
		try
		{
			//生成excel
			String fileName = new CreatePdf().generatePDFs(request,head,list);
			
			
			
			System.out.println("username = " + username);
			//插入数据并返回生成表格人的自增长id
			int importPepoleId = generateDataService
					.addGenrateDataAndGetPrimayKey(username,fileName);
			System.out.println("importPepoleId = " + importPepoleId);
			
			//导入数据格式,插入的格式id
			int formatID = generateDataService.addGenerateFormatAndGetPrimaryKey(head, importPepoleId);
			
			System.out.println("formatID = " +formatID);
			
			//导入数据
			generateDataService.addGeneratedData(list, formatID, head);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			message = "导入错误";
			System.out.println("导入错误！");
		}
		return message; 
	}
	
}
