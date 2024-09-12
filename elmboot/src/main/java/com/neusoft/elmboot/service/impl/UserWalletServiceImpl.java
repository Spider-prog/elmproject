package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.UserWalletMapper;
import com.neusoft.elmboot.po.UserWallet;
import com.neusoft.elmboot.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWalletServiceImpl implements UserWalletService {
    @Autowired
    private UserWalletMapper userWalletMapper;

    @Override
    public UserWallet getUserWalletByIdByPass(UserWallet userWallet) {
        return userWalletMapper.getUserWalletByIdByPass(userWallet);
    }

    @Override
    public int saveWalletPassword(UserWallet userWallet) {
        return userWalletMapper.saveWalletPassword(userWallet);
    }

    @Override
    public int updateCredit(String userId) {
        return userWalletMapper.updateCredit(userId);
    }

    @Override
    public Double getCreditByUserId(String userId) {return userWalletMapper.getCreditByUserId(userId);}

    @Override
    public String getWalletSaltById(String userId)
    {
        return userWalletMapper.getWalletSaltById(userId);
    }
}
