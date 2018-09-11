package com.lei.tang.factory;

import java.util.Map;

/**
 * @author tanglei
 * @date 18/9/3 下午3:10
 */
public class Factory {

    public static Hair getHair(String key){
        Map<String, String> properties = new PropertiesReader().getProperties();
        try {
            Hair hair = (Hair) Class.forName(properties.get(key)).newInstance();
            return hair;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Hair getHairByClazz(Class clazz){
        try {
            Hair hair = (Hair) clazz.newInstance();
            return hair;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
