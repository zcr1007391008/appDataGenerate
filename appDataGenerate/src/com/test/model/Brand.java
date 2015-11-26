package com.test.model;

import java.util.Date;

/**
 * 品牌
 * @author zcr
 *
 */
public class Brand
{
	private int brand_id ;
	private String logo_url ;
	private String brand_backgorund_color ;
	private int isExist_code;
	private String bar_code ;
	private String add_dates ;
	private String last_editTime ;
	
	@Override
	public String toString()
	{
		return "bind_id = " + brand_id + ",logo_url=" +logo_url + ",brand_backgorund_color=" + brand_backgorund_color
				+ ",isExist_code=" + isExist_code + ",bar_code="+ bar_code + ",add_dates="+add_dates
				+ ",last_editTime = " + last_editTime;
	}
	
	public int getBrand_id()
	{
		return brand_id;
	}
	public void setBrand_id(int brand_id)
	{
		this.brand_id = brand_id;
	}
	public String getLogo_url()
	{
		return logo_url;
	}
	public void setLogo_url(String logo_url)
	{
		this.logo_url = logo_url;
	}
	public String getBrand_backgorund_color()
	{
		return brand_backgorund_color;
	}
	public void setBrand_backgorund_color(String brand_backgorund_color)
	{
		this.brand_backgorund_color = brand_backgorund_color;
	}
	
	
	public int getIsExist_code()
	{
		return isExist_code;
	}
	public void setIsExist_code(int isExist_code)
	{
		this.isExist_code = isExist_code;
	}
	public String getBar_code()
	{
		return bar_code;
	}
	public void setBar_code(String bar_code)
	{
		this.bar_code = bar_code;
	}

	public String getAdd_dates()
	{
		return add_dates;
	}

	public void setAdd_dates(String add_dates)
	{
		this.add_dates = add_dates;
	}

	public String getLast_editTime()
	{
		return last_editTime;
	}

	public void setLast_editTime(String last_editTime)
	{
		this.last_editTime = last_editTime;
	}
	
	
	 
}
