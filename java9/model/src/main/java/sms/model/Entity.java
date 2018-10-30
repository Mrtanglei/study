package sms.model;

import java.io.Serializable;

/**
 * @author tanglei
 * @date 18/10/23
 */
public abstract class Entity implements Serializable {

    protected String id;

    public Entity() {
    }

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
