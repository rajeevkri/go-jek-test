package com.gojek.parking;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingStrategyTest {
	private ByteArrayOutputStream outContent = null;

	@Before
	public void setUpStreams() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		outContent = null;
		System.setOut(null);
	}

	@Test
	public void testAddParkingSlots() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(6);
		Assert.assertEquals(6, parkingStrategy.getTotalSlots());
		Assert.assertEquals(6, parkingStrategy.getAvailableSlots().size());
		String s = "Created parking lot with 6 slots\n";
		Assert.assertEquals(s, outContent.toString());
	}

	@Test
	public void testPark() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(0);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");

		parkingStrategy.addParkingSlots(6);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");
		Assert.assertEquals(4, parkingStrategy.getAvailableSlots().size());
		String s = "Created parking lot with 0 slots\n" + 
				"Sorry, parking is not available\n" + 
				"Sorry, parking is not available\n" + 
				"Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2";
		Assert.assertEquals(s, outContent.toString().trim());
	}

	@Test
	public void testLeaveSlot() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(0);
		parkingStrategy.leaveSlot(2);
		parkingStrategy.addParkingSlots(6);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");
		parkingStrategy.leaveSlot(4);
		String s = "Created parking lot with 0 slots\n" + 
				"Sorry, parking lot is not created\n" + 
				"Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2\n" + 
				"Slot number 4 is already empty\n" ;
		Assert.assertEquals(s, outContent.toString());
	}

	@Test
	public void testStatus() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(0);
		parkingStrategy.getParkingStatus();
		parkingStrategy.addParkingSlots(6);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");
		parkingStrategy.getParkingStatus();
		String s = "Created parking lot with 0 slots\n" + 
				"Sorry, parking lot is not created\n" + 
				"Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2\n" + 
				"Slot No.	Registration No.	Color\n" + 
				"1	KA-01-HH-1234	White\n" + 
				"2	KA-01-HH-9999	White";
		Assert.assertEquals(s, outContent.toString().trim());
	}

	@Test
	public void testGetRegistrationNumbersFromColor() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(0);
		parkingStrategy.getRegNoFromColor("White");
		parkingStrategy.addParkingSlots(6);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");
		parkingStrategy.getRegNoFromColor("White");
		parkingStrategy.getRegNoFromColor("Red");
		String s = "Created parking lot with 0 slots\n" + 
				"Sorry, parking lot is not avilable\n" + 
				"Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2\n" + 
				"\n" + 
				"KA-01-HH-1234,KA-01-HH-9999\n" + 
				"Not found\n";
		Assert.assertEquals(s, outContent.toString());
	}

	@Test
	public void getSlotNumbersFromColor() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(0);
		parkingStrategy.getSlotFromColor("White");
		parkingStrategy.addParkingSlots(6);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");
		parkingStrategy.getSlotFromColor("White");
		parkingStrategy.getSlotFromColor("Red");
		String s = "Created parking lot with 0 slots\n" + 
				"Sorry, parking lot is not created\n" + 
				"Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2\n" + 
				"\n" + 
				"1, 2\n" + 
				"Not found\n";
		Assert.assertEquals(s, outContent.toString());
	}

	@Test
	public void getSlotNumberFromRegNo() throws Exception {
		ParkingStrategy parkingStrategy = new ParkingStrategy(0);
		parkingStrategy.getSlotFromRegNo("KA-01-HH-1234");
		parkingStrategy.addParkingSlots(6);
		parkingStrategy.park("KA-01-HH-1234", "White");
		parkingStrategy.park("KA-01-HH-9999", "White");
		parkingStrategy.getSlotFromRegNo("KA-01-HH-1234");
		parkingStrategy.getSlotFromRegNo("KA-01-HH-9999");
		parkingStrategy.leaveSlot(1);
		parkingStrategy.getSlotFromRegNo("KA-01-HH-1234");
		String s = "Created parking lot with 0 slots\n" + 
				"Sorry, parking lot is not created\n" + 
				"Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2\n" + 
				"1\n" + 
				"2\n" + 
				"Slot number 1 is free\n" + 
				"Not found\n";
		Assert.assertEquals(s, outContent.toString());
	}

}
