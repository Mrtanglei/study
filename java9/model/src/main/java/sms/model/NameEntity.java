package sms.model;

/**
 * @author tanglei
 * @date 18/10/23
 */
public abstract class NameEntity extends Entity {

    protected String name;

    public NameEntity() {
    }

    public NameEntity(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
