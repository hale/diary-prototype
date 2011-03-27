package org.phil.diaryprototype;

import static org.junit.Assert.*;

import org.junit.Test;

public class DayTest {

	@Test
	public void testAppointmentsSize() {
		Day testDay = new Day(1);
		assertEquals("", testDay.MAX_APPOINTMENTS_PER_DAY, testDay.getAppointmentsLength());
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
	public void testShowAppointments() {
		fail("Not yet implemented");
	}

}
