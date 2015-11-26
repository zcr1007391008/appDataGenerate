package com.test.sevices;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.AccountCardMapper;
import com.test.model.AccountCard;

@Service
public class AccountCardService
{
	@Autowired
	private AccountCardMapper accountCardMapper; 
	
	public void insertAccountCard(String account_id,String card_id,String brand_id,String front_picture_url,
			String back_picture_url,String remark)
	{
		AccountCard accountCard = new AccountCard();
		if(StringUtils.isNotBlank(account_id))
		{
			accountCard.setAccount_id(Integer.parseInt(account_id));
		}
		if(StringUtils.isNotBlank(card_id))
		{
			accountCard.setCard_id(card_id);
		}
		if(StringUtils.isNotBlank(brand_id))
		{
			accountCard.setBrand_id(Integer.parseInt(brand_id));
		}
		if(StringUtils.isNotBlank(front_picture_url))
		{
			accountCard.setFront_picture_url(front_picture_url);
		}
		if(StringUtils.isNotBlank(back_picture_url))
		{
			accountCard.setBack_picture_url(back_picture_url);
		}
		if(StringUtils.isNotBlank(remark))
		{
			accountCard.setRemark(remark);
		}
		if(StringUtils.isNotBlank(remark))
		{
			accountCard.setRemark(remark);
		}
		
		Date nowDate = new Date();
		accountCard.setAdd_dates(nowDate);
		accountCard.setLast_edit_time(nowDate);
		
		accountCardMapper.insertAccountCard(accountCard);
		
	}
	
	public void deleteAccountCardByUserId(String user_id)
	{
		accountCardMapper.deleteAccountCardByUserId(Integer.parseInt(user_id));
	}
	
	public List<AccountCard> selectAccountCardInPage(String iCurrentPage,String iPageSize)
	{
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start",(Integer.parseInt(iCurrentPage) - 1) * Integer.parseInt(iPageSize));
		map.put("end",Integer.parseInt(iPageSize));
		return accountCardMapper.selectAccountCardInPage(map);
	}
			
			
	public String updateAccountCardByUserId(String user_id,String account_id,String card_id,String brand_id,String remark)
	{
		try
		{
			AccountCard accountCard = new AccountCard();
			if (null != user_id)
			{
				accountCard.setUser_id(Integer.parseInt(user_id));
			}
			if (null != account_id)
			{
				accountCard.setAccount_id(Integer.parseInt(account_id));
			}
			if (null != card_id)
			{
				accountCard.setCard_id(card_id);
			}
			if (null != brand_id)
			{
				accountCard.setBrand_id(Integer.parseInt(brand_id));
			}
			/*if(null != front_picture_url)
			{
				accountCard.setFront_picture_url(front_picture_url);
			}
			if(null != back_picture_url)
			{
				accountCard.setBack_picture_url(back_picture_url);
			}*/
			if (null != remark)
			{
				accountCard.setRemark(remark);
			}
			if (null != remark)
			{
				accountCard.setRemark(remark);
			}
			/*if(null != status)
			{
				accountCard.setStatus(Integer.parseInt(status));
			}*/
			accountCardMapper.updateAccountCardByUserId(accountCard);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "更新失败";
		}
		return "更新成功";
	}
	
	
	public void updateAccountCardWithPictureByUserId(String user_id,String account_id,String card_id,String brand_id,String remark,String front_picture_url,String back_picture_url)
	{
		AccountCard accountCard = new AccountCard();
		if (null != user_id)
		{
			accountCard.setUser_id(Integer.parseInt(user_id));
		}
		if (null != account_id)
		{
			accountCard.setAccount_id(Integer.parseInt(account_id));
		}
		if (null != card_id)
		{
			accountCard.setCard_id(card_id);
		}
		if (StringUtils.isNotBlank(brand_id))
		{
			accountCard.setBrand_id(Integer.parseInt(brand_id));
		}
		if(StringUtils.isNotBlank(front_picture_url))
		{
			accountCard.setFront_picture_url(front_picture_url);
		}
		if(StringUtils.isNotBlank(back_picture_url))
		{
			accountCard.setBack_picture_url(back_picture_url);
		}
		if (StringUtils.isNotBlank(remark))
		{
			accountCard.setRemark(remark);
		}
		if (StringUtils.isNotBlank(remark))
		{
			accountCard.setRemark(remark);
		}
		accountCard.setLast_edit_time(new Date());
		
		accountCardMapper.updateAccountCardWithPictureByUserId(accountCard);
		
		
	}
	
	/**
	 * 获得相应user_id的图片url
	 * @param user_id
	 * @return
	 */
	public Map selectAccountCardPictureUrl(String user_id)
	{
		return accountCardMapper.selectAccountCardPictureUrl(Integer.parseInt(user_id));
	}
	
	public void updateAccountCardPicture(Map map)
	{
		accountCardMapper.updateAccountCardPicture(map);
	}
	
	
	/**
	 * 查询出user_id相应的用户卡信息
	 * @param user_id
	 * @return
	 */
	public AccountCard selectAccountCardByCardID(int user_id)
	{
		return accountCardMapper.selectAccountCardByCardID(user_id);
	}
	
	
	public void addAccountCardUseJson(Map map)
	{
		accountCardMapper.addAccountCardUseJson(map);
	
	}
	
	
	
}
