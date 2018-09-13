package com.gojek.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gojek.model.Car;

public class ParkingStrategy {
	private int totalSlots = 0;

	private List<Integer> availableSlots;

	private Map<Integer, Car> slotCarMap;

	private Map<String, Integer> regNoCarSlotMap;

	private Map<String, List<String>> colorCarMap;

	private static ParkingStrategy parkingStrategy = null;

	public ParkingStrategy(int inputSlots) {
		this.totalSlots = inputSlots;
		availableSlots = new ArrayList<Integer>();

		for (int i = 1; i <= inputSlots; i++) {
			availableSlots.add(i);
		}

		slotCarMap = new HashMap<Integer, Car>();
		regNoCarSlotMap = new HashMap<String, Integer>();
		colorCarMap = new HashMap<String, List<String>>();
		System.out.println("Created parking lot with " + inputSlots + " slots");
	}

	public void addParkingSlots(int inputSlots) {
		this.totalSlots = this.totalSlots + inputSlots;
		availableSlots = new ArrayList<Integer>();

		for (int i = 1; i <= inputSlots; i++) {
			availableSlots.add(i);
		}

		slotCarMap = new HashMap<Integer, Car>();
		regNoCarSlotMap = new HashMap<String, Integer>();
		colorCarMap = new HashMap<String, List<String>>();
		System.out.println("Created parking lot with " + inputSlots + " slots");
	}

	public void park(String regNo, String color) {
		Car car = new Car(regNo, color);
		parkCar(car);
	}

	private void parkCar(Car car) {
		if (totalSlots == 0) {
			System.out.println("Sorry, parking is not available");
			return;
		} else if (slotCarMap.size() == totalSlots) {
			System.out.println("Sorry, parking lot is full");
			return;
		} else {
			Collections.sort(availableSlots);
			int slot = availableSlots.get(0);
			slotCarMap.put(slot, car);
			regNoCarSlotMap.put(car.getRegNo(), slot);
			if (colorCarMap.containsKey(car.getColor())) {
				List<String> regNoList = colorCarMap.get(car.getColor());
				colorCarMap.remove(car.getColor());
				regNoList.add(car.getRegNo());
				colorCarMap.put(car.getColor(), regNoList);
			} else {
				LinkedList<String> regNoList = new LinkedList<String>();
				regNoList.add(car.getRegNo());
				colorCarMap.put(car.getColor(), regNoList);
			}
			System.out.println("Allocated slot number: " + slot + "");
			availableSlots.remove(0);
		}
	}

	public void leaveSlot(Integer slotNo) {
		if (totalSlots == 0) {
			System.out.println("Sorry, parking lot is not created");
		} else if (slotCarMap.size() > 0) {
			Car carToLeave = slotCarMap.get(slotNo);
			if (carToLeave != null) {
				slotCarMap.remove(slotNo);
				regNoCarSlotMap.remove(carToLeave.getRegNo());
				List<String> regNoList = colorCarMap.get(carToLeave.getColor());
				if (regNoList.contains(carToLeave.getRegNo())) {
					regNoList.remove(carToLeave.getRegNo());
				}
				availableSlots.add(slotNo);
				System.out.println("Slot number " + slotNo + " is free");
			} else {
				System.out.println("Slot number " + slotNo + " is already empty");
			}
		} else {
			System.out.println("Parking lot is empty");
		}
	}

	public void getParkingStatus() {
		if (totalSlots == 0) {
			System.out.println("Sorry, parking lot is not created");
		} else if (slotCarMap.size() > 0) {
			System.out.println("Slot No.\tRegistration No.\tColor");
			Car car;
			for (int i = 1; i <= totalSlots; i++) {
				if (slotCarMap.containsKey(i)) {
					car = slotCarMap.get(i);
					System.out.println(i + "\t" + car.getRegNo() + "\t" + car.getColor());
				}
			}
			System.out.println();
		} else {
			System.out.println("Parking lot is empty");
		}
	}

	public void getRegNoFromColor(String color) {
		if (totalSlots == 0) {
			System.out.println("Sorry, parking lot is not avilable");
		} else if (colorCarMap.containsKey(color)) {
			List<String> regNoList = colorCarMap.get(color);
			System.out.println();
			for (int i = 0; i < regNoList.size(); i++) {
				if (!(i == regNoList.size() - 1)) {
					System.out.print(regNoList.get(i) + ",");
				} else {
					System.out.print(regNoList.get(i));
				}
			}
			System.out.println();
		} else {
			System.out.println("Not found");
		}
	}

	public void getSlotFromColor(String color) {
		if (totalSlots == 0) {
			System.out.println("Sorry, parking lot is not created");
		} else if (colorCarMap.containsKey(color)) {
			List<String> regNoList = colorCarMap.get(color);
			List<Integer> slotList = new ArrayList<Integer>();
			System.out.println();
			for (int i = 0; i < regNoList.size(); i++) {
				slotList.add(Integer.valueOf(regNoCarSlotMap.get(regNoList.get(i))));
			}
			Collections.sort(slotList);
			for (int j = 0; j < slotList.size(); j++) {
				if (!(j == slotList.size() - 1)) {
					System.out.print(slotList.get(j) + ", ");
				} else {
					System.out.print(slotList.get(j));
				}
			}
			System.out.println();
		} else {
			System.out.println("Not found");
		}
	}

	public void getSlotFromRegNo(String regNo) {
		if (totalSlots == 0) {
			System.out.println("Sorry, parking lot is not created");
		} else if (regNoCarSlotMap.containsKey(regNo)) {
			System.out.println(regNoCarSlotMap.get(regNo));
		} else {
			System.out.println("Not found");
		}
	}

	public int getTotalSlots() {
		return totalSlots;
	}

	public List<Integer> getAvailableSlots() {
		return availableSlots;
	}
	
}
