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
        double time = 0;
        double pLeftVelocity = 0;
        double pRightVelocity = 0;

        for(Spline spline : path) {
            for(double t = 0; t <= 1; t += 0.001) {
                double dDistance = Math.sqrt(Math.pow(spline.getdx(t), 2) + Math.pow(spline.getdy(t), 2)) * 0.001;
                double dLeftDistance = Math.sqrt((Math.pow((spline.getLeftPosY(t) - spline.getLeftPosY(t + 0.001)) / (spline.getLeftPosX(t) - spline.getLeftPosX(t + 0.001)), 2) + 1)) * Math.abs(spline.getLeftPosX(t) - spline.getLeftPosX(t + 0.001));
                double dRightDistance = Math.sqrt((Math.pow((spline.getRightPosY(t) - spline.getRightPosY(t + 0.001)) / (spline.getRightPosX(t) - spline.getRightPosX(t + 0.001)), 2) + 1)) * Math.abs(spline.getRightPosX(t) - spline.getRightPosX(t + 0.001));

                double leftVelocity;
                double rightVelocity;
                if(spline.getdx(t) > 0 && spline.getd2ydx2(t) > 0 || spline.getdx(t) < 0 && spline.getd2ydx2(t) < 0) {
                    rightVelocity = Math.min(MAX_VELOCITY, calcMaxVelocity(pRightVelocity, dRightDistance));
                    leftVelocity = calcInnerWheelVelocity(rightVelocity, spline.getCurvature(t));
                    if(leftVelocity > calcMaxVelocity(pLeftVelocity, dLeftDistance)) {
                        leftVelocity = calcMaxVelocity(pLeftVelocity, dLeftDistance);
                        rightVelocity = calcOuterWheelVelocity(leftVelocity, spline.getCurvature(t));
                    }
                } else if (spline.getdx(t) < 0 && spline.getd2ydx2(t) > 0 || spline.getdx(t) > 0 && spline.getd2ydx2(t) < 0) {
                    leftVelocity = Math.min(MAX_VELOCITY, calcMaxVelocity(pLeftVelocity, dLeftDistance));
                    rightVelocity = calcInnerWheelVelocity(leftVelocity, spline.getCurvature(t));
                    if(rightVelocity > calcMaxVelocity(pRightVelocity, dRightDistance)) {
                        rightVelocity = calcMaxVelocity(pRightVelocity, dRightDistance);
                        leftVelocity = calcOuterWheelVelocity(rightVelocity, spline.getCurvature(t));
                    }
                } else {
                    double maxLeftVelocity = calcMaxVelocity(pLeftVelocity, dLeftDistance);
                    double maxRightVelocity = calcMaxVelocity(pRightVelocity, dRightDistance);
                    double currentMaxVelocity = Math.min(maxLeftVelocity, maxRightVelocity);
                    leftVelocity = Math.min(currentMaxVelocity, MAX_VELOCITY);
                    rightVelocity = Math.min(currentMaxVelocity, MAX_VELOCITY);
                }

                double velocity = (leftVelocity + rightVelocity) / 2;
                double dTime = dDistance / velocity;

                pLeftVelocity = leftVelocity;
                pRightVelocity = rightVelocity;

                distance += dDistance;
                time += dTime;

                times.add(time);
                velocities.add(velocity);
                leftVelocities.add(leftVelocity);
                rightVelocities.add(rightVelocity);
            }
        }
        pathTime = time;
        pathDistance = distance;
    }

    private static double calcMaxVelocity(double pVelocity, double distance) {
        return Math.sqrt(Math.pow(pVelocity, 2) + 2 * MAX_ACCELERATION * distance);
    }
    private static double calcInnerWheelVelocity(double outerVelocity, double curvature) {
        double turningRadius = Math.abs(1 / curvature);
        return (2 * turningRadius * outerVelocity - WHEELBASE * outerVelocity) / (2 * turningRadius + WHEELBASE);
    }
    private static double calcOuterWheelVelocity(double innerVelocity, double curvature) {
        double turningRadius = Math.abs(1 / curvature);
        return (2 * turningRadius * innerVelocity + WHEELBASE * innerVelocity) / (2 * turningRadius - WHEELBASE);
    }

    public static ArrayList<Double> getTimes() { return times; }
    public static ArrayList<Double> getLeftVelocities() { return leftVelocities; }
    public static ArrayList<Double> getRightVelocities() { return rightVelocities; }
    public static ArrayList<Double> getVelocities() { return velocities; }
}