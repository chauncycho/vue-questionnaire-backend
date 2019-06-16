package my.tset.javaweb3.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getRandomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
