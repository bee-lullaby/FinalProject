package vn.edu.fpt.timetabling.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "course_semester")
public class CourseSemester implements Comparable<CourseSemester> {

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
	private Course courseCondition;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "courseSemester", orphanRemoval = true)
	@OrderBy
	private Set<TeacherCourseSemester> teacherCourseSemesters = new LinkedHashSet<TeacherCourseSemester>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "courseSemester", orphanRemoval = true)
	@OrderBy
	private Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "courseSemester", orphanRemoval = true)
	@OrderBy
	private Set<ProgramSemesterDetail> programSemesterDetails = new LinkedHashSet<ProgramSemesterDetail>();

	/**
	 * @return the teacherCourseSemesters
	 */
	public Set<TeacherCourseSemester> getTeacherCourseSemesters() {
		return teacherCourseSemesters;
	}

	/**
	 * @param teacherCourseSemesters
	 *            the teacherCourseSemesters to set
	 */
	public void setTeacherCourseSemesters(
			Set<TeacherCourseSemester> teacherCourseSemesters) {
		this.teacherCourseSemesters = teacherCourseSemesters;
	}

	/**
	 * @return the classCourseSemesters
	 */
	public Set<ClassCourseSemester> getClassCourseSemesters() {
		return classCourseSemesters;
	}

	/**
	 * @param classCourseSemesters
	 *            the classCourseSemesters to set
	 */
	public void setClassCourseSemesters(
			Set<ClassCourseSemester> classCourseSemesters) {
		this.classCourseSemesters = classCourseSemesters;
	}

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
		this.courseCondition = course_condition;
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
	 * @return the courseCondition
	 */
	public Course getCourseCondition() {
		return courseCondition;
	}

	/**
	 * @param courseCondition
	 *            the courseCondition to set
	 */
	public void setCourseCondition(Course courseCondition) {
		this.courseCondition = courseCondition;
	}

	/**
	 * @return the programSemesterDetails
	 */
	public Set<ProgramSemesterDetail> getProgramSemesterDetails() {
		return programSemesterDetails;
	}

	/**
	 * @param programSemesterDetails
	 *            the programSemesterDetails to set
	 */
	public void setProgramSemesterDetails(
			Set<ProgramSemesterDetail> programSemesterDetails) {
		this.programSemesterDetails = programSemesterDetails;
	}

	public Set<Teacher> getListTeacherOfCourse() {
		Set<Teacher> teachers = new HashSet<Teacher>();
		for (TeacherCourseSemester tcs : getTeacherCourseSemesters()) {
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
				+ slots + ", course_condition=" + courseCondition + "]";
	}

	@Override
	public int compareTo(CourseSemester courseSemester) {
		if (this.semester.compareTo(courseSemester.getSemester()) == 0) {
			return this.course.getCode().compareTo(
					courseSemester.getCourse().getCode());
		} else if (this.semester.compareTo(courseSemester.getSemester()) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
