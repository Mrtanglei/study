package com.lei.tang.factory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author tanglei
 * @date 18/9/3 下午3:01
 */
public class PropertiesReader {

    public Map<String,String> getProperties(){
        Properties properties = new Properties();
        Map<String,String> map = new HashMap<>();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("factory.properties"));
            Enumeration<?> enumeration = properties.propertyNames();
            if(enumeration != null){
                while (enumeration.hasMoreElements()){
                    String key = (String) enumeration.nextElement();
                    String value = properties.getProperty(key);
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
