package entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author cvoinea
 */
@Embeddable
public class TeacherDetails {

    @ManyToMany
    private List<Teacher> teachers;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
