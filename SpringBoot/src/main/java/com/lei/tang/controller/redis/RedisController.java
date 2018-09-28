package com.lei.tang.controller.redis;

import com.lei.tang.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author tanglei
 * @date 18/9/27
 */
@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @GetMapping(value = "/test")
    public String test() {
        log.info("in controller");
        User user = new User();
        user.setName("aaaaa");
        redisTemplate.opsForValue().set("sss", user, 20, TimeUnit.SECONDS);

        return (User.class.cast(redisTemplate.opsForValue().get("sss"))).toString();
    }
}
