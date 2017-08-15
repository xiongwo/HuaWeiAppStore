package com.xiongwo.huaweiappstore.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Dagger2自定义作用域  Application
 * Created by 熊，我 on 2017/8/7.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
