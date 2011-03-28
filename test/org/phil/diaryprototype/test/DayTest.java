package org.phil.diaryprototype.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.phil.diaryprototype.Appointment;
import org.phil.diaryprototype.Day;

public class DayTest {
		
	@Test
	public void testAppointmentsSize() {
		Day day = new Day(1);
		assertEquals(Day.MAX_APPOINTMENTS_PER_DAY, day.getAppointmentsLength());
	}
	
	@Test
	public void testFindSpaceEmpty() {
		Day day = new Day(1);
		int actual = day.findSpace(new Appointment("" , 1));
		assertEquals(Day.START_OF_DAY, actual);
	}
	
	@Test
	public void testFindSpaceFull() {
		Day day = new Day(1);
		day = fillDay(day);
		int actual = day.findSpace(new Appointment("", 1));
		assertEquals(-1, actual);		
	}

	@Test
	public void testMakeAptFirst() {
		Day day = new Day(1);
		boolean actual = day.makeAppointment(Day.START_OF_DAY, new Appointment("Test", 1));
		assertTrue(actual);
		
		/*
		 * Need to assert that the new appointment at the start of the day 
		 * refers to the newly created appointment. 
		 * Appointment actual = day.getAppointment(Day.START_OF_DAY);
		 * Appointment expected = new Appointment("Test", 1);
		 * assertSame(expected, actual);
		*/
	}
	
	@Test
	public void testMakeAptLast() {
		Day day = new Day(1);
		boolean actual = day.makeAppointment(Day.FINAL_APPOINTMENT_TIME, new Appointment("Test", 1));
		assertTrue(actual);
	}
		
	@Test 
	public void testMakeAptLowerFail() {
		Day day = new Day(1);
		boolean actual = day.makeAppointment(Day.START_OF_DAY - 1, new Appointment("Test", 1));
		assertFalse(actual);
	}
	
	@Test
	public void testMakeAptUpperFail() {
		Day day = new Day(1);
		boolean actual = day.makeAppointment(Day.FINAL_APPOINTMENT_TIME+1, new Appointment("Test", 1));
		assertFalse(actual);
	}

	@Test
	public void testShowAppointmentsEmpty() {
		Day day = new Day(1);
		StringBuilder sb = new StringBuilder();
		sb.append("=== Day " + day.getDayNumber() + " ===\n");
        int time = Day.START_OF_DAY;
        for(int i = 0; i < Day.MAX_APPOINTMENTS_PER_DAY; i++) {
            sb.append(time + ": \n");
            time++;
        }
		StringBuilder actual = day.showAppointments();
		assertEquals(sb.toString(), actual.toString());
	}
	
	@Test
	public void testShowAppointmentsFull()  {
		Day day = new Day(1);
		day = fillDay(day);
		StringBuilder actual = day.showAppointments();
		
		StringBuilder expected = new StringBuilder();
		expected.append("=== Day " + day.getDayNumber() + " ===\n");
        int time = Day.START_OF_DAY;
        for(int i = 0; i < Day.MAX_APPOINTMENTS_PER_DAY; i++) {
            expected.append(time + ": Test " + time + "\n");
            time++;
        }
		assertEquals(expected.toString(), actual.toString());
	}
	
	private Day fillDay(Day day) {
		for (int time = Day.START_OF_DAY;
		time <= Day.FINAL_APPOINTMENT_TIME;
		time++)  {
			day.makeAppointment(
					time, new Appointment("Test " + time, 1));
		}
		return day;
	}


}
