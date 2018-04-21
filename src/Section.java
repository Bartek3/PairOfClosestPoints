import java.awt.*;

// Obiekt opisujący odcinek, w konstruktorze wymagane jest podanie dwóch punktów
public class Section {

    private Point pointA;
    private Point pointB;

    public Section(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Point getA() {
        return pointA;
    }

    public Point getB() {
        return pointB;
    }

    public double length(){
        return Math.sqrt(Math.pow((pointA.getX() - pointB.getX()), 2) + Math.pow((pointA.getY() - pointB.getY()), 2));
    }

    public String toString(){
        return "punkty " + "(" + pointA.x + ", " + pointA.y + ") " + "oraz " + "(" + pointB.x + ", " + pointB.y + ")";
    }

}