package lesson1;

import java.util.Scanner;

public class FirstApp {
    public static void main(String[] args) {
        byte bt = 50;
        short shrt = 500;
        int intgr = 308779;
        long lng = 2345654321345L;
        float flt = 12.4f;
        double dbl = 23.999;
        char chr = 'A';
        boolean bln = true;

        int year;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter the year: ");
            year = sc.nextInt();
            if (checkYear(year)) System.out.println("The year is leap.");
            else System.out.println("The year is not leap.");
        } while(year != 0);
    }

    public static float calcFloat(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static boolean checkSum(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }

    public static boolean checkYear(long year) {
        if (year <= 0 || year >= Long.MAX_VALUE) return false;
        if (year % 400 == 0) return true;
        return (year % 4 == 0) && (year % 100 != 0);
    }
}
