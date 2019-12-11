package frc.team2410.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Timer;
import frc.team2410.robot.Robot;

import static frc.team2410.robot.RobotMap.*;

public class Autonomous {
	public Timer timer;
	private int autoNumber;
	private int state = 0;
	private boolean autoDone;
	
	public Autonomous() {
	
	}
	
	public void init(int autoNumber) {
		autoDone = false;
		state = 0;
		this.autoNumber = autoNumber;
		timer = new Timer();
	}

	public boolean getAutoDone() { return autoDone; }
	public void setAutoDone(boolean autoDone) { this.autoDone = autoDone; }
	
	public void loop() {
		SmartDashboard.putNumber("Auto State", state);
		SmartDashboard.putNumber("Auto Number", autoNumber);
		SmartDashboard.putBoolean("Auto Done", autoDone);
		switch(autoNumber) {
			case 0:
				autoDone = true;
				break;
			case 1:
				driveForward(10);
				break;
			default:
				autoDone = true;
				break;
		}
		
		if(Robot.oi.getAbortAuto()) autoDone = true;
	}

	public void driveForward(double distance) {
		switch(state) {
			case 0:
				Robot.pathFinder.setPath(distance);
				state++;
			case 1:
				Robot.drivetrain.tankForward(Robot.pathFinder.getCurrentSpeed() / MAX_SPEED);
				if(Robot.pathFinder.getPathDone()) state++;
				break;
			case 2:
				Robot.drivetrain.brake();
				autoDone = true;
				break;
		}
	}
}