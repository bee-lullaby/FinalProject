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
@Table(name = "course_semester")
public class CourseSemester {

	@Id
	@Column(name = "course_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseSemesterId;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

	@Column(name = "slots")
	private int slots;

	@ManyToOne
	@JoinColumn(name = "course_condition_id", referencedColumnName = "course_id")
	private Course course_condition;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "courseSemester", orphanRemoval = true)
	List<TeacherCourseSemester> teacherCourseSemester = new ArrayList<TeacherCourseSemester>();

	/**
	 * 
	 */
	public CourseSemester() {
		super();
	}

	/**
	 * @param courseSemesterId
	 * @param course
	 * @param semester
	 * @param slots
	 * @param course_condition
	 */
	public CourseSemester(int courseSemesterId, Course course,
			Semester semester, int slots, Course course_condition) {
		super();
		this.courseSemesterId = courseSemesterId;
		this.course = course;
		this.semester = semester;
		this.slots = slots;
		this.course_condition = course_condition;
	}

	/**
	 * @return the courseSemesterId
	 */
	public int getCourseSemesterId() {
		return courseSemesterId;
	}

	/**
	 * @param courseSemesterId
	 *            the courseSemesterId to set
	 */
	public void setCourseSemesterId(int courseSemesterId) {
		this.courseSemesterId = courseSemesterId;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
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
	 * @return the slots
	 */
	public int getSlots() {
		return slots;
	}

	/**
	 * @param slots
	 *            the slots to set
	 */
	public void setSlots(int slots) {
		this.slots = slots;
	}

	/**
	 * @return the course_condition
	 */
	public Course getCourse_condition() {
		return course_condition;
	}

	/**
	 * @param course_condition
	 *            the course_condition to set
	 */
	public void setCourse_condition(Course course_condition) {
		this.course_condition = course_condition;
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

	public List<Teacher> getListTeacherOfCourse() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		for (TeacherCourseSemester tcs : getTeacherCourseSemester()) {
			teachers.add(tcs.getTeacherSemester().getTeacher());
		}
		return teachers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CourseSemester [courseSemesterId=" + courseSemesterId
				+ ", course=" + course + ", semester=" + semester + ", slots="
				+ slots + ", course_condition=" + course_condition + "]";
	}

}
