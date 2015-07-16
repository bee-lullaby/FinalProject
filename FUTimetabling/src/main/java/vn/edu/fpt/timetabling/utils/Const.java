package vn.edu.fpt.timetabling.utils;

public class Const {
	public static final String URL = "http://localhost:8080/Timetabling/";
	public static final String CLIENT_ID = "938120756299-8u3vpmb12jptc4jn8h5bdqftlurbfll9.apps.googleusercontent.com";
	public static final String CLIENT_SECRET = "dj70o3tz4Qeu42qL0oDhV7Nm";
	public static final String REDIRECT_URI = "postmessage";
	public static final String FPT_DOMAIN = "fpt.edu.vn";

	public class ClassType {
		public static final String SPECIALIZED = "Specialized";
		public static final String COURSE = "Course";
	}

	public class StudentNumber {
		public static final int OPTIMAL_NUMBER_OF_STUDENTS_IN_CLASS = 25;
		public static final int MAX_NUMBER_OF_STUDENTS_IN_CLASS = 30;
	}
}
