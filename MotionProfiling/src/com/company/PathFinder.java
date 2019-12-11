package com.company;

public class PathFinder {
	private double maxPathSpeed;
	private double pathTime;
	private double pathTimeToTopSpeed;
	private double maxSpeedTime;
	private double maxSpeedDistance;

	private double currentTime;

	public PathFinder() {
		maxPathSpeed = Constants.MAX_SPEED;
		pathTime = 2 * Constants.TIME_TO_TOP_SPEED;
		maxSpeedTime = 0;

		currentTime = System.currentTimeMillis();
	}

	public void setPath(double xDistance, double yDistance) {
		currentTime = System.currentTimeMillis();
		maxSpeedDistance = yDistance - 2 * Constants.DISTANCE_TO_MAX_SPEED;

		if(maxSpeedDistance >= 0) {
			maxPathSpeed = Constants.MAX_SPEED;
			pathTimeToTopSpeed = Constants.TIME_TO_TOP_SPEED;
			maxSpeedTime = maxSpeedDistance / maxPathSpeed;
			pathTime = 2 * pathTimeToTopSpeed + maxSpeedTime;
		} else {
			maxSpeedDistance = 0;
			maxSpeedTime = 0;
			maxPathSpeed = Math.sqrt(Constants.MAX_ACCELERATION * yDistance);
			pathTimeToTopSpeed = maxPathSpeed / Constants.MAX_ACCELERATION;
			pathTime = 2 * pathTimeToTopSpeed;
		}
	}

	public double getCurrentSpeed() {
		double time = getElapsedTime();

		if(time < pathTimeToTopSpeed) {
			return Constants.MAX_ACCELERATION * time;
		} else if(time > pathTimeToTopSpeed && time < (pathTimeToTopSpeed + maxSpeedTime)) {
			return maxPathSpeed;
		}
		else {
			return maxPathSpeed - ((time - (pathTimeToTopSpeed + maxSpeedTime)) * Constants.MAX_ACCELERATION);
		}
	}

	public double getElapsedTime() { return (System.currentTimeMillis() - currentTime) / 1000; }

	public double getPathTime() { return pathTime; }
}
