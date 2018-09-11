package com.lei.tang.aop.service;

import com.lei.tang.aop.security.AdminOnly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    AuthService authService;

    @AdminOnly
    public void addProduct(){
//        authService.check();
        log.info("in add");
    }

    @AdminOnly
    public void deleteProduct(){
//        authService.check();
        log.info("in delete");
    }
}
