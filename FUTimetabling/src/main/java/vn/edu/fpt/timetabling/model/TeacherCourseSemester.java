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
@Table(name = "teacher_course_semester")
public class TeacherCourseSemester {
	@Id
	@Column(name = "teacher_course_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacherCourseSemesterId;

	@ManyToOne
	@JoinColumn(name = "teacher_semester_id")
	private TeacherSemester teacherSemester;

	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private CourseSemester courseSemester;

	/**
	 * 
	 */
	public TeacherCourseSemester() {
		super();
	}

	/**
	 * @param teacherCourseSemesterId
	 * @param teacherSemester
	 * @param courseSemester
	 */
	public TeacherCourseSemester(int teacherCourseSemesterId,
			TeacherSemester teacherSemester, CourseSemester courseSemester) {
		super();
		this.teacherCourseSemesterId = teacherCourseSemesterId;
		this.teacherSemester = teacherSemester;
		this.courseSemester = courseSemester;
	}

	/**
	 * @return the teacherCourseSemesterId
	 */
	public int getTeacherCourseSemesterId() {
		return teacherCourseSemesterId;
	}

	/**
	 * @param teacherCourseSemesterId
	 *            the teacherCourseSemesterId to set
	 */
	public void setTeacherCourseSemesterId(int teacherCourseSemesterId) {
		this.teacherCourseSemesterId = teacherCourseSemesterId;
	}

	/**
	 * @return the teacherSemester
	 */
	public TeacherSemester getTeacherSemester() {
		return teacherSemester;
	}

	/**
	 * @param teacherSemester
	 *            the teacherSemester to set
	 */
	public void setTeacherSemester(TeacherSemester teacherSemester) {
		this.teacherSemester = teacherSemester;
	}

	/**
	 * @return the courseSemester
	 */
	public CourseSemester getCourseSemester() {
		return courseSemester;
	}

	/**
	 * @param courseSemester
	 *            the courseSemester to set
	 */
	public void setCourseSemester(CourseSemester courseSemester) {
		this.courseSemester = courseSemester;
	}

}
