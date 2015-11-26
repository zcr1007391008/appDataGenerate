package com.test.mapper;

import java.util.List;
import java.util.Map;

import com.test.model.AccountCard;

public interface AccountCardMapper
{
	public void insertAccountCard(AccountCard accountCard);
	
	public void deleteAccountCardByUserId(int user_id);
	
	public List<AccountCard> selectAccountCardInPage(Map map);
	
	public void updateAccountCardByUserId(AccountCard accountCard);
	
	public Map selectAccountCardPictureUrl(int user_id);
	
	public void updateAccountCardPicture(Map map);
	
	public void updateAccountCardWithPictureByUserId(AccountCard accountCard);
	
	public AccountCard selectAccountCardByCardID(int user_id);
	
	public void addAccountCardUseJson(Map map);
}
