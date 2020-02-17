package MotionProfiling;

public class CubicApproximation {
    double a, b, pathTime;

    public CubicApproximation(double pathTime) {
        this.pathTime = pathTime;
        computeCoefficients();
    }

    private void computeCoefficients() {
        a = -2 / Math.pow(pathTime, 3);
        b = 3 / Math.pow(pathTime, 2);
    }

    public double getT(double time) {
        return a * Math.pow(time, 3) + b * Math.pow(time, 2);
    }
}
