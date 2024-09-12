package com.neusoft.elmboot.util;

import org.apache.commons.codec.digest.DigestUtils;
import static com.neusoft.elmboot.util.RamUtil.getRandomNumber;

public class MD5forDBUtil {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //test
    public static void main(String args[]) {
        String s = "123";
        System.out.println("原始：" + s);
        String salt = getRandomNumber(8);
        System.out.println("加密后："+ MD5forDBUtil.formPassToDBPass(s,salt));
        System.out.println("盐值："+salt);
    }
}

