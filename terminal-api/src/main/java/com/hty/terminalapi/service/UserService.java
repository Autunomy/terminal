package com.hty.terminalapi.service;

import java.util.Map;

public interface UserService {

    /***
     * 登陆
     * @param username
     * @param password
     * @return
     */
    Map<String, String> login(String username,String password);

    /***
     * 注册
     * @param username
     * @param password
     * @param confirmedPassword
     * @return
     */
    Map<String, String> register(String username, String password, String confirmedPassword);

    /***
     * 获取用户信息
     * @return
     */
    Map<String, String> getInfo();
}
