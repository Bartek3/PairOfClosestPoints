import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class MainTest {

    @Test
    public void smallTest1() throws Exception {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(3, 3));
        points.add(new Point(5 ,5));

        ArrayList<Point> xSorted = new ArrayList<>(points);
        xSorted.sort(Comparator.comparingDouble(Point::getX));

        ArrayList<Point> ySorted = new ArrayList<>(points);
        ySorted.sort(Comparator.comparingDouble(Point::getY));

        String expected = new Section (new Point(0, 0), new Point(1, 0)).toString();
        String result = Main.shortestSection(xSorted, ySorted).toString();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void smallTest2() throws Exception {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(-1, 8));
        points.add(new Point(0, 7));
        points.add(new Point(2, 0));
        points.add(new Point(3,3));
        points.add(new Point(5, 4));
        points.add(new Point(6,1));
        points.add(new Point(8, 0));
        points.add(new Point(9,-1));
        points.add(new Point(9, -3));
        points.add(new Point(7, -4));
        points.add(new Point(5, -8));
        points.add(new Point(0,-5));
        points.add(new Point(-4, -7));
        points.add(new Point(-3,-4));
        points.add(new Point(-2, -3));
        points.add(new Point(-7,0));
        points.add(new Point(-6,3));
        points.add(new Point(-4, 3));
        points.add(new Point(-2,7));

        ArrayList<Point> xSorted = new ArrayList<>(points);
        xSorted.sort(Comparator.comparingDouble(Point::getX));

        ArrayList<Point> ySorted = new ArrayList<>(points);
        ySorted.sort(Comparator.comparingDouble(Point::getY));

        String expected = new Section (new Point(-2, 7), new Point(-1, 8)).toString();
        String result = Main.shortestSection(xSorted, ySorted).toString();

        Assertions.assertEquals(expected, result);
    }

}