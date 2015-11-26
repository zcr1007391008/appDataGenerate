package com.test.model;

public class GeneratedData
{
	private int id;
	private String json_data;
	private int row_num;
	private int data_format_id;
	
	public GeneratedData()
	{
		
	}
	
	public GeneratedData(String json_data,int row_num,int data_format_id)
	{
		this.json_data = json_data;
		this.row_num = row_num;
		this.data_format_id = data_format_id;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getJson_data()
	{
		return json_data;
	}

	public void setJson_data(String json_data)
	{
		this.json_data = json_data;
	}

	public int getRow_num()
	{
		return row_num;
	}
	public void setRow_num(int row_num)
	{
		this.row_num = row_num;
	}
	public int getData_format_id()
	{
		return data_format_id;
	}
	public void setData_format_id(int data_format_id)
	{
		this.data_format_id = data_format_id;
	}
}
