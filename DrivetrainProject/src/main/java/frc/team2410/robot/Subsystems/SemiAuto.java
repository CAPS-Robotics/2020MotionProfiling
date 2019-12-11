package frc.team2410.robot.Subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2410.robot.Robot;
import static frc.team2410.robot.RobotMap.*;

public class SemiAuto {
	
	public int placeState = 0;
	private int climbState = 0;
	public Timer t;
	public boolean engaged = false;
	public boolean ceng = false;
	public boolean reng = false;
	public boolean lift = false;
	public double pval = 0;
	private double pspeed = 0;
	private double frontPower = 0.20;
	private double backPower = -0.5;
	public double pFrontPos;
	public double pBackPos;
	public double targetAngle;
	
	
	public SemiAuto() {
		
		t = new Timer();
		targetAngle = Robot.gyro.getHeading();
	}
	
	public void reset(boolean place) {
		if(place) {
			placeState = 0;
			reng = false;
		} else {
			climbState = 0;
			ceng = false;
		}
		engaged = ceng || reng;
	}
	
	public boolean startMatch() {
		return true;
	}
	
	public void turnToNearestAngle(double angle) {
		reng = true;
		engaged = true;
		Robot.drivetrain.desiredHeading = angle;
	}
	
	public void turnToNearestAngle(double [] angles) {
		reng = true;
		engaged = true;
		
		double angle = Robot.gyro.getHeading();
		double lowestOffset = 180;
		double target = 0;
		
		for(int i = 0; i < angles.length; i++) {
			double offset = Math.abs(angles[i] - angle);
			if(offset < lowestOffset) {
				lowestOffset = offset;
				target = angles[i];
			}
		}
		
		Robot.drivetrain.desiredHeading = target;
	}
	
	private boolean driveToDistance(double distance, boolean useGyro){
		if(Math.abs(distance) < 1) return true;
		
		double speed = distance/10;
		/*if(speed < -1) speed = -1;
		if(speed > 1) speed = 1;*/
		speed = Math.abs(speed)/speed;
		Robot.drivetrain.crabDrive(0, speed, 0, 0.3, useGyro);
		
		return false;
	}
	
	
}
