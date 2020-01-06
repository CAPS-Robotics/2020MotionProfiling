package MotionProfiling;

public class Spline {
    double x0, y0, x1, y1, theta0, theta1;
    double ax, bx, cx, dx, ex, fx, ay, by, cy, dy, ey, fy;
    double scale;

    public Spline(double x0, double y0, double x1, double y1, double theta0, double theta1) {
        scale = 1.2 * Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.theta0 = theta0;
        this.theta1 = theta1;

        calculateCoefficients();
    }

    public void calculateCoefficients() {
        ax = -6 * x0 + 6 * x1 - 3 * Math.cos(Math.toRadians(theta0)) * scale - 3 * Math.cos(Math.toRadians(theta1)) * scale;
        bx = 15 * x0 - 15 * x1 + 8 * Math.cos(Math.toRadians(theta0)) * scale + 7 * Math.cos(Math.toRadians(theta1)) * scale;
        cx = -10 * x0 + 10 * x1 - 6 * Math.cos(Math.toRadians(theta0)) * scale - 4 * Math.cos(Math.toRadians(theta1)) * scale;
        dx = 0;
        ex = Math.cos(Math.toRadians(theta0)) * scale;
        fx = x0;

        ay = -6 * y0 + 6 * y1 - 3 * Math.sin(Math.toRadians(theta0)) * scale - 3 * Math.sin(Math.toRadians(theta1)) * scale;
        by = 15 * y0 - 15 * y1 + 8 * Math.sin(Math.toRadians(theta0)) * scale + 7 * Math.sin(Math.toRadians(theta1)) * scale;
        cy = -10 * y0 + 10 * y1 - 6 * Math.sin(Math.toRadians(theta0)) * scale - 4 * Math.sin(Math.toRadians(theta1)) * scale;
        dy = 0;
        ey = Math.sin(Math.toRadians(theta0)) * scale;
        fy = y0;
    }
    public double getX(double t) {
        return ax * Math.pow(t, 5) + bx * Math.pow(t, 4) + cx * Math.pow(t, 3) + dx * Math.pow(t, 2) + ex * Math.pow(t, 1) + fx;
    }
    public double getY(double t) {
        return ay * Math.pow(t, 5) + by * Math.pow(t, 4) + cy * Math.pow(t, 3) + dy * Math.pow(t, 2) + ey * Math.pow(t, 1) + fy;
    }
    public double getdx(double t) {
        return 5 * ax * Math.pow(t, 4) + 4 * bx * Math.pow(t, 3) + 3 * cx * Math.pow(t, 2) + 2 * dx * Math.pow(t, 1) + ex;
    }
    public double getdy(double t) {
        return 5 * ay * Math.pow(t, 4) + 4 * by * Math.pow(t, 3) + 3 * cy * Math.pow(t, 2) + 2 * dy * Math.pow(t, 1) + ey;
    }
    public double getddx(double t) {
        return 20 * ax * Math.pow(t, 3) + 12 * bx * Math.pow(t, 2) + 6 * cx * Math.pow(t, 1) + 2 * dx;
    }
    public double getddy(double t) {
        return 20 * ay * Math.pow(t, 3) + 12 * by * Math.pow(t, 2) + 6 * cy * Math.pow(t, 1) + 2 * dy;
    }
    public double getCurvatureSquared(double t) {
        return Math.pow(getdx(t) * getddy(t) - getdy(t) * getddx(t) / Math.pow(Math.pow(getdx(t), 2) + Math.pow(getdy(t), 2), 1.5), 2);
    }
    public double getCurvatureSum() {
        double sum = 0;
        for(double t = 0; t <= 1; t += 0.01) {
            sum += getCurvatureSquared(t);
        }
        return sum;
    }

    private void optimizeScale() {

    }
}
