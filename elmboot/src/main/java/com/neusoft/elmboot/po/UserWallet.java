package com.neusoft.elmboot.po;

import lombok.Data;

@Data
public class UserWallet {

    private String userId;
    private String walletPassword;
    private Double credit;
    private String salt;

}
