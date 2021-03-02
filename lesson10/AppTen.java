package lesson10;

import java.util.Arrays;

public class AppTen {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        method1();
        method2();
    }

    public static void method1() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.) * Math.cos(0.2f + i / 5.) * Math.cos(0.4f + i / 2.));
        }
        System.out.println("Calculation time by method #1: " + (System.currentTimeMillis() - start));
    }

    public static void method2() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        float[] part1 = new float[HALF];
        float[] part2 = new float[HALF];
        System.arraycopy(arr, 0, part1, 0, HALF);
        System.arraycopy(arr, HALF, part2, 0, HALF);
        ArrayCalc thread1 = new ArrayCalc(part1);
        ArrayCalc thread2 = new ArrayCalc(part2);
        try {
            thread1.getThrd().join();
            thread2.getThrd().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(part1, 0, arr, 0, HALF);
        System.arraycopy(part2, 0, arr, HALF, HALF);
        System.out.println("Calculation time by method #2: " + (System.currentTimeMillis() - start));
    }
}
