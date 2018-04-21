package university_system.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="students")
public class Student extends Person{

    private double averageGrade;
    private int attendance;
    private Set<Course> enrolledCourses;

    @Column(name = "average_grade")
    public double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
    @Column(name = "attendance")
    public int getAttendance() {
        return this.attendance;
    }

    public void setAttendance(int attendace) {
        this.attendance = attendace;
    }

    @ManyToMany()
    @JoinTable(name ="student_courses",
    joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name ="course_id",referencedColumnName = "id"))
    public Set<Course> getEnrolledCourses() {
        return this.enrolledCourses;
    }

    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
