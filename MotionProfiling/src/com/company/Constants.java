package com.company;

public class Constants {
	public static final double MAX_SPEED = 11;
	public static final double MAX_ACCELERATION = 5.225;
	public static final double TIME_TO_TOP_SPEED = Constants.MAX_SPEED / Constants.MAX_ACCELERATION;
	public static final double DISTANCE_TO_MAX_SPEED = TIME_TO_TOP_SPEED * Constants.MAX_SPEED;
}
