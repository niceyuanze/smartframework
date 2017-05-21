package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by niceyuanze on 17-5-18.
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        if( str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
