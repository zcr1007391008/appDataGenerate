package com.test.model;

/**
 * 文件格式
 * @author zcr
 *
 */
public class GenerateFormat
{
	private int id;
	private String pattern;
	private int generate_id;
	
	public GenerateFormat()
	{
		
	}
	
	public GenerateFormat(String pattern,int generate_id)
	{
		this.pattern = pattern;
		this.generate_id = generate_id;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getPattern()
	{
		return pattern;
	}
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
	public int getGenerate_id()
	{
		return generate_id;
	}
	public void setGenerate_id(int generate_id)
	{
		this.generate_id = generate_id;
	}
}
