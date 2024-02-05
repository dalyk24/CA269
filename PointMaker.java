interface PointMaker {
    Point makePoint(double x, double y); // returns point with given x,y
    int countPointsCreated(); // returns count of points created via factory
}
// TODO: create class PointFactory which implements the interface PointMaker