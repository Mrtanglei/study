package sms.service.impl;

import sms.persistence.PersistenceService;

import java.util.Optional;
import java.util.ServiceLoader;

/**
 * @author tanglei
 * @date 18/10/29
 */
public class PersistenceLoder {

    public static PersistenceService persistenceService;

    static {
        if (persistenceService == null){
            //通过ServiceLoader加载PersistenceService返回的是所有实现类，findFirst方法返回第一个实现类
            Optional<PersistenceService> serviceOptional = ServiceLoader.load(PersistenceService.class).findFirst();
            if(serviceOptional.isPresent()){
                persistenceService = serviceOptional.get();
            }else{
                throw new RuntimeException("No persistence service");
            }
        }
    }
}
