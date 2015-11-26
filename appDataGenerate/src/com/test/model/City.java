package com.test.model;

import java.io.Serializable;

/**
 * 城市
 * @author owner
 *
 */
public class City implements Serializable
{
	private int city_id;
	private String city_name;
	private int country_id;
	
	public City()
	{
		
	}
	public City(int city_id,String city_name,int country_id)
	{
		this.city_id = city_id;
		this.city_name = city_name;
		this.country_id = country_id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "city_id = " + city_id + ",city_name=" + city_name + ",country_id=" + country_id;
	}
	
	public int getCity_id()
	{
		return city_id;
	}
	public void setCity_id(int city_id)
	{
		this.city_id = city_id;
	}
	public String getCity_name()
	{
		return city_name;
	}
	public void setCity_name(String city_name)
	{
		this.city_name = city_name;
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
