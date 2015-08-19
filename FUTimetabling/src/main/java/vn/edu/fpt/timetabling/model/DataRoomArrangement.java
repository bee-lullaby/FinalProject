package vn.edu.fpt.timetabling.model;

public class DataRoomArrangement {

	
	private ClassSemester classSemester;
	private boolean setRoomSuccessful;
	private int numberOfSlots;
	private int numberOfSlots_WereSetSuccessful;
	private String note;

	/**
	 * 
	 */
	public DataRoomArrangement() {
		super();
	}

	/**
	 * @param classSemester
	 * @param setRoomSuccessful
	 * @param numberOfSlots
	 * @param numberOfSlots_WereSetSuccessful
	 * @param note
	 */
	public DataRoomArrangement(ClassSemester classSemester,
			boolean setRoomSuccessful, int numberOfSlots,
			int numberOfSlots_WereSetSuccessful, String note) {
		super();
		this.classSemester = classSemester;
		this.setRoomSuccessful = setRoomSuccessful;
		this.numberOfSlots = numberOfSlots;
		this.numberOfSlots_WereSetSuccessful = numberOfSlots_WereSetSuccessful;
		this.note = note;
	}

	/**
	 * @return the classSemester
	 */
	public ClassSemester getClassSemester() {
		return classSemester;
	}

	/**
	 * @param classSemester
	 *            the classSemester to set
	 */
	public void setClassSemester(ClassSemester classSemester) {
		this.classSemester = classSemester;
	}

	/**
	 * @return the setRoomSuccessful
	 */
	public boolean isSetRoomSuccessful() {
		return setRoomSuccessful;
	}

	/**
	 * @param setRoomSuccessful
	 *            the setRoomSuccessful to set
	 */
	public void setSetRoomSuccessful(boolean setRoomSuccessful) {
		this.setRoomSuccessful = setRoomSuccessful;
	}

	/**
	 * @return the numberOfSlots
	 */
	public int getNumberOfSlots() {
		return numberOfSlots;
	}

	/**
	 * @param numberOfSlots
	 *            the numberOfSlots to set
	 */
	public void setNumberOfSlots(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
	}

	/**
	 * @return the numberOfSlots_WereSetSuccessful
	 */
	public int getNumberOfSlots_WereSetSuccessful() {
		return numberOfSlots_WereSetSuccessful;
	}

	/**
	 * @param numberOfSlots_WereSetSuccessful
	 *            the numberOfSlots_WereSetSuccessful to set
	 */
	public void setNumberOfSlots_WereSetSuccessful(
			int numberOfSlots_WereSetSuccessful) {
		this.numberOfSlots_WereSetSuccessful = numberOfSlots_WereSetSuccessful;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

}
