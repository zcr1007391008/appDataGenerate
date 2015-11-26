package com.test.until;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从字符串中截取出电话号码
 * @author zcr
 *
 */
public class CheckIfIsPhoneNumber 
{
	
	/*
	String regexp="^((\\d{3})|(\\d{3}\\-))?13[0-9]\\d{8}|15[89]\\d{8}";     //�ֻ�
	String regexp2 = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";//�绰
*/	
	/**
	 * 获得电话号码的正则表达式：包括固定电话和移动电话
	 * 符合规则的号码：
	 * 	1》、移动电话
	 * 		86+‘-’+11位电话号码
	 * 		86+11位正常的电话号码
	 * 		11位正常电话号码a
	 * 		(+86) + 11位电话号码
	 * 		(86) + 11位电话号码
	 * 	2》、固定电话
	 * 		区号 + ‘-’ + 固定电话  + ‘-’ + 分机号
	 * 		区号 + ‘-’ + 固定电话 
	 * 		区号 + 固定电话
	 * @return	电话号码的正则表达式
	 */
	public static String isPhoneRegexp()
	{
		String regexp = "";
		
	/*	(\\p{Space})?)*/
		/*(^(((\\(?\\+86\\-?\\))?)|(\\(?86\\-?\\)?)))?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8}*/
		/*
		(^((\\(?\\+86\\-?\\)?)|(\\(?86\\-?\\)?)))?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8}*/
		
		//�ֻ����������ʽ--����
		//String mobilePhoneRegexp = "(?:(?:(\\(\\+?86\\))?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|(?:\\+?86((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8}))";
		
		//�޷������ƥ��
		//String mobilePhoneRegexp = "(?:(\\(\\+?86\\))?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|(?:\\+?8{1}6{1}((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";
		
		//�������ƥ�䣬���޷���ɹ������ź͵绰����֮���пո�����
		String mobilePhoneRegexp = "(?:(\\(\\+?86\\))((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +     
				"(?:86-?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +
				"(?:((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";
		
		
		
		
		//	System.out.println("regexp = " + mobilePhoneRegexp);
		//�̶��绰������ʽ
		/* �Ѿ�������
		 * String landlinePhoneRegexp = "(?:(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";*/
		//�޷������ƥ��
		/*String landlinePhoneRegexp = "(?:(\\(\\+?86\\))?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?) |" +
									 "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";*/
		String landlinePhoneRegexp = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
				"(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
		
		
		
		

		regexp += "(?:" + mobilePhoneRegexp + "|" + landlinePhoneRegexp +")"; 
		//System.out.println("regexp = " + regexp);
		return regexp;
	}
	
	

	/**
	 * 从dataStr中获取出所有的电话号码（固话和移动电话），将其放入Set
	 * @param dataStr	待查找的字符串
	 * @param phoneSet	dataStr中的电话号码
	 */
	public static void getPhoneNumFromStrIntoSet(String dataStr,Set<String> phoneSet)
	{
		//��ù̶��绰���ƶ��绰��������ʽ
		String regexp = isPhoneRegexp();
		
		System.out.println("Regexp = " + regexp);
		
		Pattern pattern = Pattern.compile(regexp); 
		Matcher matcher = pattern.matcher(dataStr); 

		//�����ģʽƥ����������е���һ��������
	    while (matcher.find()) 
	    { 
	    	//��ȡ��֮ǰ���ҵ����ַ������������set��
	    	phoneSet.add(matcher.group());
	    } 
	    //System.out.println(phoneSet);
	}
	
	
	
	
	
	
	
	
	public static void getPhoneFromStr(String dataStr,Set<String> phoneSet)
	{
		String regexp = isPhoneRegexp();
		/*System.out.println(regexp);
		System.out.println("��ݣ�" + dataStr);
		Pattern pattern2 = Pattern.compile(regexp); 
		Matcher matcher2 = pattern2.matcher("0754-86694639"); 
		System.out.println(matcher2.matches());*/
		
		
		Pattern pattern = Pattern.compile(regexp); 
		Matcher matcher = pattern.matcher(dataStr); 
		    StringBuffer bf = new StringBuffer(64);
		  /*  System.out.println("ddd=" + matcher.find());*/
		    while (matcher.find()) { 
		    	System.out.println(matcher.group());
		      bf.append(matcher.group()).append(","); 
		    } 
		    int len = bf.length(); 
		    if (len > 0) { 
		      bf.deleteCharAt(len - 1); 
		    } 
		   
		System.out.println(bf.toString()); 
	}
	
	public static void main(String[] args) 
	{
		/*String dataStr = "(86)13522158842;�ж�˹̩;test2;13000002222;(+86)13111113313";*/
		String dataStr = "kaishi:0754-86694639;�ж�˹̩;test2;88888888;(+86)0754-86694639,1361004957913610049579,(86)13610049579,075486694639";
		Set<String> phoneSet = new HashSet<String>();
		getPhoneFromStr( dataStr,phoneSet);
		
		System.out.println("wanle");
		
		
		getPhoneNumFromStrIntoSet(dataStr,phoneSet);
		
	}
	
	
	
}
