package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by niceyuanze on 17-5-25.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {



    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
