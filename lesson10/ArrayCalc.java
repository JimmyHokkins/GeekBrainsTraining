package lesson10;

public class ArrayCalc implements Runnable {

    private final float[] arr;
    private final Thread thrd;

    public ArrayCalc(float[] arr) {
        this.arr = arr;
        thrd = new Thread(this);
        thrd.start();
    }

    public Thread getThrd() {
        return thrd;
    }

    public float[] getArr() {
        return arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.) * Math.cos(0.2f + i / 5.) * Math.cos(0.4f + i / 2.));
        }
    }


}
