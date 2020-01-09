package MotionProfiling;

import java.util.ArrayList;

public class VelocityProfile {
    public static final double MAX_VELOCITY = 14.33;
    public static final double MAX_ACCELERATION = 9;

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
        double time = 0;
        double pLeftVelocity = 0;
        double pRightVelocity = 0;
        double pVelocity = 0;

        for(Spline spline : path) {
            for(double t = 0; t <= 1; t += 0.01) {
                double dDistance = Math.sqrt(Math.pow(spline.getdx(t), 2) + Math.pow(spline.getdy(t), 2)) * 0.01;
                double maxVelocity = Math.sqrt(Math.pow(pVelocity, 2) + 2 * MAX_ACCELERATION * dDistance);
                double velocity = Math.min(MAX_VELOCITY, maxVelocity);
                double dTime = dDistance / velocity;

                distance += dDistance;
                time += dTime;
                pVelocity = velocity;

                times.add(time);
                velocities.add(velocity);
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