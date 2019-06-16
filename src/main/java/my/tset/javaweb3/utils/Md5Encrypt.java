package my.tset.javaweb3.utils;

import org.springframework.util.DigestUtils;

public class Md5Encrypt {
    private static String key = "a6n3m21r";//secret key

    //加密
    public static String encrypt(String s){
        String value = key + s;
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    //判断值是否相等
    public static boolean isEqual(String secretWords, String trueValue){
        if (secretWords == null || trueValue == null){
            return false;
        }
        return secretWords.equals(encrypt(trueValue));
    }
}
