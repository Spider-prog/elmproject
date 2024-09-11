package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.Business;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QueryMapper {
    public List<Business> listBusinessByBusinessName(String businessOrFoodName);
}
