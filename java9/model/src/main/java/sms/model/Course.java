package sms.model;

/**
 * @author tanglei
 * @date 18/10/23
 */
public class Course extends NameEntity {

    public Course() {
    }

    public Course(String id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Course{" + "name='" + name + '\'' + ", id='" + id + '\'' + '}';
    }
}
