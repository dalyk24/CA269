/**
  * Task.java 
  * @author Killian Daly
*/

// Define a class named Point that implements the Comparable interface
public class Point implements Comparable {
    // Private instance variables to store the coordinates of the point
    private double x, y;

    // Constructor to initialize the Point with given x and y coordinates
    public Point(double newX, double newY) {
        // Call the setX and setY methods to ensure valid initialization
        setX(newX);
        setY(newY);
    }

    // Getter method to retrieve the x coordinate
    public double getX() {
        return x;
    }

    // Getter method to retrieve the y coordinate
    public double getY() {
        return y;
    }

    // Setter method to update the x coordinate
    public void setX(double x) {
        this.x = x;
    }

    // Setter method to update the y coordinate
    public void setY(double y) {
        this.y = y;
    }

    // Override the toString method to provide a string representation of the point
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

    // Check if this Point is equal to another Point
    public boolean equals(Point other) {
        // Compare x and y coordinates for equality
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        } else {
            return false;
        }
    }

    // Check if two double values are equal
    public boolean equals(double x, double y) {
        // Compare two double values for equality
        if (x == y) {
            return true;
        } else {
            return false;
        }
    }

    // Compare this Point with another Order object to check if it's less than
    public boolean lessThan(Order other) {
        // Cast the other Order object to Point
        Point otherPoint = (Point) other;

        // Compare x coordinates first, then y coordinates if x is equal
        if (equals(this.getX(), otherPoint.getX())) {
            if (this.getY() < otherPoint.getY()) {
                return true;
            } else {
                return false;
            }
        } else if (this.getX() < otherPoint.getX()) {
            return true;
        } else {
            return false;
        }
    }

    // Implement the compareTo method required by the Comparable interface
    public int compareTo(Object other) {
        // Cast the other object to Point
        Point otherPoint = (Point) other;

        // Compare this Point with the other Point
        if (this.equals(otherPoint)) {
            return 0; // Points are equal
        } else if (this.lessThan(otherPoint)) {
            return -1; // This Point is less than the other Point
        } else {
            return 1; // This Point is greater than the other Point
        }
    }

    public static void main(String[] args) {
        Order O1 = new Point(0, 0);
        Order O2 = new Point(1, 1);
        Order O3 = new Point(0, 1);

        System.out.println("O1 less than O2: " + O1.lessThan(O2)); // true
        System.out.println("O1 less than O3: " + O1.lessThan(O3)); // true
        System.out.println("O2 less than O3: " + O2.lessThan(O3)); // false
        System.out.println("O3 less than O3: " + O3.lessThan(O3)); // false

        Comparable P1 = new Point(0, 0);
        Comparable P2 = new Point(1, 1);
        Comparable P3 = new Point(0, 1);

        System.out.println("P1 less than P2: " + P1.compareTo(P2)); // -1
        System.out.println("P1 less than P3: " + P1.compareTo(P3)); // -1
        System.out.println("P2 less than P3: " + P2.compareTo(P3)); // 1
        System.out.println("P3 less than P3: " + P3.compareTo(P3)); // 0
    }
}