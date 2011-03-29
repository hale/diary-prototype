package org.phil.diaryprototype.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.phil.diaryprototype.Appointment;
import org.phil.diaryprototype.Day;

public class OneHourDayTest {
	
	private Day day;
		
	@Test
	public void testAppointmentsSize() {
		newDay();
		assertEquals(Day.MAX_APPOINTMENTS_PER_DAY, day.getAppointmentsLength());
	}
	
	@Test
	public void testFindSpaceEmpty() {
		newDay();
		int actual = day.findSpace(new Appointment("" , 1));
		assertEquals(Day.START_OF_DAY, actual);
	}
	
	@Test
	public void testFindSpaceFull() {
		newDay();
		day = fillDay(day);
		int actual = day.findSpace(new Appointment("", 1));
		assertEquals(-1, actual);		
	}
	
	@Test
	public void makeEarlyAppointment() {
		newDay();
		assertTrue(day.makeAppointment(Day.START_OF_DAY, new Appointment("First", 1)));
	}
	
	@Test
	public void makeMidAppointment() {
		newDay();
		Random random = new Random();
		// Make an appointment somewhere between the beginning and end of the day
		int time = random.nextInt(Day.FINAL_APPOINTMENT_TIME - Day.START_OF_DAY) + Day.START_OF_DAY + 1;
		assertTrue(day.makeAppointment(time, new Appointment("Test", 1)));
	}
	
	@Test
	public void makeLateAppointment() {
		newDay();
		assertTrue(day.makeAppointment(Day.FINAL_APPOINTMENT_TIME, new Appointment("Third", 1)));
	}

	@Test 
	public void testMakeAptLowerFail() {
		newDay();
		boolean actual = day.makeAppointment(Day.START_OF_DAY - 1, new Appointment("Test", 1));
		assertFalse(actual);
	}
	
	@Test
	public void testMakeAptUpperFail() {
		newDay();
		boolean actual = day.makeAppointment(Day.FINAL_APPOINTMENT_TIME+1, new Appointment("Test", 1));
		assertFalse(actual);
	}

	@Test
	public void testShowAppointmentsEmpty() {
		newDay();
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
		newDay();
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
	
	@Test
	public void testDoubleBooking()  {
		makeEarlyAppointment();
		Appointment badAppointment = new Appointment("Error", 1);
		boolean actual = day.makeAppointment(Day.START_OF_DAY, badAppointment);
		assertFalse(actual);
	}
	
	private void newDay()  {
		day = new Day(1);
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
