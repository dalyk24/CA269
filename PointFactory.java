/**
    * Task.java 
    * @author Killian Daly
*/

enum Quadrant {
    Q1, // x +ve, y +ve
    Q2, // x -ve, y +ve
    Q3, // x -ve, y -ve
    Q4; // x +ve, y -ve
}

interface GridQuadrant {
    Quadrant getQuadrant(); // return which quadrant the point is in
        // if point is at (0,0) return null
}

interface CompareQuadrant {
    boolean isInSameQuadrant(Point point); // true if given point is in the same quadrant as this point
}

class Point implements GridQuadrant, CompareQuadrant {
    private double x, y;

    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    // getters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    // setters
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public Quadrant getQuadrant() {
        if(this.x > 0 && this.y > 0) {
            return Quadrant.Q1;
        }
        else if(this.x < 0 && this.y > 0) {
            return Quadrant.Q2;
        }
        else if(this.x < 0 && this.y < 0) {
            return Quadrant.Q3;
        }
        else if(this.x > 0 && this.y < 0) {
            return Quadrant.Q4;
        }
        else {
            return null;
        }
    }

    public boolean isInSameQuadrant(Point point) {
        if(this.getQuadrant() == point.getQuadrant()) {
            return true;
        }
        else {
            return false;
        }
    }
}

interface PointMaker {
    Point makePoint(double x, double y); // returns point with given x,y
    int countPointsCreated(); // returns count of points created via factory
}

class PointFactory implements PointMaker {
    private int pointsCreated = 0;

    public Point makePoint(double x, double y) {
        pointsCreated++;
        return new Point(x, y);
    }

    public int countPointsCreated() {
        return pointsCreated;
    }

    public static void main(String args[]) {
        PointFactory nerv = new PointFactory();

        Point rei = nerv.makePoint(0, 0);
        Point shinji = nerv.makePoint(1, 1);
        Point asuka = nerv.makePoint(-2, 2);
        Point toji = nerv.makePoint(-3, -3);
        Point npc = nerv.makePoint(4, -4);
        Point kaworu = nerv.makePoint(1, 1);

        System.out.println(rei.getQuadrant());
        System.out.println(asuka.getQuadrant());
        System.out.println(shinji.isInSameQuadrant(toji));
        System.out.println(shinji.isInSameQuadrant(kaworu));
        System.out.println(nerv.countPointsCreated());
    }
}
