package vn.edu.fpt.timetabling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_course_student_semester")
public class ClassCourseStudentSemester {
	@Id
	@Column(name = "class_course_student_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classCourseStudentSemesterId;

	@ManyToOne
	@JoinColumn(name = "class_course_semester_id")
	private ClassCourseSemester classCourseSemester;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public ClassCourseStudentSemester(int classCourseStudentSemesterId, ClassCourseSemester classCourseSemester,
			Student student) {
		super();
		this.classCourseStudentSemesterId = classCourseStudentSemesterId;
		this.classCourseSemester = classCourseSemester;
		this.student = student;
	}

	public ClassCourseStudentSemester() {
		super();
	}

	public int getClassCourseStudentSemesterId() {
		return classCourseStudentSemesterId;
	}

	public void setClassCourseStudentSemesterId(int classCourseStudentSemesterId) {
		this.classCourseStudentSemesterId = classCourseStudentSemesterId;
	}

	public ClassCourseSemester getClassCourseSemester() {
		return classCourseSemester;
	}

	public void setClassCourseSemester(ClassCourseSemester classCourseSemester) {
		this.classCourseSemester = classCourseSemester;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
