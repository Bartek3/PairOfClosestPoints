import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

// Metody pomocnicze
abstract class Calc {

    // Metoda pobierająca od użytkownika zbiór punktów
    static void readPoints(Scanner in, ArrayList<Point> points) {
        int howManyPoints = in.nextInt();
        for (int i = 1; i <= howManyPoints; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points.add(new Point(x, y));
            in.nextLine();
        }
    }

    // Metoda wyznaczająca prostą, z pomocą której zbiór punktów dzielony jest na dwie części
    static double findStraight(ArrayList<Point> points){
        double median;

        if (points.size() % 2 == 0)
            median = (points.get(points.size()/2).getX() + points.get(points.size()/2 - 1).getX())/2;
        else
            median = points.get(points.size()/2).getX();

        return median;
    }

    // Metoda zwracająca najbliższą parę punktów z trzypunktowego zbioru
    static Section naiveFor3(Point A, Point B, Point C){
        double AB = new Section(A, B).length();
        double AC = new Section(A, C).length();
        double BC = new Section(B, C).length();
        if (AB < AC && AB < BC) return new Section(A, B);
        if (AC < AB && AC < BC) return new Section (A, C);
        else return new Section(B, C);
    }

    //Metoda zwracająca najbliższą parę punktów z danego zbioru punktów, w całym programie używana dla nie więcej niż 7-punktowych zbiorów
    static Section naiveForMore(ArrayList<Point> points) {
        Section actualMinimumSection = null;
        double actualMinimumLength = Double.POSITIVE_INFINITY;

        for (Point pointA : points) {
            for (Point pointB : points) {
                if (!pointA.equals(pointB)) {
                    Section AB = new Section(pointA, pointB);
                    double length = AB.length();
                    if (length < actualMinimumLength) {
                        actualMinimumSection = AB;
                        actualMinimumLength = length;
                    }
                }
            }
        }
        return actualMinimumSection;
    }

    // Metoda zwracająca pierwszy indeks punktu w posortowanym według X zbiorze punktów, który spełnia określony warunek
    static int getMinIndex(ArrayList<Point> points, double straight, double minLength){
        for (int i = 0; i <= points.size() - 1; i++) {
            if (points.get(i).getX() >= straight - minLength) return i;
        }
        return 0;
    }

    // Metoda zwracająca ostatni indeks punktu w posortowanym według X zbiorze punktów, który spełnia określony warunek
    static int getMaxIndex(ArrayList<Point> points, double straight, double minLength){
        for (int i = (points.size() - 1); i >= 0; i--) {
            if (points.get(i).getX() <= straight + minLength) return i;
        }
        return 0;
    }

}

