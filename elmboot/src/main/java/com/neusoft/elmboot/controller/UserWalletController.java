package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.UserWallet;
import com.neusoft.elmboot.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.neusoft.elmboot.util.MD5forDBUtil.formPassToDBPass;
import static com.neusoft.elmboot.util.RamUtil.getRandomNumber;

@RestController
@RequestMapping("/userWalletController")
public class UserWalletController {
    @Autowired
    private UserWalletService userWalletService;

    //先用userId获取盐值，在进行单向加密后对比数据库
    @RequestMapping("/getUserWalletByIdByPass")
    public UserWallet getUserByIdByPass(UserWallet userWallet) throws Exception{
        String salt = userWalletService.getWalletSaltById(userWallet.getUserId());
        userWallet.setSalt(salt);
        userWallet.setWalletPassword(formPassToDBPass(userWallet.getWalletPassword(),userWallet.getSalt()));
        return userWalletService.getUserWalletByIdByPass(userWallet);
    }

    @RequestMapping("/saveWalletPassword")
    public int saveUser(UserWallet userWallet) throws Exception{
        String salt = getRandomNumber(8);
        userWallet.setSalt(salt);
        userWallet.setWalletPassword(formPassToDBPass(userWallet.getWalletPassword(),userWallet.getSalt()));
        return userWalletService.saveWalletPassword(userWallet);
    }

    @RequestMapping("/updateCredit")
    public Double updateCredit(String userId) throws Exception{
        userWalletService.updateCredit(userId);
        return userWalletService.getCreditByUserId(userId);
    }
}
