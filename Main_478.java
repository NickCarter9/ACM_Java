import java.lang.Math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


class Main {
    
    private static ArrayList<Rectangle> rectangles;
    private static ArrayList<Circle> circles;
    private static ArrayList<Triangle> triangles;
    
    private static class Figure {
        protected int index;
        
        
        public void setIndex(int index) {
            this.index = index;
        }
        
        public int getIndex() {
            return index;
        }
        
        protected boolean inFigure(float pointX, float pointY) {
            return true;
        }
    }
    
    private static class Point {
        private float x;
        private float y;
        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
        
        public float getX() {
            return x;
        }
        
        public float getY() {
            return y;
        }
    }
    
    private static class Rectangle extends Figure {
        private Point leftUpperPoint;
        private Point rightBottomPoint;
        
        public Rectangle (Point leftUpper, Point rightBottom) {
            this.leftUpperPoint = leftUpper;
            this.rightBottomPoint = rightBottom;
        }
        
        public Point getUpperPoint() {
            return leftUpperPoint;
        }
        
        public Point getBottomPoint() {
            return rightBottomPoint;
        }
        
        @Override
        protected boolean inFigure(float pointX, float pointY) {
            if (pointX > leftUpperPoint.getX() && pointX < rightBottomPoint.getX()
                && pointY > rightBottomPoint.getY() && pointY < leftUpperPoint.getY()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    private static class Circle  extends Figure {
        private Point centerPoint;
        private float radius;
        
        public Circle (Point centerPoint, float radius) {
            this.centerPoint = centerPoint;
            this.radius = radius;
        }
        
        public Point getCenterPoint() {
            return centerPoint;
        }
        
        public float getRadius() {
            return radius;
        }
        
        @Override
        protected boolean inFigure(float pointX, float pointY) {
            double powX = Math.pow(pointX - centerPoint.getX(), 2.0);
            double powY = Math.pow(pointY - centerPoint.getY(), 2.0);
            double distance = Math.sqrt(powX + powY);
            if (distance < radius) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    private static class Triangle extends Figure {
        private Point PointA;
        private Point PointB;
        private Point PointC;
        
        public Triangle(Point pointA, Point pointB, Point pointC) {
            this.PointA = pointA;
            this.PointB = pointB;
            this.PointC = pointC;
        }
        
        public Point getPointA() {
            return PointA;
        }
        
        public Point getPointB() {
            return PointB;
        }
        
        public Point getPointC() {
            return PointC;
        }
        
        @Override
        protected boolean inFigure(float pointX, float pointY) {
            //XA
            float CrossXA = pointX - PointA.getX();
            float CrossYA = pointY - PointA.getY();
    
            //XB
            float CrossXB = pointX - PointB.getX();
            float CrossYB = pointY - PointB.getY();
    
            //XC
            float CrossXC = pointX - PointC.getX();
            float CrossYC = pointY - PointC.getY();
    
            float CrossXAXB = CrossXA * CrossYB - CrossYA * CrossXB;
            float CrossXBXC = CrossXB * CrossYC - CrossYB * CrossXC;
            float CrossXCXA = CrossXC * CrossYA - CrossYC * CrossXA;
    
            if ((CrossXAXB > 0 && CrossXBXC > 0 && CrossXCXA > 0)
                || (CrossXAXB < 0 && CrossXBXC < 0 && CrossXCXA < 0)) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        BufferedReader buffReader 
            = new BufferedReader(new InputStreamReader(System.in));

        rectangles = new ArrayList<Rectangle>();
        circles = new ArrayList<Circle>();
        triangles = new ArrayList<Triangle>();
        
        String[] content;
        int countFigures = 0;
        int countPoint = 1;
        
        try{
            while(true) {
                content = buffReader.readLine().split(" ");
                if (content[0].equalsIgnoreCase("*")) {
                    break;
                } else if (content[0].equalsIgnoreCase("r")) {
                    Point leftUpperPoint = new Point(Float.valueOf(content[1]),
                                                        Float.valueOf(content[2]));
                    Point rightBottomPoint = new Point(Float.valueOf(content[3]),
                                                        Float.valueOf(content[4]));
                    Rectangle rectangle = new Rectangle(leftUpperPoint, rightBottomPoint);
                    rectangle.setIndex(countFigures);
                    rectangles.add(rectangle);
                    countFigures++;
                } else if (content[0].equalsIgnoreCase("c")) {
                    Point centerPoint = new Point(Float.valueOf(content[1]),
                                                    Float.valueOf(content[2]));
                    Circle circle = new Circle(centerPoint, Float.valueOf(content[3]));
                    circle.setIndex(countFigures);
                    circles.add(circle);
                    countFigures++;
                } else if (content[0].equalsIgnoreCase("t")) {
                    Point pointA = new Point(Float.valueOf(content[1]),
                                    Float.valueOf(content[2]));
                    Point pointB = new Point(Float.valueOf(content[3]),
                                    Float.valueOf(content[4]));
                    Point pointC = new Point(Float.valueOf(content[5]),
                                    Float.valueOf(content[6]));
                    Triangle triangle = new Triangle(pointA, pointB, pointC);
                    triangle.setIndex(countFigures);
                    triangles.add(triangle);
                    countFigures++;
                } else {
                    continue;
                }
            
            }
            
             while(true) {
                    content = buffReader.readLine().split(" ");
                    float pointX = Float.valueOf(content[0]);
                    float pointY = Float.valueOf(content[1]);
                    if (pointX >= 9999 && pointY >= 9999) {
                        break;
                    } else {
                        checkInFigure(countPoint, pointX, pointY);
                        countPoint++;
                    }
                }
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
        }
    }
    
    private static void checkInFigure(int countPoint, float pointX, float pointY) {
        int belongCount = 0;
        for (Rectangle rectangle: rectangles) {
            if (rectangle.inFigure(pointX, pointY)) {
                System.out.println("Point " + countPoint 
                            + " is contained in figure " + (rectangle.getIndex() + 1));
                belongCount++;
            } else {
                continue;
            }
        }
        
        for (Circle circel: circles) {
            if (circel.inFigure(pointX, pointY)) {
                System.out.println("Point " + countPoint 
                            + " is contained in figure " + (circel.getIndex() + 1));
                belongCount++;
            } else {
                continue;
            }
        }
        
        for (Triangle triangle: triangles) {
            if (triangle.inFigure(pointX, pointY)) {
                System.out.println("Point " + countPoint 
                            + " is contained in figure " + (triangle.getIndex() + 1));
                belongCount++;
            } else {
                continue;
            }
        }
        
        if (belongCount == 0) {
            System.out.println("Point " + countPoint + " is not contained in any figure");
        }
    
    }
    
    
    
}