package vn.edu.fpt.timetabling.model;

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
@Table(name = "program_semester")
public class ProgramSemester {
	@Id
	@Column(name = "program_semester_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int programSemesterId;

	@ManyToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

	@Column(name = "current_semester")
	private int currentSemester;

	@ManyToOne
	@JoinColumn(name = "specialized_id")
	private Specialized specialized;

	@ManyToOne
	@JoinColumn(name = "detail_specialized_id")
	private Specialized detailSpecialized;

	@Column
	private int batch;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "programSemester", orphanRemoval = true)
	@OrderBy
	Set<ProgramSemesterDetail> programSemesterDetails = new LinkedHashSet<ProgramSemesterDetail>();

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	/**
	 * @return the detailSpecialized
	 */
	public Specialized getDetailSpecialized() {
		return detailSpecialized;
	}

	/**
	 * @param detailSpecialized
	 *            the detailSpecialized to set
	 */
	public void setDetailSpecialized(Specialized detailSpecialized) {
		this.detailSpecialized = detailSpecialized;
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
	public void setProgramSemesterDetails(Set<ProgramSemesterDetail> programSemesterDetails) {
		this.programSemesterDetails = programSemesterDetails;
	}

	/**
	 * 
	 */
	public ProgramSemester() {
		super();
	}

	/**
	 * @param programSemesterId
	 * @param semester
	 * @param currentSemester
	 * @param specialized
	 */
	public ProgramSemester(int programSemesterId, Semester semester, int currentSemester, Specialized specialized) {
		super();
		this.programSemesterId = programSemesterId;
		this.semester = semester;
		this.currentSemester = currentSemester;
		this.specialized = specialized;
	}

	/**
	 * @return the programSemesterId
	 */
	public int getProgramSemesterId() {
		return programSemesterId;
	}

	/**
	 * @param programSemesterId
	 *            the programSemesterId to set
	 */
	public void setProgramSemesterId(int programSemesterId) {
		this.programSemesterId = programSemesterId;
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
	 * @return the currentSemester
	 */
	public int getCurrentSemester() {
		return currentSemester;
	}

	/**
	 * @param currentSemester
	 *            the currentSemester to set
	 */
	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
	}

	/**
	 * @return the specialized
	 */
	public Specialized getSpecialized() {
		return specialized;
	}

	/**
	 * @param specialized
	 *            the specialized to set
	 */
	public void setSpecialized(Specialized specialized) {
		this.specialized = specialized;
	}

}
