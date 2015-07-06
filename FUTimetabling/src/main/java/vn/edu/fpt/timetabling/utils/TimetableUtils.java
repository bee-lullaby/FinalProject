package vn.edu.fpt.timetabling.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vn.edu.fpt.timetabling.model.Timetable;

public class TimetableUtils {

	public static Timetable containsTimetable(List<Timetable> list, Date date, int slot) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Timetable t : list) {
			if (t != null && sdf.format(t.getDate()).compareTo(sdf.format(date)) == 0 && t.getSlot() == slot) {
				return t;
			}
		}
		return null;
	}
	
	public static int findNumberSameDaySlot(List<Timetable> list, Date date, int slot) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int count = 0;
		for(Timetable t : list) {
			if (t != null && sdf.format(t.getDate()).compareTo(sdf.format(date)) == 0 && t.getSlot() == slot) {
				count++;
			}
		}
		return count;
	}
}
