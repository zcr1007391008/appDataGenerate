package com.test.sevices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.test.until.GenerateFileName;
import com.test.until.GenerateFold;
import com.test.until.GetFilePlace;
import com.zcr.test.User;

/**
 * 生成pdf
 * @author zcr
 */
public class CreatePdf
{
	Document document = new Document();// 建立一个Document对象

	private static Font headfont;// 设置字体大小
	private static Font keyfont;// 设置字体大小
	private static Font textfont;// 设置字体大小

	static
	{
		//中文格式
		BaseFont bfChinese;
		try
		{
			// 设置中文显示
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 文成文件
	 * @param file 待生成的文件名
	 */
	public CreatePdf(File file)
	{
		document.setPageSize(PageSize.A4);// 设置页面大小
		try
		{
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public CreatePdf()
	{
		
	}
	
	public void initFile(File file)
	{
		document.setPageSize(PageSize.A4);// 设置页面大小
		try
		{
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

	int maxWidth = 520;
	
	/**
	 * 为表格添加一个内容
	 * @param value       	值
	 * @param font			字体
	 * @param align			对齐方式
	 * @return				添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align)
	{
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}
	
	/**
	 * 为表格添加一个内容
	 * @param value       	值
	 * @param font			字体
	 * @return				添加的文本框
	 */
	public PdfPCell createCell(String value, Font font)
	{
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	/**
	 * 为表格添加一个内容
	 * @param value       	值
	 * @param font			字体
	 * @param align			对齐方式
	 * @param colspan		占多少列
	 * @return				添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan)
	{
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}
	
	/**
	 * 为表格添加一个内容
	 * @param value       	值
	 * @param font			字体
	 * @param align			对齐方式
	 * @param colspan		占多少列
	 * @param boderFlag		是否有有边框
	 * @return				添加的文本框
	 */
	public PdfPCell createCell(String value, Font font, int align, int colspan,
			boolean boderFlag)
	{
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		if (!boderFlag)
		{
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(8.0f);
		}
		return cell;
	}

	/**
	 * 创建一个表格对象
	 * @param colNumber  表格的列数
	 * @return			  生成的表格对象
	 */
	public PdfPTable createTable(int colNumber)
	{
		PdfPTable table = new PdfPTable(colNumber);
		try
		{
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createTable(float[] widths)
	{
		PdfPTable table = new PdfPTable(widths);
		try
		{
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createBlankTable()
	{
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.addCell(createCell("", keyfont));
		table.setSpacingAfter(20.0f);
		table.setSpacingBefore(20.0f);
		return table;
	}

	public <T> void generatePDF(String [] head,List<T> list,int colNum) 
	{
		Class classType = list.get(0).getClass();
		
		// 创建一个只有5列的表格
		PdfPTable table = createTable(colNum);

		// 添加备注,靠左，不显示边框
		table.addCell(createCell("APP信息列表：", keyfont, Element.ALIGN_LEFT, colNum,false));
		
		//设置表头
		for(int i = 0 ; i < colNum ; i++)
		{
			table.addCell(createCell(head[i], keyfont, Element.ALIGN_CENTER));
		}
		
		
		if(null != list && list.size() > 0)
		{
			int size = list.size();
			for(int i = 0 ; i < size ; i++)
			{
				T t = list.get(i);
				for(int j = 0 ; j < colNum ; j ++)
				{
					//获得首字母
				    String firstLetter = head[j].substring(0,1).toUpperCase(); 
				    
				    //获得get方法,getName,getAge等
				    String getMethodName = "get" + firstLetter + head[j].substring(1);
				   
				    Method method;
				    try
					{
						//通过反射获得相应的get方法，用于获得相应的属性值
						method = classType.getMethod(getMethodName, new Class[]{});
					    try
						{
					    	 System.out.print(getMethodName +":" + method.invoke(t, new Class[]{}) +",");
					    	 //添加数据
					    	 table.addCell(createCell(method.invoke(t, new Class[]{}).toString(), textfont));
						}
						catch (IllegalArgumentException e)
						{
							e.printStackTrace();
						}
						catch (IllegalAccessException e)
						{
							e.printStackTrace();
						}
						catch (InvocationTargetException e)
						{
							e.printStackTrace();
						}  
					}
					catch (SecurityException e)
					{
						e.printStackTrace();
					}
					catch (NoSuchMethodException e)
					{
						e.printStackTrace();
					}
				}
				
				System.out.println("");
			}
		}
		
		try
		{
			//将表格添加到文档中
			document.add(table);
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		finally
		{
			//关闭流
			document.close();
		}
		
		
	}
	
	
	/**
	 * 提供外界调用的接口，生成以head为表头，list为数据的pdf
	 * @param request	用于获得tomcat的目录
	 * @param head  //数据表头
	 * @param list  //数据
	 * @return		//excel所在的路径
	 */
	public <T> String generatePDFs(HttpServletRequest request,String [] head,List<T> list)
	{
		final String FilePath = "pdfPath";
		String saveFilePathAndName = "";
	
		//获得存储的相对路径
		String savePath = new GetFilePlace().getFileDirFromProperties(FilePath,"filedir.properties");

		//获得tomcat存储的根目录
		String path = request.getSession().getServletContext().getRealPath(savePath); 
		
		
		//获得当天存储的路径,不存在则生成当天的文件夹
		String realSavePath = new GenerateFold().getFold(path);
		
		
		saveFilePathAndName = new GenerateFileName().generateFileName(realSavePath,"pdf");
		
		System.out.println("保存的路径和名字：" + saveFilePathAndName);
		
		File file = new File(saveFilePathAndName);
		try
		{
			file.createNewFile();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
			initFile(file);
		try
		{
			file.createNewFile();  //生成一个pdf文件
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		new CreatePdf(file).generatePDF(head,list,head.length);
		
		return saveFilePathAndName;
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println("begin");
		
		String [] head = {"name","sex","adress","height","age","jj"};
		
		List<User> list = new ArrayList<User>();
		User user1 = new User("zhangsan",1,1.1f,"北京","男","AA");
		User user2 = new User("lisi",22222,3.2f,"上海","女","BB");
		
		list.add(user1);
		list.add(user2);
		
		/*
		String filePath = new CreatePdf().generatePDFs(head,list);*/
		/*System.out.println(filePath);*/
		System.out.println("end");
	}

}
