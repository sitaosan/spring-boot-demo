package com.yxc.common.utils;
import java.util.List;

public class VerfyUtil {

    public static boolean isEmpty(String str)
    {
        if(null == str)
        {
            return true;
        }
        return str.isEmpty();
    }
    public static boolean isEmpty(List lst)
    {

        if(null == lst)
        {
            return true;
        }
        return lst.isEmpty();
    }
    public static boolean isNull(Object lst)
    {
        if(null == lst)
        {
            return true;
        }
        return false;
    }
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;

    }
}
