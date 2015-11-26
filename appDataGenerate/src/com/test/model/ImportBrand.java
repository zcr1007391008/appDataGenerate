package com.test.model;

import java.util.List;

public class ImportBrand
{
	private int id;
	private String name;
	private String has_barcode;
	private String barcode_format;
	private String homepage;
	private List<String> regions;
	private List<String> other_stores;
	private List<String> typos;
	private String logo;
	private String android_banner_url;
	private String ios_banner_url;
	private String ios_logo_url;
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "id=" + id + ",name = " + name + ",has_barcode = " + has_barcode + ",barcode_format=" + barcode_format +
				",homepage =" + homepage + ",regions = " + regions +",logo = " + logo +",android_banner_url = " + android_banner_url +
				",ios_banner_url=" + ios_banner_url + ",ios_logo_url = " + ios_logo_url;
	}
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getHas_barcode()
	{
		return has_barcode;
	}
	public void setHas_barcode(String has_barcode)
	{
		this.has_barcode = has_barcode;
	}
	public String getBarcode_format()
	{
		return barcode_format;
	}
	public void setBarcode_format(String barcode_format)
	{
		this.barcode_format = barcode_format;
	}
	public String getHomepage()
	{
		return homepage;
	}
	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}
	
	
	public List<String> getRegions()
	{
		return regions;
	}


	public void setRegions(List<String> regions)
	{
		this.regions = regions;
	}


	public List<String> getOther_stores()
	{
		return other_stores;
	}
	public void setOther_stores(List<String> other_stores)
	{
		this.other_stores = other_stores;
	}
	public List<String> getTypos()
	{
		return typos;
	}
	public void setTypos(List<String> typos)
	{
		this.typos = typos;
	}
	public String getLogo()
	{
		return logo;
	}
	public void setLogo(String logo)
	{
		this.logo = logo;
	}
	public String getAndroid_banner_url()
	{
		return android_banner_url;
	}
	public void setAndroid_banner_url(String android_banner_url)
	{
		this.android_banner_url = android_banner_url;
	}
	public String getIos_banner_url()
	{
		return ios_banner_url;
	}
	public void setIos_banner_url(String ios_banner_url)
	{
		this.ios_banner_url = ios_banner_url;
	}
	public String getIos_logo_url()
	{
		return ios_logo_url;
	}
	public void setIos_logo_url(String ios_logo_url)
	{
		this.ios_logo_url = ios_logo_url;
	}
}
