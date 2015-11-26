package com.test.model;

public class User 
{
    private String name;
    private int age ;
    private float height;
    private String adress;
    private String sex;
    private String jj;
    
    public String getJj()
	{
		return jj;
	}

	public void setJj(String jj)
	{
		this.jj = jj;
	}

	public User()
    {
	
    }

    public User(String name,int age,float height,String adress,String sex,String jj)
    {
		this.name = name;
		this.age = age;
		this.height = height;
		this.adress = adress;
		this.sex = sex;
		this.jj = jj;
    }
    
    public String getAdress()
	{
		return adress;
	}

	public void setAdress(String adress)
	{
		this.adress = adress;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    
    
}
