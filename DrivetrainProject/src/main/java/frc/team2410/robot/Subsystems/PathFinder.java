package frc.team2410.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static frc.team2410.robot.RobotMap.*;

import edu.wpi.first.wpilibj.Timer;
import frc.team2410.robot.Robot;

public class PathFinder {
    private double maxPathSpeed;
    private double pathTime;
    private double pathTimeToTopSpeed;
    private double maxSpeedTime;
    private double maxSpeedDistance;
    private boolean pathDone = false;

    Timer t;

    public PathFinder() {
        maxPathSpeed = MAX_SPEED;
        pathTime = 2 * TIME_TO_TOP_SPEED;
        maxSpeedTime = 0;

        t = new Timer();
    }

    public void setPath(double distance) {
        t.reset();
        t.start();
        pathDone = false;

        maxSpeedDistance = distance - 2 * DISTANCE_TO_MAX_SPEED;

        if(maxSpeedDistance >= 0) {
            maxPathSpeed = MAX_SPEED;
            pathTimeToTopSpeed = TIME_TO_TOP_SPEED;
            maxSpeedTime = maxSpeedDistance / maxPathSpeed;
            pathTime = 2 * pathTimeToTopSpeed + maxSpeedTime;
        } else {
            maxSpeedDistance = 0;
            maxSpeedTime = 0;
            maxPathSpeed = Math.sqrt(MAX_ACCELERATION * distance);
            pathTimeToTopSpeed = maxPathSpeed / MAX_ACCELERATION;
            pathTime = 2 * pathTimeToTopSpeed;
        }
    }

    public double getCurrentSpeed() {
        double time = t.get();

        if(time < pathTimeToTopSpeed) {
            return MAX_ACCELERATION * time;
        } else if(time > pathTimeToTopSpeed && time < (pathTimeToTopSpeed + maxSpeedTime)) {
            return maxPathSpeed;
        }
        else {
            double speed = maxPathSpeed - ((time - (pathTimeToTopSpeed + maxSpeedTime)) * MAX_ACCELERATION);
            if(speed <= 0) pathDone = true;
            return speed;
        }
    }

    public double getPathTime() { return pathTime; }
    public boolean getPathDone() { return pathDone; }
}
