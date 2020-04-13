package main.model;

public class Window {
    // Параметры "окна" в "мировых" координатах
    private double x1;
    private double x2;
    private double y1;
    private double y2;

    // Параметры "окна" в "экранных" координатах
    private double xs1;
    private double xs2;
    private double ys1;
    private double ys2;

    private Window() {
    }

    public double toScreenX(double x) {
        return xs1 + (xs2 - xs1) * (x - x1) / (x2 - x1);
    }

    public double toScreenY(double y) {
        return ys2 + (ys1 - ys2) * (y - y1) / (y2 - y1);
    }

    public double toWorldX(double xs) {
        return x1 + (x2 - x1) * (xs - xs1) / (xs2 - xs1);
    }

    public double toWorldY(double ys) {
        return y1 + (y2 - y1) * (ys - ys2) / (ys1 - ys2);
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getXs1() {
        return xs1;
    }

    public double getXs2() {
        return xs2;
    }

    public double getYs1() {
        return ys1;
    }

    public double getYs2() {
        return ys2;
    }

    public static class Builder {
        private final Window window = new Window();

        public Builder x1(double v) {
            window.x1 = v;
            return this;
        }

        public Builder x2(double v) {
            window.x2 = v;
            return this;
        }

        public Builder y1(double v) {
            window.y1 = v;
            return this;
        }

        public Builder y2(double v) {
            window.y2 = v;
            return this;
        }

        public Builder xs1(double v) {
            window.xs1 = v;
            return this;
        }

        public Builder xs2(double v) {
            window.xs2 = v;
            return this;
        }

        public Builder ys1(double v) {
            window.ys1 = v;
            return this;
        }

        public Builder ys2(double v) {
            window.ys2 = v;
            return this;
        }

        public Window build() {
            return window;
        }

    }
}
