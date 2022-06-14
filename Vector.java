public class Vector{
   public int x;
   public int y;
   public int linearSpeed;

   public Vector(int x, int y, int linearSpeed){
     this.x     = x;
     this.y     = y;
     this.linearSpeed = linearSpeed;
   }

   public Vector(int x, int y){
     this.x     = x;
     this.y     = y;
   }

   public void dot(Vector v){
     this.x += v.x;
     this.y += v.y;
   }
    
   public void scale(int coef){
    this.x *= coef;
    this.y *= coef;
   }

}
