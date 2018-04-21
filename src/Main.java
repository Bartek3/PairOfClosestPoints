// Program znajduje parę najbliższych punktów w podanym zbiorze.
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        UI.startMessage();
        UI.writeRequirements();
        Scanner in = new Scanner(System.in);
        Calc.readPoints(in, points);

        ArrayList<Point> pointsSortedByX = new ArrayList<>(points);
        pointsSortedByX.sort(Comparator.comparingDouble(Point::getX));

        ArrayList<Point> pointsSortedByY = new ArrayList<>(points);
        pointsSortedByY.sort(Comparator.comparingDouble(Point::getY));

        UI.writeSolution(shortestSection(pointsSortedByX, pointsSortedByY));
    }

    private static Section shortestSection(ArrayList<Point> xSorted, ArrayList<Point> ySorted) {
        if (xSorted.size() == 1) return null;
        if (xSorted.size() == 2) return new Section(xSorted.get(0), xSorted.get(1));
        if (xSorted.size() == 3) return Calc.naiveFor3(xSorted.get(0), xSorted.get(1), xSorted.get(2));
        else {
            Section min;

            ArrayList<Point> xSorted1 = new ArrayList<>();
            ArrayList<Point> xSorted2 = new ArrayList<>();
            ArrayList<Point> ySorted1 = new ArrayList<>();
            ArrayList<Point> ySorted2 = new ArrayList<>();

            double straight = Calc.findStraight(xSorted);

            for (Point point : xSorted) {
                if (point.getX() <= straight) xSorted1.add(point);
                else xSorted2.add(point);
            }

            for (Point point : ySorted) {
                if (point.getX() <= straight) ySorted1.add(point);
                else ySorted2.add(point);
            }

            Section min1 = shortestSection(xSorted1, ySorted1);
            Section min2 = shortestSection(xSorted2, ySorted2);

            if (min1 != null && min2 != null) {
                if (min1.length() <= min2.length()) min = min1;
                else min = min2;
            } else if (min1 == null) min = min2;
            else min = min1;

            double actualMinLength = min.length();

            double ySortedfirstY = ySorted.get(0).getY();
            double ySortedlastY = ySorted.get(ySorted.size() - 1).getY();

            int xSortedMinIndex = Calc.getMinIndex(xSorted, straight, actualMinLength);
            int xSortedMaxIndex = Calc.getMaxIndex(xSorted, straight, actualMinLength);

            double howManyYDeltaSquares = ((ySortedlastY - ySortedfirstY) / actualMinLength);

            for (int i = 0; i <= (int) howManyYDeltaSquares; i++) {
                ArrayList<Point> potentialShorterPoints = new ArrayList<>();
                double actualYMax = ySortedlastY - (actualMinLength * i);
                double actualYMin = (actualYMax - actualMinLength * 2);
                for (int j = xSortedMinIndex; j <= xSortedMaxIndex; j++) {
                    if (xSorted.get(j).getY() <= actualYMax && xSorted.get(j).getY() >= actualYMin) {
                        potentialShorterPoints.add(xSorted.get(j));
                    }
                }

                Section potentialMin = Calc.naiveForMore(potentialShorterPoints);

                if (potentialMin == null) continue;

                if (potentialMin.length() < actualMinLength) {
                    min = potentialMin;
                    actualMinLength = potentialMin.length();
                }
            }
            return min;
        }
    }

}
