package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.po.UserWallet;

public interface UserWalletService {
    public UserWallet getUserWalletByIdByPass(UserWallet userWallet);
    public int saveWalletPassword(UserWallet userWallet);
    public int updateCredit(String userId);
    public Double getCreditByUserId(String userId);
    public String getWalletSaltById(String userId);
}
