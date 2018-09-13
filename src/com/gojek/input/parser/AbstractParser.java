package com.gojek.input.parser;

import com.gojek.input.Instructions;
import com.gojek.parking.ParkingStrategy;

public abstract class AbstractParser {

	public void validateAndProcess(String inputString) throws Exception {
		ParkingStrategy parkingLot = null;
		String[] inputStrArr = inputString.split(" ");
		if (inputStrArr[0].equals("")) {
			System.out.println("Thanks for using the program");
			return;
		}
		Instructions command = Instructions.findByName(inputStrArr[0]);

		if (command == null) {
			System.out.println("Invalid command");
			return;
		}

		switch (command) {
		case CREATE:
			if (inputStrArr.length != 2) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			int noOfPrakingSlots = Integer.parseInt(inputStrArr[1]);
			parkingLot = new ParkingStrategy(noOfPrakingSlots);
			break;
		case PARK:
			if (inputStrArr.length != 3) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			String regNo = inputStrArr[1];
			String color = inputStrArr[2];
			parkingLot.park(regNo, color);
			break;
		case LEAVE:
			if (inputStrArr.length != 2) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			int slotNo = Integer.parseInt(inputStrArr[1]);
			parkingLot.leaveSlot(slotNo);
			break;
		case STATUS:
			if (inputStrArr.length != 1) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			parkingLot.getParkingStatus();
			break;
		case FETCH_CARE_FROM_COLOR:
			if (inputStrArr.length != 2) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			parkingLot.getRegNoFromColor(inputStrArr[1]); // color
			break;
		case FETCH_SLOT_FROM_COLOR:
			if (inputStrArr.length != 2) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			parkingLot.getSlotFromColor(inputStrArr[1]); // color
			break;
		case FETCH_SLOT_FROM_REG_NO:
			if (inputStrArr.length != 2) {
				throw new Exception("Invalid no of arguments for command : " + command);
			}
			parkingLot.getSlotFromRegNo(inputStrArr[1]); // regNo
			break;
		}
	}

	public abstract void process() throws Exception;

}
