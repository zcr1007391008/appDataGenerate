package com.test.mapper;

import com.test.model.GenerateFormat;
import com.test.model.GenerateUser;
import com.test.model.GeneratedData;

public interface GenerateDataMapper
{
	public void addGenerateAndGetPrimayKey(GenerateUser generateUser);
	public void addGenerateFormatAndGetPrimaryKey(GenerateFormat generateFormat);
	public void addGeneratedData(GeneratedData generatedData);
	
	public void addGenrateDataAndGetPrimayKey(GenerateUser generateUser);
}
