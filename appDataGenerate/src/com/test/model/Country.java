package com.test.model;

import java.io.Serializable;

/**
 * 国家
 * @author owner
 *
 */
public class Country implements Serializable
{
	private String country_name;
	private int country_id;
	
	public Country()
	{
		// TODO Auto-generated constructor stub
	}
	public Country(String country_name,int country_id)
	{
		this.country_id = country_id;
		this.country_name = country_name;
	}
	
	@Override
	public String toString() {
		return "country_name = " + country_name + ",country_id=" + country_id;
	}
	
	
	public String getCountry_name()
	{
		return country_name;
	}
	public void setCountry_name(String country_name)
	{
		this.country_name = country_name;
	}
	public int getCountry_id()
	{
		return country_id;
	}
	public void setCountry_id(int country_id)
	{
		this.country_id = country_id;
	}
	
}
