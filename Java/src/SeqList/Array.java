package SeqList;

import java.util.concurrent.ThreadLocalRandom;

public class Array {
    public int random_access(int[] arr) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, arr.length);

        return arr[randomIndex];
    }

    public void insert(int[] arr, int num, int index) {
        for (int i = arr.length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = num;
    }

    public void remove(int[] arr, int index) {
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
    }

    public int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int[] extend(int[] arr, int enlarge) {
        int[] res = new int[arr.length + enlarge];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }
}
