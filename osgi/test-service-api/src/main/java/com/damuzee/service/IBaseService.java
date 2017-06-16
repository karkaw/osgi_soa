package com.damuzee.service;

import org.osgi.service.component.ComponentContext;

/**
 * Created by karka.w on 2016/8/7.
 */
public interface IBaseService {

    /**
     * 简单Hello Word
     *
     * @param name
     * @return
     */
    String getHelloWord(String name);

    /**
     * 获取用户
     *
     * @param name
     * @param age
     * @return
     */
    String getUser(String name, int age);

    void bind(ComponentContext content);

    void unbind(ComponentContext content);
}
