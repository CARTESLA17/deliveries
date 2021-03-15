package org.com.deliveries.models;

import java.io.Serializable;

public class Drone implements Serializable{
	
	private int X = 0;
	private int Y = 0;
	private String currentDirection = "N";
	public static final String FORWARD_MOVE = "A";
	public static final String LEFT_MOVE = "I";
	public static final String RIGHT_MOVE = "D";

	private static final long serialVersionUID = -159134927326801903L;

	public Drone() {
		super();		
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public String getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(String currentDirection) {
		this.currentDirection = currentDirection;
	}

}
