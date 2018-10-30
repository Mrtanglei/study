/**
 * @author tanglei
 * @date 18/10/29
 */
module sms.filestore {
    exports sms.filestore;
    requires sms.model;
    requires sms.persistence;
    provides sms.persistence.PersistenceService with sms.filestore.PersistenceServiceImpl;
}