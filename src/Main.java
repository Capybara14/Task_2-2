import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Point p1 = readPoint(scanner, 1);
        Point p2 = readPoint(scanner, 2);
        Point p3 = readPoint(scanner, 3);

        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p3, p1);

        if (isTriangleExists(a, b, c)) {
            String side = getSideType(a, b, c);
            String angle = getAngleType(a, b, c);
            printResult(side, angle);
        }
    }

    static Point readPoint(Scanner scanner, int number) {
        System.out.printf("Введите координаты точки №%d (x y): ", number);
        Point p = new Point();
        p.x = scanner.nextDouble();
        p.y = scanner.nextDouble();
        return p;
    }

    static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static boolean isTriangleExists(double a, double b, double c) {
        final double EPS = 1e-9;

        if (a < EPS || b < EPS || c < EPS) {
            System.out.println("Треугольник не существует! Точки совпадают.");
            return false;
        }

        if (Math.abs((a + b) - c) < EPS || Math.abs((a + c) - b) < EPS || Math.abs((b + c) - a) < EPS) {
            System.out.println("Треугольник не существует! Точки лежат на одной прямой.");
            return false;
        }
        return true;
    }

    static String getSideType(double a, double b, double c) {
        final double EPS = 1e-9;
        boolean ab = Math.abs(a - b) < EPS;
        boolean bc = Math.abs(b - c) < EPS;
        boolean ca = Math.abs(c - a) < EPS;

        if (ab && bc) {
            return "равносторонний";
        } else if (ab || bc || ca) {
            return "равнобедренный";
        } else {
            return "разносторонний";
        }
    }

    static String getAngleType(double a, double b, double c) {
        final double EPS = 1e-9;
        double[] sides = {a, b, c};
        Arrays.sort(sides);

        double diff = (sides[2] * sides[2]) - (sides[0] * sides[0] + sides[1] * sides[1]);

        if (Math.abs(diff) < EPS) {
            return "прямоугольный";
        } else if (diff > EPS) {
            return "тупоугольный";
        } else {
            return "остроугольный";
        }
    }

    static void printResult(String side, String angle) {
        System.out.printf("Тип треугольника построенного по введенным координатам: %s и %s.%n", side, angle);
    }

    static class Point {
        double x;
        double y;
    }
}