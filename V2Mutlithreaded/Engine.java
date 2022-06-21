package V2Mutlithreaded;

import com.sun.jdi.connect.spi.Connection;

import javax.lang.model.type.ArrayType;
import java.util.*;
import java.util.stream.Collectors;

public class Engine implements Runnable{
    private static final double refreshTimeInMs = 17;
    public static int fieldSize;

    private static List<Particle> particleList = new ArrayList<>();

    private static final Vector LEFT_AND_RIGTH = new Vector(-1,1);
    private static final Vector TOP_AND_BOT = new Vector(1,-1);

    private List<Particle> particles;

    public static boolean[][] booleans;

    public Engine(int size) {
        fieldSize = size;
        this.particles = new ArrayList<>();
        booleans = new boolean[size][size];
    }

    public synchronized static boolean[][] getBooleans() {
        return Arrays.copyOf(booleans, fieldSize);
    }

    public void circleInitializer(int r){
        for (int i = -r-1 ; i <= r+1 ; i++){
            for (int j = -r-1 ; j <= r+1 ; j++){
                if (Math.round(Math.sqrt(Math.abs(i*i + j*j))) == r) {
                    Vector ps = new Vector(i + (fieldSize/2) ,j + (fieldSize/2));
                    Vector s = new Vector(i * (fieldSize/r)  ,j *(fieldSize/r));
                    this.particles.add(new Particle(ps,s));
                }
            }
        }
    }
    public void initializer(int nb){
        for (int i = 0 ; i < nb ; i++){
            Vector p = new Vector(Math.random()*fieldSize,Math.random()*fieldSize);
            Vector s = new Vector(new Random().doubles(-fieldSize,fieldSize).findAny().getAsDouble(),
                    new Random().doubles(-fieldSize ,fieldSize).findAny().getAsDouble());
            this.particles.add(new Particle(p,s));
        }
    }

    public void initializer(Particle particle){
        this.particles.add(particle);
    }

    public void initializer(List <Particle> particleList){
        for (Particle p : particleList) {
            this.particles.add(p);
        }
    }
    public static synchronized List<Particle> getParticleList(){
        return new ArrayList<>(particleList);
    }
    public void runEngine(){
        boolean[][] tempBoard;
        long start, end;
        while (true){
            tempBoard = new boolean[fieldSize][fieldSize];
            start = System.currentTimeMillis();
            for (Particle p : this.particles) {
                Vector newPosition = updatePosition(p ,((refreshTimeInMs/10000)));
                tempBoard[(int) p.getPosition().getX()][ (int) p.getPosition().getY()] = true;
                if (! p.setPosition(newPosition, fieldSize)) {
                    updateSpeed(p);
                    p.setPosition(newPosition, fieldSize);
                }
            }

//            collisionDetection();

            booleans = tempBoard;
            particleList = particles;
            end = System.currentTimeMillis();
            try {
                Thread.sleep((end - start) > refreshTimeInMs ? 0 : (long) (refreshTimeInMs + start - end));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void collisionDetection(){
        List<Particle> unique = particles.stream().distinct().collect(Collectors.toList());
        List<Particle> duplicates = new ArrayList<>(particles);
        duplicates.removeAll(unique);
        List<Particle> duplicatesSingleton = duplicates.stream().distinct().collect(Collectors.toList());

        List<Particle> temp;

        for (Particle p : duplicatesSingleton) {
            temp = duplicates.stream().filter(particle -> particle.equals(p)).collect(Collectors.toList());
            for (Particle tempParticle : temp) {
                System.exit(9);
                particles.stream().filter(x -> x.equals(tempParticle)).forEach(
                        x -> {  x.getSpeed().setX(x.getSpeed().getX()*-1);
                                x.getSpeed().setY(x.getSpeed().getY()*-1);
                        });
            }
        }
    }

    public boolean updateSpeed (Particle particle){
        Vector speed = particle.getSpeed();
        //Mur droit ou gauche
        if ( (int) particle.getPosition().getX() >= fieldSize-1 || (int) particle.getPosition().getX() <= 0) {
            particle.setSpeed(VectorService.crosseProduct(LEFT_AND_RIGTH, speed));
            return true;
        }
        //mur bas ou  haut
        if ( (int) particle.getPosition().getY() >= fieldSize-1 || (int) particle.getPosition().getY() <= 0) {
            particle.setSpeed(VectorService.crosseProduct(TOP_AND_BOT, speed));
            return  true;
        }

        return false;
    }
    public Vector updatePosition (Particle particle,double time){
        return VectorService.addition(
                particle.getPosition(),
                VectorService.dotProduct(
                        particle.getSpeed(), time
                )
        );
    }

    @Override
    public void run() {
        runEngine();
    }
}
