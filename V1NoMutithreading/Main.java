package V1NoMutithreading;

import V1NoMutithreading.Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{
    public static void main(String[] args) throws Exception {
        int size = Integer.parseInt(args[0]);
        int points = Integer.parseInt(args[1]);
        Engine engine = new Engine(size);


        engine.initializer(randomPoints(points, size));
        engine.run();
    }

    public static List<Particle> randomPoints(int numberOfPoint , int size){
        List<Particle> particleList = new ArrayList<>();
        for (int i = 0 ; i < numberOfPoint ; i++){
            Vector pos = new Vector(Math.random()*size, Math.random()*size);
//            Vector pos = new Vector(size/2, size/2);
            Vector speed = new Vector(new Random().nextInt(2)-1, new Random().nextInt(2)-1);
            Particle p = new Particle(pos, speed);
            particleList.add(p);
        }
        return particleList;
    }
}
