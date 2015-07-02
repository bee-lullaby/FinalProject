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
@Table(name = "program_semester_detail")
public class ProgramSemesterDetail {
	@Id
	@Column(name = "program_semester_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int programSemesterDetailId;

	@ManyToOne
	@JoinColumn(name = "program_semester_id")
	private ProgramSemester programSemester;

	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private CourseSemester courseSemester;

	/**
	 * 
	 */
	public ProgramSemesterDetail() {
		super();
	}

	/**
	 * @param programSemesterDetailId
	 * @param programSemester
	 * @param courseSemester
	 */
	public ProgramSemesterDetail(int programSemesterDetailId,
			ProgramSemester programSemester, CourseSemester courseSemester) {
		super();
		this.programSemesterDetailId = programSemesterDetailId;
		this.programSemester = programSemester;
		this.courseSemester = courseSemester;
	}

	/**
	 * @return the programSemesterDetailId
	 */
	public int getProgramSemesterDetailId() {
		return programSemesterDetailId;
	}

	/**
	 * @param programSemesterDetailId
	 *            the programSemesterDetailId to set
	 */
	public void setProgramSemesterDetailId(int programSemesterDetailId) {
		this.programSemesterDetailId = programSemesterDetailId;
	}

	/**
	 * @return the programSemester
	 */
	public ProgramSemester getProgramSemester() {
		return programSemester;
	}

	/**
	 * @param programSemester
	 *            the programSemester to set
	 */
	public void setProgramSemester(ProgramSemester programSemester) {
		this.programSemester = programSemester;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return courseSemester.getCourse().getName();
	}

}
