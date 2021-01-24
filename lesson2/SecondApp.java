package lesson2;

import java.util.Arrays;

public class SecondApp {
    public static void main(String[] args) {
        System.out.println(checkBalance(new int[] { 1, 1, 1, 1, 2 }));
        shiftArray(new int[] { 1, 2, 3, 4, 5 }, 2);
    }

    public static boolean checkBalance(int[] array) {
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < array.length - 1; i++) {
            sumLeft += array[i];
            for (int j = i + 1; j < array.length; j++) {
                sumRight += array[j];
            }
            if (sumLeft == sumRight) return true;
            sumRight = 0;
        }
        return false;
    }

    public static void shiftArray(int[] array, int n) {
        int temp;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                temp = array[array.length - 1];
                for (int j = array.length - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = temp;
            }
        }
        else {
            for (int i = 0; i < -1 * n; i++) {
                temp = array[0];
                for (int j = 0; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[array.length - 1] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

}
