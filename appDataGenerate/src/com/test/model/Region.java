package com.test.model;

/**
 * 地域
 * @author 
 *
 */
public class Region
{
	private int city_id;
	private Country country;
	private City city;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "city_id=" + city_id + ",country=" +country + ",city=" + city;
	}
	
	
	public Region()
	{
		
	}
	
	public Region(int city_id,Country country,City city)
	{
		this.city_id = city_id;
		this.city = city;
		this.country = country;
	}
	
	
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
