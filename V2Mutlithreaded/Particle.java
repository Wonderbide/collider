package V2Mutlithreaded;

import java.util.Objects;

public class Particle {
    private Vector position;
    //speed by second
    private Vector speed;
    int color = (int)((Math.random()*7) + 30);

    int r = (int)((Math.random()*5));
    //private double weight;
    //private int ray;
    //private double elasticity;


    public Particle(Vector position, Vector speed) {
        if (VectorService.isPositionNotValid(position)){
            try {
                throw new Exception("Wrong initial position : Start at at negative position");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.position = position;
        this.speed = speed;
    }

    public Vector getPosition() {
        return position;
    }

    public boolean setPosition(Vector position, int max) {
        if (VectorService.isPositionNotValid(position) || position.getX() > max || position.getY() > max){
            return false;
        }

        this.position = position;
        return true;
    }
    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public Vector getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Particle { \n" +
                " position = " + position +
                ",\nspeed = " + speed +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return Objects.equals(position, particle.position) && Objects.equals(speed, particle.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, speed);
    }
}
