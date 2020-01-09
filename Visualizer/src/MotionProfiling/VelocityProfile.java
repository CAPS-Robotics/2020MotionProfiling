package MotionProfiling;

import java.util.ArrayList;

public class VelocityProfile {
    public static final double MAX_VELOCITY = 16.8;
    public static final double MAX_ACCELERATION = 5;

    private static ArrayList<Spline> path = new ArrayList<>();

    private static double pathDistance;
    private static double pathTime;

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
}