package com.xiongwo.huaweiappstore.di.scope;

import javax.inject.Qualifier;

/**
 * Context类型  Dagger2自定义限定符
 * Created by 熊，我 on 2017/8/7.
 */
@Qualifier
public @interface ContextLife {
    String value() default "";
}
