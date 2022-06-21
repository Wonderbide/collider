package V2Mutlithreaded;

import java.util.Objects;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Integer.compare((int) vector.x, (int) x) == 0 && Integer.compare((int) vector.y, (int) y) == 0;
    }

    @Override
    public String toString() {
        return "{" +
                "x = " + x +
                " , y = " + y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
