import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
    
    private static ArrayList<Rectangle> rectangles;
    
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
    
    private static class Rectangle {
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
    }
    
    
    public static void main(String[] args) {
        rectangles = new ArrayList<Rectangle>();
        
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in)); 
        String[] content;
        String[] pointContent;
        float pointX;
        float pointY;
        int pointIndex = 1;

        try {
            while (true) {
                content = buffReader.readLine().split(" ");
    
                if (content[0].equalsIgnoreCase("*")) {
                    break;
                } else {
                    Point leftUpperPoint = new Point(Float.valueOf(content[1]), Float.valueOf(content[2]));
                    Point rightBottomPoint = new Point(Float.valueOf(content[3]), Float.valueOf(content[4]));
                    Rectangle rectangle = new Rectangle(leftUpperPoint, rightBottomPoint);
                    rectangles.add(rectangle);
                }
            }
        
            //System.out.println("Rectangles Size:  " + rectangles.size());
            
            while(true) {
                pointContent = buffReader.readLine().split(" ");
                pointX = Float.valueOf(pointContent[0]);
                pointY = Float.valueOf(pointContent[1]);
                if (pointX >= 9999 && pointY >= 9999) {
                   break; 
                } else {
                    checkBelongsRectangle(pointIndex, pointX, pointY);
                    pointIndex++;
                }        
                
                
            }
        } catch (java.io.IOException exception) {
            exception.printStackTrace();
        }
    }
    
    private static void checkBelongsRectangle(int pointIndex, float pointX, float pointY) {
        int count = 0;
        int length = rectangles.size();
        for (int i = 0 ; i < length ; i++) {
            Rectangle rectangle = (Rectangle) rectangles.get(i);
            Point leftUpperPoint = rectangle.getUpperPoint();
            Point rightBottomPoint = rectangle.getBottomPoint();
            if (pointX > leftUpperPoint.getX() && pointX < rightBottomPoint.getX()
                && pointY > rightBottomPoint.getY() && pointY < leftUpperPoint.getY()) {
                    System.out.println("Point " + pointIndex + " is contained in figure " + (i+1));
                    count++;
            } else {
                continue;
            }
        }
        
        if (count == 0) {
            System.out.println("Point " + pointIndex + " is not contained in any figure");
        }
        
    }
    
}