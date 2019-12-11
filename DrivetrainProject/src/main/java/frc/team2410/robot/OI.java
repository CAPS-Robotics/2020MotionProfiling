package frc.team2410.robot;

import edu.wpi.first.wpilibj.*;

import static frc.team2410.robot.RobotMap.*;

public class OI {
	private boolean[][] canPress = new boolean[2][12];
	private boolean canPressPOV = true;
	private GenericHID[] controllers = new GenericHID[3];
	private Joystick joy;
	private XboxController xboxOther;
	private XboxController xboxDrive;
	
	OI() {
		joy = new Joystick(0);
		xboxOther = new XboxController(1);
		xboxDrive = new XboxController(2);
		controllers[0] = joy;
		controllers[2]  = xboxDrive;
		controllers[1] = xboxOther;
	}
	
	void pollButtons() {
		if(joy.getRawButton(5)) {
			Robot.drivetrain.returnWheelsToZero();
		}
		
		if(joy.getRawButton(6)) {
			Robot.drivetrain.resetHeading(0);
		} else if(joy.getRawButton(7)) {
			Robot.drivetrain.resetHeading(180);
		}
		
		boolean resetPlace = true;
		
		if(joy.getRawButton(11)){
			Robot.semiAuto.turnToNearestAngle(180);
			resetPlace = false;
		} else if(joy.getRawButton(9)) {
			Robot.semiAuto.turnToNearestAngle(0);
			resetPlace = false;
		} else if(joy.getRawButton(12)){
			Robot.semiAuto.turnToNearestAngle(ROCKET_SIDE_ANGLES);
			resetPlace = false;
		} else if(joy.getRawButton(10)) {
			Robot.semiAuto.turnToNearestAngle(ROCKET_HATCH_ANGLES);
			resetPlace = false;
		} else {
			Robot.semiAuto.reng = false;
		}
		
		Robot.fieldOriented = !joy.getRawButton(2);
		
		Robot.drivetrain.tankDrive(-xboxDrive.getRawAxis(1), true);
		Robot.drivetrain.tankDrive(-xboxDrive.getRawAxis(5), false);
		
		if(leadingEdge(false, 10)) {
			//Robot.elevator.reset(0);
		}
		
		if(resetPlace) {
			Robot.semiAuto.reset(true);
		}
	}
	
	//Returns true for the first frame the button is pressed
	private boolean leadingEdge(boolean joystick, int button) {
		int n = joystick?0:1;
		if(controllers[n].getRawButton(button)) {
			if(canPress[n][button-1]) {
				canPress[n][button-1] = false;
				return true;
			}
		} else { canPress[n][button-1] = true; }
		return false;
	}
	
	public double getX() {
		return this.applyDeadzone(joy.getRawAxis(0), 0.05, 1);
	}
	
	public double getY() {
		return this.applyDeadzone(-joy.getRawAxis(1), 0.05, 1);
	}
	
	public double getTwist() {
		return this.applyDeadzone(joy.getRawAxis(2), 0.01, 1)/2;
	}
	
	public double getSlider() {
		return (1-joy.getRawAxis(3))/2;
	}
	
	public double getAnalogStick(boolean rightStick, boolean yAxis){
		return this.applyDeadzone(xboxOther.getRawAxis((rightStick?1:0)*2+(yAxis?1:0)), 0.25, 1);
	}
	
	public boolean getAbortAuto() { return joy.getRawButton(8); }
	
	public boolean startPressed() { return xboxOther.getRawButton(10); }
	
	public double getJoyPOV() {
		return this.joy.getPOV(0);
	}
	
	private double applyDeadzone(double val, double deadzone, double maxval) {
		if (Math.abs(val) <= deadzone) return 0;
		double sign = val / Math.abs(val);
		val = sign * maxval * (Math.abs(val) - deadzone) / (maxval - deadzone);
		return val;
	}
}
