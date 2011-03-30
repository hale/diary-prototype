package org.phil.diaryprototype.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.phil.diaryprototype.Appointment;
import org.phil.diaryprototype.Day;

public class TwoHourTests {
	private Day day;
	
	@Test
	public void makeEarlyAppointment() {
		day = new Day(1);
		boolean actual = day.makeAppointment(Day.START_OF_DAY, new Appointment("Two Hour Test Early", 2));
		assertTrue(actual);
	}
	
	@Test
	public void makeMidAppointment() {
		day = new Day(1);
		Random random = new Random();
		// Make an appointment somewhere between the beginning and end of the day
		Appointment mid = new Appointment("Two Hour Test Mid", 2);
		int time = random.nextInt(Day.FINAL_APPOINTMENT_TIME - Day.START_OF_DAY) + Day.START_OF_DAY;
		assertTrue(day.makeAppointment(time, mid));		
	}
	
	@Test
	public void makeLateAppointment() {
		day = new Day(1);
		Appointment late = new Appointment("Two Hour Test Late", 2);
		boolean actual = day.makeAppointment(Day.FINAL_APPOINTMENT_TIME - late.getDuration(), late);
		assertTrue(actual);
	}
	
	/*
	@Test
	public void fillDay() {
		day = new Day(1);
		boolean actual = true;
		for (int time = Day.START_OF_DAY;
			time <= Day.FINAL_APPOINTMENT_TIME;
				time += 2)  {
			Appointment appointment = new Appointment("Test " + time, 2);
			if (day.findSpace(appointment) != -1) {
				actual = (actual && 
					day.makeAppointment(time, appointment));
			}
		}
		assertTrue(actual);
	}
	*/
	/*
	@Test
	public void testDoubleBookingFull()  {
		fillDay();
		Appointment badAppointment = new Appointment("Error", 2);
		boolean actual = day.makeAppointment(9, badAppointment);
		assertFalse(actual);
	}
	*/
	@Test
	public void testDoubleBooking() {
		Appointment first = new Appointment("First", 2);
		Appointment second = new Appointment("Second", 2);
		day = new Day(1);
		day.makeAppointment(Day.START_OF_DAY, first);
		assertFalse(day.makeAppointment(Day.START_OF_DAY + 1, second));
	}
	
	public void testDoubleBookingMix() {
		Appointment oneH = new Appointment("Single", 1);
		Appointment twoH = new Appointment("Double", 2);
		Day day = new Day(1);
		day.makeAppointment(10, oneH);
		assertFalse(day.makeAppointment(9, twoH));
		
	}

}
