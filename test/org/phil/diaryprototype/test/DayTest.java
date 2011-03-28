package org.phil.diaryprototype.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.phil.diaryprototype.Appointment;
import org.phil.diaryprototype.Day;

public class DayTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams()  {
		System.setOut(new PrintStream(outContent));
	}
	
	
	@Test
	public void testAppointmentsSize() {
		Day testDay = new Day(1);
		assertEquals("", Day.MAX_APPOINTMENTS_PER_DAY, testDay.getAppointmentsLength());
	}
	
	@Test
	public void testFindSpace() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeAppointment() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowAppointmentsEmpty() {
		Day testDay = new Day(1);
		StringBuilder sb = new StringBuilder();
		sb.append("=== Day " + testDay.getDayNumber() + " ===\n");
        int time = Day.START_OF_DAY;
        for(int i = 0; i < Day.MAX_APPOINTMENTS_PER_DAY; i++) {
            sb.append(time + ": \n");
            sb.append("\n");            
            time++;
        }
		sb.append("\n");
        
		testDay.showAppointments();
		assertEquals(sb.toString(), outContent.toString());
	}
	
	public void testShowAppointmentsFull()  {
		Day testDay = new Day(1);
		for (int time = Day.START_OF_DAY;
							time <= Day.FINAL_APPOINTMENT_TIME;
								time++)  {
			testDay.makeAppointment(
					time, new Appointment("Test " + time, 1));
		}
		testDay.showAppointments();
	}
	
	@After
	public void cleanUpStreams()  {
		System.setOut(null);
	}

}
