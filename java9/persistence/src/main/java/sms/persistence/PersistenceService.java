package sms.persistence;

import sms.model.Entity;

import java.util.List;
import java.util.Optional;

/**
 * @author tanglei
 * @date 18/10/29
 */
public interface PersistenceService {

    <T extends Entity> List<T> list(Class<T> type);

    <T extends Entity> Optional<T> get(Class<T> type, String id);

    void save(Entity entity);

    <T extends Entity> void delete(Class<T> type, String id);
}
