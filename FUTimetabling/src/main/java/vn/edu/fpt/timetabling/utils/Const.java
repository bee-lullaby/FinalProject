package vn.edu.fpt.timetabling.utils;

public class Const {
	public static final String URL = "http://localhost:8080/Timetabling";
	public static final String CLIENT_ID = "938120756299-8u3vpmb12jptc4jn8h5bdqftlurbfll9.apps.googleusercontent.com";
	public static final String CLIENT_SECRET = "dj70o3tz4Qeu42qL0oDhV7Nm";
	public static final String REDIRECT_URI = "postmessage";
	public static final String FPT_DOMAIN = "fpt.edu.vn";

	public class ClassType {
		public static final String SPECIALIZED = "Specialized";
		public static final String COURSE = "Course";
	}

	public class StudentNumber {
		public static final int MAX_DISPLAY_STUDENT = 50;
		public static final int MAX_NUMBER_OF_STUDENTS_IN_CLASS = 30;
		public static final int MIN_NUMBER_OF_STUDENTS_IN_CLASS = 15;
	}

	public static class Timetable {
		public static final int NUMBER_WEEKS_DIFF = 4;
		public static final int NUMBER_WEEKS_SIMILAR = 3;
		public static final int NUMBER_DAYS_IN_WEEK = 7;
		public static final int NUMBER_WORKING_DAYS_IN_WEEK = 5;
		public static final int NUMBER_WEEKS_BETWEEN_BLOCK = 3;
		public static final int NUMBER_SLOTS_PER_DAY = 6;
		public static final int NUMBER_WEEKS_PER_BLOCK = 6;
		public static final int[][] SLOT_TIME_MAP = { { 7, 30 }, { 9, 10 }, { 10, 50 }, { 12, 50 }, { 14, 30 },
				{ 16, 10 }, { 18, 05 }, { 19, 45 } };
	}
}
