package lesson1;

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
    }

    public static float calcFloat(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static boolean checkSum(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }
}
