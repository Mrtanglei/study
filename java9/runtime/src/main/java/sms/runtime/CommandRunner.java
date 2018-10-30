package sms.runtime;

import sms.service.StudentService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tanglei
 * @date 18/10/30
 */
public class CommandRunner {

    //服务map
    private static Map<String,Object> services = new HashMap<>();
    //方法map
    private static Map<String,Map<String, Method>> methds = new HashMap<>();
    //服务对应的class
    private static Map<String,Class<?>> serviceTypes = Map.of("student", StudentService.class);

    public CommandRunner() {
        serviceTypes.forEach((type,clazz) -> {
            if(ServiceLoader.load(clazz).findFirst().isPresent()){
                Object obj = ServiceLoader.load(clazz).findFirst().get();
                services.put(type, obj);
                methds.put(type, Stream.of(clazz.getDeclaredMethods()).collect(Collectors.toMap(Method::getName,
                        Function.identity())));
            }
        });
    }

    /**
     *
     * @param service 服务的名称
     * @param task 方法的名称
     * @param args 方法的参数
     */
    public void run(String service, String task, List<Object> args){
        Object serviceObj = services.get(service);
        Method method = methds.get(service).get(task);
        try {
            Object invoke = method.invoke(serviceObj, args.toArray());
            System.out.println(invoke);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
