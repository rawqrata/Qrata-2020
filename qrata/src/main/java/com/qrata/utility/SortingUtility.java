package com.qrata.utility;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.qrata.models.CommonEntity;

public class SortingUtility<T extends CommonEntity> implements Comparator<T>{
	public int compare(T obj1 , T obj2){
		String obj1Name = obj1.getSortName();
		String obj2Name = obj2.getSortName();
		if(!StringUtils.isEmpty(obj1Name) && !StringUtils.isEmpty(obj2Name))
			return obj1Name.toLowerCase().compareTo(obj2Name.toLowerCase());
		else
			return 1;
	}

}
