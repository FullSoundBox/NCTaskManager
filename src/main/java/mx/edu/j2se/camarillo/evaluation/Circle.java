package mx.edu.j2se.camarillo.evaluation;

import static java.lang.Math.PI;

public class Circle {
    private int radius;

    public Circle(){
        radius = 1;
    }

    public Circle(int radius) throws IllegalArgumentException{
        if (radius<=0){throw new IllegalArgumentException("Radius should be positive");}
        this.radius = radius;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }
    public int getRadius(){
        return this.radius;
    }

    public double getArea(){
        return PI*this.radius*this.radius;
    }

}
