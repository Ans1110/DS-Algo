package Sort;

import java.util.*;

public class Sort {
    public void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int base = arr[1], j = i - 1;
            while (j >= 0 && arr[j] > base) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = base;
        }
    }

    public void sellSort(int[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int base = arr[i], j = i;

                while (j >= h && arr[j - h] > base) {
                    arr[j] = arr[j - h];
                    j -= h;
                }
                arr[j] = base;
            }
            h = h / 3;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int partition(int[] arr, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        return i;
    }

    public void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = partition(arr, l, r);
        quickSort(arr, l, pivot - 1);
        quickSort(arr, pivot + 1, r);
    }

    private void merge(int[] arr, int l, int m, int r) {
        int[] tmp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        while (i <= m) {
            tmp[k++] = arr[i++];
        }

        while (j <= r) {
            tmp[k++] = arr[j++];
        }

        System.arraycopy(tmp, 0, arr, l + 0, tmp.length);
    }

    public void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private void siftDown(int[] arr, int n, int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int max = i;
            if (left < n && arr[left] > arr[max]) {
                max = left;
            }
            if (right < n && arr[right] > arr[max]) {
                max = right;
            }
            if (max == i) {
                break;
            }
            swap(arr, i, max);
            i = max;
        }
    }

    public void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            siftDown(arr, arr.length, i);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    public void bucketSort(int[] arr) {
        int k = arr.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }

        for (float num : arr) {
            int index = (int) (num / k);
            buckets.get(index).add(num);
        }

        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = (int) num;
            }
        }
    }

    public void countingSort(int[] arr) {
        int max = 0;
        for (int num : arr) {
            max = Math.max(max, num);
        }

        int[] counter = new int[max + 1];
        for (int num : arr) {
            counter[num]++;
        }

        for (int i = 0; i < max; i++) {
            counter[i + 1] += counter[i];
        }

        int n = arr.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            res[counter[num] - 1] = num;
            counter[num]--;
        }

        System.arraycopy(res, 0, arr, 0, n);
    }
    
    private int digit(int num, int exp) {
        return (num / exp) % 10;
    }
    
    private void countingSortDigit(int[] arr, int exp) {
        int[] counter = new int[10];
        int n = arr.length;
        for (int k : arr) {
            int d = digit(k, exp);
            counter[d]++;
        }

        for (int i = 0; i < 10; i++) {
            counter[i] += counter[i - 1];
        }
        
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int d = digit(arr[i], exp);
            int j = counter[d] - 1;
            res[j] = arr[i];
            counter[d]--;
        }
        System.arraycopy(res, 0, arr, 0, n);
    }

    public void radixSort(int[] arr) {
        int max = 0;
        for (int num : arr) {
            max = Math.max(max, num);
        }

        for (int exp = 1; exp <= max; exp *= 10) {
            countingSortDigit(arr, exp);
        }
    }
}
