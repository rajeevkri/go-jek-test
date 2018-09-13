package com.gojek.model;

public class Car {
	String regNo;
	String color;
	
	public Car(String regNo, String color) {
        this.regNo = regNo;
        this.color = color;
    }

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
