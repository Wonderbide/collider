package V1NoMutithreading;

public class Vector {
   public double x;
   public double y;

   public Vector(double x, double y){
     this.x     = x;
     this.y     = y;
   }

   public void dot(Vector v){
     this.x += v.x;
     this.y += v.y;
   }
    
   public void scale(double coef){
    this.x *= coef;
    this.y *= coef;
   }

   public void addition(Vector deplacement){
       this.x += deplacement.x ;
       this.y += deplacement.y;
   }

}
