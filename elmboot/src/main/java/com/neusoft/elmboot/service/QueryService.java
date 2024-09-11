package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Business;

import java.util.List;

public interface QueryService {
    public List<Business> listBusinessByBusinessName(String businessOrFoodName);
}
