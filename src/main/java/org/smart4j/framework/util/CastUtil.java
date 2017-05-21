package org.smart4j.framework.util;

/**
 * Created by niceyuanze on 17-5-19.
 */
public final class CastUtil {

    public static String castString(Object obj){
        return CastUtil.castString(obj,"");
    }

    public static String castString(Object obj, String defaultValue){
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj, 0);
    }

    public static double castDouble(Object obj, double defaultValue){
        double doubleValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if( StringUtil.isNotEmpty(strValue)){
                doubleValue = Double.parseDouble(strValue);
            }
        }
        return doubleValue;
    }

    public static long castLong(Object obj){
        return castLong(obj,0);
    }

    public static long castLong(Object obj, long defaultValue){
        long longValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if( StringUtil.isNotEmpty(strValue)){
                longValue = Long.parseLong(strValue);
            }
        }
        return longValue;
    }

    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    public static int castInt(Object obj, int defaultValue){
        int intValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if( StringUtil.isNotEmpty(strValue)){
                intValue = Integer.parseInt(strValue);
            }
        }
        return intValue;
    }


    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if( StringUtil.isNotEmpty(strValue)){
                booleanValue = Boolean.parseBoolean(strValue);
            }
        }
        return booleanValue;
    }





}
