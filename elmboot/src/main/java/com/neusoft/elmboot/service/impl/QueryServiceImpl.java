package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.QueryMapper;
import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.QueryService;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryMapper queryMapper;

    @Override
    public List<Business> listBusinessByBusinessName(String businessOrFoodName) {
        //对于用户可能存在的多条件查询，将所有条件分开查询
        String[] index = businessOrFoodName.split(" |,|、");
        List<Business> result = new ArrayList<Business>();
        for(int i = 0; i < index.length; i++) {
            result.addAll(queryMapper.listBusinessByBusinessName(index[i]));
        }
        return result;
    }
}
