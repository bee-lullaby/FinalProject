package vn.edu.fpt.timetabling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "time_table")
public class Timetable {

	@Id
	@Column(name = "time_table_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int timeTableId;

	@ManyToOne
	@JoinColumn(name = "class_course_semester_id")
	private ClassCourseSemester classCourseSemester;

	@Column(name = "date", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "slot")
	private int slot;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "teacher_semester_id")
	private TeacherSemester teacherSemester;

	/**
	 * 
	 */
	public Timetable() {
		super();
	}

	/**
	 * @param timeTableId
	 * @param classCourseSemester
	 * @param date
	 * @param slot
	 * @param room
	 * @param teacherSemester
	 */
	public Timetable(int timeTableId, ClassCourseSemester classCourseSemester, Date date, int slot, Room room,
			TeacherSemester teacherSemester) {
		super();
		this.timeTableId = timeTableId;
		this.classCourseSemester = classCourseSemester;
		this.date = date;
		this.slot = slot;
		this.room = room;
		this.teacherSemester = teacherSemester;
	}

	/**
	 * @return the classCourseSemester
	 */
	public ClassCourseSemester getClassCourseSemester() {
		return classCourseSemester;
	}

	/**
	 * @param classCourseSemester
	 *            the classCourseSemester to set
	 */
	public void setClassCourseSemester(ClassCourseSemester classCourseSemester) {
		this.classCourseSemester = classCourseSemester;
	}

	/**
	 * @return the timeTableId
	 */
	public int getTimeTableId() {
		return timeTableId;
	}

	/**
	 * @param timeTableId
	 *            the timeTableId to set
	 */
	public void setTimeTableId(int timeTableId) {
		this.timeTableId = timeTableId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * @param slot
	 *            the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the teacher
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeTable [timeTableId=" + timeTableId + ", date=" + date + ", slot=" + slot + ", room=" + room
				+ ", teacher=" + teacherSemester + "]";
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

	public boolean isSameTime(Timetable timetable) {
		if (this.date.equals(timetable.getDate()) && this.slot == timetable.getSlot()) {
			return true;
		}
		return false;
	}

}
