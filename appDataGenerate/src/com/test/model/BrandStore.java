package com.test.model;

import java.util.Date;
import java.util.List;

/**
 * 品牌库
 * @author zcr
 *
 */
public class BrandStore
{
	private int id;
	private String name;
	private int has_barcode;
	private String barcode_format;
	private String barcode;
	private String homepage;
	private String regions;
	private String other_stores;
	private String typos;
	private String logo;
	private String android_banner_url;
	private String ios_banner_url;
	private String ios_logo_url;
	private String background_color;
	private Date add_dates;
	private Date last_edit_dates;
	private int status;
	private String download_message;
	
	
	private List<Region>  regionList;
	
	
	
	
	
	
	@Override
	public String toString()
	{
	/*	System.out.println("list是否为空："+(null == regionList));*/
		// TODO Auto-generated method stub
		return "id=" +id + ", name=" +name + ",has_barcode =" +has_barcode + ", barcode_format=" + barcode_format+ ",barcode =" + barcode+ ",homepage ="
				 +homepage + ", regions=" +regions + ", other_stores=" +other_stores + ",typos =" +typos + ",logo ="
				  +logo + ",android_banner_url=" +android_banner_url + ",ios_banner_url =" +ios_banner_url + ", ios_logo_url=" + ios_logo_url+ ", background_color="
				   +background_color + ",add_dates =" + add_dates + " ，地域=" +regionList.toString() ;
	}
	
	
	
	public BrandStore(String name, int has_barcode,String barcode_format,String barcode,String homepage,String regions,
			String other_stores,String typos,String logo,String android_banner_url,String ios_banner_url,
			String ios_logo_url,String background_color/*,String status*/,String download_message)
	{
		this.name = name;
		this.has_barcode = has_barcode;
		this.barcode_format = barcode_format;
		this.barcode = barcode;
		this.homepage = homepage;
		this.regions = regions;
		this.other_stores = other_stores;
		this.typos = typos;
		this.logo = logo;
		this.android_banner_url = android_banner_url;
		this.ios_banner_url = ios_banner_url;
		this.ios_logo_url = ios_logo_url;
		this.background_color = background_color;
		this.download_message = download_message;
	}
	
	public BrandStore()
	{
		
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
	public int getHas_barcode()
	{
		return has_barcode;
	}
	public void setHas_barcode(int has_barcode)
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
	public String getBarcode()
	{
		return barcode;
	}
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}
	public String getHomepage()
	{
		return homepage;
	}
	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}
	public String getRegions()
	{
		return regions;
	}
	public void setRegions(String regions)
	{
		this.regions = regions;
	}
	public String getOther_stores()
	{
		return other_stores;
	}
	public void setOther_stores(String other_stores)
	{
		this.other_stores = other_stores;
	}
	public String getTypos()
	{
		return typos;
	}
	public void setTypos(String typos)
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
	public String getBackground_color()
	{
		return background_color;
	}
	public void setBackground_color(String background_color)
	{
		this.background_color = background_color;
	}
	public Date getAdd_dates()
	{
		return add_dates;
	}
	public void setAdd_dates(Date add_dates)
	{
		this.add_dates = add_dates;
	}
	public Date getLast_edit_dates()
	{
		return last_edit_dates;
	}
	public void setLast_edit_dates(Date last_edit_dates)
	{
		this.last_edit_dates = last_edit_dates;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getDownload_message()
	{
		return download_message;
	}

	public void setDownload_message(String download_message)
	{
		this.download_message = download_message;
	}

	public List<Region> getRegionList()
	{
		return regionList;
	}

	public void setRegionList(List<Region> regionList)
	{
		this.regionList = regionList;
	}
	

	
}
