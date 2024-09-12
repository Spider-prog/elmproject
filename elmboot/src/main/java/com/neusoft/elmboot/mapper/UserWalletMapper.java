package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.UserWallet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserWalletMapper {
    @Select("select * from userwallet where userId=#{userId} and walletPassword=#{walletPassword}")
    public UserWallet getUserWalletByIdByPass(UserWallet userWallet);

    @Insert("insert into userwallet values(#{userId},#{walletPassword},#{credit},#{salt})")
    public int saveWalletPassword(UserWallet userWallet);

    @Update("UPDATE Credit from userwallet where userId=#{userId}")
    public int updateCredit(String userId);

    @Select("select credit from userwallet where userId=#{userId}")
    public Double getCreditByUserId(String userId);

    @Select("select salt from userwallet where userId=#{userId}")
    public String getWalletSaltById(String userId);
}
