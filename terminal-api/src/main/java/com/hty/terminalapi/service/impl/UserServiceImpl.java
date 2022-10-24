package com.hty.terminalapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hty.terminalapi.dao.UserMapper;
import com.hty.terminalapi.pojo.User;
import com.hty.terminalapi.service.UserService;
import com.hty.terminalapi.utils.JwtUtil;
import com.hty.terminalapi.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> login(String username, String password) {
        Map<String,String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();

        String jwt = JwtUtil.createJWT(user.getId().toString());

        map.put("message","success");
        map.put("token",jwt);
        return map;
    }

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String,String> map = new HashMap<>();
        if(username == null){
            map.put("message","用户名不能为空");
            return map;
        }

        if(password == null || confirmedPassword == null){
            map.put("message","密码不能为空");
            return map;
        }

        //去掉username首尾的空格
        username = username.trim();
        if(username.length() == 0){
            map.put("message","用户名不能为空");
            return map;
        }

        if(password.length() == 0 || confirmedPassword.length() == 0){
            map.put("message","密码不能为空");
            return map;
        }

        if(username.length() > 100){
            map.put("message","用户名长度不能大于100");
            return map;
        }

        if(password.length()>100 || confirmedPassword.length() > 100){
            map.put("message","密码长度不能大于100");
            return map;
        }

        if(!password.equals(confirmedPassword)){
            map.put("message","两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("message","用户名已存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        int rows = userMapper.insert(user);

        if(rows == 1){
            map.put("message","success");
        }else{
            map.put("message","注册失败");
        }

        return map;
    }

    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Map<String,String> map = new HashMap<>();
        map.put("message","success");
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        return map;
    }
}
