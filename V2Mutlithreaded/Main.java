package V2Mutlithreaded;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Engine engine = new Engine(Integer.parseInt(args[0]));



        engine.initializer(Integer.parseInt(args[1]));
//        for (int i = 0 ; i < 3 ; i ++) engine.circleInitializer(Integer.parseInt(args[1])+(i*i));

        Printer printer = new Printer();

        Thread t1 = new Thread(engine);
        Thread t2 = new Thread(printer);

        t1.start();
        t2.start();

        t1.join();
        t1.join();
    }
}
