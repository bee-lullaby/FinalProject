package vn.edu.fpt.timetabling.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_semester")
public class TeacherSemester {
	@Id
	@Column(name = "teacher_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherSemesterId;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacherSemester")
	List<TeacherCourseSemester> teacherCourseSemester = new ArrayList<TeacherCourseSemester>();

	/**
	 * 
	 */
	public TeacherSemester() {
		super();
	}

	/**
	 * @param teacherSemesterId
	 * @param teacher
	 * @param semester
	 */
	public TeacherSemester(int teacherSemesterId, Teacher teacher,
			Semester semester) {
		super();
		this.teacherSemesterId = teacherSemesterId;
		this.teacher = teacher;
		this.semester = semester;
	}

	/**
	 * @return the teacherSemesterId
	 */
	public int getTeacherSemesterId() {
		return teacherSemesterId;
	}

	/**
	 * @param teacherSemesterId
	 *            the teacherSemesterId to set
	 */
	public void setTeacherSemesterId(int teacherSemesterId) {
		this.teacherSemesterId = teacherSemesterId;
	}

	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the semester
	 */
	public Semester getSemester() {
		return semester;
	}

	/**
	 * @param semester
	 *            the semester to set
	 */
	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	/**
	 * @return the teacherCourseSemester
	 */
	public List<TeacherCourseSemester> getTeacherCourseSemester() {
		return teacherCourseSemester;
	}

	/**
	 * @param teacherCourseSemester
	 *            the teacherCourseSemester to set
	 */
	public void setTeacherCourseSemester(
			List<TeacherCourseSemester> teacherCourseSemester) {
		this.teacherCourseSemester = teacherCourseSemester;
	}

	public List<Course> getListCoursesOfTeacher() {
		List<Course> courses = new ArrayList<Course>();
		for (TeacherCourseSemester tcs : getTeacherCourseSemester()) {
			courses.add(tcs.getCourseSemester().getCourse());
		}
		return courses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TeacherSemester [teacherSemesterId=" + teacherSemesterId
				+ ", teacher=" + teacher + ", semester=" + semester + "]";
	}

}
