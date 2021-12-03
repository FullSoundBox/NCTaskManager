package mx.edu.j2se.camarillo.evaluation;

public class Evaluation1 {
    public static void main(String[] args) {
        try{
            Circle circulo1 = new Circle(0);
        }
        catch (IllegalArgumentException i1){
            System.out.println("Hey, that's not a valid radius!");
        }

        Circle[] circles = {new Circle(9), new Circle(1), new Circle(6)};

        System.out.println('\n' + "The radius of the largest circle is: " + circles[biggestCircle(circles)].getRadius());
    }

    /**
     * Looks for the largest circle inside a Circle array
     * @param circles a Circle class array
     * @return int index of the largest radius
     */
    public static int biggestCircle (Circle[] circles){
        int largest = 0;
        int index = 0;
        for (int i=0;i<circles.length;i++) {
            if(circles[i].getRadius()>largest) {
                largest = circles[i].getRadius();
                index = i;
            }
        }
        return index;
    }
}
