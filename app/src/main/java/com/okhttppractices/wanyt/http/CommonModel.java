package com.okhttppractices.wanyt.http;

/**
 * Created on 2016/7/4 15:06
 * <p/>
 * author wanyt
 * <p/>
 * Description:数据模型的基类，把响应解析成数据模型的类都要继承自这个类。
 */
public class CommonModel<T> {

    public String code;

    T t;

}
