package com.lei.tang.aop.service;

import com.lei.tang.aop.security.CurrentUserHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public void check(){
        if(!"admin".equals(CurrentUserHolder.get())){
            throw new RuntimeException("无权限");
        }
    }
}
