package V2Mutlithreaded;

import javax.swing.text.Position;
import java.util.List;
import java.util.Random;

public class Printer implements Runnable{

    static int[][] booleans = new int[Engine.fieldSize][Engine.fieldSize];

    public static void printPos() throws InterruptedException {
        String empty = "░";
        String body = "█";


        while (true){
            StringBuilder sb = new StringBuilder();

            for (int x = 0 ; x < Engine.fieldSize ; x++) {
                for (int y = 0 ; y < Engine.fieldSize ; y++){

                    refreshScreen();

                    if (booleans[x][y] > 0){
                        sb.append(colorize(booleans[x][y] , body));
                    }else{
                        sb.append(empty.repeat(2));
                    }
                }
                sb.append("\n");
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(sb);
            sb = new StringBuilder();
            Thread.sleep(30);
        }
    }

    public static void refreshScreen(){
        List<Particle> particleList = Engine.getParticleList();
        booleans = new int[Engine.fieldSize][Engine.fieldSize];
        for (Particle p : particleList) {
            drawPoint(p);
        }
    }

    public static void drawPoint(Particle particle){

        for (int x = -particle.r ; x <= particle.r ; x++){
            for (int y = -particle.r ; y <= particle.r ; y++) {
                if (Math.round(Math.sqrt(Math.abs(x*x + y*y))) <= particle.r){
                    try {
                        booleans[(int) (x+particle.getPosition().getX())][(int) (y + particle.getPosition().getY())] = particle.color;
                    }catch (Exception e){
                        continue;
                    }
                }
            }
        }
    }

    public static String colorize(int grad, String body){
        return "\033[1;" + grad + "m" + body.repeat(2) + "\033[0m";
    }

    @Override
    public void run() {
        try {
            printPos();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
