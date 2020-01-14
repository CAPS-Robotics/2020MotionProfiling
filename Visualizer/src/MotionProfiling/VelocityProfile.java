package MotionProfiling;

import java.util.ArrayList;

public class VelocityProfile {
    public static final double MAX_VELOCITY = 14.33;
    public static final double MAX_ACCELERATION = 9;
    public static final double WHEELBASE = 3;

    private static ArrayList<Spline> path = new ArrayList<>();

    private static double pathDistance;
    private static double pathTime;

    private static ArrayList<Double> times;
    private static ArrayList<Double> leftVelocities;
    private static ArrayList<Double> rightVelocities;
    private static ArrayList<Double> velocities;

    public static void setPath(ArrayList<Spline> motionPath) { path = motionPath; }

    public static void calculateDistance() {
        double distance = 0;

        for(Spline spline : path) {
            for(double t = 0; t <= 1; t += 0.001) {
                distance += Math.sqrt(Math.pow(spline.getdx(t), 2) + Math.pow(spline.getdy(t), 2)) * 0.001;
            }
        }
        pathDistance = distance;
    }

    public static double getPathDistance() { return pathDistance; }
    public static double getPathTime() { return pathTime; }

    public static void calculateVelocities() {
        times = new ArrayList<>();
        leftVelocities = new ArrayList<>();
        rightVelocities = new ArrayList<>();
        velocities = new ArrayList<>();

        double distance = 0;
        double leftDistance = 0;
        double rightDistance = 0;
        double time = 0;
        double pVelocity = 0;

        for(Spline spline : path) {
            for(double t = 0; t <= 1; t += 0.001) {
                double dDistance = Math.sqrt(Math.pow(spline.getdx(t), 2) + Math.pow(spline.getdy(t), 2)) * 0.001;
                double dLeftDistance = Math.sqrt((Math.pow((spline.getLeftPosY(t) - spline.getLeftPosY(t + 0.001)) / (spline.getLeftPosX(t) - spline.getLeftPosX(t + 0.001)), 2) + 1)) * Math.abs(spline.getLeftPosX(t) - spline.getLeftPosX(t + 0.001));
                double dRightDistance = Math.sqrt((Math.pow((spline.getRightPosY(t) - spline.getRightPosY(t + 0.001)) / (spline.getRightPosX(t) - spline.getRightPosX(t + 0.001)), 2) + 1)) * Math.abs(spline.getRightPosX(t) - spline.getRightPosX(t + 0.001));
                double currentMaxVelocity = Math.sqrt(Math.pow(pVelocity, 2) + 2 * MAX_ACCELERATION * dDistance);
                double velocity = Math.min(MAX_VELOCITY, currentMaxVelocity);
                double dTime = dDistance / velocity;

                distance += dDistance;
                leftDistance += dLeftDistance;
                rightDistance += dRightDistance;
                time += dTime;
                pVelocity = velocity;

                times.add(time);
                velocities.add(velocity);
                leftVelocities.add(velocity);
                rightVelocities.add((velocity + 10));
            }
        }
        pathTime = time;
        pathDistance = distance;
    }

    public static ArrayList<Double> getTimes() { return times; }
    public static ArrayList<Double> getLeftVelocities() { return leftVelocities; }
    public static ArrayList<Double> getRightVelocities() { return rightVelocities; }
    public static ArrayList<Double> getVelocities() { return velocities; }
}