package vn.edu.fpt.timetabling.model;


public class DataRoomArrangement {
	private ClassSemester classSemester;
	private int morningClass;
	private int afternoonClass;
	/**
	 * 
	 */
	public DataRoomArrangement() {
		super();
	}
	/**
	 * @param classSemester
	 * @param morningClass
	 * @param afternoonClass
	 */
	public DataRoomArrangement(ClassSemester classSemester, int morningClass,
			int afternoonClass) {
		super();
		this.classSemester = classSemester;
		this.morningClass = morningClass;
		this.afternoonClass = afternoonClass;
	}
	/**
	 * @return the classSemester
	 */
	public ClassSemester getClassSemester() {
		return classSemester;
	}
	/**
	 * @param classSemester the classSemester to set
	 */
	public void setClassSemester(ClassSemester classSemester) {
		this.classSemester = classSemester;
	}
	/**
	 * @return the morningClass
	 */
	public int getMorningClass() {
		return morningClass;
	}
	/**
	 * @param morningClass the morningClass to set
	 */
	public void setMorningClass(int morningClass) {
		this.morningClass = morningClass;
	}
	/**
	 * @return the afternoonClass
	 */
	public int getAfternoonClass() {
		return afternoonClass;
	}
	/**
	 * @param afternoonClass the afternoonClass to set
	 */
	public void setAfternoonClass(int afternoonClass) {
		this.afternoonClass = afternoonClass;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataRoomArrangement [classSemester=" + classSemester
				+ ", morningClass=" + morningClass + ", afternoonClass="
				+ afternoonClass + "]";
	}

	
	

}
