package V1NoMutithreading;

public class Particle {
    public Vector speed;
    public Vector position;

    public Particle(Vector initialPosition, Vector initialSpeed) {
        this.position = initialPosition;
        this.speed    = initialSpeed;
    }

    public Particle(Vector position) {
        this.position = position;
    }

    public Particle setNextPosition(){
        this.position.addition(speed);
        return this;
    }

    public static Vector evaluateNewPosition(Vector position, Vector speed){
        return new Vector(position.x + speed.x, position.y + speed.y);
    }

}
