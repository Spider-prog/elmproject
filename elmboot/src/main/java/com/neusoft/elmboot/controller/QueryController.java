package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.mapper.QueryMapper;
import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/QueryController")
public class QueryController {
    @Autowired
    private QueryService queryService;

    //实现搜索引擎的模糊查询
    @RequestMapping("/listBusinessByBusinessName")
    public List<Business> listBusinessByBusinessName(String businessOrFoodName) {
        return queryService.listBusinessByBusinessName(businessOrFoodName);
    }
}
