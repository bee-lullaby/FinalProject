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
@Table(name = "class_course_semester_merge")
public class ClassCourseSemesterMerge {
	@Id
	@Column(name = "class_course_semester_merge_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classCourseSemesterMergeId;

	@ManyToOne
	@JoinColumn(name = "class_course_semester_id_1")
	private ClassCourseSemester classCourseSemester1;

	@ManyToOne
	@JoinColumn(name = "class_course_semester_id_2")
	private ClassCourseSemester classCourseSemester2;

	public ClassCourseSemesterMerge() {
		super();
	}

	public ClassCourseSemesterMerge(int classCourseSemesterMergeId, ClassCourseSemester classCourseSemester1,
			ClassCourseSemester classCourseSemester2) {
		super();
		this.classCourseSemesterMergeId = classCourseSemesterMergeId;
		this.classCourseSemester1 = classCourseSemester1;
		this.classCourseSemester2 = classCourseSemester2;
	}

	public int getClassCourseSemesterMergeId() {
		return classCourseSemesterMergeId;
	}

	public void setClassCourseSemesterMergeId(int classCourseSemesterMergeId) {
		this.classCourseSemesterMergeId = classCourseSemesterMergeId;
	}

	public ClassCourseSemester getClassCourseSemester1() {
		return classCourseSemester1;
	}

	public void setClassCourseSemester1(ClassCourseSemester classCourseSemester1) {
		this.classCourseSemester1 = classCourseSemester1;
	}

	public ClassCourseSemester getClassCourseSemester2() {
		return classCourseSemester2;
	}

	public void setClassCourseSemester2(ClassCourseSemester classCourseSemester2) {
		this.classCourseSemester2 = classCourseSemester2;
	}
}
