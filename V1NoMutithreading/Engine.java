package V1NoMutithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class Engine {
    Particle[][] simulation;
    public final int size;
    List<Particle> particleList = new ArrayList<>();

    public Engine(int size) {
        this.simulation = new Particle[size][size];
        this.size = size;
    }

    public void initializer(Particle p){
                simulation[(int) p.position.x][(int) p.position.y] = p;
                particleList.add(p);
    }
    public void initializer(List<Particle> particles) throws Exception {
        for (Particle p : particles) {
//            if (simulation[(int) p.position.x][(int) p.position.y] != null) {
                simulation[(int) p.position.x][(int) p.position.y] = p;
                particleList.add(p);
//            }else {
//                throw new Exception();
//            }
        }
    }

    public void run(){
        while (true){

            for (Particle particle : particleList) {
                checkWallCollision(particle);
                particle.setNextPosition();
//                }else {
//                    particle.speed.dot(normal);
//                    particle.setNextPosition();
//                }
            }
            updateSimulation();
            printSimulation();
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void updateSimulation(){
        simulation = new Particle[size][size];
        for (Particle particle: particleList) {
            simulation[(int) particle.position.x][(int) particle.position.y] = particle;
        }
    }

    public boolean checkWallCollision(Particle particle){

        Vector newPosition = Particle.evaluateNewPosition(particle.position, particle.speed);

//        //Mur droit
//        if (newPosition.x >= this.size-1) return new Vector(-1, 0);
//
//        //Mur gauche
//        if (newPosition.x < 0) return new Vector(1, 0);
//
//        //mur bas
//        if (newPosition.y >= this.size-1) return new Vector(0, -1);
//
//        //mur haut
//        if (newPosition.y < 0) return new Vector(0, 1);
//
        //Mur droit
        if (newPosition.x > this.size-1) {
            particle.speed.x *= -1;
            return true;
        }

        //Mur gauche
        if (newPosition.x < 0){
            particle.speed.x*= -1;
            return true;
        }

        //mur bas
        if (newPosition.y > this.size-1) {
            particle.speed.y*= -1;
            return true;
        }

        //mur haut
        if (newPosition.y < 0) {
            particle.speed.y*= -1;
            return true;
        }

        return false;
    }
    public void printSimulation(){
        Particle particleA = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (Particle[] particles : simulation){
            for (Particle particle : particles) {
                if (particle == null)
                    stringBuilder.append("░░");
                else {
                    stringBuilder.append("██");
                    particleA = particle;
                }
            }
            stringBuilder.append("\n");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-----------------START-------------------");
        System.out.println("speed : ( "+ particleA.speed.x +" ; "+ particleA.speed.y + " )");
        System.out.println(stringBuilder);
        System.out.println();
        System.out.println("-----------------END-------------------");
    }
}