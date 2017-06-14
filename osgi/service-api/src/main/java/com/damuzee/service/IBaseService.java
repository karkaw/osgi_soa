package com.damuzee.service;

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
    public String getHelloWord(String name);

    /**
     * 获取用户
     *
     * @param name
     * @param age
     * @return
     */
    public String getUser(String name,int age);
}
