package com.test.model;

import java.util.Date;

/**
 * 生成和用户
 * @author owner
 *
 */
public class GenerateUser
{
	private int id ;
	private String username;
	private Date generate_date;
	private String file_name;
	
	
	

	public GenerateUser()
	{
		
	}
	
	public GenerateUser(String username,Date generate_date)
	{
		this.username = username;
		this.generate_date = generate_date;
	}
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public Date getGenerate_date()
	{
		return generate_date;
	}
	public void setGenerate_date(Date generate_date)
	{
		this.generate_date = generate_date;
	}
	
}
