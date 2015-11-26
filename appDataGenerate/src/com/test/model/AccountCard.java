package com.test.model;

import java.util.Date;

public class AccountCard
{
	private int user_id ;
	private int account_id ;
	private String card_id ;
	private int brand_id ;
	private String front_picture_url ;
	private String back_picture_url ;
	private String remark ;
	private Date add_dates ;
	private Date last_edit_time ;
	private int status ;
	
	@Override
	public String toString()
	{
		return "user_id=" + user_id + ",account_id=" + account_id  + ",brand_id = "+ brand_id + 
				" ,front_picture_url=" + front_picture_url + ",back_picture_url=" + back_picture_url +
				",remark=" + remark;
	}
	
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public int getAccount_id()
	{
		return account_id;
	}
	public void setAccount_id(int account_id)
	{
		this.account_id = account_id;
	}
	public String getCard_id()
	{
		return card_id;
	}
	public void setCard_id(String card_id)
	{
		this.card_id = card_id;
	}
	public int getBrand_id()
	{
		return brand_id;
	}
	public void setBrand_id(int brand_id)
	{
		this.brand_id = brand_id;
	}
	public String getFront_picture_url()
	{
		return front_picture_url;
	}
	public void setFront_picture_url(String front_picture_url)
	{
		this.front_picture_url = front_picture_url;
	}
	public String getBack_picture_url()
	{
		return back_picture_url;
	}
	public void setBack_picture_url(String back_picture_url)
	{
		this.back_picture_url = back_picture_url;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public Date getAdd_dates()
	{
		return add_dates;
	}
	public void setAdd_dates(Date add_dates)
	{
		this.add_dates = add_dates;
	}
	public Date getLast_edit_time()
	{
		return last_edit_time;
	}
	public void setLast_edit_time(Date last_edit_time)
	{
		this.last_edit_time = last_edit_time;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	
}
