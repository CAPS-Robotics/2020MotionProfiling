package frc.team2410.robot;

import jdk.jfr.Unsigned;

public class RobotMap
{
	public static final boolean COMPETITION_BOT = true;

	//PID
	public static final float SWERVE_MODULE_P = 5;
	public static final float SWERVE_MODULE_I = 0;
	public static final float SWERVE_MODULE_D = 3;
	public static final double GYRO_P = .03;
	public static final double GYRO_I = 0;
	public static final double GYRO_D = 0;

	//CAN
	public static final int BACK_LEFT_STEER = 1;
	public static final int BACK_LEFT_DRIVE = 2;
	public static final int BACK_RIGHT_STEER = 3;
	public static final int BACK_RIGHT_DRIVE = 4;
	public static final int PIGEON_IMU_SRX = 10;
	public static final int FRONT_RIGHT_STEER = 5;
	public static final int FRONT_RIGHT_DRIVE = 6;
	public static final int FRONT_LEFT_STEER = 7;
	public static final int FRONT_LEFT_DRIVE = 8;
	public static final int ELEVATOR_A = 9;
	public static final int ELEVATOR_B = 15;
	public static final int INTAKE_MOTOR = 11;
	public static final int WRIST_MOTOR = 12;
	public static final int PCM = 13;
	public static final int CLIMB_ELEVATOR = 14;

	//Analog In
	public static final int BL_STEER_ENCODER = 0;
	public static final int BR_STEER_ENCODER = 1;
	public static final int FL_STEER_ENCODER = 2;
	public static final int FR_STEER_ENCODER = 3;
	
	//DIO
	public static final int CIMCODER_A = 0;
	public static final int CIMCODER_B = 1;

	//Offsets
	public static final float BR_OFFSET = 2.4316403760000003f;
	public static final float BL_OFFSET = 1.8701169960000001f;
	public static final float FR_OFFSET = 0.797119059f;
	public static final float FL_OFFSET = 1.666259595f;

	//Field Angles
	public static final double CARGO_SHIP_FRONT = 0;
	public static final double ROCKET_RIGHT_FRONT = -90;
	public static final double ROCKET_RIGHT_RIGHT = -90.0 + 61.25;
	public static final double ROCKET_RIGHT_LEFT = -90 - 61.25;
	public static final double ROCKET_LEFT_FRONT = 90;
	public static final double ROCKET_LEFT_RIGHT = 90.0 + 61.25;
	public static final double ROCKET_LEFT_LEFT = 90 - 61.25;
	public static final double INTAKE = 180;
	public static final double INTAKE2 = -180;
	public static final double [] ROCKET_SIDE_ANGLES = {ROCKET_RIGHT_FRONT, ROCKET_LEFT_FRONT};
	public static final double [] ROCKET_HATCH_ANGLES = {ROCKET_LEFT_LEFT, ROCKET_LEFT_RIGHT, ROCKET_RIGHT_LEFT, ROCKET_RIGHT_RIGHT};

	//Field Distances
	public static final double CARGO_DISTANCE = 1;
	public static final double HATCH_DISTANCE = 4;

	//Encoder Conversions
	//Diameter * PI / gear ratio / full encoder cycles (edges/4)
	public static final double DRIVE_DIST_PER_PULSE = 3.0*Math.PI/100.0;
	public static final double WINCH_DIST_PER_PULSE = 1.91 * Math.PI * 2 / 65 / 3; //two stages
	public static final double WINCH_CLIMB_DIST_PER_PULSE = 2.00 * Math.PI / 216.66 / 3;

	//public static final int PI = 3.1416;
	//public static final double PI = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679; //memz
}
