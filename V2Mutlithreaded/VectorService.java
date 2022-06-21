package V2Mutlithreaded;

public class VectorService {
    public static Vector dotProduct(Vector v1, double coefficient){
        return new Vector(v1.getX()*coefficient, v1.getY()*coefficient);
    }

    public static Vector dotProductX(Vector v1, double coefficient){
        return new Vector(v1.getX()*coefficient, v1.getY());
    }
    public static Vector dotProductY(Vector v1, double coefficient){
        return new Vector(v1.getX(), v1.getY()*coefficient);
    }
    public static Vector crosseProduct(Vector v1, Vector v2){
        return new Vector(v1.getX()* v2.getX() , v1.getY() * v2.getY());
    }
    public static Vector addition(Vector v1, Vector v2){
        return new Vector(v1.getX() + v2.getX() , v1.getY() + v2.getY() );
    }
    public static Vector subtraction(Vector v1, Vector v2){
        return new Vector(v1.getX() - v2.getX() , v1.getY() - v2.getY() );
    }

    public static double distanceBetween2points(Vector v1, Vector v2){
        double dx1 = Math.abs(v1.getX());
        double dx2 = Math.abs(v2.getX());
        double dy1 = Math.abs(v1.getY());
        double dy2 = Math.abs(v1.getY());
        double l = dx2 - dx1;
        double h = dy2 - dy1;
        return  Math.sqrt((h*h)+(l*l));
    }
    public static boolean isPositionNotValid(Vector position){
        return position.getX() < 0 || position.getY() < 0 ;
    }
}
