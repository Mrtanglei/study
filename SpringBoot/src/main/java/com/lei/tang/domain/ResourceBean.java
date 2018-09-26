package com.lei.tang.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tanglei
 * @date 18/9/25
 *
 * 实体与配置文件映射
 */
@Configuration
//配置文件与实体对应的前后缀
@ConfigurationProperties(prefix = "com.lei.tang")
//配置文件路径
@PropertySource(value = "classpath:resource.properties",encoding = "utf-8")
@Getter
@Setter
public class ResourceBean {

    private String name;

    private int age;
}
