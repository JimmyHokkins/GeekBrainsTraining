package lesson10;

public class ArrayCalc implements Runnable {

    private final float[] arr;
    private final Thread thrd;
    private final int add;

    public ArrayCalc(float[] arr, int add) {
        this.arr = arr;
        this.add = add;
        thrd = new Thread(this);
        thrd.start();
    }

    public Thread getThrd() {
        return thrd;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + add) / 5.) * Math.cos(0.2f + (i + add) / 5.) * Math.cos(0.4f + (i + add) / 2.));
        }
    }


}
