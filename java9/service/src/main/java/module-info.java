/**
 * @author tanglei
 * @date 18/10/23
 */
module sms.service {
    exports sms.service;
    //依赖其它模块，这种依赖是不可传递的
    requires sms.model;
    requires sms.persistence;
    //这种依赖是可可传递依赖（其它模块依赖sms.service，同时也就依赖了sms.model模块）
    //requires transitive sms.model;
    provides sms.service.StudentService with sms.service.impl.StudentServiceImpl;

    uses sms.persistence.PersistenceService;
}